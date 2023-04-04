
package javafxapplication18;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *
 * @author uğur
 */


public class JavaFXApplication18 extends Application {
    
    Stage stage;
    TextField txtName;
    TextField txtPhone;
    TextField txtAddress;
    RadioButton rdoSmall;
    RadioButton rdoMedium;
    RadioButton rdoLarge;
    RadioButton rdoThin;
    RadioButton rdoThick;
    CheckBox chkPepperoni;
    CheckBox chkMushrooms;
    CheckBox chkAnchovies;
    @Override
    public void start(Stage primaryStage) {
       stage  = primaryStage;
       
       Label lblName = new Label("Name:");
       txtName = new TextField();
       txtName.setMinWidth(100);
       txtName.setPrefWidth(200);
       txtName.setMaxWidth(300);
       txtName.setPromptText("Enter the name here");
       
       Label lblPhone = new Label("Phone Number:");
       txtPhone = new TextField();
       txtPhone.setMinWidth(60);
       txtPhone.setPrefWidth(120);
       txtPhone.setMaxWidth(180);
       txtPhone.setPromptText("Enter the phone number here");
       
       Label lblAddress = new Label("Address:");
       txtAddress = new TextField();
       txtAddress.setMinWidth(100);
       txtAddress.setPrefWidth(200);
       txtAddress.setMaxWidth(300);
       txtAddress.setPromptText("Enter the address here");
       
       Label lblSize = new Label("Size");
       rdoSmall = new RadioButton("Small");
       rdoMedium = new RadioButton("Medium");
       rdoLarge = new RadioButton("Large");
       rdoMedium.setSelected(true);
       ToggleGroup groupSize = new ToggleGroup();
       rdoSmall.setToggleGroup(groupSize);
       rdoMedium.setToggleGroup(groupSize);
       rdoLarge.setToggleGroup(groupSize);
       VBox paneSize = new VBox(lblSize, rdoSmall, rdoMedium, rdoLarge);
       paneSize.setSpacing(10);
       
       Label lblCrust = new Label("Crust");
       rdoThin = new RadioButton("Thin");
       rdoThick = new RadioButton("Thick");
       rdoThin.setSelected(true);
       ToggleGroup groupCrust = new ToggleGroup();
       rdoThin.setToggleGroup(groupCrust);
       rdoThick.setToggleGroup(groupCrust);
       VBox paneCrust = new VBox(lblCrust, rdoThin, rdoThick);
       paneCrust.setSpacing(10);
       
       Label lblToppings = new Label("Toppings");
       chkPepperoni = new CheckBox("Pepperoni");
       chkMushrooms = new CheckBox("Mushrooms");
       chkAnchovies = new CheckBox("Anchovies");
       VBox paneToppings = new VBox(lblToppings, chkPepperoni,
       chkMushrooms, chkAnchovies);
       paneToppings.setSpacing(10);
       
       Button btnOK  = new Button("OK");
       btnOK.setPrefWidth(80);
       btnOK.setOnAction(e -> btnOK_Click());
       
       Button btnCancel = new Button("Cancel");
       btnCancel.setPrefWidth(80);
       btnCancel.setOnAction(e -> btnCancel_Click() );
       
       HBox paneButtons = new HBox(10, btnOK, btnCancel);
       
       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10));
       grid.setHgap(10);
       grid.setVgap(10);
       grid.setMinWidth(500);
       grid.setPrefWidth(500);
       grid.setMaxWidth(800);
       
       grid.addRow(0, lblName, txtName);
       grid.addRow(1, lblPhone, txtPhone);
       grid.addRow(2, lblAddress, txtAddress);
       grid.addRow(3, paneSize, paneCrust, paneToppings);
       grid.add(paneButtons,2,4);
       
       grid.setHalignment(lblName, HPos.RIGHT);
       grid.setHalignment(lblPhone, HPos.RIGHT);
       grid.setHalignment(lblAddress, HPos.RIGHT);
       grid.setColumnSpan(txtName,2);
       grid.setColumnSpan(txtPhone,2);
       grid.setColumnSpan(txtAddress,2);
       
       ColumnConstraints col1 = new ColumnConstraints();
       col1.setPercentWidth(33);
       ColumnConstraints col2 = new ColumnConstraints();
       col2.setPercentWidth(33);
       ColumnConstraints col3 = new ColumnConstraints();
       col3.setPercentWidth(33);
       grid.getColumnConstraints().addAll(col1, col2, col3);
       
       Scene scene = new Scene(grid);
       primaryStage.setScene(scene);
       primaryStage.setTitle("Pizza Order");
       primaryStage.setMinWidth(500);
       primaryStage.setMaxWidth(900);
       primaryStage.show();
       
       
    }

    public void btnOK_Click(){
        {
// Create a message string with the customer information
       String msg = "Customer:\n\n";
       msg += "\t" + txtName.getText() + "\n";
       msg += "\t" + txtPhone.getText() + "\n\n";
       msg += "\t" + txtAddress.getText() + "\n";
       msg += "You have ordered a ";
// Add the pizza size
       if (rdoSmall.isSelected())
       msg += "small ";
       if (rdoMedium.isSelected())
       msg += "medium ";
       if (rdoLarge.isSelected())
       msg += "large ";
        if(rdoThin.isSelected()) msg += "thin crust pizza with ";
        if (rdoThick.isSelected())
        msg += "thick crust pizza with ";
        String toppings = " ";
        toppings = buildToppings(chkPepperoni, toppings);
        toppings = buildToppings(chkMushrooms, toppings);
        toppings = buildToppings(chkAnchovies, toppings);
        
        if (toppings.equals(" "))
        msg += "no toppings.";
        else
        msg += "the following toppings:\n"+ toppings;
// Display the message
            System.out.println(msg);
    }       
    }
        
        public String buildToppings(CheckBox chk, String msg) 
{
// Helper method for displaying the list of toppings
if (chk.isSelected())
{
if (!msg.equals(""))
{
msg += ", ";
}
msg += chk.getText();
}
return msg;
}
        public void btnCancel_Click()
{
stage.close();
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
