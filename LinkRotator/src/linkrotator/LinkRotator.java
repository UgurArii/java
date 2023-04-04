package linkrotator;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LinkRotator extends JFrame implements Runnable, ActionListener{

    String[] pageTitle = new String[6];
    URI[] pageLink = new URI[6];
    int current = 0;
    Thread runner;
    JLabel siteLabel = new JLabel();
    
    
    
    public LinkRotator(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        FlowLayout flo = new FlowLayout();
        setLayout(flo);
        pageTitle = new String[]{
            "Oracle's Java site",
            "Server Side",
            "JavaWorld",
            "Java in 24 Hours",
            "Sams Publishing",
            "Workbench"
        };
        pageLink[0] = getURI("http://www.oracle.com/technetwork/java");
        pageLink[1] = getURI("http://www.theserverside.com");
        pageLink[2] = getURI("http://www.javaworld.com");
        pageLink[3] = getURI("http://www.java24hours.com");
        pageLink[4] = getURI("http://www.samspublishing.com");
        pageLink[5] = getURI("http://workbench.cadenhead.org");
    }
    
    private URI getURI(String urlText)
    {
        URI pageURI = null;
        try{
            pageURI = new URI(urlText);
        }catch(URISyntaxException ex)
        {
            
        }
        return pageURI;
    }
    
    public void start(){
        if(runner == null)
        {
            runner = new Thread(this);
            runner.start();
        }
    }
    
    public void run(){
        Thread thisThread = Thread.currentThread();
        while(runner == thisThread)
        {
            current++;
            if(current>5)
            {
                current = 0;
                        
            }
            siteLabel.setText(pageTitle[current]);
            repaint();
            try{
                Thread.sleep(2000);
            }catch(InterruptedException exc){
                
            }
        }
    }
    
    public void actionPerformed(ActionEvent event)
    {
        Desktop desktop = Desktop.getDesktop();
        if(pageLink[current] != null)
        {
            try{
                desktop.browse(pageLink[current]);
                runner = null;
                System.exit(0);
            }catch(IOException exc){
                
            }
        }
    }
    
    public static void main(String[] args) {
        new LinkRotator();
    }
    
}
