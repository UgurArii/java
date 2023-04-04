 package autorectangle;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
/**
 *
 * @author uğur
 */


public class AutoRectangle extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      Rectangle r = new Rectangle(100,100);
      r.setFill(Color.LIGHTGRAY);
      r.setStroke(Color.BLACK);
      r.setStrokeWidth(2);
      
      StackPane p = new StackPane();
      p.setMinHeight(200);
      p.setMinWidth(200);
      p.setPrefWidth(200);
      p.setMaxHeight(200);
      p.setPrefHeight(200);
      p.setMaxHeight(200);
      
      p.getChildren().add(r);
      
      p.widthProperty().addListener( 
      (observable, oldvalue, newvalue) ->
      r.setWidth((Double)newvalue/2)
      );
      
      p.heightProperty().addListener( 
      (observable, oldvalue, newvalue) ->
      r.setHeight((Double)newvalue/2)
      );
      
      Scene scene = new Scene(p);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Auto Rectangle");
      primaryStage.show();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
