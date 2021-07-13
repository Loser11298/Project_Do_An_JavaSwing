/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.view;

import com.quan.datn.model.BenhNhan;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.response.BenhNhanResponse;
import com.quan.datn.network.APIManager;
import com.quan.datn.network.BenhNhanRepository;
import com.quan.datn.utils.SmardCardManager;
import static com.quan.datn.utils.SmardCardManager.verifyFalse;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class XuatVien extends javax.swing.JFrame {

    /**
     * Creates new form XuatVien
     */
    
    public static XuatVien shareInstance = new XuatVien();
    private final SmardCardManager cardManager;
    private boolean isConnect = false;
    private final BenhNhanRepository benhNhanRepository;
    private BenhNhanResponse benhNhan;
    private int amount = 0;
    
    
    public XuatVien() {
        initComponents();
        cardManager = new SmardCardManager();
        benhNhanRepository = new BenhNhanRepository();
    }

     public void getInfoBenhNhan(String maBn, boolean very) {
        if (!maBn.equals("")) {
            benhNhan = benhNhanRepository.getBenhNhanInfo(maBn);
            if (benhNhan != null) {
                txtMaBN.setText(benhNhan.getMaBN());
                if (benhNhan.getBenhAnResponse() != null) {
                    txtMaBA.setText(benhNhan.getBenhAnResponse().getMaBA());
                } else {
                    txtMaBA.setText("");
                }
                txtDiaChi.setText(benhNhan.getDiaChi());
                txtHo.setText(benhNhan.getHoBn());
                txtTen.setText(benhNhan.getTenBn());
                txtNgaySinh.setText(benhNhan.getNgaySinh());
                txtGioiTinh.setText(benhNhan.getGioiTinh());
                txtSDT.setText(benhNhan.getSdt());
                txtAmount.setText(benhNhan.getAmount() + "");
                txtVienPhi.setText(benhNhan.getVienPhi() + "");
                if(very)
                    verifyCard(benhNhan.getPublicKey());
                new Thread(() -> {
                    lbImage.setIcon(APIManager.loadImageIcon(benhNhan.getAvatar(), lbImage.getWidth(), lbImage.getHeight()));
                }).start();
            } else {
                resetData(true);
            }
        } else {
            resetData(true);
        }
    }

    private void verifyCard(String keyString) {
        if (keyString != null && !keyString.equals("")) {
            if (checkVerifyCard(keyString)) {
                JOptionPane.showMessageDialog(this, "Thẻ đã được xác minh thành công");
                amount = cardManager.getBalance();
                txtAmount.setText(amount + "");
            } else {
                resetData(true);
            }
        } else {
            resetData(true);
        }
    }

    public void resetData(boolean disconnect) {
        txtMaBN.setText("");
        txtMaBA.setText("");
        txtDiaChi.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtGioiTinh.setText("");
        txtSDT.setText("");
        txtAmount.setText("0");
        txtVienPhi.setText("");
        btnNaptien.setEnabled(false);
        if (cardManager.isConnect && disconnect) {
            cardManager.disconnect();
            isConnect = false;
            btnConnect.setText("Connect");
            btnConnect.setForeground(new Color(18, 110, 140));
        }
        new Thread(() -> {
            int index = 0;
            while (index < 5) {
                try {
                    if (lbImage.getIcon() != null || index >= 5) {
                        lbImage.setIcon(null);
                        break;
                    }
                    index++;
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }).start();
    }
    
     private boolean checkVerifyCard(String publicKey) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("<html><p style=\"color:red\">Thẻ sẽ bị khóa sau <b>" + (3 - verifyFalse)
                + "</b> lần nhập </p>\n Nhập mã pin");
        JPasswordField pass = new JPasswordField(4);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Nhập mật khẩu để xác thực thẻ",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if (option == 0) {
            String password = new String(pass.getPassword());
            if (cardManager.verifyCard(password)) {
                if (cardManager.verifySignatureCard(password, publicKey)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Không nhận dạng được thẻ...");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mã pin sai");
                if (SmardCardManager.verifyFalse < 3) {
                    checkVerifyCard(publicKey);
                } else {
                    JOptionPane.showMessageDialog(null, "Thẻ đã bị khóa do nhập sai mã pin quá nhiều lần");
                }
            }
        }
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaBN = new javax.swing.JTextField();
        txtMaBA = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnNaptien = new javax.swing.JButton();
        btrnClose = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        txtVienPhi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thanh Toán Viện Phí");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(214, 240, 255));

        jPanel5.setBackground(new java.awt.Color(214, 240, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin bệnh nhân", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã BN : ");

        txtMaBN.setEditable(false);
        txtMaBN.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        txtMaBA.setEditable(false);
        txtMaBA.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mã bệnh án : ");

        txtHo.setEditable(false);
        txtHo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Họ : ");

        txtTen.setEditable(false);
        txtTen.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tên : ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính : ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ngày Sinh : ");

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Địa Chỉ : ");

        txtDiaChi.setEditable(false);
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("SĐT : ");

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        txtGioiTinh.setEditable(false);
        txtGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Số dư: ");

        txtAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAmount.setForeground(new java.awt.Color(255, 51, 51));
        txtAmount.setText("1000000000");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("vnđ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tiền viện phí: ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("vnđ");

        btnNaptien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNaptien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_money_25x25.png"))); // NOI18N
        btnNaptien.setText("Xuất Viện");
        btnNaptien.setEnabled(false);
        btnNaptien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNaptien.setPreferredSize(new java.awt.Dimension(128, 39));
        btnNaptien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNaptienActionPerformed(evt);
            }
        });

        btrnClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btrnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_X_25x25.png"))); // NOI18N
        btrnClose.setText("Đóng");
        btrnClose.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btrnClose.setPreferredSize(new java.awt.Dimension(128, 39));
        btrnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrnCloseActionPerformed(evt);
            }
        });

        btnConnect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_Card_25x25.png"))); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnConnect.setPreferredSize(new java.awt.Dimension(128, 39));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        txtVienPhi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVienPhi.setForeground(new java.awt.Color(255, 51, 51));
        txtVienPhi.setText("1000000000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtHo, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                            .addComponent(txtMaBN))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSDT))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaBA, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtTen)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNaptien, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btrnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAmount)
                            .addComponent(txtVienPhi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(185, 185, 185)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaBA, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVienPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNaptien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btrnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNaptienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaptienActionPerformed
        if (isConnect) {
            int amountInput = Integer.parseInt(txtVienPhi.getText());
            if (amountInput  > 0) {
                if (cardManager.debit(amountInput)) {
                    amount = cardManager.getBalance();
                    txtAmount.setText(amount + "");
                    UpdateBenhNhanRequest request = benhNhan.getUpdateRequest();
                    request.setAmount(amount);
                    request.setTrangThai("Đã xuất viện");
                    BenhNhan bn = benhNhanRepository.updateBenhNhan(request, null);
                    if (bn != null) {
                        JOptionPane.showMessageDialog(this, "Bệnh nhân đã được xuất viện");
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra khi cập nhật thông tin");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra trong quá trình thanh toán, vui lòng thử lại sau...");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Thẻ chưa được connect...");
        }
    }//GEN-LAST:event_btnNaptienActionPerformed

    private void btrnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrnCloseActionPerformed
        if (cardManager.isConnect) {
            cardManager.disconnect();
            isConnect = false;
            btnConnect.setText("Connect");
            btnConnect.setForeground(new Color(18, 110, 140));
        }
        this.dispose();
    }//GEN-LAST:event_btrnCloseActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        if (!isConnect) {
            if (cardManager.connectCard()) {
                isConnect = true;
                btnConnect.setText("Disconnect");
                btnConnect.setForeground(Color.RED);
                BenhNhan bn = cardManager.getInfo();
                if (bn != null) {
                    txtMaBN.setText(bn.getMaBN());
                    txtDiaChi.setText(bn.getDiaChi());
                    txtHo.setText(bn.getHoBn());
                    txtTen.setText(bn.getTenBn());
                    txtNgaySinh.setText(bn.getNgaySinh());
                    txtGioiTinh.setText(bn.getGioiTinh());
                    txtSDT.setText(bn.getSdt());
                    txtAmount.setText("0");
                    getInfoBenhNhan(bn.getMaBN(), true);
                } else {
                    JOptionPane.showMessageDialog(this, "Thẻ không có dữ liệu");
                    cardManager.disconnect();
                    isConnect = false;
                    btnConnect.setText("Connect");
                    btnConnect.setForeground(new Color(18, 110, 140));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa connect được đến card");
                isConnect = false;
                btnConnect.setText("Connect");
                btnConnect.setForeground(new Color(18, 110, 140));
            }
        } else {
            if (cardManager.disconnect()) {
                isConnect = false;
                btnConnect.setText("Connect");
                btnConnect.setForeground(new Color(18, 110, 140));
                resetData(false);
            }
        }
        btnNaptien.setEnabled(isConnect);
    }//GEN-LAST:event_btnConnectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XuatVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XuatVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnNaptien;
    private javax.swing.JButton btrnClose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel txtAmount;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMaBA;
    private javax.swing.JTextField txtMaBN;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JLabel txtVienPhi;
    // End of variables declaration//GEN-END:variables
}
