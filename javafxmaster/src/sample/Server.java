/*******************************************************************************
 A Server class that creates instances of MovieObject's and has methods that
 provide necessary data needed by the controller classes.
 ******************************************************************************/
package sample;

import javafx.scene.image.Image;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Server {
    ArrayList<MovieObject> list = new ArrayList<>();
    ArrayList<MovieImages> movieimages = new ArrayList<>();

    /**
     * Constructor which creates and runs a thread that will
     * automatically update the data stored on movies locally,
     * in the background and the thread will terminate upon
     * closure of program.
     *
     * @throws IOException
     */
    public Server() throws IOException {
//        startUpdating();
        initialise();
    }

    public boolean[] getEmptySeats(){
        boolean[] seats = new boolean[20];
        for (int i = 0; i < 20; i++)
            seats[i] = false;
        return seats;
    }

    public void initialise() throws IOException {


        MovieObject panther = new MovieObject(0, "Black Panther", 6.7, "2:15:00", 8);
        panther.setImage("./src/sample/images/blackpanther.jpg");
        panther.addSlot("SCREEN1_SLOT1", new MovieData(getEmptySeats(), 80, 9));
        panther.addSlot("SCREEN3_SLOT3", new MovieData(getEmptySeats(), 80, 9));
        panther.setTicketsold(700);

        MovieObject galaxy = new MovieObject(0, "Guardians Of The Galaxy 2", 7.7, "2:18:00", 13);
        galaxy.setImage("./src/sample/images/galaxy.jpg");
        galaxy.addSlot("SCREEN1_SLOT3", new MovieData(getEmptySeats(), 80, 9));
        galaxy.setTicketsold(598);

        MovieObject paddington = new MovieObject(0, "Paddington 2", 8.2, "1:44:00", 6);
        paddington.setImage("./src/sample/images/paddington.jpg");
        paddington.addSlot("SCREEN3_SLOT2", new MovieData(getEmptySeats(), 80, 9));
        paddington.setTicketsold(645);

        MovieObject mazerunner = new MovieObject(0, "Maze Runner: The Death Cure", 6.8, "2:23:00", 5);
        mazerunner.setImage("./src/sample/images/mazerunner.jpg");
        mazerunner.addSlot("SCREEN3_SLOT1", new MovieData(getEmptySeats(), 80, 9));
        mazerunner.setTicketsold(350);

        MovieObject avatar = new MovieObject(0, "Avatar", 7.8, "2:42:00", 2);
        avatar.setImage("./src/sample/images/avatar.jpg");
        avatar.addSlot("SCREEN2_SLOT1", new MovieData(getEmptySeats(), 80, 9));
        avatar.setTicketsold(378);

        MovieObject starwars = new MovieObject(0, "Star Wars: The Last Jedi", 7.4, "2:32:00", 11);
        starwars.setImage("./src/sample/images/starwars.jpeg");
        starwars.addSlot("SCREEN1_SLOT2", new MovieData(getEmptySeats(), 80, 9));

        MovieObject avengers = new MovieObject(0, "Avengers: Infinity War", 8.9, "2:36:00", 10);
        avengers.setImage("./src/sample/images/avengers.jpg");
        avengers.addSlot("SCREEN2_SLOT2", new MovieData(getEmptySeats(), 80, 9));
        avengers.addSlot("SCREEN1_SLOT4", new MovieData(getEmptySeats(), 80, 9));
        avatar.setTicketsold(890);

        MovieObject showman = new MovieObject(0, "The Greatest Showman", 7.9, "1:46:00", 3);
        showman.setImage("./src/sample/images/showman.jpg");
        showman.addSlot("SCREEN2_SLOT3", new MovieData(getEmptySeats(), 80, 9));
        showman.setTicketsold(580);

        MovieObject tangled = new MovieObject(0, "Tangled", 7.8, "1:40:00", 4);
        tangled.setImage("./src/sample/images/tangled.jpg");
        tangled.addSlot("SCREEN2_SLOT4", new MovieData(getEmptySeats(), 80, 9));
        tangled.setTicketsold(696);

        MovieObject beautybeast = new MovieObject(0, "Beauty and The Beast 2017", 7.3, "2:10:00", 3);
        beautybeast.setImage("./src/sample/images/beauty.jpg");
        beautybeast.addSlot("SCREEN3_SLOT4", new MovieData(getEmptySeats(), 80, 9));
        beautybeast.setTicketsold(1178);

        list.add(panther);
        list.add(galaxy);
        list.add(paddington);
        list.add(mazerunner);
        list.add(avatar);
        list.add(starwars);
        list.add(avengers);
        list.add(showman);
        list.add(tangled);
        list.add(beautybeast);
    }


    /**
     * Return an array of booleans representing the booked and
     * available seats for the vip ticket option.
     *
     * @param movie movie to retrieve the data from
     * @param slot  the specific slot for which the booked seats is required
     * @return an array of booleans with false meaning available and true booked etc.
     */
    public boolean[] getBookings(String movie, String slot) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {

                Map<String, MovieData> map = list.get(i).getSlots();
                for (Map.Entry<String, MovieData> entry : map.entrySet()) {
                    if (entry.getKey().equals(slot)) {
                        return entry.getValue().getSeats();
                    }
                }
            }
        }
        return null;
    }

    public void setVipBookings(String movie, String slot, boolean[] bookings) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                Map<String, MovieData> map = list.get(i).getSlots();
                for (Map.Entry<String, MovieData> entry : map.entrySet()) {
                    if (entry.getKey().equals(slot)) {
                        boolean[] b = entry.getValue().getSeats();
                        for (int k = 0; k < 20; k++) {
                            if (bookings[k] == true)
                                b[k] = true;
                        }
                        entry.getValue().setSeats(b);
                    }
                }
            }
        }
    }

    public void setStdBookings(String movie, String slot, int tickets) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {

                Map<String, MovieData> map = list.get(i).getSlots();
                for (Map.Entry<String, MovieData> entry : map.entrySet()) {
                    if (entry.getKey().equals(slot)) {
                        entry.getValue().setStdnseats(entry.getValue().getStdnseats()-tickets);
                    }
                }
            }
        }
    }

    /**
     * Check to see if a given movies poster/image is stored
     * locally or not.
     *
     * @param movieid movie to check if currently stored locally
     * @return true if that movies poster is stored as object else null
     */
    public Image checkLocalImage(int movieid) {
        for (MovieImages image : movieimages) {
            if (image.getId() == movieid) {
                return image.getImage();
            }
        }
        return null;
    }

    /**
     * Return an array of all movie names
     *
     * @return array of all movie names to be played today
     */
    public String[] getMovieNames() {
        String[] names = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getName();
        }
        return names;
    }

    /**
     * Return the name of the movie that is currently playing on a particular
     * screen.
     *
     * @param screen screen to process
     * @return the name of the movie currently playing on the screen
     */
    public String getCurrentMovieName(String screen) {
        LocalDateTime now = LocalDateTime.now();
        int currenthour = now.getHour();
        String currentslot = getCurrentTime(currenthour);
        String currentmovie = findMovie(screen + "_" + currentslot);
        return currentmovie;
    }

    /**
     * Return the name of the next movie to be played on a particular screen
     *
     * @param screen screen to find the next movie for
     * @return name of the movie to be played next
     */
    public String getNextMovie(String screen) {
        LocalDateTime now = LocalDateTime.now();
        int forward = now.getHour() + 3;
        String slot = getCurrentTime(forward);
        String nextmovie = findMovie(screen + "_" + slot);
        return nextmovie;
    }

    /**
     * Return the name of a movie based entirely on the timeslot
     * passed to it
     *
     * @param timeslot timeslot to look for
     * @return name of movie that is playing in that timeslot
     */
    public String findMovie(String timeslot) {
        for (int i = 0; i < list.size(); i++) {
            String allslots = getMovieSlot(list.get(i).getName());
            String[] temp = allslots.split(" ");
            String[] slot;
            for (int j = 0; j < temp.length; j++) {
                slot = temp[j].split(":");
                if (slot[0].equals(timeslot)) {
                    return list.get(i).getName();
                }
            }
        }
        return "";
    }

    /**
     * Return the available number of seats for a given movie
     * that is currently playing on a screen.
     *
     * @param screen    screen on which the movie is being played
     * @param moviename movie to look for
     * @return string representation of the available seats
     */
    public String getAvailableSeats(String screen, String moviename) {
        String slot = getCurrentTimeslot(screen, false);
        String allslots = getMovieSlot(moviename);
        String[] temp = allslots.split(" ");
        String[] tmp;

        for (int j = 0; j < temp.length; j++) {
            tmp = temp[j].split(":");
            if (tmp[0].equals(slot)) {
                return tmp[1];
            }
        }
        return "";
    }

    /**
     * Return the current timeslot such as from SLOT1 to 12 to 3
     *
     * @param screen    the screen currently playing
     * @param formatted check to see which format the result is required in
     * @return the current numerical timeslot in string format
     */
    public String getCurrentTimeslot(String screen, boolean formatted) {
        LocalDateTime now = LocalDateTime.now();
        String slot = getCurrentTime(now.getHour());
        if (formatted == false) {
            return screen + "_" + slot;
        }

        if (slot.equals("SLOT1")) {
            return "12 To 3";
        } else if (slot.equals("SLOT2")) {
            return "3 To 6";
        } else if (slot.equals("SLOT3")) {
            return "6 To 9";
        } else if (slot.equals("SLOT4")) {
            return "9 To 12";
        }
        return "";
    }

    /**
     * Return the current slot category the system is in,
     * depending on the hour passed as argument
     *
     * @param currenthour
     * @return current slot the system is in
     */
    public String getCurrentTime(int currenthour) {
        //day                                       //night
        if ((currenthour >= 12 && currenthour < 15) || (currenthour >= 0 && currenthour < 3)) {
            return "SLOT1";
        } else if ((currenthour >= 15 && currenthour < 18) || (currenthour >= 3 && currenthour < 6)) {
            return "SLOT2";
        } else if ((currenthour >= 18 && currenthour < 21) || (currenthour >= 6 && currenthour < 9)) {
            return "SLOT3";
        } else if ((currenthour >= 21 && currenthour < 24) || (currenthour >= 9 && currenthour < 12)) {
            return "SLOT4";
        }
        return "error";
    }

    /**
     * Returns a string representation of all the slots booked
     * for a particular movie
     *
     * @param movie movie to find the slots for
     * @return string representation of all the slots
     */
    public String getMovieSlot(String movie) {
        String temp = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                Map<String, MovieData> map = list.get(i).getSlots();
                for (Map.Entry<String, MovieData> entry : map.entrySet()) {
                    temp += entry.getKey() + ":" + entry.getValue().getTotalseats() + " ";
                }
                return temp.trim();
            }
        }
        return "Error";
    }


    public String getSlotID(String movie) {
        String slot = checkoutController.getSelectedMovieSlot(checkoutController.selectedscreen, checkoutController.selectedtime);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {

                Map<String, MovieData> map = list.get(i).getSlots();
                for (Map.Entry<String, MovieData> entry : map.entrySet()) {
                    if (entry.getKey().equals(slot)) {
                        return "" + entry.getValue().getSlotid();
                    }
                }
            }
        }
        return "Error";
    }

    /**
     * Return the number of slots booked for a given movie
     *
     * @param movie movie to calculate the number of slots for
     * @return number of slots for that movie
     */
    public int getNumberOfSlots(String movie) {
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                Map<String, MovieData> map = list.get(i).getSlots();
                for (Map.Entry<String, MovieData> entry : map.entrySet()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Return the default movie which is simply the first 1
     * in the list
     *
     * @return name of first movie in the list
     */
    public String getDefaultMovie() {
        if (list.size() > 0) {
            return list.get(0).getName();
        }
        return "list is empty";
    }

    /**
     * Return the status of a movie
     *
     * @param movie movie to search for
     * @return status of the movie as string
     */
    public String getStatus(String movie) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                return "" + list.get(i).getStatus();
            }
        }
        return "Error";
    }

    /**
     * Return the Image object for a given movie
     *
     * @param movie movie to search for
     * @return the image object for that movie
     */
    public Image getImage(String movie) {
        Image image = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                image = list.get(i).getImage();
            }
        }
        return image;
    }

    /**
     * Return the title of a movie
     *
     * @param movie movie to search for
     * @return movie title
     */
    public String getTitle(String movie) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                return "" + list.get(i).getName();
            }
        }
        return "Error";
    }

    /**
     * Return the imdb rating for a movie
     *
     * @param movie movie to look for
     * @return its rating as a string
     */
    public String getRating(String movie) {
        String m = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                m = "" + list.get(i).getRating();
            }
        }
        return m;
    }

    /**
     * Return the duration for a given movie
     *
     * @param movie movie to look for
     * @return Duration for the movie
     */
    public String getDuration(String movie) {
        String m = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                m = "" + list.get(i).getDuration();
            }
        }
        return m;
    }

    /**
     * Return the number of repeats left for a movie
     *
     * @param movie movie to look for
     * @return number of repeats left as string
     */
    public String getRepeats(String movie) {
        String m = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                m = "" + list.get(i).getRepeats();
            }
        }
        return m;
    }

    /**
     * Return the ticket sold for a movie
     *
     * @param movie movie to look for
     * @return ticket sold for that movie as string
     */
    public String getTicketSold(String movie) {
        String m = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(movie)) {
                m = "" + list.get(i).getTicketsold();
            }
        }
        return m;
    }
}
