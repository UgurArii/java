package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class seatsController extends CommonMethods implements Initializable{

    @FXML
    private JFXButton cancelbtn, confirmbtn, E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,
            F1,F2,F3,F4,F5,F6,F7,F8,F9,F10;
    private JFXButton[] seats=new JFXButton[20];
    public static boolean[] bookings=new boolean[20];
    public static boolean[] booked;
    private int maxseats=(checkoutController.adulttickets+ checkoutController.childtickets+ checkoutController.seniortickets);
    private int numberofseats=0;
    @FXML
    private Label movietitle, totaltickets, totalprice, seatsselected, limiterror;
    @FXML
    private AnchorPane mainpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seatsselected.setText("");
        booked=new boolean[20];
        leftpanecolor = "#AC005D";
        moveWindow(mainpane);
        disableAllFocus(mainpane);
        initialiseArray();
        setUpSeats();
        movietitle.setText(checkoutController.selectedmovie);
        totaltickets.setText(""+maxseats);
        totalprice.setText("£"+String.format( "%.2f", checkoutController.totalprice));
    }

    //avail= #8EA6B4    selected= #4A772F   booked= #C40018
    private void setUpSeats(){
        for(int i=0; i<seats.length; i++){
            if(bookings[i]==false){
                seats[i].setStyle("-fx-background-color:  #8EA6B4");
                int finalI1 = i;

                seats[i].setOnAction(event -> {
                    if(booked[finalI1]==false){
//                        bookings[finalI1]=true;
                        if(numberofseats<maxseats){
                            numberofseats++;
                            seats[finalI1].setStyle("-fx-background-color:  #4A772F ");
                            setBookedSeats(seats[finalI1], true);
                            limiterror.setVisible(false);
                        }else {
                            limiterror.setText("Error! Max Seat Limit Reached");
                            limiterror.setVisible(true);
                        }
                    }else if(booked[finalI1]==true){
//                        bookings[finalI1]=false;
                        numberofseats--;
                        seats[finalI1].setStyle("-fx-background-color:  #8EA6B4");
                        setBookedSeats(seats[finalI1], false);
                        limiterror.setVisible(false);
                    }
                    popSeat(seats[finalI1]);
                });
            }else if(bookings[i]==true){
                seats[i].setStyle("-fx-background-color:  #C40018");
                int finalI = i;
                seats[i].setOnAction(event -> rotateButton(seats[finalI]));
            }
        }
    }

    private void popSeat(JFXButton btn){
        ScaleTransition st = new ScaleTransition(Duration.millis(200), btn);
        st.setToX(1.2);
        st.setToY(1.2);
        st.setRate(1.5);
        st.setCycleCount(1);
        st.play();
        st.setOnFinished(event -> {
            ScaleTransition st2 = new ScaleTransition(Duration.millis(200), btn);
            st2.setToX(1);
            st2.setToY(1);
            st2.setRate(1.5);
            st2.setCycleCount(1);
            st2.play();
        });
    }

    private void setBookedSeats(JFXButton btn, boolean selected){
        int seat=0;
        if(btn.getId().startsWith("E")){
            seat=(Integer.parseInt(btn.getId().substring(1)))-1;
        }else if(btn.getId().startsWith("F")){
            seat=(Integer.parseInt(btn.getId().substring(1)))+9;
        }
        booked[seat]=selected;
        String btnid=btn.getId();
        String s=seatsselected.getText();
        if(selected==true){
            if(s.isEmpty()){
                seatsselected.setText(btnid);
            }else{
                seatsselected.setText(s+", "+btnid);
            }
        }else{
            if(s.startsWith(btnid) && s.length()==2) {
                seatsselected.setText(s.replaceAll(btn.getId(), ""));
            }else if(s.startsWith(btnid) && s.length()>2) {
                seatsselected.setText(s.replaceAll(btn.getId()+", ", ""));
            }else {
                seatsselected.setText(s.replaceAll(", "+btn.getId(), ""));
            }
        }
    }

    /**
     * Handles the cancelation of the seats which runs animation and unselects the
     * selected seats.
     * @param event
     */
    public void handleCancellation(ActionEvent event){
        SequentialTransition fly=makeBtnFly(cancelbtn);
        fly.play();
        fly.setOnFinished(event1 -> {
            checkoutController.seatsselected=false;
            handleClose(event);
        });
    }

    /**
     * ensures that each ticket selected has been seleted a seat if VIP is
     * option is chosen.
     * @param event
     */
    public void handleConfirmation(ActionEvent event){
        SequentialTransition fly=makeBtnFly(confirmbtn);
        fly.play();
        fly.setOnFinished(event1 -> {
           if(numberofseats==maxseats){
               checkoutController.seatsselected=true;
               handleClose(event);
           }else {
               limiterror.setText("Error! Select All Seats");
               limiterror.setVisible(true);
           }
        });
    }
    private void initialiseArray(){
        seats[0]=E1;
        seats[1]=E2;
        seats[2]=E3;
        seats[3]=E4;
        seats[4]=E5;

        seats[5]=E6;
        seats[6]=E7;
        seats[7]=E8;
        seats[8]=E9;
        seats[9]=E10;

        seats[10]=F1;
        seats[11]=F2;
        seats[12]=F3;
        seats[13]=F4;
        seats[14]=F5;

        seats[15]=F6;
        seats[16]=F7;
        seats[17]=F8;
        seats[18]=F9;
        seats[19]=F10;
    }
}
