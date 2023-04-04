package linesrectsovals;

import java.awt.Color;
import java.awt.Graphics;



public class LinesRectsOvalsJPanel extends javax.swing.JPanel {

   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       this.setBackground(Color.WHITE);
       
       g.setColor(Color.RED);
       g.drawLine(5, 30, 380, 30);
       
       g.setColor(Color.BLUE);
       g.drawRect(5, 40, 90, 55);
       g.fillRect(100, 40, 90, 55);
       
       g.setColor(Color.CYAN);
       g.fillRoundRect( 195, 40, 90, 55, 50, 50 );
       g.drawRoundRect( 290, 40, 90, 55, 20, 20 );
       
       g.setColor(Color.GREEN);
       g.draw3DRect( 5, 100, 90, 55, true );
       g.fill3DRect( 100, 100, 90, 55, false );
       
       g.setColor(Color.MAGENTA);
       g.drawOval( 195, 100, 90, 55 );
       g.fillOval( 290, 100, 90, 55 );
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}