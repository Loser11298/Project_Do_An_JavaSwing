/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.view;

import com.quan.datn.model.PhongKham;
import com.quan.datn.model.request.BenhAnRequest;
import com.quan.datn.model.response.BenhNhanResponse;
import com.quan.datn.network.BenhAnRepository;
import com.quan.datn.network.BenhNhanRepository;
import com.quan.datn.network.PhongKhamRepository;
import com.quan.datn.utils.DateTimeUtils;
import com.quan.datn.utils.SmardCardManager;
import com.quan.datn.utils.Utils;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

public class KhamBenh extends javax.swing.JFrame {

    /**
     * Creates new form KhamBenh
     */
    private final PhongKhamRepository pkRepository;
    private List<PhongKham> listPhongKham;
    public static KhamBenh sharedInstances = new KhamBenh();
    private List<BenhNhanResponse> dsBenhNhans;
    private List<BenhNhanResponse> tempList;
    private final BenhNhanRepository bnRepository;
    private final BenhAnRepository benhAnRepository;
    private final SmardCardManager cardManager;
    private int readCardCount = 0;

    public KhamBenh() {
        initComponents();
        listPhongKham = new ArrayList<>();
        pkRepository = new PhongKhamRepository();
        benhAnRepository = new BenhAnRepository();
        cardManager = new SmardCardManager();
        getListPhongKham();
        dsBenhNhans = new ArrayList<>();
        bnRepository = new BenhNhanRepository();
        dsBenhNhans = bnRepository.getAllBenhNhan();
        txtMaPhieuKham.setText("BA" + Utils.getTimeString("ddyyyyHHmmss"));
        showListBenhNhan(dsBenhNhans);
    }

    private void showListBenhNhan(List<BenhNhanResponse> benhNhans) {
        DefaultTableModel mode = (DefaultTableModel) tableBN.getModel();
        mode.setRowCount(0);
        if (!benhNhans.isEmpty()) {
            Object[] row = new Object[10];
            for (int i = 0; i < benhNhans.size(); i++) {
                row[0] = i;
                row[1] = benhNhans.get(i).getMaBN();
                row[2] = benhNhans.get(i).getHoBn();
                row[3] = benhNhans.get(i).getTenBn();
                row[4] = benhNhans.get(i).getCmnd();
                row[5] = benhNhans.get(i).getSdt();
                row[6] = benhNhans.get(i).getGioiTinh();
                row[7] = benhNhans.get(i).getNgaySinh();
                row[8] = benhNhans.get(i).getNgheNghiep();
                row[9] = benhNhans.get(i).getDiaChi();
                mode.addRow(row);
            }
        }
    }

    private void getListPhongKham() {
        new Thread(() -> {
            listPhongKham = pkRepository.getAllPhongKham();
            if (listPhongKham != null && listPhongKham.size() > 0) {
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                listPhongKham.forEach((phongKham) -> {
                    model.addElement(phongKham.getTenPK());
                });
                txtPhongKham.setModel(model);
                String tenBS = listPhongKham.get(0).getBacSi().getHo() + " "
                        + listPhongKham.get(0).getBacSi().getTen();
                txtMaBacSi.setText(listPhongKham.get(0).getBacSi().getMaNV());
                txtTenBacSi.setText(tenBS);
            } else {
                listPhongKham = new ArrayList<>();
            }
        }).start();
    }

    private String getMaBenhNhanFromCard() {
        if (cardManager.isConnect) {
            return cardManager.getInfo().getMaBN();
        } else {
            readCardCount++;
            if (cardManager.connectCard() && readCardCount < 10) {
                getMaBenhNhanFromCard();
            } else {
                readCardCount = 0;
            }
        }
        return "";
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBN = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtKeySearch = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btn_Close = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtMaPhieuKham = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNgayKham = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txtPhongKham = new javax.swing.JComboBox<>();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        txtGioKham = new javax.swing.JSpinner(sm);
        jLabel17 = new javax.swing.JLabel();
        txtMaBacSi = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTenBacSi = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtYeuCauKham = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nhập thông tin khám bệnh cho bệnh nhân");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(214, 240, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin bệnh nhân", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(591, 398));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tableBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableBN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Bệnh Nhân", "Họ", "Tên", "CMND", "SĐT", "Giới Tính", "Ngày Sinh", "Nghề Nghiệp", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBN.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableBN.setGridColor(new java.awt.Color(204, 255, 204));
        tableBN.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        tableBN.setMinimumSize(new java.awt.Dimension(600, 156));
        tableBN.setRowHeight(26);
        tableBN.setSelectionBackground(new java.awt.Color(183, 219, 255));
        tableBN.setSelectionForeground(new java.awt.Color(0, 102, 255));
        tableBN.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableBN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBN);
        if (tableBN.getColumnModel().getColumnCount() > 0) {
            tableBN.getColumnModel().getColumn(0).setMinWidth(80);
            tableBN.getColumnModel().getColumn(1).setMinWidth(200);
            tableBN.getColumnModel().getColumn(2).setMinWidth(120);
            tableBN.getColumnModel().getColumn(3).setMinWidth(200);
            tableBN.getColumnModel().getColumn(4).setMinWidth(150);
            tableBN.getColumnModel().getColumn(5).setMinWidth(160);
            tableBN.getColumnModel().getColumn(6).setMinWidth(120);
            tableBN.getColumnModel().getColumn(7).setMinWidth(130);
            tableBN.getColumnModel().getColumn(8).setMinWidth(250);
            tableBN.getColumnModel().getColumn(9).setMinWidth(390);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm BN: ");
        jLabel1.setPreferredSize(new java.awt.Dimension(75, 29));

        txtKeySearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtKeySearch.setToolTipText("Tìm kiếm theo tên, số điện thoại hoặc theo số CMND");
        txtKeySearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeySearchKeyReleased(evt);
            }
        });

        btnConnect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_Card_25x25.png"))); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.setPreferredSize(new java.awt.Dimension(158, 30));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKeySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtKeySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(809, 80));

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/save_50x50.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setPreferredSize(new java.awt.Dimension(158, 59));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btn_Close.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_X_50x50.png"))); // NOI18N
        btn_Close.setText("Đóng");
        btn_Close.setPreferredSize(new java.awt.Dimension(158, 59));
        btn_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(862, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(214, 240, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khám", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(809, 269));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Mã phiếu khám : ");
        jLabel13.setPreferredSize(new java.awt.Dimension(63, 26));

        txtMaPhieuKham.setEditable(false);
        txtMaPhieuKham.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaPhieuKham.setPreferredSize(new java.awt.Dimension(59, 25));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Ngày khám : ");
        jLabel14.setPreferredSize(new java.awt.Dimension(63, 26));

        txtNgayKham.setDateFormatString("dd/MM/yyyy");
        txtNgayKham.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtNgayKham.setPreferredSize(new java.awt.Dimension(91, 26));
        txtNgayKham.setDate(new Date());

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Phòng khám : ");

        txtPhongKham.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtPhongKham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng khám 1", "Phòng khám 2", "Phòng khám 3", "Phòng khám 4", "Phòng khám 5" }));
        txtPhongKham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtPhongKhamItemStateChanged(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(txtGioKham, "HH:mm:ss");
        txtGioKham.setEditor(de);
        txtGioKham.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Mã bác sĩ : ");
        jLabel17.setPreferredSize(new java.awt.Dimension(63, 26));

        txtMaBacSi.setEditable(false);
        txtMaBacSi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMaBacSi.setMinimumSize(new java.awt.Dimension(6, 28));
        txtMaBacSi.setPreferredSize(new java.awt.Dimension(59, 25));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Bác sĩ phụ trách: ");
        jLabel18.setPreferredSize(new java.awt.Dimension(63, 26));

        txtTenBacSi.setEditable(false);
        txtTenBacSi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTenBacSi.setPreferredSize(new java.awt.Dimension(59, 28));

        txtYeuCauKham.setColumns(20);
        txtYeuCauKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtYeuCauKham.setLineWrap(true);
        txtYeuCauKham.setRows(5);
        txtYeuCauKham.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtYeuCauKham);

        jLabel19.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel19.setText("Yêu cầu khám ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaPhieuKham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtNgayKham, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtGioKham, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtPhongKham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTenBacSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaBacSi, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 374, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPhieuKham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(txtNgayKham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGioKham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaBacSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenBacSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhongKham, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (tableBN.getSelectedRow() != -1) {
                String maBN;
                if (tempList != null) {
                    maBN = tempList.get(tableBN.getSelectedRow()).getMaBN();
                } else {
                    maBN = dsBenhNhans.get(tableBN.getSelectedRow()).getMaBN();
                }
                String maBK = listPhongKham.get(txtPhongKham.getSelectedIndex()).getMaPK();
                String ngayKhamString = DateTimeUtils.formatDate(txtNgayKham.getDate());
                String gioKhamString = Utils.formatTimeString(txtGioKham.getValue().toString());
                String thoiGianKham = ngayKhamString + " " + gioKhamString;
                String lyDoKham = txtYeuCauKham.getText();
                String maBA = txtMaPhieuKham.getText();
                BenhAnRequest request = new BenhAnRequest(maBA, maBN, maBK, thoiGianKham, lyDoKham);
                if (benhAnRepository.addBenhAn(request)) {
                    JOptionPane.showMessageDialog(this, "Thêm thông tin thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại...");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cần chọn bệnh nhân nhập viện");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btn_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_CloseActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        String maBN = getMaBenhNhanFromCard();
        if (!maBN.equals("")) {
            List<BenhNhanResponse> bnrs = new ArrayList<>();
            dsBenhNhans.stream().filter((dsBenhNhan) -> (dsBenhNhan.getMaBN() == null ? maBN == null : dsBenhNhan.getMaBN().equals(maBN))).forEachOrdered((dsBenhNhan) -> {
                bnrs.add(dsBenhNhan);
            });
            showListBenhNhan(bnrs);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm được bệnh nhân");
        }

    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtPhongKhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtPhongKhamItemStateChanged
        String tenBS = listPhongKham.get(txtPhongKham.getSelectedIndex()).getBacSi().getHo() + " "
                + listPhongKham.get(txtPhongKham.getSelectedIndex()).getBacSi().getTen();
        txtMaBacSi.setText(listPhongKham.get(txtPhongKham.getSelectedIndex()).getBacSi().getMaNV());
        txtTenBacSi.setText(tenBS);
    }//GEN-LAST:event_txtPhongKhamItemStateChanged

    private void txtKeySearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeySearchKeyReleased
        String keySearch = txtKeySearch.getText();
        tempList = null;
        if (!keySearch.equals("")) {
            tempList = new ArrayList<>();
            for (int i = 0; i < dsBenhNhans.size(); i++) {
                String sdt = dsBenhNhans.get(i).getSdt();
                String cmnd = dsBenhNhans.get(i).getCmnd();
                String ten = dsBenhNhans.get(i).getTenBn();
                if (sdt.contains(keySearch) || cmnd.contains(keySearch) || ten.contains(keySearch)) {
                    tempList.add(dsBenhNhans.get(i));
                }
            }
            showListBenhNhan(tempList);
        } else {
            showListBenhNhan(dsBenhNhans);
        }
    }//GEN-LAST:event_txtKeySearchKeyReleased

    private void tableBNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBNMouseClicked
        txtYeuCauKham.setText("");
    }//GEN-LAST:event_tableBNMouseClicked

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
            java.util.logging.Logger.getLogger(KhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhamBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhamBenh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btn_Close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableBN;
    private javax.swing.JSpinner txtGioKham;
    private javax.swing.JTextField txtKeySearch;
    private javax.swing.JTextField txtMaBacSi;
    private javax.swing.JTextField txtMaPhieuKham;
    private com.toedter.calendar.JDateChooser txtNgayKham;
    private javax.swing.JComboBox<String> txtPhongKham;
    private javax.swing.JTextField txtTenBacSi;
    private javax.swing.JTextArea txtYeuCauKham;
    // End of variables declaration//GEN-END:variables
}
