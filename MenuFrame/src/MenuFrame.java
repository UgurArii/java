
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;


public class MenuFrame extends javax.swing.JFrame {

    private final Color[] colorValues = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};
    private JRadioButtonMenuItem[] colorItems;
    private JRadioButtonMenuItem[] fonts;
    private JCheckBoxMenuItem[] styleItems;
    private JLabel displayJLabel;
    private ButtonGroup fontButtonGroup;
    private ButtonGroup colorButtonGroup;
    private int style;
    
    public MenuFrame()
    {
        super("using JMenus");
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        
        JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic('A');
        fileMenu.add(aboutItem);
        
        aboutItem.addActionListener(
        new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(MenuFrame.this, 
                        "This is an example\nof using menus",
                        "About",JOptionPane.PLAIN_MESSAGE);
            }
        }
        );
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        fileMenu.add(exitItem);
        
        exitItem.addActionListener(
            new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        }
        );
        
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(fileMenu);
        
        JMenu formatMenu = new JMenu("Format");
        formatMenu.setMnemonic('r');
        
        String[] colors = {"Black","Blue","Red","Green"};
        
        JMenu colorMenu = new JMenu("Color");
        colorMenu.setMnemonic('C');
        
        colorItems = new JRadioButtonMenuItem[colors.length];
        colorButtonGroup = new ButtonGroup();
        ItemHandler  itemHandler = new ItemHandler();
        
        for(int count = 0; count < colors.length; count++)
        {
            colorItems[count] = new JRadioButtonMenuItem(colors[count]);
            colorMenu.add(colorItems[count]);
            colorButtonGroup.add(colorItems[count]);
            colorItems[count].addActionListener((ActionListener) itemHandler);
        }
        
        fonts[0].setSelected(true);
    //    fontMenu.addSeparator();
        
        String[] styleNames = {"Bold","Italic"};
        styleItems = new JCheckBoxMenuItem[styleNames.length];
        StyleHandler styleHandler = new StyleHandler();
        
        for(int count = 0; count < styleNames.length; count++){
            styleItems[count] = new JCheckBoxMenuItem(styleNames[count]);
      //      fontMenu.add(styleItems[count]);
            styleItems[count].addItemListener((ItemListener) styleHandler);
            
        }
        PopupMenu fontMenu = null;
        
        formatMenu.add(fontMenu);
        bar.add(formatMenu);
        
        displayJLabel = new JLabel("Sample Test",SwingConstants.CENTER);
        displayJLabel.setForeground(colorValues[0]);
        displayJLabel.setFont(new Font("Serif", Font.PLAIN,72));
        
        getContentPane().setBackground(Color.CYAN);
        add(displayJLabel,BorderLayout.CENTER);
        
        
    }

    private class ItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            for(int count = 0; count < colorItems.length;count++)
            {
                if(colorItems[count].isSelected())
                {
                    displayJLabel.setForeground(colorValues[count]);
                    break;
                }
            }
            for ( int count = 0; count < fonts.length; count++ )
            {
                if(event.getSource() == fonts[count])
                {
                    displayJLabel.setFont(new Font(fonts[count].getText(),style,72));
                    
                }
            }
            repaint();
        }
        
        
    }
    
    private class StyleHandler implements ItemListener
    {
        public void itemStateChanged(ItemEvent e)
        {
            String name = displayJLabel.getFont().getName();
            Font font;
            
            if(styleItems[0].isSelected() && styleItems[1].isSelected())
            font = new Font(name, Font.BOLD + Font.ITALIC,72);
            else if ( styleItems[ 0 ].isSelected() )
            font = new Font( name, Font.BOLD, 72 );
            else if ( styleItems[ 1 ].isSelected() )
            font = new Font( name, Font.ITALIC, 72 );
            else
            font = new Font( name, Font.PLAIN, 72 );
            
            displayJLabel.setFont(font);
            repaint();
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
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
