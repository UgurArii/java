import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JOptionPane;



public class AdditionApplet extends JApplet {

    private double sum;
    
    public void init(){
        String firstNumber = JOptionPane.showInputDialog("Enter first floating-point value");
        String secondNumber = JOptionPane.showInputDialog("EEnter second floating-point value");
        
        double number1 = Double.parseDouble(firstNumber);
        double number2 = Double.parseDouble(secondNumber);
        
        sum = number1 + number2;
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawRect(15,10,270,20);
        
        g.drawString("The sum is " + sum, 25, 25);
    }
}
