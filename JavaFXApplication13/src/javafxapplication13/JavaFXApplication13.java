
package javafxapplication13;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *
 * @author uğur
 */



public class JavaFXApplication13 extends Application {
    
TextField txtCharacter;
TextField txtActor;
    private Object lblCharacter;
    @Override
    public void start(Stage primaryStage) {
        Label lblCharacter = new Label("Character's Name:");
        lblCharacter.setMinWidth(100);
        lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);
        
        txtCharacter = new TextField();               
        txtCharacter.setMinWidth(200);
        txtCharacter.setMaxWidth(200);
        txtCharacter.setPromptText(
        "Enter the name of the character here.");
        
        Label lblActor = new Label("Actor's Name:");
        lblActor.setMinWidth(100);
        lblActor.setAlignment(Pos.BOTTOM_RIGHT);
        
        txtActor = new TextField();
        txtActor.setMinWidth(200);
        txtActor.setMaxWidth(200);
        txtActor.setPromptText("Enter the name of the actor here.");
        
        Button btnOK = new Button("OK");
        btnOK.setMinWidth(75);
        btnOK.setOnAction(e -> btnOK_Click() );
        
        HBox paneCharacter = new HBox(20, lblCharacter, txtCharacter);
        paneCharacter.setPadding(new Insets(10));
        
        HBox paneActor = new HBox(20, lblActor, txtActor);
        paneActor.setPadding(new Insets(10));
        
        HBox paneButton = new HBox(20, btnOK);
        paneButton.setPadding(new Insets(10));
        paneButton.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox pane = new VBox(10, paneCharacter, paneActor, paneButton);
        
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Role Player");
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void btnOK_Click() {
        String errorMessage = "";
        
        if (txtCharacter.getText().length() == 0)
{
errorMessage += "\nCharacter is a required field.";
}
        if (txtActor.getText().length() == 0)
{
errorMessage += "\nActor is a required field.";
}

if (errorMessage.length() == 0)
{
String message = "The role of "
+ txtCharacter.getText()
+ " will be played by "
+ txtActor.getText()
+ ".";

}
else
{
    
}
    }
    
}
