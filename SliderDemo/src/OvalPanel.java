
import java.awt.Dimension;
import java.awt.Graphics;


public class OvalPanel extends javax.swing.JPanel {

   private int diameter = 10;
   
   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       g.fillOval(10,10,diameter,diameter);
   }
   
   public void setDiameter(int newDiameter)
   {
       diameter = (newDiameter >= 0 ? newDiameter:10);
       repaint();
   }
   
   public Dimension getPreferredSize()
   {
       return new Dimension(200,200);
   }
   
   public Dimension getMinimumSize()
   {
       return getPreferredSize();
   }

   
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
