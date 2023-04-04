/*******************************************************************************
            Controller class and logic implementation for login.fxml
 ******************************************************************************/
package sample;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

import javax.json.*;
import java.io.*;
import java.net.URL;
import java.util.*;
public class loginController extends CommonMethods implements Initializable{

    @FXML
    private JFXTextField userfield;
    @FXML
    private JFXProgressBar progressbar;
    @FXML
    private JFXPasswordField passfield;
    @FXML
    private JFXButton loginbtn, closebtn, minimisebtn;
    @FXML
    private AnchorPane mainloginpane;
    @FXML
    private StackPane stackpane;
    @FXML
    private Label errorlabel, forgotpassword;
    private boolean loginsuccess, vanished;
    /**
     *  Initialise method that gets run whenever the login.fxml is loaded.
     *  Needed in order to initialise and set up all the logic for login.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableAllFocus(mainloginpane);
        errorlabel.setVisible(false);
        page="Login";

        if (CommonMethods.loggedout) {
            errorlabel.setText("           Logged out successfully");
            errorlabel.setVisible(true);
            FadeTransition ft=new FadeTransition(Duration.millis(1200), errorlabel);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
            ft.setOnFinished(event -> {
                FadeTransition f=new FadeTransition(Duration.millis(1200), errorlabel);
                f.setFromValue(1);
                f.setToValue(0);
                f.play();
                f.setOnFinished(event1 -> {
                    errorlabel.setText("Error! Incorrect Password Or Username");
                    errorlabel.setVisible(false);
                });
            });
            CommonMethods.loggedout=false;
        }

        customiseWindowButtons(closebtn,minimisebtn);
        loginbtn.setOnMouseEntered(e -> {
            loginbtn.setStyle("-fx-background-color: #FF9A00;");
            loginbtn.setEffect(new Bloom(0.85));
        });
        loginbtn.setOnMouseExited(e -> {
            loginbtn.setStyle("-fx-background-color:  #FF5722;");
            loginbtn.setEffect(new Bloom(1));
        });

        forgotpassword.setOnMouseReleased(event -> showDialog());
        moveWindow(mainloginpane);
        userfield.setOnKeyPressed(event -> fieldListners(event));
        passfield.setOnKeyPressed(event -> fieldListners(event));
    }

    /**
     *  Shows a JFXDialog  whenever the forgot password label is clicked.
     *  Simply displays a message and includes a close btn for closing dialog.
     */
    public void showDialog(){
        Text title = new Text("Help With Forgotten Credentials");
        title.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 13));
        String content = "It looks like you forgot your username or password,\nif so then no need to "+
                "worry. Simply please get in \ntouch with or knock on my office door and ill be \nhappy to help.\n\n"+
                "Thanks\nNajm, Your Manager";
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(title);
        dialogContent.setPrefWidth(280);
        dialogContent.setBody(new Text(content));

        JFXButton close = new JFXButton("Close");
        close.setButtonType(JFXButton.ButtonType.RAISED);
        close.setStyle("-fx-background-color: #FF9A00; -fx-text-fill: white");
        dialogContent.setActions(close);
        JFXDialog dialog = new JFXDialog(stackpane, dialogContent, JFXDialog.DialogTransition.TOP);
        dialog.setOverlayClose(false);
        close.setOnAction(event -> {
            dialog.close();
        });
        dialog.show();

        dialog.setOnDialogOpened(event -> mainloginpane.setEffect(new GaussianBlur(5d)));
        dialog.setOnDialogClosed(event -> mainloginpane.setEffect(new GaussianBlur(0d)));
    }

    /**
     *  Animates a progress bar and then moves the login btn up whenever it
     *  is clicked. shows an error msg and rotates the btn if incorrect
     *  username or password is entered. if login is success, closes the login
     *  window and loads up the home page.
     * @param event     ActionEvent when the login btn is clicked
     */
    public void staffLogin(ActionEvent event){
        if(CommonMethods.disablelogin==true){
            Timeline timeline=animateLogin();
            timeline.play();
            timeline.setOnFinished(e -> {
                if(loginsuccess==true){
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    loginsuccess=false;
                    CommonMethods.username=userfield.getText();
                    loadHome();
                }else{
                    rotateButton(loginbtn);
                    Timeline timeline2 = new Timeline();
                    timeline2.setCycleCount(1);
                    timeline2.getKeyFrames().add(new KeyFrame(Duration.millis(300),
                            new KeyValue (progressbar.translateYProperty(), 0)));
                    timeline2.getKeyFrames().add(new KeyFrame(Duration.millis(200),
                            new KeyValue(loginbtn.translateYProperty(), 0)));
                    timeline2.play();

                    timeline2.setOnFinished(event1 -> {
                        errorlabel.setVisible(true);
                        vanished=false;
                        errorlabel.setOpacity(0);
                        FadeTransition ft=new FadeTransition(Duration.millis(500),errorlabel);
                        ft.setFromValue(0);
                        ft.setToValue(1);
                        ft.play();
                        progressbar.setVisible(false);
                        CommonMethods.disablelogin=true;
                    });
                    }
            });
        }
    }

    /**
     *  Uses a timeline to animate the transition of the progress bar and
     *  the login btn. Also runs a new thread in which the login details
     *  are checked with the server.
     * @return      timeline for the animation
     */
    private Timeline animateLogin(){
        CommonMethods.disablelogin=false;
        progressbar.setVisible(true);
        errorlabel.setVisible(false);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300),
                new KeyValue (progressbar.translateYProperty(), -70)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200),
                new KeyValue(loginbtn.translateYProperty(), -30)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2400)));

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                loginsuccess=checkUser();
            }
        });
        t.start();
        return timeline;
    }

    /**
     *  Upon successful login, the login window is closed and this method is
     *  called to load up the home page.
     */
    private void loadHome() {
        CommonMethods.confirmed=false;
        CommonMethods.disablelogin=true;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage stage = new Stage();
            Scene scene=new Scene(parent, 1200,700);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error in switching stages");
        }
    }

    /**
     *  Retrieves the text from the username and password fields, establishes
     *  a connection with the server using http client. checks whether the user
     *  details are correct.
     * @return      boolean indicating if the user details are correct or not
     */
    private boolean checkUser(){
        String username=userfield.getText();
        String password=passfield.getText();

//        String[] name = {"username", "pwd", "signInButton", "format"};
//        String[] value = {username, password, "", "json"};
//        JsonObject parser = CommonMethods.connection.getData("Login.jsp", name, value);

        if(username.equals("najm") && password.equals("najm")){
           return true;
        } else {
           Platform.runLater(new Runnable() {
               @Override
               public void run() {
                   errorlabel.setText("Error! Incorrect Password Or Username");
               }
           });
           return false;
        }
    }

    /**
     *  Called whenever a key is pressed in either the username field or
     *  the password field, checks if the key entered is the enter key. If
     *  so, fires the event for the login btn, otherwise, fades the error
     *  label if its visible.
     * @param event     KeyEvent caused by a key pressed on keybrd
     */
    private void fieldListners(KeyEvent event){
        FadeTransition ft=new FadeTransition(Duration.millis(500),errorlabel);
        if(event.getCode()== KeyCode.ENTER){
            loginbtn.fire();
        }else if(errorlabel.isVisible() && vanished==false){
            vanished=true;
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            ft.setCycleCount(1);
            ft.setOnFinished(event1 -> {
                errorlabel.setVisible(false);
            });
        }
    }
}
