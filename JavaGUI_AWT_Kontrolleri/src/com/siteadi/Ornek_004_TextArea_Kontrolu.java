/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siteadi;

/**
 *
 * @author uğur
 */
public class Ornek_004_TextArea_Kontrolu extends javax.swing.JFrame {

	/**
	 * Creates new form Ornek_004_TextArea_Kontrolu
	 */
	public Ornek_004_TextArea_Kontrolu() {
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

                jScrollPane1 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();
                textArea1 = new java.awt.TextArea();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jTextArea1.setBackground(new java.awt.Color(0, 204, 255));
                jTextArea1.setColumns(20);
                jTextArea1.setRows(5);
                jTextArea1.setText("Gele dilber gel Allahı seversen\nGel ağlatma beni eller içinde\nNe acayip olur şu halk-ı alem\nSöyleşirler bizi diller içinde\n\nBunca zaman hasretinden gülmedim\nBöyle zalim olacağın bilmedim\nÇok yerleri gezdim amma görmedim\nBencileyin geda kullar içinde\n\nBedir olur doğar artık dulunmaz\nAkar çeşmim yaşı bir dem silinmez\nUmarım ki şunda asla bulunmaz\nSencileyin gonce güller içinde\n\nMustafa söyler sözünü saz ile\nSüregür devranı şevkce şaz ile\nKırmızılar giy de salın naz ile\nKo ben görüneyim çullar içinde");
                jScrollPane1.setViewportView(jTextArea1);

                textArea1.setBackground(new java.awt.Color(0, 204, 204));
                textArea1.setText("Gele dilber gel Allahı seversen\nGel ağlatma beni eller içinde\nNe acayip olur şu halk-ı alem\nSöyleşirler bizi diller içinde\n\nBunca zaman hasretinden gülmedim\nBöyle zalim olacağın bilmedim\nÇok yerleri gezdim amma görmedim\nBencileyin geda kullar içinde\n\nBedir olur doğar artık dulunmaz\nAkar çeşmim yaşı bir dem silinmez\nUmarım ki şunda asla bulunmaz\nSencileyin gonce güller içinde\n\nMustafa söyler sözünü saz ile\nSüregür devranı şevkce şaz ile\nKırmızılar giy de salın naz ile\nKo ben görüneyim çullar içinde");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(69, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                                .addContainerGap(166, Short.MAX_VALUE))
                );

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
			java.util.logging.Logger.getLogger(Ornek_004_TextArea_Kontrolu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Ornek_004_TextArea_Kontrolu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Ornek_004_TextArea_Kontrolu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Ornek_004_TextArea_Kontrolu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Ornek_004_TextArea_Kontrolu().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextArea1;
        private java.awt.TextArea textArea1;
        // End of variables declaration//GEN-END:variables
}