
package canvasfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CanvasFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
     primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawCircles(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    private void drawCircles(GraphicsContext gc) {
gc.setFill(Color.RED);
gc.setStroke(Color.BLACK);
gc.setLineWidth(2);
gc.fillOval(50, 50, 300, 300);
gc.strokeOval(50, 50, 300, 300);
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
