/*
 * JavaGUI_009_Klasor_Dosya_İslemleriApp.java
 */

package javaguı_009_klasor_dosya_islemleri;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class JavaGUI_009_Klasor_Dosya_İslemleriApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new JavaGUI_009_Klasor_Dosya_İslemleriView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of JavaGUI_009_Klasor_Dosya_İslemleriApp
     */
    public static JavaGUI_009_Klasor_Dosya_İslemleriApp getApplication() {
        return Application.getInstance(JavaGUI_009_Klasor_Dosya_İslemleriApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(JavaGUI_009_Klasor_Dosya_İslemleriApp.class, args);
    }
}
