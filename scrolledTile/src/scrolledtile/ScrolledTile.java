
package scrolledtile;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.geometry.*;

/**
 *
 * @author uğur
 */


public class ScrolledTile extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       TilePane tile1 = new TilePane();
       tile1.setHgap(10);
       tile1.setVgap(10);
       
       tile1.setPrefColumns(2);
       tile1.setPadding(new Insets(10,10,10,10));
       
       for(int i=1;i<13;i++){
           Rectangle r = new Rectangle(100,100);
           r.setFill(Color.LIGHTGREEN);
           Label l = new Label("A-"+i);
           StackPane s = new StackPane(r,l);
           tile1.getChildren().add(s);
       }
       
       ScrollPane spane = new ScrollPane(tile1);
       spane.setMinWidth(250);
       spane.setPrefWidth(250);
       spane.setMaxWidth(250);
       spane.setPrefHeight(400);
       spane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
       
       StackPane stack = new StackPane(spane);
       stack.setMargin(spane, new Insets(10,10,10,10));
       
       Scene scene = new Scene(stack);
       primaryStage.setScene(scene);
       primaryStage.setTitle("Scrolled Tiles");
       primaryStage.show();
       
       
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
