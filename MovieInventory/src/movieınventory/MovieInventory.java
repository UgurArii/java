
package movieınventory;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.scene.control.cell.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.util.converter.*;

/**
 *
 * @author uğur
 */


public class MovieInventory extends Application {
    
    private TableView<Movie> table;
    private TextField txtTitle, txtYear, txtPrice;
    @Override public void start(Stage primaryStage) {
    
    Label lblHeading = new Label("Movie Inventory");
    
    table = new TableView<Movie>();
    table.setEditable(true);
    table.setItems(loadData());
    
    TableColumn colTitle = new TableColumn("Title");
    colTitle.setMinWidth(300);
    colTitle.setCellValueFactory(
    new PropertyValueFactory<Movie, String>("Title"));
    colTitle.setCellFactory(
    TextFieldTableCell.forTableColumn());
    colTitle.setOnEditCommit( e -> colTitle_OnEditCommit(e) );
    
    TableColumn colYear = new TableColumn("Year");
    colYear.setMinWidth(100);
    colYear.setCellValueFactory(
    new PropertyValueFactory<Movie, Integer>("Year"));
    colYear.setCellFactory(
    TextFieldTableCell.forTableColumn(
    new IntegerStringConverter()));
    colYear.setOnEditCommit( e -> colYear_OnEditCommit(e) );
        
    TableColumn colPrice = new TableColumn("Price");
    colPrice.setMinWidth(100);
    colPrice.setCellValueFactory(
    new PropertyValueFactory<Movie, Double>("Price"));
    colPrice.setCellFactory(
    TextFieldTableCell.forTableColumn(
    new DoubleStringConverter()));
    colPrice.setOnEditCommit( e -> colPrice_OnEditCommit(e) );
   
    table.getColumns().addAll(colTitle, colYear, colPrice);
    
    txtTitle = new TextField();
    txtTitle.setPromptText("Title");
    txtTitle.setMinWidth(100);
    
    txtYear = new TextField();
    txtYear.setMaxWidth(100);
    txtYear.setPromptText("Year");
    
    txtPrice = new TextField();
    txtPrice.setMaxWidth(100);
    txtPrice.setPromptText("Price");
    

    
    Button btnAdd = new Button("Add");
    btnAdd.setMinWidth(100);
    btnAdd.setOnAction(e -> btnAdd_Clicked());
    
    Button btnDelete = new Button("Delete");
    btnDelete.setMinWidth(60);
    btnDelete.setOnAction(e -> btnDelete_Clicked());
    
    HBox paneAdd = new HBox();
    paneAdd.setSpacing(8);
    paneAdd.getChildren().addAll(txtTitle, txtYear, txtPrice,btnAdd, btnDelete);
    
    VBox paneMain = new VBox();
    paneMain.setSpacing(10);
    paneMain.setPadding(new Insets(10, 10, 10,10));
     paneMain.getChildren().addAll(lblHeading, table, paneAdd);
    
    Scene scene = new Scene(paneMain);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Movie Inventory");
    primaryStage.show();
    }
 
    public ObservableList<Movie> loadData(){
        ObservableList<Movie> data = FXCollections.observableArrayList();
        data.add(new Movie("It is a Wonderful Life", 1946, 14.95)); 
        data.add(new Movie("Young Frankestion", 1974, 16.95)); 
        data.add(new Movie("Star Wars Episode 4",1976, 17.95));
        data.add(new Movie("The Princess Bride",1987, 16.95));
        data.add(new Movie("Glory",1989, 14.95));
        data.add(new Movie("The Game",1997, 14.95));
        data.add(new Movie("Shakespeare in Love",1998, 19.95));
        data.add(new Movie("The Invention of Lying",2009, 18.95));
        data.add(new Movie("The King's Speech",2010, 19.95));
        
        return data;
    }
    
    public void colTitle_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<Movie, String> ce;
        ce = (TableColumn.CellEditEvent<Movie, String>) e;
        Movie m = ce.getRowValue();
        m.setTitle(ce.getNewValue());
    }
    public void colYear_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<Movie, Integer> ce;
        ce = (TableColumn.CellEditEvent<Movie, Integer>) e;
        Movie m = ce.getRowValue();
        m.setYear(ce.getNewValue());
    }
    
    public void colPrice_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<Movie, Double> ce;
        ce = (TableColumn.CellEditEvent<Movie, Double>) e;
        Movie m = ce.getRowValue();
        m.setPrice(ce.getNewValue());
        
    }
    
    public void btnAdd_Clicked()
    {
        Movie m = new Movie();
        m.setTitle(txtTitle.getText());
        m.setYear(Integer.parseInt(txtYear.getText()));
        m.setPrice(Double.parseDouble(txtPrice.getText()));
        table.getItems().add(m);
        txtTitle.clear();
        txtYear.clear();
        txtPrice.clear();
    }
    public void btnDelete_Clicked()
    {
        ObservableList<Movie> sel, items;
        items = table.getItems();
        sel = table.getSelectionModel().getSelectedItems();
        for (Movie m : sel)
        items.remove(m);

}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
