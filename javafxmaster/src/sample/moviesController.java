/*******************************************************************************
            Controller class and logic implementation for movies.fxml
 ******************************************************************************/
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;

import java.util.*;
public class moviesController extends CommonMethods implements Initializable{

    @FXML
    private JFXButton closebtn, minimisebtn, logoutbtn, homebtn, addbtn,
            issueticketbtn, detailsbtn, allschedulesbtn, searchicon;
    @FXML
    private JFXTextField searchfield;
    @FXML
    private AnchorPane mainmoviespane, leftpane, toppane, moviepane, tablepane, SLOT1,
            slot1shadow, SLOT2, slot2shadow, SLOT3, slot3shadow, SLOT4, slot4shadow;
    @FXML
    private ImageView movieimage;
    @FXML
    private Label movietitle, movierating, movieduration, movierepeats, movieticketsold,
            userlabel;

    @FXML
    private String[] movienames;
    private boolean faded=false,showingall=false;
    public static String currentmovie="";
    private int currentslots=0;
    public static int slotseatNo =-1 ;

    /**
     *  Initialise method required for implementing initializable and,
     *  sets up and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            movienames=server.getMovieNames();
            startSearch();
            setDefaultMovie();
        } catch (IOException e) {
            e.printStackTrace();
        }
        leftpanecolor=leftpane.getStyle().substring(22,29);
        page="Movie Screens";
        disableAllFocus(mainmoviespane);
        displayUserName(userlabel);
        customiseWindowButtons(closebtn,minimisebtn);
        moveWindow(leftpane);
        moveWindow(toppane);
        animate();
    }

    /**
     *  Called when user clicks on show all schedules btn. First removes
     *  all current slots on the table. Then for each movie in the movienames
     *  array, calls setTimeSlots for that movie. After all 12 slots are added
     *  to the table, creates and plays a FadeTransition array for making the
     *  slots appear in a transition rather than instantaneously. Lastly calls
     *  the animateTable() method to add hover effects to all slots.
     */
    public void showAllSchedule(){
        SequentialTransition st=popButton(allschedulesbtn,1.1);
        st.play();
        st.setOnFinished(event -> {
            AnchorPane[] panes=getCurrentPanes();
            for(AnchorPane pane: panes){
                tablepane.getChildren().remove(pane);
            }
            for(int i=0; i<movienames.length; i++){
                setTimeSlots(movienames[i]);
            }
            movietitle.setText("Showing All Movie Schedules For Today");
            currentmovie="";
            currentslots=12;
            FadeTransition[] transitions=getSlotsFadeArray(0,1,300);
            for(FadeTransition fade: transitions){
                fade.play();
            }
            animateTable();
            showingall=true;
        });
    }

    /**
     *  Given the name of a movie, gets all slots for that movie from the
     *  server. Information from the server is parsed and assignPane is called
     *  for each lot to reveal that slot on the table.
     * @param movie
     */
    private void setTimeSlots(String movie){
        String allslots=server.getMovieSlot(movie);
        String[] temp=allslots.split(" ");
        String[] slot;
        for(int i=0; i<temp.length; i++){
            slot=temp[i].split(":");
            assignPane(slot[0],Integer.parseInt(slot[1]), ("ID:"+movie));
        }
    }

    /**
     *  Dynamically creates an AnchorPane representing a slot, in a specific
     *  location on the table, adds relevant labels and styles, and assigns
     *  it an ID. The x and y values of the new pane is determined by the
     *  position parameter that is passed to it such as "SCREEN1_SLOT2".
     *  The enum Timetable is used to find out where the slot belongs.
     * @param position          position for the new pane/slot
     * @param availableseats    Available seats to show on the slot
     * @param id                ID to assign to the AnchorPane in the end
     */
    private void assignPane(String position, int availableseats, String id){
        AnchorPane pane=new AnchorPane();
        tablepane.getChildren().add(pane);
        pane.setPrefWidth(180d);
        pane.setPrefHeight(90d);
        Timetable location=Timetable.valueOf(position);
        pane.setLayoutX(location.getX());
        pane.setLayoutY(location.getY());

        pane.getStyleClass().add("tiles");
        Label info=new Label("Available Seats");
        Label seats=new Label(String.valueOf(availableseats));
        info.setLayoutX(10);
        info.setLayoutY(5);
        info.setStyle("-fx-text-fill: white; -fx-font-size: 13px");
        seats.setLayoutX(10);
        seats.setLayoutY(20);
        seats.setStyle("-fx-text-fill: white; -fx-font-size: 29px");
        pane.getChildren().addAll(info,seats);
        setSubPane(pane,availableseats);
        pane.setId(id);
        seats.setId("seat");
    }

    /**
     * Second part of assigning dynamic slots on the table. Creates a new
     * subpane and sets this as a child of the AnchorPane passed to it as
     * argument. Specific styling is added to the parent and new subpane
     * depending on the availableseats argument, also adds a white shopping
     * cart icon to the new subpane
     * @param pane              Parent pane to add the subpane to
     * @param availableseats    used to determine the background colours
     */
    private void setSubPane(AnchorPane pane, int availableseats){
        AnchorPane subpane=new AnchorPane();
        pane.getChildren().add(subpane);
        subpane.setPrefWidth(67d);
        subpane.setPrefHeight(88d);
        subpane.setLayoutX(113d);
        subpane.setLayoutY(1d);

        if(availableseats>0){
            pane.setStyle("-fx-background-color:  #A2C11C");
            subpane.setStyle("-fx-background-color:  #68AE00");
        }else{
            pane.setStyle("-fx-background-color:     #fc1919");
            subpane.setStyle("-fx-background-color:  #da0202");
        }
        subpane.getStyleClass().add("tiles");

        FontAwesomeIconView cart=new FontAwesomeIconView();
        cart.setGlyphName("SHOPPING_CART");
        cart.setSize("30");
        subpane.getChildren().add(cart);
        cart.setLayoutX(18d);
        cart.setY(52d);
        cart.setStyle("-fx-fill: white");

    }

    /**
     *  Converts the search textfield into a proper search field by calling
     *  bindAutoCompletion on the searchfield which binds the searchfield to
     *  the array of movie names. This means that results are displayed as the
     *  user types in the field. Also changes the currently showing movie by
     *  calling setMovie() method if the user presses the enter key and the
     *  text in the search field corresponds to an actual name in the movienames array.
     * @throws IOException
     */
    public void startSearch() throws IOException {
        AutoCompletionBinding bind=TextFields.bindAutoCompletion(searchfield,movienames);
        bind.setOnAutoCompleted(event -> {
            setMovie(searchfield.getText());
        });
        searchfield.setOnKeyReleased(event -> {
            if(event.getCode()==KeyCode.ENTER ){
                searchMovie(searchfield.getText());
            }else if(event.getCode()==KeyCode.DOWN && searchfield.getText().isEmpty()){
                searchfield.setText(" ");
            }
        });
    }

    /**
     *  Given a string representing a movie name which is usually the text
     *  from the textfield. Checks if the name is an actual movie name present
     *  in the array. If so, sets this new movie else, changes the colour of
     *  the textfield and search icon to red indicating an incorrect movie.
     * @param movie     String to search for and see if its a correct name
     */
    private void searchMovie(String movie){
        boolean exists=false;
        for (int i=0; i<movienames.length; i++){
            if(movienames[i].equalsIgnoreCase(movie)){
                setMovie(movie);
                exists=true;
                break;
            }
        }
        if(exists==false){// #181818 def
            searchfield.setFocusColor(Color.web("#fc1919"));
            searchfield.setStyle("-fx-text-fill: #fc1919 ");
            searchicon.setStyle("-fx-background-color:#fc1919 ");
            rotateButton(searchicon);
            searchfield.setOnKeyPressed(event -> {
                searchfield.setFocusColor(Color.web("#7cbd1e"));
                searchfield.setStyle("-fx-text-fill: #181818 ");
                searchicon.setStyle("-fx-background-color:#FC950D ");
            } );
        }
    }

    /**
     *  Creates and returns an array of AnchorPanes representing the current
     *  slots visible on the table. Does this by checking each node of the
     *  tablepanes children and seeing if their Id's start with "ID:" which
     *  is assigned in the assignPane() method.
     * @return      AnchorPane array of movie slots
     */
    private AnchorPane[] getCurrentPanes(){
        AnchorPane[] panes=new AnchorPane[currentslots];
        int i=0;
        for (Node node: tablepane.getChildren()){
            if((node instanceof AnchorPane) && node.getId()!=null ) {
                if(node.getId().startsWith("ID:")){
                    panes[i]=(AnchorPane)node;
                    i++;
                }
            }
        }
        return panes;
    }

    /**
     *  Creates and returns an array of Fade Transitions for each pane in
     *  the array returned by the previous getCurrentPanes() methods.
     * @param from      Fade from value
     * @param to        Fade to value
     * @param millis    Value for Duration.millis()
     * @return          FadeTransition Array
     */
    private FadeTransition[] getSlotsFadeArray(double from, double to, int millis){
        FadeTransition[] transitions=new FadeTransition[currentslots];
        AnchorPane[] panes=getCurrentPanes();
        int i=0;
        for(AnchorPane pane: panes){
            FadeTransition fade=new FadeTransition(Duration.millis(millis),pane);
            fade.setFromValue(from);
            fade.setToValue(to);
            transitions[i]=fade;
            i++;
        }

        return transitions;
    }

    /**
     *  Replaces the currently displaying movie with a new one passed to it
     *  as parameter. First fades away the current movie image, slots and
     *  labels, then sets new movie content. This new content is then revealed
     *  in another Parallel transition animation made up of FadeTransitions.
     * @param movie     New movie name to display
     */
    private void setMovie(String movie){
        if(faded==false){
            showingall=false;
            faded=true;
            ParallelTransition para=fadeMovieNodes(1,0,250);
            para.play();
            para.setOnFinished(event -> {
                movieimage.setImage(server.getImage(movie));
                movietitle.setText(server.getTitle(movie));
                movierating.setText(server.getRating(movie));
                movieduration.setText(server.getDuration(movie));
                movierepeats.setText(server.getRepeats(movie));
                movieticketsold.setText(server.getTicketSold(movie));

                //delete previous slots on table
                AnchorPane[] panes=getCurrentPanes();
                for(AnchorPane pane: panes){
                    tablepane.getChildren().remove(pane);
                }
                setTimeSlots(movie);
                currentmovie=movie;
                currentslots=server.getNumberOfSlots(currentmovie);

                ParallelTransition parallel2=fadeMovieNodes(0,1,250);
                parallel2.play();
                parallel2.setOnFinished(event1 -> {
                    faded=false;
                    animateTable();
                });
            });
        }
    }

    /**
     *  Creates and returns a ParallelTransition made up of FadeTransitions
     *  for each movie label and image.
     * @param from      Fade from value
     * @param to        Fade to value
     * @param millis    value for Duration.millis()
     * @return          ParallelTransition for fading
     */
    private ParallelTransition fadeMovieNodes(double from, double to, int millis){
        ParallelTransition p=new ParallelTransition();
        FadeTransition fade1=fadeEffect(movieimage,millis, from,to);
        FadeTransition fade2=fadeEffect(movietitle,millis,from,to);
        FadeTransition fade3=fadeEffect(movierating,millis,from,to);
        FadeTransition fade4=fadeEffect(movieduration,millis,from,to);
        FadeTransition fade5=fadeEffect(movierepeats,millis,from,to);
        FadeTransition fade6=fadeEffect(movieticketsold,millis,from,to);

        FadeTransition[] transitions=getSlotsFadeArray(from,to,millis);
        for(FadeTransition fade: transitions){
            p.getChildren().add(fade);
        }
        p.getChildren().addAll(fade1,fade2,fade3,fade4,fade5,fade6);
        return p;
    }

    /**
     *  Creates and returns a FadeTransition on a node passed to it as
     *  parameter.
     * @param node      Node to apply transition on
     * @param millis    value for Duartion.millis()
     * @param from      Fade from value
     * @param to        Fade to value
     * @return          FadeTransition on node
     */
    public FadeTransition fadeEffect(Node node,int millis, double from, double to){
        FadeTransition fade=new FadeTransition(Duration.millis(millis),node);
        fade.setFromValue(from);
        fade.setToValue(to);
        return fade;
    }

    /**
     *  Loads up and displays a default movie which is recieved from the
     *  server. First checks the static viewmovie boolean in homeController.
     *  If thats set to true, then displays the movie named in the static
     *  viewmoviename variable, else displays whatever is sent from the server.
     */
    private void setDefaultMovie(){
        searchicon.setOnMouseReleased(event -> searchMovie(searchfield.getText()));
        searchicon.setOnMouseEntered(event -> {
            searchicon.setStyle("-fx-background-color: #68AE00");
            searchicon.setEffect(new Bloom(0.75));
        });
        searchicon.setOnMouseExited(event -> {
            searchicon.setStyle("-fx-background-color:  #FC950D");
            searchicon.setEffect(new Bloom(1));
        });

        String defaultmovie=currentmovie;
        if(homeController.viewmovie==true){
            defaultmovie= homeController.viewmoviename;
            homeController.viewmovie=false;
            homeController.viewmoviename="";
        }else if(currentmovie.isEmpty()){
            defaultmovie=server.getDefaultMovie();
        }
        movieimage.setImage(server.getImage(defaultmovie));
        movietitle.setText(server.getTitle(defaultmovie));
        movierating.setText(server.getRating(defaultmovie));
        movieduration.setText(server.getDuration(defaultmovie));
        movierepeats.setText(server.getRepeats(defaultmovie));
        movieticketsold.setText(server.getTicketSold(defaultmovie));
        setTimeSlots(defaultmovie);
        currentmovie=defaultmovie;
        currentslots=server.getNumberOfSlots(currentmovie);
    }

    /**
     *  Adds animation effects to nodes in the scene. Primarily it plays
     *  a scale transition on the movie and table panes. Also calll's other
     *  animation methods to complete the animation list.
     */
    public void animate(){
        popNode(moviepane);
        popNode(tablepane);
        animateTable();

        btnEffect(issueticketbtn);
        btnEffect(detailsbtn);
        btnEffect(allschedulesbtn);

        slotsEffect(SLOT1, slot1shadow);
        slotsEffect(SLOT2, slot2shadow);
        slotsEffect(SLOT3, slot3shadow);
        slotsEffect(SLOT4, slot4shadow);
        detailsbtn.setOnAction(event -> {
            SequentialTransition st=popButton(detailsbtn,1.1);
            st.play();
            st.setOnFinished(event2 -> {
                viewMovieOnWeb();
            });
        });
    }

    private void viewMovieOnWeb(){
//        try {
//            Desktop.getDesktop().browse(new URI("https://merlin.gotdns.ch/"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
    }
    /**
     *  Called to apply hover effects on the dynamically created panes/slots.
     *  Simply calls and passes each slots AnchorPane to the rollEffect() method.
     */
    private void animateTable(){
        AnchorPane[] panes=getCurrentPanes();
        for (AnchorPane pane: panes){
            rollEffect(pane);
        }
    }

    /**
     *  Adds hover effect to the pane passed to it which is usually a slot
     *  on the table. Hovering over a slot will shift its subpane to the
     *  left and labels to the right. Also makes the subpane glow when
     *  hovered over.
     * @param pane  AnchorPane to add effect to
     */
    public void rollEffect(AnchorPane pane){
        AnchorPane subpane = null;
        Label[] labels=new Label[2];
        int i=0;
        int j=0;
        for(Node node: pane.getChildren()){
            if(node instanceof AnchorPane){
                subpane=(AnchorPane)node;
            }else if(node instanceof Label){
                labels[i]=(Label)node;
                i++;
            }
        }

        String[] relatives=getLocationOnTable(pane);
        subpane.setCursor(Cursor.HAND);
        AnchorPane finalSubpane = subpane;
        AnchorPane screen=findPane(tablepane, relatives[0]);
        AnchorPane timeslot=findPane(tablepane, relatives[1]);

        Label moviename=new Label(" "+pane.getId().substring(3)+" ");
        PopOver pop=new PopOver(moviename);
        pop.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);

        pane.setOnMouseEntered(event -> {
            mouseEnteredEffect(finalSubpane, labels, screen, timeslot, pop);
            if(showingall){
                movietitle.setText(pane.getId().substring(3));
                movieimage.setImage(server.getImage(pane.getId().substring(3)));
                movierating.setText(server.getRating(pane.getId().substring(3)));
                movieduration.setText(server.getDuration(pane.getId().substring(3)));
                movierepeats.setText(server.getRepeats(pane.getId().substring(3)));
                movieticketsold.setText(server.getTicketSold(pane.getId().substring(3)));
            }
        });
        pane.setOnMouseExited(event ->  {
            mouseExitedEffect(finalSubpane, labels, screen, timeslot,pop);
        });

        subpane.setOnMouseEntered(event -> finalSubpane.setEffect(new Glow(0.4)));
        subpane.setOnMouseExited(event -> finalSubpane.setEffect(new Glow(0)));
        subpane.setOnMousePressed(event -> finalSubpane.setEffect(new Bloom(0.8)));
        subpane.setOnMouseReleased(event -> {
            setTimeName( timeslot);
            setScreenName( screen);
            finalSubpane.setEffect(new Glow(0.4));
//            pop.hide();
            checkoutController.ismovieselected=true;
            goToAddPage(pane ,pop);
        });
    }

    private void goToAddPage(AnchorPane pane, PopOver pop){
        slotseatNo = Integer.parseInt(((Label)pane.getChildren().get(1)).getText());
        checkoutController.selectedmovie = movietitle.getText();
        if(slotseatNo<=0){
            pop.contentNodeProperty().set(new Label("No seats"));
        }else{
            addbtn.fire();
        }
    }

    public void issueTicket(){
        SequentialTransition st=popButton(issueticketbtn,1.1);
        st.play();
        st.setOnFinished(event -> {
            checkoutController.selectedmovie = movietitle.getText();
            checkoutController.selectedtime = "";
            checkoutController.selectedscreen = "";
            addbtn.fire();
        });
    }

    /**
     *  Plays the timeline animation when the mouse is hovered over a slot.
     *  Also fires MouseEntered events on the corresponding screens pane and
     *  timeslot. e.g. if the user hovers over a slot in row 2 coloumn 3, row
     *  2's and cloumn 3's title/panes will also glow.
     * @param finalSubpane  The subpane inside a slot to move to the left
     * @param labels        Array of the 2 labels inside the pane
     * @param screen        The pane that labels the row
     * @param timeslot      The pane that labels the coloumn
     * @param pop           PopOver to show
     */
    private void mouseEnteredEffect(AnchorPane finalSubpane, Label[] labels,AnchorPane screen, AnchorPane timeslot, PopOver pop ){
        Event.fireEvent(screen, new MouseEvent(MouseEvent.MOUSE_ENTERED,screen.getLayoutX(), screen.getLayoutY(),
                screen.getLayoutX(), screen.getLayoutY(), MouseButton.PRIMARY, 1,
                true, true, true, true, true,
                true, true, true, true, true,null));

        Event.fireEvent(timeslot, new MouseEvent(MouseEvent.MOUSE_ENTERED,timeslot.getLayoutX(), timeslot.getLayoutY(),
                timeslot.getLayoutX(), timeslot.getLayoutY(), MouseButton.PRIMARY, 1,
                true, true, true, true, true,
                true, true, true, true, true,null));


    }

    private void setTimeName(AnchorPane pane){
        for(Node node: pane.getChildren()){
            if(node instanceof Label){
                checkoutController.selectedtime=((Label)node).getText();
            }
        }
    }

    private void setScreenName(AnchorPane pane){
        for(Node node: pane.getChildren()){
            if(node instanceof Label){
                checkoutController.selectedscreen=((Label)node).getText();
            }
        }
    }


    /**
     *  Simply the reverse of mouseEnteredEffect() method, plays a timeline
     *  in which the nodes inside the pane go back to their original places.
     *  MouseExited events are fired at the row and coloumn panes.
     * @param finalSubpane  The subpane inside a slot to move to the right
     * @param labels        Array of the 2 labels inside the pane
     * @param screen        The pane that labels the row
     * @param timeslot      The pane that labels the coloumn
     * @param pop           PopOver to hide
     */
    private void mouseExitedEffect(AnchorPane finalSubpane, Label[] labels,AnchorPane screen, AnchorPane timeslot,PopOver pop ){
        Event.fireEvent(screen, new MouseEvent(MouseEvent.MOUSE_EXITED, screen.getLayoutX(), screen.getLayoutY(),
                screen.getLayoutX(), screen.getLayoutY(), MouseButton.PRIMARY, 1,
                true, true, true, true, true,
                true, true, true, true, true, null));

        Event.fireEvent(timeslot, new MouseEvent(MouseEvent.MOUSE_EXITED, timeslot.getLayoutX(), timeslot.getLayoutY(),
                timeslot.getLayoutX(), timeslot.getLayoutY(), MouseButton.PRIMARY, 1,
                true, true, true, true, true,
                true, true, true, true, true, null));
    }

    /**
     *  Creates and returns a string array showing the position of the pane
     *  on the table. For example 'names={"SCREEN1","SLOT3"}' etc.
     * @param pane      AnchorPane to find the position for
     * @return          String array representing the screen and slot number
     */
    private String[] getLocationOnTable(AnchorPane pane){
        String allslots=server.getMovieSlot(pane.getId().substring(3));
        String[] temp=allslots.split(" ");
        String[] slot;
        Timetable paneposition;
        String[] names=new String[2];

        for(int i=0; i<temp.length; i++){
            slot=temp[i].split(":");
            paneposition=Timetable.valueOf(slot[0]);
            if(paneposition.getX()==pane.getLayoutX() && paneposition.getY()==pane.getLayoutY()){
                names[0]=paneposition.name().substring(0,7);
                names[1]=paneposition.name().substring(8);
                break;
            }
        }
        return names;
    }

    /**
     *  Adds animation and hover effects a pane similar to the shadow sliding
     *  effect in homeController. Simply reveals a shadow on top of the pane
     *  and makes it glow on MouseEntered, and does the reverse when Mouse is exited.
     * @param pane      Main Anchorpane to add effect to
     * @param shadow    Shadow Anchorpane which is a child of pane
     */
    public void slotsEffect(AnchorPane pane, AnchorPane shadow){
        pane.setOnMouseEntered(event -> {
            Label text=null;
            for(Node n: pane.getChildren()){
                if(n instanceof Label){
                    text=(Label)n;
                }
            }
            pane.setEffect(new Glow(0.5));
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(400),
                    new KeyValue (shadow.minWidthProperty(), 120)));
            timeline.play();
            text.setLayoutY(3);
        });

        pane.setOnMouseExited(event -> {
            Label text=null;
            for(Node n: pane.getChildren()){
                if(n instanceof Label){
                    text=(Label)n;
                }
            }
            pane.setEffect(new Glow(0));

            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(400),
                    new KeyValue (shadow.minWidthProperty(), 1)));
            timeline.play();
            text.setLayoutY(10);
        });
    }


}
