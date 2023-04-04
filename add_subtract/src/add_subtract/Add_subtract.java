
package add_subtract;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author uğur
 */


public class Add_subtract extends Application  implements EventHandler<ActionEvent> {
    
     Button btnAdd;
      Button btnSubtrack;
    Label lbl;
    int iCounter = 0;
    @Override
    public void start(Stage primaryStage) {
       btnAdd = new Button();
        btnAdd.setText("Add");
        btnAdd.setOnAction((EventHandler<ActionEvent>) this);
        
         btnSubtrack = new Button();
        btnSubtrack.setText("Subtrack");
        btnSubtrack.setOnAction((EventHandler<ActionEvent>) this);
            
            lbl = new Label();
            lbl.setText(Integer.toString(iCounter));
            
            HBox pane = new HBox(10);
            pane.getChildren().addAll(lbl, btnAdd, btnSubtrack);
            
  
        
        Scene scene = new Scene(pane, 300, 250);
        
        primaryStage.setTitle("Add/Sub");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void handle(ActionEvent event) {
       if(event.getSource()==btnAdd){
           iCounter++;
       }else{
             if(event.getSource()==btnSubtrack){
           iCounter--;
       }
       }
       lbl.setText(Integer.toString(iCounter));
    }
    
}
