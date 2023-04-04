/*******************************************************************************
 Controller class and logic implementation for checkout.fxml
 ******************************************************************************/
package sample;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.*;
import java.text.*;
import java.io.IOException;
import java.net.URL;

public class checkoutController extends CommonMethods implements Initializable{
    //Inisilisation of javafx objects and variables used.
    @FXML
    private JFXButton closebtn, minimisebtn, checkoutbtn,homebtn,
            moviescreensbtn, logoutbtn, viewscreensbtn, resetbtn;
    @FXML
    private JFXComboBox childcombo, adultcombo, seniorcombo, movieCombo;
    @FXML
    private ImageView movieImage;
    @FXML
    private Rectangle startImage;
    @FXML
    private AnchorPane mainpane, leftpane, toppane, selectPane, moviePane, seatscontent;
    @FXML
    private Label ticketPriceLabel,timelabel,datelabel,screenlabel,errorlabel,startText,userlabel,
            adultpricelabel, childpricelabel, seniorpricelabel;
    @FXML
    private JFXToggleButton viptogglebtn;
    @FXML
    private JFXCheckBox checkbox;

    public static double totalprice = 0.00;
    public static int childtickets = 0,adulttickets = 0,seniortickets = 0;
    public static String selectedmovie="", selectedtime="", selectedscreen="";
    public static boolean ismovieselected = false, isvip=false, seatsselected=false, issuedticket=false;
    private boolean hided=false;
    private double adultprice=8.50, childprice=5.0, seniorprice=7.5, vipextra=2.0;

    /**
     *  Initialise method that gets run whenever the checkout.fxml is loaded.
     *  Needed in order to initialise and set up all the logic for ticket selection.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUserName(userlabel);
        leftpanecolor = leftpane.getStyle().substring(22, 29);
        page = "Add";
        moveWindow(leftpane);
        moveWindow(toppane);
        customiseWindowButtons(closebtn, minimisebtn);
        disableAllFocus(mainpane);
        popNode(selectPane);
        popNode(moviePane);

        Date date = new Date();
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy");
        datelabel.setText("Date:       "+date2.format(date));
        timelabel.setText("Time:       "+selectedtime);
        screenlabel.setText("Screen:   "+selectedscreen);
        setUpLogic();
    }

    /**
     * Method that enables and disables the combo boxes if a movie is selected.
     * It also handles the VIP selection which updates the ticket prices.
     */
    private void setUpLogic(){
        childcombo.getSelectionModel().select(childtickets);
        adultcombo.getSelectionModel().select(adulttickets);
        seniorcombo.getSelectionModel().select(seniortickets);
        ticketPriceLabel.setText("£" + (String.format( "%.2f", totalprice)));

        viptogglebtn.setSelected(isvip);
        if(isvip){
            updatePrices();
            totalprice = (adulttickets * adultprice) +(childtickets * childprice) + (seniortickets * seniorprice);
            adultpricelabel.setText("£"+String.format( "%.2f", adultprice));
            childpricelabel.setText("£"+String.format( "%.2f", childprice));
            seniorpricelabel.setText("£"+String.format( "%.2f", seniorprice));
            checkoutbtn.setText("Select Seats");
            checkoutbtn.setStyle("-fx-background-color:   #EE712B; -fx-background-radius:20");
            checkbox.setVisible(true);
        }
        if(isvip==true && seatsselected==true){
            checkbox.setSelected(true);
            checkoutbtn.setText("Checkout");
            checkoutbtn.setStyle("-fx-background-color:  #73A800; -fx-background-radius:20");
        }
        ObservableList list = FXCollections.observableArrayList(server.getMovieNames());
        movieCombo.setItems(list);
        if(selectedmovie.length()>=1){
            childcombo.setDisable(false);
            adultcombo.setDisable(false);
            seniorcombo.setDisable(false);

            startImage.setVisible(false);
            startText.setVisible(false);
            movieCombo.getSelectionModel().select(list.indexOf(selectedmovie));
        }
        movieImage.setImage(server.getImage(selectedmovie));
    }

    /**
     * Method used when the reset button is pressed. This resets the selections
     * made back to how they were originally.
     * @param event
     */
    public void resetPage(ActionEvent event){
        JFXButton btn=(JFXButton)event.getSource();
        SequentialTransition st=popButton(btn,1.15);
        st.play();

        movieCombo.getSelectionModel().clearSelection();
        startImage.setVisible(true);
        startText.setVisible(true);
        selectedmovie="";
        movieImage.toBack();
        timelabel.setText("Time:       ");
        screenlabel.setText("Screen:   ");

        adultcombo.getSelectionModel().selectFirst();
        childcombo.getSelectionModel().selectFirst();
        seniorcombo.getSelectionModel().selectFirst();
        viptogglebtn.setSelected(false);
        checkbox.setSelected(false);
        checkbox.setVisible(false);
        totalprice = (adulttickets * adultprice) +(childtickets * childprice) + (seniortickets * seniorprice);
        ticketPriceLabel.setText("£" + (String.format( "%.2f", totalprice)));
        checkoutbtn.setText("Checkout");
        checkoutbtn.setStyle("-fx-background-color:  #73A800; -fx-background-radius:20");
        seatsselected=false;
        isvip=false;
        ismovieselected=false;
        adultprice=8.50; childprice=5.0; seniorprice=7.5;
        adultpricelabel.setText("£"+String.format( "%.2f", adultprice));
        childpricelabel.setText("£"+String.format( "%.2f", childprice));
        seniorpricelabel.setText("£"+String.format( "%.2f", seniorprice));
    }

    /**
     * Updates the prices of the tickets when the VIP option is selected.
     */
    private void updatePrices(){
        if(isvip){
            adultprice+=vipextra;
            childprice+=vipextra;
            seniorprice+=vipextra;
        }else {
            adultprice-=vipextra;
            childprice-=vipextra;
            seniorprice-=vipextra;
        }
    }

    //called by vip toggle btn
    public void handleVIP(ActionEvent event){
        if(viptogglebtn.isSelected()){
            isvip=true;
            checkbox.setVisible(true);
            updatePrices();
            totalprice = (adulttickets * adultprice) +(childtickets * childprice) + (seniortickets * seniorprice);
            adultpricelabel.setText("£"+String.format( "%.2f", adultprice));
            childpricelabel.setText("£"+String.format( "%.2f", childprice));
            seniorpricelabel.setText("£"+String.format( "%.2f", seniorprice));

            ticketPriceLabel.setText("£" + (String.format( "%.2f", totalprice)));
            checkoutbtn.setText("Select Seats");
            checkoutbtn.setStyle("-fx-background-color:   #EE712B; -fx-background-radius:20");
        }else {
            checkbox.setVisible(false);
            checkbox.setSelected(false);
            isvip=false;
            updatePrices();
            totalprice = (adulttickets * adultprice) +(childtickets * childprice) + (seniortickets * seniorprice);
            adultpricelabel.setText("£"+String.format( "%.2f", adultprice));
            childpricelabel.setText("£"+String.format( "%.2f", childprice));
            seniorpricelabel.setText("£"+String.format( "%.2f", seniorprice));

            ticketPriceLabel.setText("£" + (String.format( "%.2f", totalprice)));
            checkoutbtn.setText("Checkout");
            checkoutbtn.setStyle("-fx-background-color:  #73A800; -fx-background-radius:20");
            seatsselected=false;
        }
    }

    /**
     * Ensures that the user has selected atleast one seat and selected a movie.
     * It also allows VIP users to select their seat by redirecting them to the seats page.
     * If they are not VIP they are taken to the payment page.
     * @param event
     */
    public void validatePage(ActionEvent event){
        if(!ismovieselected) {
            errorlabel.setText("Error! Select A Movie or Timeslot");
            fadeErrorLabel(false);
            return;
        }else if ((childtickets + adulttickets + seniortickets) > moviesController.slotseatNo) {
            totalprice = 0.00;
            errorlabel.setText("Error! Too Many Tickets Selected. Only "+ moviesController.slotseatNo+" Are Available");
            fadeErrorLabel(false);
            return;
        }else if((adulttickets+childtickets+seniortickets)<=0){
            errorlabel.setText("Error! Select At Least 1 Ticket");
            fadeErrorLabel(false);
            return;
        }
        seatsController.bookings=server.getBookings(selectedmovie,getSelectedMovieSlot(selectedscreen, selectedtime));
        if(ismovieselected && (totalprice >0) && !isvip){
            showPopUp("payment");
        }else if(ismovieselected && (totalprice >0) && isvip && !seatsselected){
            showPopUp("seats");
            if(seatsselected){
                checkoutbtn.setText("Checkout");
                checkoutbtn.setStyle("-fx-background-color:  #73A800; -fx-background-radius:20");
                checkbox.setSelected(true);
            }
        }else if(ismovieselected && (totalprice >0) && isvip && seatsselected){
            showPopUp("payment");
        }
    }

    /**
     * Storing the seats and screen in another format which can be used by other
     * methods.
     */
    public static String getSelectedMovieSlot(String selectedscreen, String selectedtime){
        String slot="";
        switch (selectedscreen){
            case "Screen 1":    slot+="SCREEN1_";
                break;
            case "Screen 2":    slot+="SCREEN2_";
                break;
            case "Screen 3":    slot+="SCREEN3_";
                break;
        }

        switch (selectedtime){
            case "12 To 3":     slot+="SLOT1";
                break;
            case "3 To 6":      slot+="SLOT2";
                break;
            case "6 To 9":      slot+="SLOT3";
                break;
            case "9 To 12":     slot+="SLOT4";
                break;
        }

        return slot;
    }

    /**
     * Displays a pop up for the payment or seats scenes.
     * @param page
     */
    public void showPopUp(String page){
        Stage stage = (Stage) mainpane.getScene().getWindow();
        mainpane.setEffect(new ColorAdjust(0,0,-0.25,0));
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(page+".fxml"));
            Stage popup = new Stage();
            Scene scene=new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            popup.initOwner(stage);
            popup.setScene(scene);
            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
            mainpane.setEffect(new ColorAdjust(0,0,0,0));
            popup.close();
            page = "Add";
        } catch (IOException ex) {
            System.out.println("Error in showing pop up");
            ex.printStackTrace();
        }
    }

    /**
     * When the user selects a movie from dropdown and wants to select the time
     * and screen, the moviscreen button is fired and is redirected to the
     * movies page.
     * @param event
     */
    public void checkTimeslots(ActionEvent event){
        JFXButton btn=(JFXButton)event.getSource();
        SequentialTransition st=popButton(btn,1.15);
        st.play();
        st.setOnFinished(event2 -> {
            if (movieCombo.getSelectionModel().isEmpty()) {
                movieCombo.getSelectionModel().selectFirst();
            }
            homeController.viewmovie = true;
            homeController.viewmoviename = (String) movieCombo.getValue();
            selectedmovie = (String) movieCombo.getValue();
            moviescreensbtn.fire();
        });
    }

    /**
     * Retrives the amount of tickets selected from the combobox and calculates
     * if the amount selected is greater than the amount available. If too many
     * tickets are selected then an error message is displayed.
     * @param e
     */
    public void handleChoice(ActionEvent e) {
        childtickets = Integer.parseInt((String) childcombo.getValue());
        adulttickets = Integer.parseInt((String) adultcombo.getValue());
        seniortickets = Integer.parseInt((String)seniorcombo.getValue());
        totalprice = (adulttickets * adultprice) +(childtickets * childprice) + (seniortickets * seniorprice);

        if (childtickets + adulttickets + seniortickets > moviesController.slotseatNo) {
            totalprice = 0.0;
            errorlabel.setText("Error! Too Many Tickets Selected. Only "+ moviesController.slotseatNo+" Are Available");
            fadeErrorLabel(false);
        }else if((childtickets + adulttickets + seniortickets)<=0){
            ticketPriceLabel.setText("£" + (String.format( "%.2f", totalprice)));
        }else {
            fadeErrorLabel(true);
        }
        ticketPriceLabel.setText("£" + (String.format( "%.2f", totalprice)));

        if(isvip && seatsselected){
            seatsselected=false;
            checkbox.setSelected(false);
            checkoutbtn.setText("Select Seats");
            checkoutbtn.setStyle("-fx-background-color:   #EE712B; -fx-background-radius:20");
        }
    }

    /**
     * Animation used for the error label.
     * @param hide
     */
    private  void fadeErrorLabel(boolean hide){
        FadeTransition ft=new FadeTransition(Duration.millis(500),errorlabel);
        ft.setCycleCount(1);
        if(hide==true && hided==false){
            hided=true;
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            ft.setOnFinished(event ->  {
                errorlabel.setVisible(false);
                hided=false;
            });
        }else if(hide==false){
            errorlabel.setVisible(true);
            errorlabel.setOpacity(0);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }

    //for movie combobox
    public void movieDetails(ActionEvent event){
        startImage.setVisible(false);
        startText.setVisible(false);
        ismovieselected=false;
        selectedmovie=(String)movieCombo.getValue();

        movieImage.setImage(server.getImage((String) movieCombo.getValue()));
        datelabel.setText("Date:       ");
        timelabel.setText("Time:       ");
        screenlabel.setText("Screen:   ");
    }
}
