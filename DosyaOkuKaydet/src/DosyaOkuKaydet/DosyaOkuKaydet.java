
package DosyaOkuKaydet;

import static java.awt.SystemColor.text;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

public class DosyaOkuKaydet extends javax.swing.JFrame {

	
	public DosyaOkuKaydet() {
		initComponents();
	}

	
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                text = new javax.swing.JTextArea();
                menuBar = new javax.swing.JMenuBar();
                fileMenu = new javax.swing.JMenu();
                openMenuItem = new javax.swing.JMenuItem();
                saveMenuItem = new javax.swing.JMenuItem();
                saveAsMenuItem = new javax.swing.JMenuItem();
                exitMenuItem = new javax.swing.JMenuItem();
                editMenu = new javax.swing.JMenu();
                cutMenuItem = new javax.swing.JMenuItem();
                copyMenuItem = new javax.swing.JMenuItem();
                pasteMenuItem = new javax.swing.JMenuItem();
                deleteMenuItem = new javax.swing.JMenuItem();
                helpMenu = new javax.swing.JMenu();
                contentsMenuItem = new javax.swing.JMenuItem();
                aboutMenuItem = new javax.swing.JMenuItem();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jButton1.setText("Dosya Oku");
                jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jButton1MouseClicked(evt);
                        }
                });

                jButton2.setText("Dosyaya Yaz");
                jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jButton2MouseClicked(evt);
                        }
                });

                text.setColumns(20);
                text.setRows(5);
                jScrollPane1.setViewportView(text);

                fileMenu.setMnemonic('f');
                fileMenu.setText("File");

                openMenuItem.setMnemonic('o');
                openMenuItem.setText("Open");
                fileMenu.add(openMenuItem);

                saveMenuItem.setMnemonic('s');
                saveMenuItem.setText("Save");
                fileMenu.add(saveMenuItem);

                saveAsMenuItem.setMnemonic('a');
                saveAsMenuItem.setText("Save As ...");
                saveAsMenuItem.setDisplayedMnemonicIndex(5);
                fileMenu.add(saveAsMenuItem);

                exitMenuItem.setMnemonic('x');
                exitMenuItem.setText("Exit");
                exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                exitMenuItemActionPerformed(evt);
                        }
                });
                fileMenu.add(exitMenuItem);

                menuBar.add(fileMenu);

                editMenu.setMnemonic('e');
                editMenu.setText("Edit");

                cutMenuItem.setMnemonic('t');
                cutMenuItem.setText("Cut");
                editMenu.add(cutMenuItem);

                copyMenuItem.setMnemonic('y');
                copyMenuItem.setText("Copy");
                editMenu.add(copyMenuItem);

                pasteMenuItem.setMnemonic('p');
                pasteMenuItem.setText("Paste");
                editMenu.add(pasteMenuItem);

                deleteMenuItem.setMnemonic('d');
                deleteMenuItem.setText("Delete");
                editMenu.add(deleteMenuItem);

                menuBar.add(editMenu);

                helpMenu.setMnemonic('h');
                helpMenu.setText("Help");

                contentsMenuItem.setMnemonic('c');
                contentsMenuItem.setText("Contents");
                helpMenu.add(contentsMenuItem);

                aboutMenuItem.setMnemonic('a');
                aboutMenuItem.setText("About");
                helpMenu.add(aboutMenuItem);

                menuBar.add(helpMenu);

                setJMenuBar(menuBar);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(137, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(26, 26, 26)
                                                .addComponent(jButton2)
                                                .addGap(191, 191, 191))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
	    System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    
        private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
                JFileChooser openDialog = new JFileChooser();
		openDialog.showOpenDialog(menuBar);
		openDialog.getSelectedFile();
		File dosya = new File("E:\\dosya.txt");
		try {
			FileReader oku = new FileReader(dosya);
		        BufferedReader bf = new BufferedReader(oku);
		        String veri = bf.readLine()+"\n";
			while(bf.ready()){
				veri += bf.readLine();
				
			}
			text.setText(veri);
			
			bf.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(DosyaOkuKaydet.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException e){
			
		}
		
		
        }//GEN-LAST:event_jButton1MouseClicked

        private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
                JFileChooser saveDialog = new JFileChooser();
		
		
		File dosya = saveDialog.getSelectedFile();
		try {
			FileWriter yaz = new FileWriter(dosya);
	                BufferedWriter bw = new BufferedWriter(yaz);
			String veri = text.getText();
			bw.write(veri);
			bw.close();
		} catch (IOException ex) {
			Logger.getLogger(DosyaOkuKaydet.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        }//GEN-LAST:event_jButton2MouseClicked

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
			java.util.logging.Logger.getLogger(DosyaOkuKaydet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DosyaOkuKaydet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DosyaOkuKaydet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DosyaOkuKaydet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DosyaOkuKaydet().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JMenuItem aboutMenuItem;
        private javax.swing.JMenuItem contentsMenuItem;
        private javax.swing.JMenuItem copyMenuItem;
        private javax.swing.JMenuItem cutMenuItem;
        private javax.swing.JMenuItem deleteMenuItem;
        private javax.swing.JMenu editMenu;
        private javax.swing.JMenuItem exitMenuItem;
        private javax.swing.JMenu fileMenu;
        private javax.swing.JMenu helpMenu;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JMenuBar menuBar;
        private javax.swing.JMenuItem openMenuItem;
        private javax.swing.JMenuItem pasteMenuItem;
        private javax.swing.JMenuItem saveAsMenuItem;
        private javax.swing.JMenuItem saveMenuItem;
        private javax.swing.JTextArea text;
        // End of variables declaration//GEN-END:variables

}
