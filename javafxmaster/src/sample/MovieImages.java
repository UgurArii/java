package sample;
import javafx.scene.image.Image;

public class MovieImages {
    private int id;
    private Image image=null;

    //Defult Contructor
    public MovieImages(int movieid, Image movieimage){
        id = movieid;
        image = movieimage;
    }

    //************************************************//
    //   Getter methods for the movie ID and Image    //
    //                                                //
    //************************************************//
    public int getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }
}
