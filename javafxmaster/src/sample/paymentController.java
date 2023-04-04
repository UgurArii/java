/*******************************************************************************
 Controller class and logic implementation for payment.fxml
 ******************************************************************************/
package sample;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.json.JsonObject;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class paymentController extends CommonMethods implements Initializable {
    //Inisilisation of javafx objects and variables used.
    @FXML
    private JFXButton closebtn, cancelbtn, placeorderbtn, backcardbtn, backcashbtn;
    @FXML
    private AnchorPane mainpane, insidepane, toppane, methodpane, cardpayment, cashpayment;
    @FXML
    private ImageView cardimage, cashimage;

    @FXML
    private StackPane stackpane;

    public JFXComboBox expmonthbox, expyearbox;
    @FXML
    private ImageView selectedimage;
    @FXML
    private JFXTextField firstnamefield, lastnamefield, emailfield, cardfield,
            cvvfield, poundsfield, pencefield, changefield;

    @FXML
    private Label errorlabel, adultslabel, childslabel, seniorslabel, viplabel, totalpricelabel;
    public static String scene = "method";
    public static boolean reversemethod = false;
    private String moveto = "";
    private boolean hided = false;
    private boolean moneytaken = false, printing = false, handledpayment = false;

    /**
     * Initialise method that gets run whenever the payment.fxml is loaded.
     * Needed in order to initialise and set up all the logic for payment.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handledpayment = false;
        leftpanecolor = "#AC005D";
        toppane.toFront();
        closebtn.toFront();
        moveWindow(toppane);
        setErrorLabelLogic();
        if (!scene.equals("method")) {
            errorlabel.setVisible(false);
        }
        if (scene.equals("method")) {
            page = "Payment";
            disableAllFocus(mainpane);
            animate();
            closebtn.setOnMouseEntered(e -> {
                closebtn.setStyle("-fx-background-color:  #F6490D");
                closebtn.setEffect(new Bloom(0.7));
            });
            closebtn.setOnMouseExited(e -> {
                closebtn.setStyle("-fx-background-color: transparent");
                closebtn.setEffect(new Bloom(1));
            });
            //Checks which payment method is selected and runs the required method.
        } else if (scene.equals("card")) {
            setUpCardPage();
        } else if (scene.equals("cash")) {
            setUpCashPage();
        }
    }

    /**
     * Method used to setup the card payment page.
     * This populates the combo boxes with the required values. It also adds the
     * selected movie information to the page.
     */
    private void setUpCardPage() {
        List<Integer> list = new ArrayList<>();
        ObservableList<Integer> o1 = FXCollections.observableList(list);
        o1.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        expmonthbox.setItems(o1);

        ObservableList<Integer> o2 = FXCollections.observableList(new ArrayList<>());
        for (int i = 24; i >= 18; i--) {
            o2.add(new Integer(i));
        }
        expyearbox.setItems(o2);

        selectedimage.setImage(server.getImage(server.getCurrentMovieName("SCREEN1")));
        roll(cardpayment, 300, 800, true, false);
        loadUpData();
    }

    /**
     * Method used to set up the cash payment page.
     * When the pounds or pence fields are edited with an amount of cash, then
     * the methods updateChangeField is called.
     */
    private void setUpCashPage() {
        selectedimage.setImage(server.getImage(server.getCurrentMovieName("SCREEN1")));
        roll(cashpayment, 300, 800, true, false);

        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.getCaretPosition() < 1 || c.getAnchor() < 1 || c.getCaretPosition() > 5) {
                return null;
            } else if (((String) c.getText()).matches("\\d*")) {
                return c;
            }
            return null;

        };
        loadUpData();
        poundsfield.setTextFormatter(new TextFormatter<String>(filter));
        poundsfield.positionCaret(1);
        poundsfield.setOnKeyReleased(event -> updateChangeField());
        pencefield.setTextFormatter(new TextFormatter<String>(filter));
        pencefield.positionCaret(1);
        pencefield.setOnKeyReleased(event -> updateChangeField());
    }


    private void loadUpData() {
        selectedimage.setImage(server.getImage(checkoutController.selectedmovie));
        adultslabel.setText(checkoutController.adulttickets + "");
        childslabel.setText(checkoutController.childtickets + "");
        seniorslabel.setText(checkoutController.seniortickets + "");
        //Checks if the user selected the VIP seating. This then allows the
        //user to select the seat if it is VIP.
        if (checkoutController.isvip) {
            viplabel.setText("Yes");
        } else {
            viplabel.setText("No");
        }
        totalpricelabel.setText(String.format("%.2f", checkoutController.totalprice));
    }

    /**
     * Method that updates the total change due when the cash payment option is
     * selected and is given an amount of cash.
     */
    private void updateChangeField() {
        double pounds = 0.0, pence = 0.0, cash = 0.0;
        double total = checkoutController.totalprice;
        total = total * 100;
        if (poundsfield.getText().length() > 1)
            pounds = Double.parseDouble(poundsfield.getText().substring(1));
        if (pencefield.getText().length() > 1)
            pence = Double.parseDouble(pencefield.getText().substring(1));
        cash = (pounds * 100) + pence;
        double diff = cash - total;

        if (pounds == 0 && pence == 0) {
            changefield.setText("£0.00");
        } else {
            changefield.setText(String.format("£%.2f", (diff / 100)));
        }
        if (diff >= 0) {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
            moneytaken = true;
        } else if (diff < 0) {
            moneytaken = false;
        }
    }

    /**
     * Prints a receipt when the payment is complete.
     * It generates a pdf with all the payment information and ticket details.
     * An animation is loaded when the order is placed signifing that the ticket
     * is printing.
     */
    private void printReceipt(String msg) {
        if (printing) {
            return;
        }
        printing = true;
        Text title = new Text(msg);
        title.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 18));
        title.setFill(Paint.valueOf("#00204A"));
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(title);
        dialogContent.setPrefWidth(300);
        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setStyle("-fx-progress-color: #3A0088");
        dialogContent.setBody(spinner);

        stackpane = new StackPane();
        if (scene.equals("cash")) {
            cashpayment.getChildren().add(stackpane);
        } else {
            cardpayment.getChildren().add(stackpane);
        }

        stackpane.setPrefWidth(800d);
        stackpane.setPrefHeight(500d);
        showDialog(stackpane, dialogContent);
    }

    private void showDialog(StackPane stackPane, JFXDialogLayout dialogContent) {
        JFXDialog dialog = new JFXDialog(stackpane, dialogContent, JFXDialog.DialogTransition.TOP);
        dialog.setOverlayClose(false);
        JFXButton close = new JFXButton("Cancel");
        close.setButtonType(JFXButton.ButtonType.RAISED);
        close.setStyle("-fx-background-color: #FF9A00; -fx-text-fill: white");
        dialogContent.setActions(close);

        dialog.show();
        PauseTransition p = new PauseTransition(Duration.millis(3000));
        p.play();
        close.setOnAction(event -> {
            p.stop();
            dialog.close();
            if (scene.equals("cash")) {
                cashpayment.getChildren().remove(stackpane);
            } else {
                cardpayment.getChildren().remove(stackpane);
            }
            printing = false;
        });
        p.setOnFinished(event -> {
            Text msg = new Text("Transaction Complete");
            msg.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 18));
            msg.setFill(Paint.valueOf("#00204A"));
            dialogContent.setHeading(msg);
            PauseTransition p2 = new PauseTransition(Duration.millis(2000));
            p2.play();
            p2.setOnFinished(event1 -> {
                printing = false;
                if (scene.equals("cash")) {
                    cashpayment.getChildren().remove(stackpane);
                } else {
                    cardpayment.getChildren().remove(stackpane);
                }
                dialog.close();
                closebtn.fire();
                processTicketData();
            });
        });

    }

    private void processTicketData() {
        if (checkoutController.isvip == true && checkoutController.seatsselected == true) {
            server.setVipBookings(checkoutController.selectedmovie, checkoutController.getSelectedMovieSlot(
                    checkoutController.selectedscreen, checkoutController.selectedtime), seatsController.booked);
            checkoutController.isvip=false;
            checkoutController.seatsselected=false;

        }else if(checkoutController.isvip==false && checkoutController.ismovieselected==true){
            int tickets=checkoutController.adulttickets+checkoutController.seniortickets+checkoutController.childtickets;
            server.setStdBookings(checkoutController.selectedmovie, checkoutController.getSelectedMovieSlot(
                    checkoutController.selectedscreen, checkoutController.selectedtime), tickets);
        }
    }

    /**
     * Handles all the button events on the checkout page.
     * The button place order checks if the validation is passed which ensures all fields
     * are filled and are valid.
     *
     * @param event
     */
    public void handleButtons(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        SequentialTransition fly = makeBtnFly(btn);
        fly.play();
        fly.setOnFinished(event1 -> {
            if (btn.getId().equals("placeorderbtn")) {
                if (scene.equals("card")) {
                    if (validateCardPage()) {
                        printReceipt("Printing Reciept");
                    }
                }
                if (scene.equals("cash")) {
                    if (validateCashPage()) {
                        printReceipt("Processing Order");
                    }
                }
            } else if (btn.getId().equals("Processing Order")) {
                cancelOrder(event);
            } else if (btn.getId().equals("backcardbtn")) {
                roll(cardpayment, 300, 800, false, true);
                reversemethod = true;
            } else if (btn.getId().equals("backcashbtn")) {
                roll(cashpayment, 300, 800, false, true);
                reversemethod = true;
            }else if (btn.getId().equals("cancelbtn")) {
                cancelOrder(event);
            }
        });
    }

    /**
     * Checks if the cash fields have been filled and checks if the email address
     * is of a valid form. Returns true and false accordingly.
     *
     * @return boolean
     */
    private boolean validateCashPage() {
        if (firstnamefield.getText().isEmpty() || lastnamefield.getText().isEmpty() ||
                emailfield.getText().isEmpty()) {
            errorlabel.setText("Error! All Fields Need To Be Full");
            fadeErrorLabel(false);
            return false;
        } else if (!(isValidEmailAddress(emailfield.getText()))) {
            errorlabel.setText("Error! Invalid Email Address");
            fadeErrorLabel(false);
            return false;
        } else if (!moneytaken) {
            errorlabel.setText("Error! Take Cash From Customer");
            fadeErrorLabel(false);
            return false;
        }

        return true;
    }

    /**
     * Validates the card payment page.
     * This checks that all fields are complete and are of a valid form such as
     * credit card length.
     *
     * @return boolean
     */
    private boolean validateCardPage() {
        if (firstnamefield.getText().isEmpty() || lastnamefield.getText().isEmpty() ||
                emailfield.getText().isEmpty() ||
                cardfield.getText().isEmpty() || cvvfield.getText().isEmpty() ||
                expmonthbox.getSelectionModel().isEmpty() ||
                expyearbox.getSelectionModel().isEmpty()) {
            errorlabel.setText("All fields need to be full");
            fadeErrorLabel(false);
            return false;
        } else if (!(isValidEmailAddress(emailfield.getText()))) {
            errorlabel.setText("Invalid Email Address");
            fadeErrorLabel(false);
            return false;
        } else if (cardfield.getText().length() != 16) {
            errorlabel.setText("Invalid Bank Card Length");
            fadeErrorLabel(false);
            return false;
        } else if (cvvfield.getText().length() != 3) {
            errorlabel.setText("Invalid CVV Length");
            fadeErrorLabel(false);
            return false;
        }

        return true;
    }

    /**
     * Returns an error if one of the fields are not valid or are not filled.
     */
    private void setErrorLabelLogic() {
        if (scene.equals("method")) {
            return;
        }

        firstnamefield.setOnKeyPressed(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        lastnamefield.setOnKeyPressed(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        emailfield.setOnKeyPressed(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        setTextFieldValidation(firstnamefield, 100);
        setTextFieldValidation(lastnamefield, 100);

        if (scene.equals("card")) {
            setCardpageLogic();
        }
    }

    /**
     * This method checks if the error label has popped up and fades it out
     * if the field is edited.
     */
    private void setCardpageLogic() {
        cardfield.setOnKeyPressed(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        cvvfield.setOnKeyPressed(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        expmonthbox.setOnAction(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        expyearbox.setOnAction(event -> {
            if (errorlabel.isVisible())
                fadeErrorLabel(true);
        });
        setNumericFieldValidation(cvvfield, 3);
        setNumericFieldValidation(cardfield, 16);
    }

    private void setNumericFieldValidation(JFXTextField field, int limit) {
        field.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than LIMIT
                if (field.getText().length() > limit) {
                    // if it's 11th character then just setText to previous
                    // one
                    field.setText(field.getText().substring(0, limit));
                } else if (!field.getText().matches("\\d*")) {
                    field.setText(field.getText().replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void setTextFieldValidation(JFXTextField field, int limit) {
        field.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than LIMIT
                if (field.getText().length() >= limit) {
                    // if it's 11th character then just setText to previous
                    // one
                    field.setText(field.getText().substring(0, limit));
                } else if (!field.getText().matches("\\D*")) {
                    field.setText(field.getText().replaceAll("[^\\D]", ""));
                }
            }
        });
    }

    /**
     * Handles the animation for the error label. This either loads the label
     * or fades out the label.
     *
     * @param hide
     */
    private void fadeErrorLabel(boolean hide) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), errorlabel);
        ft.setCycleCount(1);
        if (hide == true && hided == false) {
            hided = true;
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            ft.setOnFinished(event -> {
                errorlabel.setVisible(false);
                hided = false;
            });
        } else if (hide == false) {
            errorlabel.setVisible(true);
            errorlabel.setOpacity(0);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }

    /**
     * Checks if the email entered is valid. This done by comparing the email entered
     * and ensures that specific charecters such as '@' and '.com' are entered.
     *
     * @param email
     * @return boolean
     */
    private boolean isValidEmailAddress(String email) {
        //Compares input email with the valid charecters and returns true or false.
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Closes the scene if the cancel order button is pressed.
     *
     * @param event
     */
    public void cancelOrder(ActionEvent event) {
        scene = "method";
        handleClose(event);
    }

    /**
     * Animation loaded when the two payment options are loaded (cash and card).
     * When the checkout button is pressed a scene loads with animation and each icon
     * has its own animation aswell.
     */
    public void animate() {
        if (reversemethod) {
            roll(methodpane, 300, 400, true, true);
            reversemethod = false;
        } else {
            roll(methodpane, 300, 400, true, false);
        }

        cashimage.setOnMouseEntered(event -> {
            scaleImage(cashimage, 1.3, 1.3);
        });
        cashimage.setOnMouseExited(event -> {
            scaleImage(cashimage, 1.0, 1.0);
        });
        cashimage.setOnMouseReleased(event -> {
            moveto = "tocash";
            roll(methodpane, 300, 400, false, false);
        });


        cardimage.setOnMouseEntered(event -> {
            scaleImage(cardimage, 1.3, 1.3);
        });
        cardimage.setOnMouseExited(event -> {
            scaleImage(cardimage, 1.0, 1.0);
        });
        cardimage.setOnMouseReleased(event -> {
            moveto = "tocard";
            roll(methodpane, 300, 400, false, false);
        });
    }

    private void roll(AnchorPane pane, int speed, int width, boolean direction, boolean goforward) {
        double pos = pane.getLayoutX();
        if (direction == true && goforward == false) {
            pane.setLayoutX(pane.getLayoutX() + width + pane.getLayoutX());
        } else if (direction == false && goforward == false) {
            pane.setLayoutX(pane.getLayoutX());
        } else if (direction == false && goforward == true) {
            pane.setLayoutX(pane.getLayoutX());
        } else if (direction == true && goforward == true) {
            pane.setLayoutX(-(pane.getLayoutX() + width));
        }

        TranslateTransition t = new TranslateTransition(Duration.millis(speed), pane);
        if ((direction == true || direction == false) && goforward == false) {
            t.setToX(-(pos + width));
        } else if (direction == false && goforward == true) {
            t.setToX((pos + width));
        } else if (direction == true && goforward == true) {
            t.setToX(pos + width + pos);
        }
        t.play();
        if (direction == false && scene.equals("method") && moveto.equals("tocard")) {
            t.setOnFinished(event -> {
                scene = "card";
                load("card-payment");
            });
        }
        if (direction == false && scene.equals("method") && moveto.equals("tocash")) {
            t.setOnFinished(event -> {
                scene = "cash";
                load("cash-payment");
            });
        }
        if (goforward == true && scene.equals("card")) {
            t.setOnFinished(event -> {
                scene = "method";
                load("payment");
            });
        }
        if (goforward == true && scene.equals("cash")) {
            t.setOnFinished(event -> {
                scene = "method";
                load("payment");
            });
        }
    }

    /**
     * Method used to load the scene that is passed as a parameter.
     *
     * @param scene
     */
    private void load(String scene) {
        AnchorPane root = null;
        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource(scene + ".fxml"));
            insidepane.setLayoutX(0);
            insidepane.setLayoutY(0);
            insidepane.getChildren().setAll(findPane(root, "insidepane"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scaleImage(ImageView image, double tox, double toy) {
        ScaleTransition st = new ScaleTransition(Duration.millis(400), image);
        st.setToX(tox);
        st.setToY(toy);
        st.setRate(1.5);
        st.setCycleCount(1);
        st.play();
    }
}
