
package shapecombiner;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.*;

/**
 *
 * @author uğur
 */


public class ShapeCombiner extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Group group1 = new Group();
       
       Circle circle1 = new Circle(110,110,100);
       Circle circle2 = new Circle(210,110,100);
       Circle circle3 = new Circle(440, 110, 100);
       Circle circle4 = new Circle(540,110,100);
       Circle circle5 = new Circle(110,340,100);
       Circle circle6 = new Circle(210,340,100);
       Circle circle7 = new Circle(440,340,100);
       Circle circle8 = new Circle(540,340,100);
       
       Shape union = Shape.union(circle3, circle4);
       Shape intersect = Shape.intersect(circle5, circle6);
       Shape substract = Shape.subtract(circle7, circle8);
       
       circle1.setFill(null);
       circle1.setStroke(Color.BLACK);
       
       circle2.setFill(null);
       circle2.setStroke(Color.BLACK);
       
       union.setStroke(Color.BLACK);
       union.setFill(Color.LIGHTGRAY);
       
       intersect.setStroke(Color.GREEN);
       intersect.setFill(Color.LIGHTBLUE);

      substract.setStroke(Color.GOLD);
      substract.setFill(Color.LIGHTCYAN);
      
      group1.getChildren().addAll(circle1,circle2, union, intersect, substract);
      Scene scene = new Scene(group1);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Shape Combiner");
      primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
