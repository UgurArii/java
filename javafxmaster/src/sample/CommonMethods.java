/*******************************************************************************
   This class includes common methods that are frequently used by all the
   controller classes. All fxml/controller pages inherit this class and so can
   directly use these methods resulting in a cleaner and modularised code.
 ******************************************************************************/
package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.json.JsonObject;
import java.io.IOException;

public class CommonMethods{
    public static boolean confirmed=false;      //used in loggin out
    public static boolean disablelogin=true;    //used for login btn animation
    public static String leftpanecolor="";      //used for btn drop shadow colours
    private double initialx, initialy;          //used for dragging window
    protected static String page="";            //used for keeping track of current pages
    private boolean rotatedpane =false;         //used for rotate pane animation
    public static String username="";
    public static Server server=null;
    public static connectionController connection=null;
    public static boolean loggedout=false;
    public static boolean running = true;
    private boolean loading=false;

    /**
     *  handles the event for closing a window.
     * @param event The button which triggered the event
     */
    public void handleClose(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void displayUserName(Label label){
        label.setText("Logged in as: "+username);
    }
    /**
     *  Given a parent anchorpane and an ID, finds and returns the child
     *  anchorpane with that that id.
     * @param parent    parent anchorpane
     * @param id        id of child to find and return
     * @return          anchorpane if found
     */
    public AnchorPane findPane(AnchorPane parent, String id){
        if(parent.getId().equals(id)){
            return parent;
        }
        for(Node node: parent.getChildren()){
            if(node.getId().equals(id)){
                return (AnchorPane)node;
            }
        }
        return null;
    }
    /**
     *  Loads new scenes/pages, which page to load depends on the
     *  button which triggered the event. All buttons in the nav
     *  bar have id's which are used here to load up the correct
     *  new scene in the application.
     * @param event The button which triggered the event
     */
    public  void loadScene(ActionEvent event){
        if(loading){
            return;
        }
        loading=true;
        String newscene="";
        CommonMethods.confirmed=false;
        if(((JFXButton)(event.getSource())).getId().equals("homebtn")){
            newscene="home.fxml";
        }else if(((JFXButton)(event.getSource())).getId().equals("moviescreensbtn")){
            newscene="movies.fxml";
        }else if(((JFXButton)(event.getSource())).getId().equals("addbtn")){
            newscene="checkout.fxml";
        }else if(((JFXButton)(event.getSource())).getId().equals("checkoutbtn")){
            newscene="checkout.fxml";
        }

        Timeline t=dropShadow(event, 0.75,leftpanecolor);
        t.play();
        String finalNewscene = newscene;
        t.setOnFinished(event1 -> {
            ((JFXButton)(event.getSource())).setEffect(null);
            if(finalNewscene.isEmpty()){
                return;
            }
            try {
                Parent parent = FXMLLoader.load(getClass().getResource(finalNewscene));
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Scene scene=new Scene(parent);
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
                loading=false;
            } catch (IOException ex) {
                System.out.println("Error in switching stages");
            }
        });
    }


    /**
     *  Rotates a JFXButton 45 degrees clockwise and then 45 degrees
     *  anticlockwise using a rotate transition whenever the btn is clicked.
     * @param btn   JFXButton to add effect to
     */
    public void rotateButton(JFXButton btn){
        if(rotatedpane ==false){
            rotatedpane =true;
            RotateTransition rt=new RotateTransition(Duration.millis(60),btn);
            rt.setByAngle(45);
            rt.setCycleCount(2);
            rt.setAutoReverse(true);
            rt.play();

            rt.setOnFinished(event -> {
                RotateTransition rt2=new RotateTransition(Duration.millis(60),btn);
                rt2.setByAngle(-45);
                rt2.setCycleCount(2);
                rt2.setAutoReverse(true);
                rt2.play();
                rt2.setOnFinished(event1 -> rotatedpane =false);
            });
        }
    }

    /**
     *  Used to apply a shadow effect to a button when clicked on.
     *  the colour of the DropShadow depends on the colour of the left
     *  pane.
     * @param event         Event trigger by a button
     * @param shadowspread  Double value between 0-1 indicating level of spread
     * @param color         Color to use for the shadow effect
     * @return              A Timeline which can be played
     */
    public Timeline dropShadow(ActionEvent event, double shadowspread, String color){
        JFXButton btn=(JFXButton) event.getSource();
        Color origcolor=Color.web(color);
        Color newcolor=origcolor.brighter();
        String darker=("#"+newcolor.toString().substring(2,8));
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(darker));
        shadow.setSpread(shadowspread);

        Timeline shadowAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 0d)),
                new KeyFrame(Duration.millis(150), new KeyValue(shadow.radiusProperty(), 20d)));
        shadowAnimation.setAutoReverse(true);
        shadowAnimation.setCycleCount(2);
        Node target = btn;
        target.setEffect(shadow);
        return shadowAnimation;
    }

    /**
     *  Creates a hover effect on a anchorpane, the background color
     *  of the pane is retrieved and made darker and this new darker
     *  color is then set as the background color.
     * @param event  MouseEvent that gets triggered when mouse is hovered over
     */
    public void tileHover(MouseEvent event){
        AnchorPane pane=(AnchorPane)event.getSource();
        String original=pane.getStyle().substring(22,29);
        Color origcolor=Color.web(original);

        Color newcolor=origcolor.darker();
        String darker=("#"+newcolor.toString().substring(2,8));
        pane.setStyle("-fx-background-color: "+darker);
        pane.setEffect(new Bloom(0.85));
    }

    /**
     *  A reverse of the tileHover method, simply gets the current color
     *  of the anchorpane and creates a new brighter color which is then
     *  assigned as the new color for the pane.
     * @param event  MouseEvent that gets triggered when mouse exits pane
     */
    public void tileExit(MouseEvent event){
        AnchorPane pane=(AnchorPane)event.getSource();
        String original=pane.getStyle().substring(22,29);
        Color origcolor=Color.web(original);

        Color newcolor=origcolor.brighter();
        String brighter=("#"+newcolor.toString().substring(2,8));
        pane.setStyle("-fx-background-color: "+brighter);
        pane.setEffect(new Bloom(1));

    }

    /**
     *  Similar to the tileHover method, changes the colour of a buttons
     *  background and its opacity. Hovering over a button gives it a dark
     *  background and 100% opacity.
     * @param event  MouseEvent that gets triggered when mouse is on button
     */
    public void btnHover(MouseEvent event){
        JFXButton btn=(JFXButton) event.getSource();
        Color origcolor=Color.web(leftpanecolor);
        Color newcolor=origcolor.darker();

        btn.setOpacity(1.0);
        String darker=("#"+newcolor.toString().substring(2,8));
        btn.setStyle("-fx-background-color: "+darker);
        btn.setEffect(new Bloom(0.75));
    }

    /**
     *  Reverse of the btnHover method, when the mouse exits the buttons dimensions,
     *  the background changes to transparent and the opacity to 80%.
     * @param event  MouseEvent that gets triggered when mouse is leaves button
     */
    public void btnExit(MouseEvent event){
        JFXButton btn=(JFXButton) event.getSource();
        if((btn.getText().equals(page))) {
            btn.setOpacity(1.0);
        }else{
            btn.setOpacity(0.8);
        }
        btn.setStyle("-fx-background-color: transparent");
        btn.setEffect(new Bloom(1));
    }

    /**
     *  Adds a basic hover effect to a buttton, darkens the background
     *  color on hover and sets it back to original on exit.
     * @param btn   JFXButton to add effect to
     */
    public void btnEffect(JFXButton btn){
        String btncolor=btn.getStyle().substring(22,29);
        Color origcolor=Color.web(btncolor);
        Color newcolor=origcolor.darker();
        String darker=("#"+newcolor.toString().substring(2,8));

        btn.setOnMouseEntered(event -> {
            btn.setStyle("-fx-background-color: "+darker);
            btn.setEffect(new Bloom(0.85));
        });
        String brighter=("#"+origcolor.toString().substring(2,8));
        btn.setOnMouseExited(event -> {
            btn.setStyle("-fx-background-color: "+brighter);
            btn.setEffect(new Bloom(1));
        });
    }

    /**
     *  Method to solve small bug which is when the window or a new page gets
     *  loaded, some of the buttons visual focus is set to true and have grey
     *  backgrounds even though the mouse is not hovering over them. This simply
     *  disables all of the visualFocus on the buttons in a page.
     * @param pane  Main root pane of a page
     */
    public void disableAllFocus(Pane pane){
        for (Node n: pane.getChildren()){
            if(n instanceof JFXButton){
                ((JFXButton) n).setDisableVisualFocus(true);
            }else if((n instanceof AnchorPane) || (n instanceof HBox)){
                disableAllFocus((Pane) n);
            }
        }
    }

    /**
     *  Loads a new popup window asking the user to confirm
     *  whether or not they want to logout from application.
     * @param event The button which triggered the event
     */
    public void logOut(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        AnchorPane mainpane= (AnchorPane) ((Node) event.getSource()).getParent().getParent();
        mainpane.setEffect(new ColorAdjust(0,0,-0.25,0));
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("logoutpop.fxml"));
            Stage popup = new Stage();
            Scene scene=new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            popup.initOwner(stage);
            popup.setScene(scene);
            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
            if(confirmed==true){
                stage.close();
                loadLogin();
            }else{
                disableAllFocus(mainpane);
                mainpane.setEffect(new ColorAdjust(0,0,0,0));
            }
        } catch (IOException ex) {
            System.out.println("Error in switching stages logout btn");
            ex.printStackTrace();
        }
    }

    /**
     *  If the user confirmed in the the previous popup window
     *  for confirmation, application is closed and the initial
     *  login window is loaded and displayed. Is declared private
     *  since its only used by the logOut method.
     */
    private void loadLogin(){
        try {
//            String[] name = {"format"};
//            String[] value = {"json"};
//            JsonObject parser = connection.getData("logout.jsp", name, value);
//            if(parser.getString("status").equals("SUCCESS")) {
                loggedout = true;
//            }
            Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
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
     *  Given a pane as an argument, sets properties of the pane
     *  to allow the user to move the window around by clicking
     *  and holding on the pane which will move the window according
     *  to the position of mouse.
     * @param pane  The pane that can be used to move the window around
     */
    public void moveWindow(AnchorPane pane){
        pane.setOnMousePressed(e ->{
            initialx = e.getSceneX();
            initialy = e.getSceneY();
        });
        pane.setOnMouseDragged(e -> {
            Node source = (Node) e.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setX(e.getScreenX() - initialx);
            stage.setY(e.getScreenY() - initialy);
        });
    }

    /**
     *  Used to add hover effects to the window buttons such as
     *  close button and minimise etc. Sets the properties of the
     *  buttons passed to it so that hovering will change the background
     *  colour otherwise the background for the buttons stays transparent.
     * @param closebtn      Button to add effects to
     * @param minimisebtn   Button to add effects to
     */
    public void customiseWindowButtons(JFXButton closebtn,  JFXButton minimisebtn){
        closebtn.setOnMouseEntered(e -> {
            closebtn.setStyle("-fx-background-color:  #F6490D");
            closebtn.setEffect(new Bloom(0.7));
        });
        closebtn.setOnMouseExited(e -> {
            closebtn.setStyle("-fx-background-color: transparent");
            closebtn.setEffect(new Bloom(1));
        });

        minimisebtn.setOnMouseEntered(e -> {
            minimisebtn.setStyle("-fx-background-color:  #F6490D");
            minimisebtn.setEffect(new Bloom(0.7));
        });
        minimisebtn.setOnMouseExited(e -> {
            minimisebtn.setStyle("-fx-background-color: transparent");
            minimisebtn.setEffect(new Bloom(1));
        });
    }

    /**
     *  Provides the ability to minimise the window, which window/stage
     *  is minimised is determined from the source of the button that
     *  triggered this even to occur.
     * @param event The button which triggers the event
     */
    public void minimiseWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     *  Creates and plays a scale transition on the Anchorpane passed to it.
     *  Decreases the size of the pane to 50% and plays the animation upto
     *  its original size.
     * @param pane      AnchorPane to add this animation to
     */
    public void popNode(AnchorPane pane){
        ScaleTransition st = new ScaleTransition(Duration.millis(800), pane);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.setRate(1.5);
        st.setCycleCount(1);
        st.play();
    }

    public SequentialTransition makeBtnFly(JFXButton btn){
        TranslateTransition t1=new TranslateTransition(Duration.millis(200),btn);
        t1.setToY(-17d);
        PauseTransition p1=new PauseTransition(Duration.millis(30));
        TranslateTransition t2=new TranslateTransition(Duration.millis(200),btn);
        t2.setToY(0d);

        SequentialTransition transition=new SequentialTransition(btn, t1,p1,t2);
        return transition;
    }

    public SequentialTransition popButton(JFXButton btn, double scale){
        ScaleTransition st1 = new ScaleTransition(Duration.millis(200), btn);
        st1.setToX(scale);
        st1.setToY(scale);
        st1.setRate(1.5);
        st1.setCycleCount(1);

        ScaleTransition st2 = new ScaleTransition(Duration.millis(200), btn);
        st2.setToX(1);
        st2.setToY(1);
        st2.setRate(1.5);
        st2.setCycleCount(1);
        SequentialTransition transition=new SequentialTransition(btn, st1,st2);
        return transition;
    }
}