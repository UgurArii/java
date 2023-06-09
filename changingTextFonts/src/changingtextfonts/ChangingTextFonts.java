
package changingtextfonts;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChangingTextFonts extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 1-4 Changing Text Fonts");
        Group root = new Group();
        Scene scene = new Scene(root, 950, 450, Color.WHITE);
        scene.getStylesheets().add("default.css");
        //serif with drop shadow
        Text text2 = new Text(50, 50, "JavaFX 2.0: Intro. by Example");
        Font serif = Font.font("Serif", 30);
        text2.setFont(serif);
        text2.setFill(Color.RED);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(2.0f);
        dropShadow.setColor(Color.rgb(50, 50, 50, .588));
        text2.setEffect(dropShadow);
        root.getChildren().add(text2);
        
        //SanSerif
        Text text3 = new Text(50, 100, "JavaFX 2.0: Intro. by Example");
        Font sansSerif = Font.font("SanSerif", 30);
        text3.setFont(sansSerif);
        text3.setFill(Color.BLUE);
        root.getChildren().add(text3);
        
        // Dialog
        Text text4 = new Text(50, 150, "JavaFX 2.0: Intro. by Example");
        Font dialogFont = Font.font("Dialog", 30);
        text4.setFont(dialogFont);
        text4.setFill(Color.rgb(0, 255, 0));
        root.getChildren().add(text4);
        
        // Monospaced
        Text text5 = new Text(50, 200, "JavaFX 2.0: Intro. by Example");
        Font monoFont = Font.font("Monospaced", 30);
        text5.setFont(monoFont);
        text5.setFill(Color.BLACK);
        root.getChildren().add(text5);
        
        Reflection refl = new Reflection();
        refl.setFraction(0.8f);
        text5.setEffect(refl);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
