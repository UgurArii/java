
package basicanimation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class BasicAnimation extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    ImageView imageView = new ImageView();
    Image ball = new Image("file:C:\\Users\\HP\\Documents\\NetBeansProjects\\basicAnimation\\src\\images\\barca.png");
    imageView.setImage(ball);
    
    primaryStage.setHeight(480);
    primaryStage.setWidth(600);
    
        Group group = new Group();
        Scene scene = new Scene(group);
        group.getChildren().add(imageView);
            
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Path path = new Path();
        ArcTo arc1 = new ArcTo();
        ArcTo arc2 = new ArcTo();
        
        arc1.setX(350);
        arc1.setY(350);
        arc1.setRadiusX(150);
        arc1.setRadiusY(300);
        
        arc2.setX(150);
        arc2.setY(150);
        arc2.setRadiusX(150);
        arc2.setRadiusY(300);
        
        path.getElements().add(new MoveTo(150f, 150f));
        path.getElements().add(arc1);
        path.getElements().add(arc2);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5));
        pathTransition.setNode(group);
        pathTransition.setPath(path);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.play();
        
//        Timeline timeLine = new Timeline();
//        
//        KeyValue keyValue = new KeyValue(imageView.translateYProperty(),370);
//        KeyFrame frame = new KeyFrame(Duration.seconds(2),keyValue);
//        timeLine.getKeyFrames().add(frame);
//        timeLine.play();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
