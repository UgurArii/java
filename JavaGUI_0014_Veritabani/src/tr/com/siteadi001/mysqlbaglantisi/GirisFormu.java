/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.siteadi001.mysqlbaglantisi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author uğur
 */
public class GirisFormu extends javax.swing.JFrame {

	/**
	 * Creates new form GirisFormu
	 */
	public GirisFormu() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lblKullaniciAdi = new javax.swing.JLabel();
                lblKullaniciParola = new javax.swing.JLabel();
                txtKullaniciAdi = new javax.swing.JTextField();
                txtKullaniciParola = new javax.swing.JTextField();
                yoneticiGiris = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Yönetici Girişi");
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent evt) {
                                formWindowClosing(evt);
                        }
                        public void windowOpened(java.awt.event.WindowEvent evt) {
                                formWindowOpened(evt);
                        }
                });

                lblKullaniciAdi.setText("Kullanıcı Adı : ");

                lblKullaniciParola.setText("Parola         :");

                yoneticiGiris.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                yoneticiGiris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Checkmark.png"))); // NOI18N
                yoneticiGiris.setText("Giriş");
                yoneticiGiris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                yoneticiGiris.setIconTextGap(0);
                yoneticiGiris.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                yoneticiGirisActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblKullaniciAdi)
                                        .addComponent(lblKullaniciParola))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                                        .addComponent(txtKullaniciParola)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addComponent(yoneticiGiris)))
                                .addContainerGap(22, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblKullaniciAdi)
                                        .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblKullaniciParola)
                                        .addComponent(txtKullaniciParola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(yoneticiGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents
        Connection baglanti = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
        private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
                baglanti = mysqlBaglantisi.baglanti();
        }//GEN-LAST:event_formWindowOpened

        private void yoneticiGirisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yoneticiGirisActionPerformed
               String kullaniciGirisiSorguCumlesi = "SELECT * FROM yonetici WHERE yoneticiAdi = ? AND yoneticiParolasi = ?";
	       try {
			preparedStatement = baglanti.prepareStatement(kullaniciGirisiSorguCumlesi);
			preparedStatement.setString(1, txtKullaniciAdi.getText());
			preparedStatement.setString(2, txtKullaniciParola.getText());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				JOptionPane.showMessageDialog(null, "Kullanıcı Adı ve Parolası Doğrulandı.","MESAJ",
					JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
				 
				YoneticiFormuGUI yoneticiFormuGUINesnesi = new YoneticiFormuGUI();
				yoneticiFormuGUINesnesi.setVisible(rootPaneCheckingEnabled);
				
				
			}else{
				JOptionPane.showMessageDialog(null, "Kullanıcı Adı veya Parolası Geçersiz.","MESAJ",
					JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
		}
        }//GEN-LAST:event_yoneticiGirisActionPerformed

        private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
              if(baglanti != null){
			try{
				baglanti.close();
			}catch(Exception e){
				
			}
		}
		
		 if(resultSet != null){
			try{
				resultSet.close();
			}catch(Exception e){
				
			}
		}
		 
		  if(preparedStatement != null){
			try{
				preparedStatement.close();
			}catch(Exception e){
				
			}
		}
        }//GEN-LAST:event_formWindowClosing

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
			java.util.logging.Logger.getLogger(GirisFormu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GirisFormu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GirisFormu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GirisFormu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GirisFormu().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel lblKullaniciAdi;
        private javax.swing.JLabel lblKullaniciParola;
        private javax.swing.JTextField txtKullaniciAdi;
        private javax.swing.JTextField txtKullaniciParola;
        private javax.swing.JButton yoneticiGiris;
        // End of variables declaration//GEN-END:variables
}
