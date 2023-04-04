/*******************************************************************************
            Controller class and logic implementation for home.fxml
 ******************************************************************************/
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
public class homeController extends CommonMethods implements Initializable{

    @FXML
    private JFXButton  closebtn, minimisebtn, moviescreensbtn,s1issuebtn, s2issuebtn, s3issuebtn,addbtn;
    private JFXButton[] issuebtns=new JFXButton[3];
    @FXML
    private AnchorPane mainhomepane, leftpane,s1infopane,s2infopane,s3infopane, toppane, p1, p2, p3,p1shadow, p2shadow, p3shadow;
    private AnchorPane[] infopanes=new AnchorPane[3];
    @FXML
    private AnchorPane s1tile1,s1tile2,s1tile3,s1tile4,s1tile5,s1tile6,s2tile1,s2tile2,s2tile3,s2tile4,s2tile5,s2tile6,
            s3tile1,s3tile2,s3tile3,s3tile4,s3tile5,s3tile6;
    private AnchorPane[] tiles=new AnchorPane[18];

    @FXML
    private Label datelabel, timelabel, s1moviename, s1movieduration, s1status, s1nextmovie, s2moviename,
            s2movieduration, s2status, s2nextmovie,s3moviename, s3movieduration, s3status, s3nextmovie;
    @FXML
    private Label s1availableseats, s1repeatsleft, s1rating, s1timeremaining, s1timeslot,s2availableseats,
            s2repeatsleft, s2rating, s2timeremaining, s2timeslot,s3availableseats, s3repeatsleft, s3rating,
            s3timeremaining, s3timeslot, userlabel;

    @FXML
    private ImageView s1movieimage, s2movieimage, s3movieimage;
    @FXML
    private JFXTabPane tabpane;

    @FXML
    private String todaysdate, todaystime;
    private boolean rotatedpane=false;
    private boolean[] imageshifted={false,false,false};
    private java.time.Duration duration1, duration2, duration3;
    public static boolean viewmovie=false;
    public static String viewmoviename="";
    private double x=0, y=0;

    /**
     *  Initialise method required for implementing initializable and,
     *  sets up and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpArrays();
        populateScreens();
        leftpanecolor=leftpane.getStyle().substring(22,29);
        page="Home";
        displayTime();
        customiseWindowButtons(closebtn,minimisebtn);
        moveWindow(leftpane);
        moveWindow(toppane);
        animate();
        disableAllFocus(mainhomepane);
        displayUserName(userlabel);
        addShiftImagdeEffect(s1movieimage, 0);
        addShiftImagdeEffect(s2movieimage, 1);
        addShiftImagdeEffect(s3movieimage, 2);
        setIssueTicketsActions();
    }

    /**
     *  Main method for allowing the user to click and drag the images around.
     *  if its moved half the length of the tabpane and to the right, the image
     *  then stays on the right and all tiles from the right shift to the left.
     * @param image     Imageview to apply the effect to
     * @param screen    Screen number representing the tabs on which the image is present
     */
    public void addShiftImagdeEffect(ImageView image, int screen){
        image.setOnMousePressed(event -> {
            if(imageshifted[screen]==false){
                x=event.getSceneX()-45;
                y=event.getSceneY()-41;
            }else {
                x=event.getSceneX()-665;
                y=event.getSceneY()-41;
            }
        });
        image.setOnMouseDragged(event -> {
            image.setLayoutX(event.getSceneX()-x);
            image.setLayoutY(event.getSceneY()-y);
        });

        image.setOnMouseReleased(event -> {
            if(imageshifted[screen]==false){
                if(image.getLayoutX()>455){
                    shiftImage(true,250,screen);
                    transitionImage(image,665, 41);
                }else {
                    transitionImage(image,45,41);
                }
            }else if(imageshifted[screen]==true){
                if(image.getLayoutX()+200<455){
                    shiftImage(false,250,screen);
                    transitionImage(image,45,41);
                }else {
                    transitionImage(image,665,41);
                }
            }
        });
    }

    /**
     *  Once the image has been dragged to a location in the tabpane, this
     *  method is called when the mouse is released and plays a timeline
     *  animation in which the image is moved back to its original or new
     *  location.
     * @param image     ImageView to play the animation on
     * @param x         new x position for the image
     * @param y         new y position for the image
     */
    public void transitionImage(ImageView image, int x, int y){
        Timeline timeline=new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200),
                new KeyValue(image.layoutXProperty(),x),new KeyValue(image.layoutYProperty(),y)));
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     *  Used for the actual movement of the tiles either to the left or the
     *  right. Creates a sequential transition in which multiple transition
     *  effects are played one after the other. A call is made to a subsequent
     *  method getImageShiftTransitions which actually returns a sequential
     *  transition made up of TranslateTransitions for moving the tiles.
     * @param forward   Indicates if the tiles are to be shifted to the left or right
     * @param speed     Integer which is passed to Duration.millis()
     * @param screen    Integer representing the screen number and tab
     */
    public void shiftImage(boolean forward, int speed, int screen){
        double move=0;
        if(forward==true){
            move=-300d;
        }else{
            move=0d;
        }
        FadeTransition fade=new FadeTransition(Duration.millis(140),issuebtns[screen]);
        fade.setFromValue(1);
        fade.setToValue(0);
        FadeTransition fade2=new FadeTransition(Duration.millis(140),issuebtns[screen]);
        fade2.setFromValue(0);
        fade2.setToValue(1);

        SequentialTransition d=getImageShiftTransitions(forward, speed, move,screen);
        d.getChildren().add(0,fade);
        d.getChildren().add(d.getChildren().size(),fade2);
        fade.setOnFinished(event -> {
            if(forward==true){
                issuebtns[screen].setLayoutX(640);
            }else {
                issuebtns[screen].setLayoutX(20);
            }
        });
        d.play();
    }

    public void setIssueTicketsActions(){
        s1issuebtn.setOnAction(event1 -> {
            SequentialTransition st=popButton(s1issuebtn, 1.2);
            st.play();
            st.setOnFinished(event -> {
                checkoutController.selectedmovie=s1moviename.getText();
                checkoutController.selectedtime="";
                checkoutController.selectedscreen="";
                addbtn.fire();
            });

        });
        s1issuebtn.setCursor(Cursor.HAND);

        s2issuebtn.setOnAction(event1 -> {
            SequentialTransition st=popButton(s2issuebtn, 1.2);
            st.play();
            st.setOnFinished(event -> {
                checkoutController.selectedmovie=s2moviename.getText();
                checkoutController.selectedtime="";
                checkoutController.selectedscreen="";
                addbtn.fire();
            });
        });
        s2issuebtn.setCursor(Cursor.HAND);

        s3issuebtn.setOnAction(event1 -> {
            SequentialTransition st=popButton(s3issuebtn, 1.2);
            st.play();
            st.setOnFinished(event -> {
                checkoutController.selectedmovie=s3moviename.getText();
                checkoutController.selectedtime="";
                checkoutController.selectedscreen="";
                addbtn.fire();
            });
        });
        s3issuebtn.setCursor(Cursor.HAND);
    }

    /**
     *  Creates a SeqentialTransition to be used in the previous method.
     *  the transitions are applied to diffrent tiles/anchorpanes and the
     *  screen paramter decides which subset of anchorpanes from the tiles
     *  array to apply the effects to. The transitions are added to the
     *  sequential transition in a specific ordering depending on the shift direction.
     * @param forward   boolean indicating if tiles are to be moved to the left or right
     * @param speed     Integer which is passed to Duration.millis()
     * @param direction double value used for setting the toX method in TranslateTransition
     * @param screen    Integer representing the screen number and tab
     * @return          SequenntialTransition for shifting the tiles
     */
    public SequentialTransition getImageShiftTransitions(boolean forward, int speed, double direction, int screen){
        int i=0;
        if(screen==0){
            i=0;
        }else if(screen==1){
            i=6;
        }else if(screen==2){
            i=12;
        }

        TranslateTransition info=new TranslateTransition(Duration.millis(speed), infopanes[screen]);
        info.setToX(direction);
        TranslateTransition t1=new TranslateTransition(Duration.millis(speed), tiles[i]);
        t1.setToX(direction);
        TranslateTransition t2=new TranslateTransition(Duration.millis(speed), tiles[i+1]);
        t2.setToX(direction);
        TranslateTransition t3=new TranslateTransition(Duration.millis(speed), tiles[i+2]);
        t3.setToX(direction);
        TranslateTransition t4=new TranslateTransition(Duration.millis(speed), tiles[i+3]);
        t4.setToX(direction);
        TranslateTransition t5=new TranslateTransition(Duration.millis(speed), tiles[i+4]);
        t5.setToX(direction);
        TranslateTransition t6=new TranslateTransition(Duration.millis(speed), tiles[i+5]);
        t6.setToX(direction);

        SequentialTransition sequential=new SequentialTransition();
        if(forward==true){
            sequential.getChildren().addAll(info, t1,t4,t2,t5,t3,t6);
            imageshifted[screen]=true;
        }else {
            sequential.getChildren().addAll(info, t3,t6,t2,t5,t1,t4);
            imageshifted[screen]=false;
        }
        return sequential;
    }

    /**
     *  Sets the images and labels in the infopanes of each screen/tab.
     *  Calls relevant methods in the server to get the data it needs.
     */
    public void populateScreens(){
        s1movieimage.setImage(server.getImage(server.getCurrentMovieName("SCREEN1")));
        s1moviename.setText(server.getTitle(server.getCurrentMovieName("SCREEN1")));
        s1movieduration.setText(server.getDuration(server.getCurrentMovieName("SCREEN1")));
        s1status.setText(server.getStatus(server.getCurrentMovieName("SCREEN1")));
        s1nextmovie.setText(server.getNextMovie("SCREEN1"));

        s2movieimage.setImage(server.getImage(server.getCurrentMovieName("SCREEN2")));
        s2moviename.setText(server.getTitle(server.getCurrentMovieName("SCREEN2")));
        s2movieduration.setText(server.getDuration(server.getCurrentMovieName("SCREEN2")));
        s2status.setText(server.getStatus(server.getCurrentMovieName("SCREEN2")));
        s2nextmovie.setText(server.getNextMovie("SCREEN2"));

        s3movieimage.setImage(server.getImage(server.getCurrentMovieName("SCREEN3")));
        s3moviename.setText(server.getTitle(server.getCurrentMovieName("SCREEN3")));
        s3movieduration.setText(server.getDuration(server.getCurrentMovieName("SCREEN3")));
        s3status.setText(server.getStatus(server.getCurrentMovieName("SCREEN3")));
        s3nextmovie.setText(server.getNextMovie("SCREEN3"));

        setUpTiles();
    }

    /**
     *  Similar to the populateScreens method, assigns correct values to
     *  diffrent labels in the tiles. Calls methods in the server to get
     *  correct upto date information to display on the homepage.
     */
    public void setUpTiles(){
        s1availableseats.setText(server.getAvailableSeats("SCREEN1", server.getCurrentMovieName("SCREEN1")));
        s1repeatsleft.setText(server.getRepeats(server.getCurrentMovieName("SCREEN1")));
        s1rating.setText(server.getRating(server.getCurrentMovieName("SCREEN1")));
        s1timeslot.setText(server.getCurrentTimeslot("SCREEN1",true));
        setTimer(s1timeremaining,s1status, "SCREEN1", duration1);

        s2availableseats.setText(server.getAvailableSeats("SCREEN2", server.getCurrentMovieName("SCREEN2")));
        s2repeatsleft.setText(server.getRepeats(server.getCurrentMovieName("SCREEN2")));
        s2rating.setText(server.getRating(server.getCurrentMovieName("SCREEN2")));
        s2timeslot.setText(server.getCurrentTimeslot("SCREEN2",true));
        setTimer(s2timeremaining, s2status, "SCREEN2", duration2);

        s3availableseats.setText(server.getAvailableSeats("SCREEN3", server.getCurrentMovieName("SCREEN3")));
        s3repeatsleft.setText(server.getRepeats(server.getCurrentMovieName("SCREEN3")));
        s3rating.setText(server.getRating(server.getCurrentMovieName("SCREEN3")));
        s3timeslot.setText(server.getCurrentTimeslot("SCREEN3",true));
        setTimer(s3timeremaining,s3status, "SCREEN3", duration3);
    }
    /**
     *  Initialises the tiles, infopanes and issuebtns array. These arrays
     *  are used in the tile shifting animations. Necessary as it modularises
     *  code as java does not support pass by reference, so instead set up
     *  these arrays and in the methods, the screen paramater determines which
     *  correct subset of the arrays to use.
     */
    public void setUpArrays(){
        issuebtns[0]=s1issuebtn;
        issuebtns[1]=s2issuebtn;
        issuebtns[2]=s3issuebtn;

        infopanes[0]=s1infopane;
        infopanes[1]=s2infopane;
        infopanes[2]=s3infopane;

        tiles[0]=s1tile1;
        tiles[1]=s1tile2;
        tiles[2]=s1tile3;
        tiles[3]=s1tile4;
        tiles[4]=s1tile5;
        tiles[5]=s1tile6;

        tiles[6]=s2tile1;
        tiles[7]=s2tile2;
        tiles[8]=s2tile3;
        tiles[9]=s2tile4;
        tiles[10]=s2tile5;
        tiles[11]=s2tile6;

        tiles[12]=s3tile1;
        tiles[13]=s3tile2;
        tiles[14]=s3tile3;
        tiles[15]=s3tile4;
        tiles[16]=s3tile5;
        tiles[17]=s3tile6;
    }

    /**
     *  Creates a timer for each screen which shows how long more a movie
     *  is going to last. A slot is 3 hours long so when the timer hits 0,
     *  it simply stops the animation and displays "Finished". The actual
     *  timer itself is implemented using a timeline that cycles depending
     *  on the total number of seconds left for the movie to be finished.
     * @param label     Label which gets updated every second and displays remaining time
     * @param status    Status label for the movie which is set to finished when timer is 0
     * @param screen    Integer representing the screen number and tab
     * @param duration  Initial Duration which is the difference between current
     *                  time and time when the movie ends
     */
    public void setTimer(Label label,Label status, String screen, java.time.Duration duration){
        int hour=Integer.parseInt(s1timeslot.getText().substring(0,2).trim());
        Calendar cal = Calendar.getInstance();
        if ((cal.get(Calendar.AM_PM) == Calendar.PM) && hour!=12 ) {
            hour+=12;
        }else if ((cal.get(Calendar.AM_PM) == Calendar.AM) && hour==12) {
            hour-=12;
        }

        String[] movieduration=server.getDuration(server.getCurrentMovieName(screen)).split(":");
        int moviehour=Integer.parseInt(movieduration[0]);
        int moviemins=Integer.parseInt(movieduration[1]);
        int moviesecs=Integer.parseInt(movieduration[2]);
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime oldDate1 = LocalDateTime.of(now1.getYear(), now1.getMonth(), now1.getDayOfMonth(),
                hour+moviehour, moviemins, moviesecs);
        duration = java.time.Duration.between(oldDate1,now1);

        int finalHour = hour;
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oldDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(),
                    finalHour +moviehour, moviemins, moviesecs);
            java.time.Duration duration2 = java.time.Duration.between(oldDate,now);
            long secs=Math.abs(duration2.getSeconds());
            label.setText( String.format("%d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60)));
            }),
                new KeyFrame(Duration.seconds(1))
        );

        clock.setCycleCount((duration.getSeconds()>0) ? 0: (int) Math.abs(duration.getSeconds()));
        clock.play();
        clock.setOnFinished(event -> {
            label.setText("Finished");
            status.setText("Cleaning");
        });
    }

    /**
     *  Adds hover effect to the 3 issue btns and, applies animation to
     *  the first 3 panes on home page.
     */
    private void animate(){
        btnEffect(s1issuebtn);
        btnEffect(s2issuebtn);
        btnEffect(s3issuebtn);

        popNode(p1, p1shadow);
        popNode(p2, p2shadow);
        popNode(p3, p3shadow);
    }

    /**
     *  Is called to handle the event when the view movie screens tile is
     *  clicked. Simply rotates the pane and sets viewmovie boolean to true.
     *  Also sets the name of the movie that is currently running on the screen
     *  in which the tile is clicked to the static variable viewmoviename. lastly
     *  triggers the fire event on the movie screens btn. The static variable
     *  viewmovie tells the movie screens controller whether to load a default
     *  movie or the one named in the static variable viewmoviename.
     * @param event
     */
    public void viewMovieScreens(MouseEvent event){
        rotatePane(event);
        viewmovie=true;
        String screen=tabpane.getSelectionModel().getSelectedItem().getText().substring(0,6).toUpperCase();
        screen+=tabpane.getSelectionModel().getSelectedItem().getText().substring(7);
        viewmoviename=server.getCurrentMovieName(screen);
        moviescreensbtn.fire();
    }

    /**
     *  Simply rotates the tile that is clicked, 45 degrees clockwise and
     *  then 45 degrees anti clockwise. Uses RotateTransition for the animation.
     * @param event     MouseEvent that is triggered when mouse is released on tile
     */
    public void rotatePane(MouseEvent event){
        AnchorPane pane=(AnchorPane)event.getSource();
        if(rotatedpane==false){
            rotatedpane=true;
            RotateTransition rt=new RotateTransition(Duration.millis(60),pane);
            rt.setByAngle(40);
            rt.setCycleCount(2);
            rt.setAutoReverse(true);
            rt.play();

            rt.setOnFinished(event1 -> {
                RotateTransition rt2=new RotateTransition(Duration.millis(60),pane);
                rt2.setByAngle(-40);
                rt2.setCycleCount(2);
                rt2.setAutoReverse(true);
                rt2.play();
                rt2.setOnFinished(event2 -> rotatedpane=false);
            });
        }
    }

    /**
     *  Used to add the animation and hover effects to the first 3 tiles
     *  or anchorpanes. First plays a scale transition whenever the page
     *  is first loaded. Then adds hover effects which displays a shadow
     *  on top of the pane.
     * @param pane      AnchorPane to apply the effect to
     * @param shadow    Shadow AnchorPane that is revealed using Timeline
     */
    private void popNode(AnchorPane pane, AnchorPane shadow){
        ScaleTransition st = new ScaleTransition(Duration.millis(800), pane);
        st.setFromX(0.5);
        st.setFromY(0.5);
        st.setToX(1.0);
        st.setToY(1.0);
        st.setRate(1.5);
        st.setCycleCount(1);
        st.play();

        pane.setOnMouseEntered(event -> {
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    new KeyValue (shadow.minWidthProperty(), 200)));
            timeline.play();
            pane.setEffect(new Glow(0.2));
        });

        pane.setOnMouseExited(event -> {
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    new KeyValue (shadow.minWidthProperty(), 1)));
            timeline.play();
            pane.setEffect(new Glow(0));
        });
    }

    /**
     *  Plays an Indefinite timeline which updates the date and time labels
     *  every second to show the current date and time.
     */
    private void displayTime(){
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("hh:mm:ss a");

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            todaysdate=dateformat.format(now).toString();
            todaystime=timeformat.format(now).toString();
            datelabel.setText(todaysdate+"");
            timelabel.setText(todaystime+"");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
