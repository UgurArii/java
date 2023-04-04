package imagefx2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorAdjustBuilder;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class ImageFX2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("file:C:\\Users\\HP\\Documents\\NetBeansProjects\\imageFX2\\src\\images\\butterfly.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        applyEffects(imageView);
        imageView.setTranslateX(-50);
imageView.setTranslateY(-50);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void applyEffects(ImageView imageView) {
//      Bloom bloom = new Bloom();
//bloom.setThreshold(.5);
//imageView.setEffect(bloom);
//ColorAdjust colorAdjust = new ColorAdjust();
//colorAdjust.setBrightness(.5);
//colorAdjust.setContrast(.5);
//colorAdjust.setHue(.5);
//colorAdjust.setSaturation(.5);

//GaussianBlur blur = new GaussianBlur();
//imageView.setEffect(blur);
//        Glow glow = new Glow();
//glow.setLevel(.5);
//imageView.setEffect(glow);
        
//        DropShadow dropShadow = new DropShadow();
//dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
//dropShadow.setOffsetX(10);
//dropShadow.setOffsetY(10);
//dropShadow.setRadius(10);
//dropShadow.setSpread(.2);
//imageView.setEffect(dropShadow);
//
//Lighting lighting = new Lighting();
//Light.Spot spot = new Light.Spot(150,50,50,1,Color.WHITE);
//spot.setPointsAtX(400);
//spot.setPointsAtY(400);
//spot.setPointsAtZ(-10);
//lighting.setLight(spot);
//imageView.setEffect(lighting);
//
//SepiaTone sepia = new SepiaTone();
//sepia.setLevel(1);
//imageView.setEffect(sepia);

PerspectiveTransform transform = new PerspectiveTransform();
transform.setLlx(100);
transform.setLly(350);
transform.setLrx(400);
transform.setLry(550);
transform.setUlx(100);
transform.setUly(100);
transform.setUrx(400);
transform.setUry(100);
imageView.setEffect(transform);
    }
    
}
