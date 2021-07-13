/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.view.panel;

import com.quan.datn.model.response.BaoCaoResponse;
import com.quan.datn.network.BaoCaoRepository;
import com.quan.datn.utils.DateTimeUtils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class PNBaoCao extends javax.swing.JPanel {

    /**
     * Creates new form PNBaoCao
     */
    private final BaoCaoRepository baoCaoRepository;
    private List<BaoCaoResponse> dsBaoCao;
    private String startTime = "";
    private String endTime = "";

    public PNBaoCao() {
        initComponents();
        baoCaoRepository = new BaoCaoRepository();
        loadData();
    }

    public void loadData() {
        new Thread(() -> {
            dsBaoCao = baoCaoRepository.getAllBaoCao(startTime, endTime);
            showDSBaoCao();
        }).start();
    }

    private void showDSBaoCao() {
        DefaultTableModel mode = (DefaultTableModel) tableBaoCao.getModel();
        mode.setRowCount(0);
        if (!dsBaoCao.isEmpty()) {
            Object[] row = new Object[8];
            for (int i = 0; i < dsBaoCao.size(); i++) {
                row[0] = i;
                row[1] = dsBaoCao.get(i).getMaBN();
                row[2] = dsBaoCao.get(i).getHoTen();
                row[3] = dsBaoCao.get(i).getNgheNghiep();
                row[4] = dsBaoCao.get(i).getDiaChi();
                row[5] = dsBaoCao.get(i).getSdt();
                row[6] = dsBaoCao.get(i).getNoiDung();
                row[7] = dsBaoCao.get(i).getThoiGian();
                mode.addRow(row);
            }
        }
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
        txtStartTime = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEndTime = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBaoCao = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(941, 69));

        txtStartTime.setDateFormatString("dd/MM/yyyy");
        txtStartTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtStartTime.setPreferredSize(new java.awt.Dimension(91, 26));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Từ ngày : ");
        jLabel6.setPreferredSize(new java.awt.Dimension(63, 26));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Đến ngày :");
        jLabel7.setPreferredSize(new java.awt.Dimension(63, 26));

        txtEndTime.setDateFormatString("dd/MM/yyyy");
        txtEndTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEndTime.setPreferredSize(new java.awt.Dimension(91, 26));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/icon_search_30x30.png"))); // NOI18N
        jButton1.setText("Tìm kiếm");
        jButton1.setToolTipText("");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setPreferredSize(new java.awt.Dimension(128, 39));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quan/datn/images/save_30x30.png"))); // NOI18N
        jButton2.setText("Xuất file");
        jButton2.setToolTipText("");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setPreferredSize(new java.awt.Dimension(128, 39));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        tableBaoCao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableBaoCao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Bệnh Nhân", "Họ và Tên", "Nghề Nghiệp", "Địa Chỉ", "SĐT", "Nội dung", "Thời Gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBaoCao.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableBaoCao.setGridColor(new java.awt.Color(204, 255, 204));
        tableBaoCao.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        tableBaoCao.setMinimumSize(new java.awt.Dimension(600, 156));
        tableBaoCao.setRowHeight(26);
        tableBaoCao.setSelectionBackground(new java.awt.Color(183, 219, 255));
        tableBaoCao.setSelectionForeground(new java.awt.Color(51, 102, 255));
        tableBaoCao.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableBaoCao);
        if (tableBaoCao.getColumnModel().getColumnCount() > 0) {
            tableBaoCao.getColumnModel().getColumn(0).setMinWidth(60);
            tableBaoCao.getColumnModel().getColumn(0).setMaxWidth(150);
            tableBaoCao.getColumnModel().getColumn(1).setMinWidth(150);
            tableBaoCao.getColumnModel().getColumn(2).setMinWidth(300);
            tableBaoCao.getColumnModel().getColumn(3).setMinWidth(250);
            tableBaoCao.getColumnModel().getColumn(4).setMinWidth(350);
            tableBaoCao.getColumnModel().getColumn(5).setMinWidth(220);
            tableBaoCao.getColumnModel().getColumn(6).setMinWidth(350);
            tableBaoCao.getColumnModel().getColumn(7).setMinWidth(200);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Bao-cao");
            writeHeaderLine(sheet);
            writeDataLines(dsBaoCao, workbook, sheet);
            FileOutputStream outputStream = new FileOutputStream("Outputs/Bao-cao-" + DateTimeUtils.getDateTimeString() + ".xlsx");
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(this, "Xuất file thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, Vui lòng thử lại...");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date sTime = txtStartTime.getDate();
        Date eTime = txtEndTime.getDate();
        if (sTime != null) {
            startTime = DateTimeUtils.dateToDateTimeString(sTime);
        } else {
            startTime = "";
        }
        if (eTime != null) {
            endTime = DateTimeUtils.dateToDateTimeString(eTime);
        } else {
            endTime = "";
        }
        System.out.println("Data " + startTime + " === " + endTime);
        loadData();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void writeHeaderLine(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("STT");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Mã Bệnh Nhân");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Họ và Tên");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Nghề Nghiệp");
        
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Địa Chỉ");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Số điện thoại");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Nội dung");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Thời gian");
    }

    private void writeDataLines(List<BaoCaoResponse> dsBaoCaos, XSSFWorkbook workbook, XSSFSheet sheet) {
        int rowCount = 1;
        for (BaoCaoResponse baoCao : dsBaoCaos) {
            Row row = sheet.createRow(rowCount);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(rowCount);

            cell = row.createCell(columnCount++);
            cell.setCellValue(baoCao.getMaBN());

            cell = row.createCell(columnCount++);
            cell.setCellValue(baoCao.getHoTen());

            cell = row.createCell(columnCount++);
            cell.setCellValue(baoCao.getNgheNghiep());

            cell = row.createCell(columnCount++);
            cell.setCellValue(baoCao.getDiaChi());

            cell = row.createCell(columnCount++);
            cell.setCellValue(baoCao.getSdt());

            cell = row.createCell(columnCount++);
            cell.setCellValue(baoCao.getNoiDung());

            cell = row.createCell(columnCount);
            cell.setCellValue(baoCao.getThoiGian());

            rowCount++;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableBaoCao;
    private com.toedter.calendar.JDateChooser txtEndTime;
    private com.toedter.calendar.JDateChooser txtStartTime;
    // End of variables declaration//GEN-END:variables
}
