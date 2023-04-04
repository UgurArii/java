
package imagefx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImageFx extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Image butterfly = new Image("");
        ImageView imageView = new ImageView();
        imageView.setImage(butterfly);
        imageView.setTranslateX(30);
        imageView.setTranslateY(30);
        imageView.setRotate(45);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        applyEffect(imageView);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        
     //   primaryStage.setScene(new Scene(root,300,250));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void applyEffect(ImageView imageView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
