/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.utils;

import com.google.gson.Gson;
import com.quan.datn.model.BenhNhan;
import com.quan.datn.model.request.AddBenhNhanRequest;
import java.awt.HeadlessException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class SmardCardManager {

    public static final byte[] AID_APPLET = {0x11, 0x22, 0x33, 0x44, 0x55, 0x01, 0x01};
    private Card card;
    private TerminalFactory factory;
    private CardChannel channel;
    private CardTerminal terminal;
    private List<CardTerminal> terminals;
    private ResponseAPDU response;
    public static int verifyFalse = 0;
    public boolean isConnect = false;
    private Gson gson;

    public SmardCardManager() {
        gson = new Gson();
    }

    public boolean connectCard() {
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();
            terminal = terminals.get(0);
            card = terminal.connect("T=0");
            channel = card.getBasicChannel();
            if (channel == null) {
                return false;
            }
            response = channel.transmit(new CommandAPDU(0x00, (byte) 0xA4, 0x04, 0x00, AID_APPLET));
            String check = Integer.toHexString(response.getSW());
            switch (check) {
                case "9000":
                    if (verifyFalse < 3) {
                        verifyFalse = 0;
                        isConnect = true;
                        return true;
                    } else {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Thẻ này đã bị khóa. Bạn có muốn mở khóa thẻ không?", "Warning", JOptionPane.OK_CANCEL_OPTION);
                        if (dialogResult == JOptionPane.OK_OPTION) {
                            if (unblockCard()) {
                                verifyFalse = 0;
                                isConnect = true;
                                return true;
                            }else{
                                disconnect();
                                isConnect = false;
                                return false;
                            }
                        }else{
                            disconnect();
                            isConnect = false;
                            return false;
                        }
                    }
                case "6999":
                    JOptionPane.showMessageDialog(null, "Thẻ đã bị vô hiệu hóa");
                    verifyFalse = 3;
                    isConnect = false;
                    return false;
                default:
                    isConnect = false;
                    return false;
            }
        } catch (HeadlessException | CardException ex) {
            ex.printStackTrace();
        }
        isConnect = false;
        return false;
    }

    public boolean disconnect() {
        try {
            card.disconnect(false);
            return true;
        } catch (CardException e) {
            System.out.println("Lỗi :" + e);
        }
        return false;
    }

    public boolean verifyCard(String pinString) {
        try {
            byte[] pin = stringToByteArr(pinString);
            response = channel.transmit(new CommandAPDU(0x00, (byte) 0x05, 0x26, 0x04, pin));
            String check = Integer.toHexString(response.getSW());
            if (check.equals("9000")) {
                return true;
            } else {
                verifyFalse++;
                return false;
            }
        } catch (CardException e) {
        }
        return false;
    }

    public boolean saveInfoBenhNhan(AddBenhNhanRequest info) {
        try {
            String temp = SmardCardManager.stringToHex(benhNhanToString(info));
            byte[] dataBN = SmardCardManager.hexStringToByteArray(temp);
            response = channel.transmit(new CommandAPDU((byte) 0x00, (byte) 0x00, (byte) 0x12, (byte) 0x12, dataBN));
            String check = Integer.toHexString(response.getSW());
            if (checkVerify(check)) {
                if (check.equals("9000")) {
                    JOptionPane.showMessageDialog(null, "Lưu thông tin thành công ");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu thông tin không thành công");
                    return false;
                }
            }
        } catch (HeadlessException | CardException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BenhNhan getInfo() {
        BenhNhan info = new BenhNhan();
        try {
            response = channel.transmit(new CommandAPDU(00, 01, 26, 04));
            String check = Integer.toHexString(response.getSW());
            if (checkVerify(check)) {
                byte[] dataText = response.getData();
                StringBuilder infoText = new StringBuilder();
                for (int i = 0; i < dataText.length; i++) {
                    if (dataText[i] != 0) {
                        infoText.append(Character.toString((char) dataText[i]));
                    }
                }
                info = getInfoInString(infoText.toString());
                if (check.equals("9000")) {
                    return info;
                } else {
                    JOptionPane.showMessageDialog(null, "Read không thành công");
                    return null;
                }
            }
        } catch (HeadlessException | CardException ex) {
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
        }
        return info;
    }

    private BenhNhan getInfoInString(String data) {
        try {
            String dataDecrypt = AESUtils.shared.decrypt(null, data);
            BenhNhan info = new BenhNhan();
            String[] text = dataDecrypt.split("#");
            info.setMaBN(text[0]);
            info.setHoBn(text[1]);
            info.setTenBn(text[2]);
            info.setCmnd(text[3]);
            info.setNgaySinh(text[4]);
            info.setDiaChi(text[5]);
            info.setSdt(text[6]);
            info.setNgheNghiep(text[7]);
            info.setGioiTinh(text[8]);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String benhNhanToString(AddBenhNhanRequest info) {
        String dataEncrypt = info.getMaBN() + "#"
                + info.getHoBn() + "#"
                + info.getTenBn() + "#"
                + info.getCmnd() + "#"
                + info.getNgaySinh() + "#"
                + info.getDiaChi() + "#"
                + info.getSdt() + "#"
                + info.getNgheNghiep() + "#"
                + info.getGioiTinh();
        return AESUtils.shared.encrypt(null, dataEncrypt);
    }

    public int getBalance() {
        try {
            response = channel.transmit(new CommandAPDU(0x00, (byte) 0x04, 0x26, 0x04));
            String check = Integer.toHexString(response.getSW());
            if (checkVerify(check)) {
                byte[] data = response.getData();
                return byteArrayToInt(data);
            }
        } catch (HeadlessException | CardException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean credit(int vnd) {
        try {
            byte[] cre = intToByteArray(vnd);
            response = channel.transmit(new CommandAPDU((byte) 0x00, (byte) 0x02, (byte) 0x12, (byte) 0x12, cre));
            String check = Integer.toHexString(response.getSW());
            return checkVerify(check);
        } catch (HeadlessException | CardException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean debit(int vnd) {
        try {
            response = channel.transmit(new CommandAPDU((byte) 0x00, (byte) 0x03, (byte) 0x12, (byte) 0x12, intToByteArray(vnd)));
            String check = Integer.toHexString(response.getSW());
            return checkVerify(check);
        } catch (HeadlessException | CardException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unblockCard() {
        try {
            response = channel.transmit(new CommandAPDU(0x00, (byte) 0x06, 0x26, 0x04));
            String check = Integer.toHexString(response.getSW());
            if (check.equals("9000")) {
                verifyFalse = 0;
                return true;
            } else {
                return false;
            }
        } catch (CardException e) {
        }
        return false;
    }

    public boolean changePin(String newPinString) {
        try {
            byte[] newPin = stringToByteArr(newPinString);
            response = channel.transmit(new CommandAPDU(0x00, (byte) 0x07, 0x26, 0x04, newPin));
            String check = Integer.toHexString(response.getSW());
            return checkVerify(check);
        } catch (HeadlessException | CardException e) {
        }
        return false;
    }

    private boolean checkVerify(String result) {
        try {
            switch (result.trim()) {
                case "6311":
                    if (verifyFalse < 3) {
                        JPanel panel = new JPanel();
                        JLabel label = new JLabel("<html><p style=\"color:red\">Thẻ sẽ bị khóa sau <b>" + (3 - verifyFalse)
                                + "</b> lần nhập </p>\n Nhập mã pin");
                        JPasswordField pass = new JPasswordField(10);
                        panel.add(label);
                        panel.add(pass);
                        String[] options = new String[]{"OK", "Cancel"};
                        int option = JOptionPane.showOptionDialog(null, panel, "Nhập mật khẩu để xác thực thẻ",
                                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, options, options[1]);
                        if (option == 0) {
                            String password = new String(pass.getPassword());
                            if (verifyCard(password)) {
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Mã pin sai");
                                return false;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thẻ đã bị khóa do nhập mã pin sai quá số lượt");
                        return false;
                    }
                    break;
                case "6312":
                    JOptionPane.showMessageDialog(null, "Mã pin không đúng");
                    return false;
                case "6999":
                    JOptionPane.showMessageDialog(null, "Thẻ đã bị khóa do nhập mã pin sai quá số lượt");
                    verifyFalse = 3;
                    return false;
                case "9000":
                    return true;
                default:
                    return false;
            }
        } catch (HeadlessException e) {
            return false;
        }
        return false;
    }

    private RSAPublicKey getPublicKey() {
        RSAPublicKey rsaPublicKey = null;
        try {
            ResponseAPDU resp_exponent = channel.transmit(new CommandAPDU((byte) 0x00, (byte) 0x09, (byte) 0x12, (byte) 0x12));
            ResponseAPDU resp_modulus = channel.transmit(new CommandAPDU((byte) 0x00, (byte) 0x10, (byte) 0x12, (byte) 0x12));
            byte[] modulus = resp_modulus.getData();
            byte[] exponent = resp_exponent.getData();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(new BigInteger(1, modulus), new BigInteger(1, exponent));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
            System.out.println("PUBLIC KEY: " + rsaPublicKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | CardException e) {
            e.printStackTrace();
        }
        return rsaPublicKey;
    }

    //Đã bỏ mã hóa public key 
    public String getPublicKeyString() {
        RSAPublicKey rsaPublicKey = getPublicKey();
        String strKey = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
        System.out.println("PUBLIC KEY STRING: " + strKey);
        return strKey;
    }

    public RSAPublicKey decodePublicKey(String publicKey) throws NoSuchAlgorithmException {
        RSAPublicKey rsaPublicKey = null;
        try {
            System.out.println("PUBLIC KEY STRING: " + publicKey);
            byte[] bytePubkey = Base64.getDecoder().decode(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(bytePubkey));
            System.out.println("PUBLIC KEY OUTPUT: " + rsaPublicKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return rsaPublicKey;
    }

    public boolean verifySignatureCard(String pinString, String publicKey) {
        try {
            // Tạo chuỗi String để gửi xuống thẻ kí với độ dài 26 kí tự
            String signString = Utils.randomString(26);
            System.out.println("Chữ ký String: " + signString);
            byte[] signByteArr = SmardCardManager.stringToByteArr(signString);
            ResponseAPDU resp = channel.transmit(new CommandAPDU((byte) 0x00, (byte) 0x08, (byte) 0x12, (byte) 0x12, signByteArr));
            byte[] sigToVerify = resp.getData();
            Signature sig = Signature.getInstance("SHA1withRSA");
            RSAPublicKey rsaPublicKey = decodePublicKey(publicKey);
            sig.initVerify(rsaPublicKey);
            sig.update(signByteArr);
            return sig.verify(sigToVerify);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | CardException e) {
//            e.printStackTrace();
        }
        return false;
    }

    public static byte[] stringToByteArr(String text) {
        String resultHex = SmardCardManager.stringToHex(text);
        return SmardCardManager.hexStringToByteArray(resultHex);
    }

    public static String stringToHex(String str) {
        StringBuilder buf = new StringBuilder(200);
        for (char ch : str.toCharArray()) {
            buf.append(String.format("%02x", (int) ch));
        }
        return buf.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static final int byteArrayToInt(byte[] byteArr) {
        return (int) (byteArr[0] << 24
                | ((byteArr[1] << 24) >>> 8)
                | ((byteArr[2] << 24) >>> 16)
                | ((byteArr[3] << 24) >>> 24));
    }

    public static final byte[] intToByteArray(int value) {
        return new byte[]{
            (byte) (value >>> 24),
            (byte) (value >>> 16),
            (byte) (value >>> 8),
            (byte) value};
    }

}
