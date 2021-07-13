/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.view;

import com.quan.datn.utils.DBManager;
import com.quan.datn.view.panel.MainView;
import javax.swing.JFrame;

public class Main extends JFrame{

    /**
     * Creates new form Main
     */
    private final MainView mainView;
    public static Main shareInstances = new Main();

    public Main() {
        initComponents();
        mainView = new MainView();
        content.removeAll();
        content.add(mainView);
        pack();
        this.setLocationRelativeTo(null);
    }
    
    public void doLogout(){
        DBManager.sharedInstance.cleanSessionLogin();
        LoginView.shareInstances.setVisible(true);
        this.dispose();
    }
    
    public void updateNewData(){
        mainView.initMenuTab();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ĐATN - Phần mềm quản lý bệnh viện");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        content.setBackground(new java.awt.Color(214, 240, 255));
        content.setMaximumSize(new java.awt.Dimension(1920, 1080));
        content.setMinimumSize(new java.awt.Dimension(1280, 1024));
        content.setLayout(new java.awt.CardLayout());
        getContentPane().add(content);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    // End of variables declaration//GEN-END:variables



}