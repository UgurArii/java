package playingaudio;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.media.*;
import javafx.geometry.*;
import java.io.*;

/**
 *
 * @author uğur
 */

public class PlayingAudio extends Application {

    private final String PATH = "E:\\zazaca\\müzik\\AHMET ASLAN-DiREGA ZERE MI.mp3";

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Play Audio");
        btn.setOnAction(e -> playMedia());

        HBox box = new HBox(20, btn);
        box.setPadding(new Insets(20));

        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Media Player");
        primaryStage.show();
    }

    private void playMedia() {
        File f = new File(PATH);
        Media media = new Media(f.toURI().toString());
        MediaPlayer mplayer = new MediaPlayer(media);
        mplayer.setAutoPlay(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
