/*******************************************************************************
 A class to represent a movie and all of its data, needed as the local server
 class is made up of movie objects and the whole application retrieves data
 from the local server class which periodacilly updates the state of the movieobjects
 with data from the actual server and database.
 ******************************************************************************/
package sample;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MovieObject {
    //for today only
    private int id;
    private String name;
    private Image image=null;
    private double rating;
    private String duration;
    private String status="Playing";
    private int repeats;
    private int ticketsold;
//    beautybeast.addSlot("SCREEN3_SLOT4",new MovieData(b, 80, 9));
    private Map<String,MovieData> slots_seats=new TreeMap<String, MovieData>();

    public MovieObject(int movieid, String moviename, double movierating, String movieduration, int movierepeats){
        id = movieid;
        name=moviename;
        rating=movierating;
        duration=movieduration;
        repeats=movierepeats;
        ticketsold=0;
    }

    /**
     *  Sets the image field of this object using a new fileinput stream
     *  created from the path passed as argument
     * @param path
     * @throws IOException
     */
    public void setImage(String path) throws IOException{
        image=new Image(new FileInputStream(path));
    }

    public void setImageUrl(String path){
        try {
            InputStream file = CommonMethods.connection.getFile("images/" + path, new String[]{}, new String[]{});
            image = new Image(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage(){
        return image;
    }

    public void setImage(Image image) {
        this.image=image;
    }

    public void addSlot(String screenandslot, MovieData data){
            slots_seats.put(screenandslot,data);
    }

    public Map<String, MovieData> getSlots() {
        return slots_seats;
    }

    public void setSlots(Map<String, MovieData> slots_seats) {
        this.slots_seats = slots_seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public int getTicketsold() {
        return ticketsold;
    }

    public void setTicketsold(int ticketsold) {
        this.ticketsold = ticketsold;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }
}
