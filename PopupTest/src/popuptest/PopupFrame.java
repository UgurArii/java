package popuptest;

import java.awt.Color;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.ActionEvent;
 import javax.swing.JFrame;
 import javax.swing.JRadioButtonMenuItem;
 import javax.swing.JPopupMenu;
 import javax.swing.ButtonGroup;

public class PopupFrame extends javax.swing.JFrame {

    private JRadioButtonMenuItem[] items;
    private final Color[] colorValues = {Color.BLUE,Color.YELLOW,Color.RED};
    private JPopupMenu popupMenu;
    
    public PopupFrame()
    {
        super("Using JPopuMMenus");
        
        ItemHandler handler = new ItemHandler();
        String[] colors = {"Blue","Yellow","Red"};
        
        ButtonGroup colorGroup = new ButtonGroup();
        popupMenu = new JPopupMenu();
        items = new JRadioButtonMenuItem[colors.length];
        
        for(int count =0; count < items.length; count++)
        {
            items[count] = new JRadioButtonMenuItem(colors[count]);
            popupMenu.add(items[count]);
            colorGroup.add(items[count]);
            items[count].addActionListener(handler);
        }
        
        setBackground(Color.WHITE);
        
        addMouseListener(
        new MouseAdapter()
                {
                    public void mousePressed(MouseEvent event)
                    {
                        checkForTriggerEvent(event);
                    }
                    
                    public void mouseReleased(MouseEvent event)
                    {
                        checkForTriggerEvent(event);
                    }
                    
                    private void checkForTriggerEvent(MouseEvent event)
                    {
                        if(event.isPopupTrigger())
                            popupMenu.show(event.getComponent(), event.getX(), event.getY());
                                    
                                    
                    }
                }
        );
        
        
    }
    
    private class ItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            for(int i=0; i < items.length; i++)
            {
                if(event.getSource() == items[i])
                {
                    getContentPane().setBackground(colorValues[i]);
                    return;
                }
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
            java.util.logging.Logger.getLogger(PopupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopupFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
