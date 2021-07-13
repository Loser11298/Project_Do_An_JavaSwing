/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.view.panel;

import com.quan.datn.model.BenhNhan;
import com.quan.datn.model.response.BenhNhanResponse;
import com.quan.datn.network.APIManager;
import com.quan.datn.utils.SmardCardManager;
import com.quan.datn.network.BenhNhanRepository;
import com.quan.datn.view.ChangePinView;
import com.quan.datn.view.KhamBenh;
import com.quan.datn.view.NapTienView;
import com.quan.datn.view.ThemBN;
import com.quan.datn.view.XuatVien;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class PNLeTan extends javax.swing.JPanel {

    /**
     * Creates new form LeTan
     */
    private final SmardCardManager cardManager;
    private int readCardCount = 0;
    private int seachBNCount = 0;
    private final BenhNhanRepository bnRepository;
    private List<BenhNhanResponse> dsBenhNhanCho;
    private List<BenhNhanResponse> dsBenhNhanDieuTri;

    public PNLeTan() {
        initComponents();
        cardManager = new SmardCardManager();
        bnRepository = new BenhNhanRepository();
        dsBenhNhanCho = new ArrayList<>();
        dsBenhNhanDieuTri = new ArrayList<>();
        loadData();
    }

    public void loadData() {
        new Thread(() -> {
            showListBenhNhanCho();
            showListBenhNhanDieuTri();
        }).start();
    }

    private void showListBenhNhanCho() {
        dsBenhNhanCho = bnRepository.getAllBenhNhanCho();
        DefaultTableModel mode = (DefaultTableModel) tableBNCho.getModel();
        mode.setRowCount(0);
        if (!dsBenhNhanCho.isEmpty()) {
            Object[] row = new Object[11];
            for (int i = 0; i < dsBenhNhanCho.size(); i++) {
                row[0] = i;
                row[1] = dsBenhNhanCho.get(i).getMaBN();
                row[2] = dsBenhNhanCho.get(i).getHoBn();
                row[3] = dsBenhNhanCho.get(i).getTenBn();
                row[4] = dsBenhNhanCho.get(i).getGioiTinh();
                row[5] = dsBenhNhanCho.get(i).getNgaySinh();
                row[6] = dsBenhNhanCho.get(i).getCmnd();
                row[7] = dsBenhNhanCho.get(i).getNgheNghiep();
                row[8] = dsBenhNhanCho.get(i).getDiaChi();
                row[9] = dsBenhNhanCho.get(i).getSdt();
                if (dsBenhNhanCho.get(i).getBenhAnResponse() != null) {
                    row[10] = dsBenhNhanCho.get(i).getBenhAnResponse().getLyDoKham();
                } else {
                    row[10] = "";
                }
                mode.addRow(row);
            }
        }
    }

    private void showListBenhNhanDieuTri() {
        dsBenhNhanDieuTri = bnRepository.getAllBenhNhanDieuTri();
        DefaultTableModel mode = (DefaultTableModel) tableBNDieuTri.getModel();
        mode.setRowCount(0);
        if (!dsBenhNhanDieuTri.isEmpty()) {
            
            Object[] row = new Object[12];
            for (int i = 0; i < dsBenhNhanDieuTri.size(); i++) {
                row[0] = i;
                row[1] = dsBenhNhanDieuTri.get(i).getMaBN();
                row[2] = dsBenhNhanDieuTri.get(i).getHoBn();
                row[3] = dsBenhNhanDieuTri.get(i).getTenBn();
                row[4] = dsBenhNhanDieuTri.get(i).getGioiTinh();
                row[5] = dsBenhNhanDieuTri.get(i).getNgaySinh();
                row[6] = dsBenhNhanDieuTri.get(i).getCmnd();
                row[7] = dsBenhNhanDieuTri.get(i).getNgheNghiep();
                row[8] = dsBenhNhanDieuTri.get(i).getDiaChi();
                row[9] = dsBenhNhanDieuTri.get(i).getSdt();
                if (dsBenhNhanDieuTri.get(i).getBenhAnResponse() != null) {
                    if (dsBenhNhanDieuTri.get(i).getBenhAnResponse().getPhongBenh() != null) {
                        row[10] = dsBenhNhanDieuTri.get(i).getBenhAnResponse().getPhongBenh().getTenPB();
                    } else {
                        row[10] = "";
                    }
                    row[11] = dsBenhNhanDieuTri.get(i).getBenhAnResponse().getLyDoKham();
                } else {

                    row[11] = "";
                }
                mode.addRow(row);
            }
        }
    }

    synchronized private BenhNhan getInfoBenhNhanFromCard() {
        BenhNhan benhNhan = new BenhNhan();
        if (cardManager.isConnect) {
            return cardManager.getInfo();
        } else {
            readCardCount++;
            if (cardManager.connectCard() && readCardCount < 10) {
                getInfoBenhNhanFromCard();
            } else {
                readCardCount = 0;
            }
        }
        return null;
    }

    private void selectBenhNhan(BenhNhanResponse benhNhan) {
        txtMaBN.setText(benhNhan.getMaBN());
        if (benhNhan.getBenhAnResponse() != null) {
            txtMaBA.setText(benhNhan.getBenhAnResponse().getMaBA());
            txtYeuCauKham.setText(benhNhan.getBenhAnResponse().getLyDoKham());
        } else {
            txtMaBA.setText("");
            txtYeuCauKham.setText("");
        }
        txtDiaChi.setText(benhNhan.getDiaChi());
        txtHo.setText(benhNhan.getHoBn());
        txtTen.setText(benhNhan.getTenBn());
        txtNgaySinh.setText(benhNhan.getNgaySinh());
        txtGioiTinh.setText(benhNhan.getGioiTinh());
        txtSDT.setText(benhNhan.getSdt());
        new Thread(() -> {
            lbImage.setIcon(APIManager.loadImageIcon(benhNhan.getAvatar(), lbImage.getWidth(), lbImage.getHeight()));
        }).start();
    }

    private BenhNhanResponse searchBenhNhanDieuTri(String maBN) {
        for (BenhNhanResponse benhNhanResponse : dsBenhNhanDieuTri) {
            if (benhNhanResponse.getMaBN() == null ? maBN == null : benhNhanResponse.getMaBN().equals(maBN)) {
                return benhNhanResponse;
            }
        }
        return null;
    }

    private BenhNhanResponse searchBenhNhanCho(String maBN) {
        for (BenhNhanResponse benhNhanResponse : dsBenhNhanCho) {
            if (benhNhanResponse.getMaBN() == null ? maBN == null : benhNhanResponse.getMaBN().equals(maBN)) {
                return benhNhanResponse;
            }
        }
        return null;
    }
    
    
    private BenhNhanResponse searchBenhNhan(String maBN) {
        BenhNhanResponse benhNhanResponse = searchBenhNhanDieuTri(maBN);
        seachBNCount++;
        if (benhNhanResponse != null) {
            selectBenhNhan(benhNhanResponse);
        } else {
            BenhNhanResponse bnRes = searchBenhNhanCho(maBN);
            if (benhNhanResponse != null) {
                return benhNhanResponse;
            } else {
                if (seachBNCount < 5) {
                    searchBenhNhan(maBN);
                } else {
                    seachBNCount = 0;
                }
            }
        }
        return null;
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
        jButton1 = new javax.swing.JButton();
        btnXuatVien = new javax.swing.JButton();
        btnNhapVien = new javax.swing.JButton();
        btnXuatVien1 = new javax.swing.JButton();
        btnChangePin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBNDieuTri = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearchMaBN = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnSearch2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBNCho = new javax.swing.JTable();
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
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtYeuCauKham = new javax.swing.JTextArea();
        txtGioiTinh = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_add_bn_50x50.png"))); // NOI18N
        jButton1.setText("Thêm BN");
        jButton1.setHideActionText(true);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(3);
        jButton1.setPreferredSize(new java.awt.Dimension(126, 98));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnXuatVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_smile.png"))); // NOI18N
        btnXuatVien.setText("Xuất Viện");
        btnXuatVien.setHideActionText(true);
        btnXuatVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatVien.setIconTextGap(3);
        btnXuatVien.setPreferredSize(new java.awt.Dimension(126, 98));
        btnXuatVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXuatVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatVienActionPerformed(evt);
            }
        });

        btnNhapVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNhapVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_xe_cuu_thuong.png"))); // NOI18N
        btnNhapVien.setText("Nhập Viện");
        btnNhapVien.setHideActionText(true);
        btnNhapVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNhapVien.setIconTextGap(3);
        btnNhapVien.setPreferredSize(new java.awt.Dimension(126, 98));
        btnNhapVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNhapVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapVienActionPerformed(evt);
            }
        });

        btnXuatVien1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatVien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_money_75x75.png"))); // NOI18N
        btnXuatVien1.setText("Nạp Tiền");
        btnXuatVien1.setHideActionText(true);
        btnXuatVien1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatVien1.setIconTextGap(3);
        btnXuatVien1.setPreferredSize(new java.awt.Dimension(126, 98));
        btnXuatVien1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXuatVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatVien1ActionPerformed(evt);
            }
        });

        btnChangePin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChangePin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_Card_50x50.png"))); // NOI18N
        btnChangePin.setText("Đổi mã pin");
        btnChangePin.setHideActionText(true);
        btnChangePin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChangePin.setIconTextGap(3);
        btnChangePin.setPreferredSize(new java.awt.Dimension(126, 98));
        btnChangePin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChangePin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNhapVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChangePin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(640, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnXuatVien, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(btnNhapVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXuatVien1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChangePin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(51, 255, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Bệnh Nhân Điều Trị", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 300));

        tableBNDieuTri.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableBNDieuTri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Bệnh Nhân", "Họ", "Tên", "Giới Tính", "Ngày Sinh", "CMND", "Nghề Nghiệp", "Địa Chỉ", "SĐT", "Phòng bệnh", "Yêu Cầu Khám"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBNDieuTri.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableBNDieuTri.setGridColor(new java.awt.Color(204, 255, 204));
        tableBNDieuTri.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        tableBNDieuTri.setMinimumSize(new java.awt.Dimension(600, 156));
        tableBNDieuTri.setRowHeight(26);
        tableBNDieuTri.setSelectionBackground(new java.awt.Color(183, 219, 255));
        tableBNDieuTri.setSelectionForeground(new java.awt.Color(0, 102, 255));
        tableBNDieuTri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableBNDieuTri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBNDieuTriMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableBNDieuTri);
        if (tableBNDieuTri.getColumnModel().getColumnCount() > 0) {
            tableBNDieuTri.getColumnModel().getColumn(0).setMinWidth(60);
            tableBNDieuTri.getColumnModel().getColumn(0).setMaxWidth(120);
            tableBNDieuTri.getColumnModel().getColumn(1).setMinWidth(160);
            tableBNDieuTri.getColumnModel().getColumn(2).setMinWidth(100);
            tableBNDieuTri.getColumnModel().getColumn(3).setMinWidth(150);
            tableBNDieuTri.getColumnModel().getColumn(4).setMinWidth(100);
            tableBNDieuTri.getColumnModel().getColumn(5).setMinWidth(120);
            tableBNDieuTri.getColumnModel().getColumn(6).setMinWidth(120);
            tableBNDieuTri.getColumnModel().getColumn(7).setMinWidth(150);
            tableBNDieuTri.getColumnModel().getColumn(8).setMinWidth(300);
            tableBNDieuTri.getColumnModel().getColumn(9).setMinWidth(150);
            tableBNDieuTri.getColumnModel().getColumn(10).setMinWidth(150);
            tableBNDieuTri.getColumnModel().getColumn(11).setMinWidth(390);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1340, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 102, 153));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách bệnh nhân chờ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tìm mã BN: ");

        txtSearchMaBN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ImageIcon icon = new ImageIcon(getClass().getResource("/com/quan/datn/images/ic_reload.png"));
        Image image = icon.getImage().getScaledInstance(26, 26,  java.awt.Image.SCALE_SMOOTH);
        btnSearch1.setIcon(new javax.swing.ImageIcon(image));
        btnSearch1.setToolTipText("Load lại dữ liệu");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_search_30x30.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_Card_30x30.png"))); // NOI18N
        btnSearch2.setToolTipText("Tìm kiếm theo thẻ bệnh nhân");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tableBNCho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableBNCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Bệnh Nhân", "Họ", "Tên", "Giới Tính", "Ngày Sinh", "CMND", "Nghề Nghiệp", "Địa Chỉ", "SĐT", "Yêu Cầu Khám"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBNCho.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableBNCho.setGridColor(new java.awt.Color(204, 255, 204));
        tableBNCho.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        tableBNCho.setMinimumSize(new java.awt.Dimension(600, 156));
        tableBNCho.setRowHeight(26);
        tableBNCho.setSelectionBackground(new java.awt.Color(183, 219, 255));
        tableBNCho.setSelectionForeground(new java.awt.Color(0, 102, 255));
        tableBNCho.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableBNCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBNChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBNCho);
        if (tableBNCho.getColumnModel().getColumnCount() > 0) {
            tableBNCho.getColumnModel().getColumn(0).setMinWidth(60);
            tableBNCho.getColumnModel().getColumn(0).setMaxWidth(120);
            tableBNCho.getColumnModel().getColumn(1).setMinWidth(150);
            tableBNCho.getColumnModel().getColumn(2).setMinWidth(100);
            tableBNCho.getColumnModel().getColumn(3).setMinWidth(100);
            tableBNCho.getColumnModel().getColumn(4).setMinWidth(100);
            tableBNCho.getColumnModel().getColumn(5).setMinWidth(120);
            tableBNCho.getColumnModel().getColumn(6).setMinWidth(100);
            tableBNCho.getColumnModel().getColumn(7).setMinWidth(120);
            tableBNCho.getColumnModel().getColumn(8).setMinWidth(200);
            tableBNCho.getColumnModel().getColumn(9).setMinWidth(130);
            tableBNCho.getColumnModel().getColumn(10).setMinWidth(250);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
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

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel10.setText("Yêu cầu khám ");

        txtYeuCauKham.setEditable(false);
        txtYeuCauKham.setColumns(20);
        txtYeuCauKham.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtYeuCauKham.setLineWrap(true);
        txtYeuCauKham.setRows(5);
        txtYeuCauKham.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtYeuCauKham);

        txtGioiTinh.setEditable(false);
        txtGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

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
                                .addGap(0, 1, Short.MAX_VALUE))
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
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addContainerGap(128, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ThemBN.shareInstances.setVisible(true);
        ThemBN.shareInstances.resetInfo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
         String maBN = txtSearchMaBN.getText();
        if (maBN != null && !maBN.equals("")) {
//            BenhNhanResponse benhNhanResponse = searchBenhNhan(bn.getMaBN());
            boolean isExist = false;
            if (!isExist) {
                for (BenhNhanResponse bns : dsBenhNhanDieuTri) {
                    if (bns.getMaBN().equals(maBN)) {
                        selectBenhNhan(bns);
                        JOptionPane.showMessageDialog(this, "Tìm kiếm thành công bênh nhân: "+ bns.getFullName());
                        isExist = true;
                        break;
                    }
                }
            }
            
            for (BenhNhanResponse bns : dsBenhNhanCho) {
                if (bns.getMaBN().equals(maBN)) {
                    selectBenhNhan(bns);
                    JOptionPane.showMessageDialog(this, "Tìm kiếm thành công bênh nhân: "+ bns.getFullName());
                    isExist = true;
                    break;
                }
            }
            
            if (!isExist) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn cần nhập mã bệnh nhân để tìm kiếm");
        }
            
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        BenhNhan bn = getInfoBenhNhanFromCard();
        while (bn == null) {
            bn = getInfoBenhNhanFromCard();
        }
        System.out.println("Mã BN: " + bn.getMaBN());
        if (bn.getMaBN() != null && !bn.getMaBN().equals("")) {
//            BenhNhanResponse benhNhanResponse = searchBenhNhan(bn.getMaBN());
            boolean isExist = false;
            if (!isExist) {
                for (BenhNhanResponse bns : dsBenhNhanDieuTri) {
                    if (bns.getMaBN().equals(bn.getMaBN())) {
                        selectBenhNhan(bns);
                        JOptionPane.showMessageDialog(this, "Tìm kiếm thành công bênh nhân: "+ bns.getFullName());
                        isExist = true;
                        break;
                    }
                }
            }
            
            for (BenhNhanResponse bns : dsBenhNhanCho) {
                if (bns.getMaBN().equals(bn.getMaBN())) {
                    selectBenhNhan(bns);
                    JOptionPane.showMessageDialog(this, "Tìm kiếm thành công bênh nhân: "+ bns.getFullName());
                    isExist = true;
                    break;
                }
            }
            
            if (!isExist) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không đọc được dữ liệu bệnh nhân trên thẻ");
        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void tableBNChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBNChoMouseClicked
        tableBNDieuTri.clearSelection();
        int rowSelect = tableBNCho.getSelectedRow();
        selectBenhNhan(dsBenhNhanCho.get(rowSelect));

    }//GEN-LAST:event_tableBNChoMouseClicked

    private void tableBNDieuTriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBNDieuTriMouseClicked
        tableBNCho.clearSelection();
        int rowSelect = tableBNDieuTri.getSelectedRow();
        selectBenhNhan(dsBenhNhanDieuTri.get(rowSelect));

    }//GEN-LAST:event_tableBNDieuTriMouseClicked

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed

        loadData();

    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnNhapVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapVienActionPerformed

        KhamBenh.sharedInstances.setVisible(true);

    }//GEN-LAST:event_btnNhapVienActionPerformed

    private void btnXuatVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatVienActionPerformed
        String maBN = txtMaBN.getText();
        if (tableBNDieuTri.getSelectedRow() >= 0 && !maBN.isEmpty()) {
            XuatVien.shareInstance.setVisible(true);
            XuatVien.shareInstance.getInfoBenhNhan(maBN, false);
//            if (bnRepository.xuatVien(maBN)) {
//                JOptionPane.showMessageDialog(this, "Bệnh nhân đã được xuất viện");
//                loadData();
//            } else {
//                JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra, vui lòng thử lại... ");
//            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn bệnh nhân đang điều trị để xuất viện...");
        }

    }//GEN-LAST:event_btnXuatVienActionPerformed

    private void btnXuatVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatVien1ActionPerformed
      
       NapTienView.shareInstance.setVisible(true);
       NapTienView.shareInstance.resetData(true);
       
    }//GEN-LAST:event_btnXuatVien1ActionPerformed

    private void btnChangePinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePinActionPerformed
        
       ChangePinView.shareInstance.setVisible(true);
       ChangePinView.shareInstance.resetData(true);
        
    }//GEN-LAST:event_btnChangePinActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePin;
    private javax.swing.JButton btnNhapVien;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnXuatVien;
    private javax.swing.JButton btnXuatVien1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbImage;
    private javax.swing.JTable tableBNCho;
    private javax.swing.JTable tableBNDieuTri;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMaBA;
    private javax.swing.JTextField txtMaBN;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchMaBN;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextArea txtYeuCauKham;
    // End of variables declaration//GEN-END:variables

}
