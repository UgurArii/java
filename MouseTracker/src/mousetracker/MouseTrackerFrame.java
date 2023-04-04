
package mousetracker;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class MouseTrackerFrame extends javax.swing.JFrame {

    private JPanel mousePanel;
    private JLabel statusBar;
    
    
   
    public MouseTrackerFrame() {
    super("Demonstrating Mouse Events");
    
    mousePanel = new JPanel();
    mousePanel.setBackground(Color.WHITE);
    add(mousePanel, BorderLayout.CENTER);
    
    statusBar = new JLabel("Mouse outside JPanel");
    add(statusBar, BorderLayout.SOUTH);
    
    MouseHandler handler = new MouseHandler();
    mousePanel.addMouseListener( handler );
    mousePanel.addMouseMotionListener( handler );
    
    
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener
    {
        public void mouseClicked(MouseEvent event)
        {
            statusBar.setText(String.format("Clicked at [%d][%d]",
                    event.getX(), event.getY()));
            
        }

        @Override
        public void mousePressed(MouseEvent event) {
             statusBar.setText(String.format("Pressed at [%d][%d]",
                    event.getX(), event.getY()));
        }

        @Override
        public void mouseReleased(MouseEvent event) {
         statusBar.setText(String.format("Released at [%d][%d]",
                    event.getX(), event.getY()));
        }

        @Override
        public void mouseEntered(MouseEvent event) {
            statusBar.setText(String.format("Mouse entered at [%d][%d]",
                    event.getX(), event.getY()));
            mousePanel.setBackground(Color.GREEN);
        }

        @Override
        public void mouseExited(MouseEvent event) {
           statusBar.setText(String.format("Mouse eexited at [%d][%d]",
                    event.getX(), event.getY()));
            mousePanel.setBackground(Color.WHITE);
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            statusBar.setText(String.format("Dragged at [%d][%d]",
                    event.getX(), event.getY()));
          
        }

        @Override
        public void mouseMoved(MouseEvent event) {
                    statusBar.setText(String.format("Moved at [%d][%d]",
                    event.getX(), event.getY()));
            
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(MouseTrackerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MouseTrackerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MouseTrackerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MouseTrackerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MouseTrackerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
