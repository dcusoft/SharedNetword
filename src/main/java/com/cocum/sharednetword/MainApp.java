/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.cocum.sharednetword;

import com.cocum.sharednetword.controlador.ClienteController;
import com.cocum.sharednetword.controlador.ServidorController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */
public class MainApp extends javax.swing.JFrame {

    /**
     * Creates new form MainApp
     */
    ClienteController cliente;
    ServidorController server;
    boolean conectado = false;

    public MainApp() {
        initComponents();

        buttonGroup1.add(jRadioButtonCliente);
        buttonGroup1.add(jRadioButtonServidor);
        jRadioButtonCliente.setSelected(true);
        jButtonEnviar.setEnabled(conectado);
        jButton3.setEnabled(conectado);
        try {
            for (InetAddress i : InetAddress.getAllByName("COCUN-PC")) {
                if (i.isSiteLocalAddress()) {
                    jComboBox1.addItem(i.getHostAddress());
                }
            }

            jTextFieldIp.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButtonCliente = new javax.swing.JRadioButton();
        jRadioButtonServidor = new javax.swing.JRadioButton();
        jTextFieldIp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPuerto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListConectados = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButtonEnviar = new javax.swing.JButton();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabelEstado = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(660, 428));

        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButtonCliente.setText("Cliente");
        jRadioButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonClienteActionPerformed(evt);
            }
        });

        jRadioButtonServidor.setText("Servidor");
        jRadioButtonServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonServidorActionPerformed(evt);
            }
        });

        jTextFieldIp.setToolTipText("ip del server a conectar");

        jLabel1.setText("IP");

        jLabel2.setText("PUERTO");

        jLabel3.setText("Mensajes");

        jLabel4.setText("Conectados");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jListConectados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "vacio" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListConectados);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jLabel6.setText("Nombre de usuario");

        jButton3.setText("Abrir Privado");

        jLabelEstado.setText("Estado del Mensaje:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ver Ips" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonEnviar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButtonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldIp, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEstado)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonCliente)
                        .addComponent(jRadioButtonServidor)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextFieldIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEstado)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("jRadioButtonCliente" + jRadioButtonCliente.isSelected());
        System.out.println("jRadioButtonServidor" + jRadioButtonServidor.isSelected());

        if (!conectado) {
            jButton1.setText("Desconectar");
            jRadioButtonCliente.setEnabled(conectado);
            jRadioButtonServidor.setEnabled(conectado);
            jButtonEnviar.setEnabled(!conectado);
            jTextFieldIp.setEnabled(conectado);
            jTextFieldPuerto.setEnabled(conectado);
            jTextFieldUsuario.setEnabled(conectado);
            if (!jRadioButtonCliente.isSelected()) {
                //server
                server = new ServidorController();
                server.iniciarServer(Integer.parseInt(jTextFieldPuerto.getText()));

            }

            cliente = new ClienteController(Integer.parseInt(jTextFieldPuerto.getText()), jTextFieldIp.getText(), this);
            cliente.Conectar(jTextFieldUsuario.getText());
            conectado = true;
        } else {
            jRadioButtonCliente.setEnabled(conectado);
            jRadioButtonServidor.setEnabled(conectado);
            jButtonEnviar.setEnabled(!conectado);
            jTextFieldIp.setEnabled(conectado);
            jTextFieldPuerto.setEnabled(conectado);
            jTextFieldUsuario.setEnabled(conectado);
            jButton1.setText("Conectar");
            conectado = false;
            cliente.DesConectar();
            System.out.println("CLIENTE DESCONECTADO");
            server.apagarServer(Integer.parseInt(jTextFieldPuerto.getText()));
            System.out.println("SERVER APAGADO");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        System.out.println("ENVIAR MENSAJE");
        cliente.Send("\n" + jTextFieldUsuario.getText() + ": " + jTextArea2.getText(), 3);
        jTextArea2.setText("");
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jRadioButtonServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonServidorActionPerformed
        jTextFieldIp.setEnabled(false);
        jTextFieldIp.setText("localhost");
    }//GEN-LAST:event_jRadioButtonServidorActionPerformed

    private void jRadioButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonClienteActionPerformed
        jTextFieldIp.setEnabled(true);
        try {
            jTextFieldIp.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButtonClienteActionPerformed

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
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JList<String> jListConectados;
    private javax.swing.JRadioButton jRadioButtonCliente;
    private javax.swing.JRadioButton jRadioButtonServidor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextFieldIp;
    private javax.swing.JTextField jTextFieldPuerto;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextArea getjTextArea1() {
        return jTextArea1;
    }

    public void setjTextArea1(javax.swing.JTextArea jTextArea1) {
        this.jTextArea1 = jTextArea1;
    }

    public javax.swing.JLabel getjLabelEstado() {
        return jLabelEstado;
    }

    public void setjLabelEstado(javax.swing.JLabel jLabelEstado) {
        this.jLabelEstado = jLabelEstado;
    }

    public javax.swing.JList<String> getjListConectados() {
        return jListConectados;
    }

    public void setjListConectados(javax.swing.JList<String> jListConectados) {
        this.jListConectados = jListConectados;
    }

}