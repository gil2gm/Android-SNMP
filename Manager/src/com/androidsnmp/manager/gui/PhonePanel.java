/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.androidsnmp.manager.gui;

import com.androidsnmp.manager.models.ManagedDevice;
import java.awt.LayoutManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brnunes
 */
public class PhonePanel extends javax.swing.JPanel {
    protected boolean mAllowLayoutChange;
    private ManagedDevice device;
    private DefaultTableModel tableModel;
    
    /**
     * Creates new form PhonePanel
     */
    public PhonePanel(ManagedDevice device) {
        this.device = device;
        
        tableModel = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "pid", "Description", "Running Time", "Memory Used"
            });
        
        mAllowLayoutChange=true;
        initComponents();
        servicesTable.getColumnModel().getColumn(0).setPreferredWidth(54);
        servicesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        servicesTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        servicesTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        servicesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mAllowLayoutChange=false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gpsLabel = new javax.swing.JLabel();
        networkLabel = new javax.swing.JLabel();
        bluetoothLabel = new javax.swing.JLabel();
        batteryStatusLabel = new javax.swing.JLabel();
        batteryLevelLabel = new javax.swing.JLabel();
        servicesScrollPane = new javax.swing.JScrollPane();
        servicesTable = new javax.swing.JTable();
        modelLabel = new javax.swing.JLabel();
        modelValueLabel = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        versionValueLabel = new javax.swing.JLabel();
        upTimeLabel = new javax.swing.JLabel();
        upTimeValueLabel = new javax.swing.JLabel();
        ipLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        messageTextField = new javax.swing.JTextField();
        sendSetButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(640, 360));

        gpsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/androidsnmp/manager/gui/res/gps_icon.png"))); // NOI18N
        gpsLabel.setText("Unknown");
        gpsLabel.setToolTipText("Get GPS Status");
        gpsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gpsLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gpsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gpsLabelMouseClicked(evt);
            }
        });

        networkLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/androidsnmp/manager/gui/res/wireless_icon.png"))); // NOI18N
        networkLabel.setText("Unknown");
        networkLabel.setToolTipText("Get Network Status");
        networkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        networkLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        networkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                networkLabelMouseClicked(evt);
            }
        });

        bluetoothLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/androidsnmp/manager/gui/res/bluetooth_icon.png"))); // NOI18N
        bluetoothLabel.setText("Unknown");
        bluetoothLabel.setToolTipText("Get  Bluetooth Status");
        bluetoothLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bluetoothLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bluetoothLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bluetoothLabelMouseClicked(evt);
            }
        });

        batteryStatusLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/androidsnmp/manager/gui/res/gps_icon.png"))); // NOI18N
        batteryStatusLabel.setText("Unknown");
        batteryStatusLabel.setToolTipText("Get Battery Status");
        batteryStatusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        batteryStatusLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        batteryStatusLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batteryStatusLabelMouseClicked(evt);
            }
        });

        batteryLevelLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/androidsnmp/manager/gui/res/gps_icon.png"))); // NOI18N
        batteryLevelLabel.setText("Unknown");
        batteryLevelLabel.setToolTipText("Get Battery Level");
        batteryLevelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        batteryLevelLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        batteryLevelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batteryLevelLabelMouseClicked(evt);
            }
        });

        servicesTable.setModel(tableModel);
        servicesTable.setFillsViewportHeight(true);
        servicesScrollPane.setViewportView(servicesTable);

        modelLabel.setText("Model:");
        modelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelLabelMouseClicked(evt);
            }
        });

        modelValueLabel.setText("Unknown");
        modelValueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelValueLabelMouseClicked(evt);
            }
        });

        versionLabel.setText("Version:");
        versionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                versionLabelMouseClicked(evt);
            }
        });

        versionValueLabel.setText("Unknown");
        versionValueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                versionValueLabelMouseClicked(evt);
            }
        });

        upTimeLabel.setText("Up time:");
        upTimeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upTimeLabelMouseClicked(evt);
            }
        });

        upTimeValueLabel.setText("Unknown");
        upTimeValueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upTimeValueLabelMouseClicked(evt);
            }
        });

        ipLabel.setText("IP: ");

        jButton1.setText("Refresh Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        messageTextField.setText("SNMP Set Message");
        messageTextField.setToolTipText("Message to be sent to SNMP Agent");
        messageTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                messageTextFieldFocusGained(evt);
            }
        });

        sendSetButton.setText("Send");
        sendSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendSetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(upTimeLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(modelLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(versionLabel)
                                            .addGap(1, 1, 1))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(versionValueLabel)
                                    .addComponent(upTimeValueLabel)
                                    .addComponent(modelValueLabel)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(gpsLabel)
                                    .addGap(0, 0, 0)
                                    .addComponent(networkLabel)
                                    .addGap(0, 0, 0)
                                    .addComponent(bluetoothLabel)
                                    .addGap(0, 0, 0)
                                    .addComponent(batteryStatusLabel)
                                    .addGap(0, 0, 0)
                                    .addComponent(batteryLevelLabel))
                                .addComponent(ipLabel)
                                .addComponent(servicesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendSetButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gpsLabel)
                    .addComponent(networkLabel)
                    .addComponent(bluetoothLabel)
                    .addComponent(batteryStatusLabel)
                    .addComponent(batteryLevelLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelLabel)
                    .addComponent(modelValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(versionLabel)
                    .addComponent(versionValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upTimeLabel)
                    .addComponent(upTimeValueLabel))
                .addGap(18, 18, 18)
                .addComponent(servicesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendSetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ipLabel))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void gpsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gpsLabelMouseClicked
        device.updateGPSStatus();
    }//GEN-LAST:event_gpsLabelMouseClicked

    private void networkLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_networkLabelMouseClicked
        device.updateNetworkStatus();
    }//GEN-LAST:event_networkLabelMouseClicked

    private void bluetoothLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bluetoothLabelMouseClicked
        device.updateBluetoothStatus();
    }//GEN-LAST:event_bluetoothLabelMouseClicked
 
    private void batteryStatusLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batteryStatusLabelMouseClicked
        device.updateBatteryStatus();
    }//GEN-LAST:event_batteryStatusLabelMouseClicked
    
    private void batteryLevelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batteryLevelLabelMouseClicked
        device.updateBatteryLevel();
    }
    
    @Override
    public void setLayout(LayoutManager mgr) {
        if(mAllowLayoutChange) {
            super.setLayout(mgr);
        }
    }//GEN-LAST:event_batteryLevelLabelMouseClicked

    private void modelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelLabelMouseClicked
        device.updateModelName();
    }//GEN-LAST:event_modelLabelMouseClicked

    private void versionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_versionLabelMouseClicked
        device.updateVersionName();
    }//GEN-LAST:event_versionLabelMouseClicked

    private void upTimeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upTimeLabelMouseClicked
        device.updateUpTime();
    }//GEN-LAST:event_upTimeLabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        device.updateTableData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void modelValueLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelValueLabelMouseClicked
        device.updateModelName();
    }//GEN-LAST:event_modelValueLabelMouseClicked

    private void versionValueLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_versionValueLabelMouseClicked
        device.updateVersionName();
    }//GEN-LAST:event_versionValueLabelMouseClicked

    private void upTimeValueLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upTimeValueLabelMouseClicked
        device.updateUpTime();
    }//GEN-LAST:event_upTimeValueLabelMouseClicked

    private void messageTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_messageTextFieldFocusGained
        messageTextField.selectAll();
    }//GEN-LAST:event_messageTextFieldFocusGained

    private void sendSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendSetButtonActionPerformed
        device.sendMessage(messageTextField.getText());
    }//GEN-LAST:event_sendSetButtonActionPerformed

    public void setIpLabel(String text) {
        ipLabel.setText("IP: " + text);
    }

    public void setBatteryLevelLabelText(String text) {
        this.batteryLevelLabel.setText(text);
    }

    public void setBatteryStatusLabelText(String text) {
        this.batteryStatusLabel.setText(text);
    }

    public void setBluetoothLabelText(String text) {
        this.bluetoothLabel.setText(text);
    }

    public void setGpsLabelText(String text) {
        this.gpsLabel.setText(text);
    }

    public void setNetworkLabelText(String text) {
        this.networkLabel.setText(text);
    }

    public void setModelValueLabelText(String text) {
        this.modelValueLabel.setText(text);
    }

    public void setVersionValueLabel(String text) {
        this.versionValueLabel.setText(text);
    }

    public void setUpTimeValueLabelText(String text) {
        this.upTimeValueLabel.setText(text);
    }
    
    public void clearTable() {
        while(tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
    
    public void addTableRow(Object[] row) {
        tableModel.addRow(row);
        servicesScrollPane.invalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel batteryLevelLabel;
    private javax.swing.JLabel batteryStatusLabel;
    private javax.swing.JLabel bluetoothLabel;
    private javax.swing.JLabel gpsLabel;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JLabel modelValueLabel;
    private javax.swing.JLabel networkLabel;
    private javax.swing.JButton sendSetButton;
    private javax.swing.JScrollPane servicesScrollPane;
    private javax.swing.JTable servicesTable;
    private javax.swing.JLabel upTimeLabel;
    private javax.swing.JLabel upTimeValueLabel;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JLabel versionValueLabel;
    // End of variables declaration//GEN-END:variables
}
