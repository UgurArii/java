package htmljavafx;
 
import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.concurrent.Worker.State;
import javafx.scene.*;
import javafx.scene.web.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.*;


public class HtmlJavaFx extends Application {
    String url = "http://weather.yahooapis.com/forecastrss?p=USMD0033&u=f";
    int refreshCountdown = 60;
    @Override
    public void start(Stage stage) {
     //create the scene
     stage.setTitle("Html-JavaFX");
     Group root = new Group();
     Scene scene = new Scene(root, 460, 340);
     
     final WebEngine webEngine = new WebEngine();
     
     StringBuilder template = new StringBuilder();
     template.append("<head>\n");
     template.append("<style type=\"text/css\">body {backgroundcolor:#b4c8ee;}</style>\n");
     template.append("</head>\n");
     template.append("<body id='weather_background'>");
     
     final String fullHtml = template.toString();
     
     final WebView webView = new WebView();
     
     IntegerProperty countDown = new SimpleIntegerProperty(refreshCountdown);
     countDown.addListener(new ChangeListener<Number>(){
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue,
Number newValue){
             // when change occurs on countDown call JavaScript to update text in
//HTMLwebView.getEngine().executeScript("document.getElementById('countdown').innerHTML = 'Seconds till refresh: " + newValue + "'");
if (newValue.intValue() == 0) {
webEngine.reload();
}
}
         });
     
     final Timeline timeToRefresh = new Timeline();
     timeToRefresh.getKeyFrames().addAll(
     new KeyFrame(Duration.ZERO, new KeyValue(countDown, refreshCountdown)),
     new KeyFrame(Duration.seconds(refreshCountdown), new KeyValue(countDown, 0))
     
     );
    
     webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>(){
         @Override
         public void changed(ObservableValue<? extends State> observable, State oldValue,
State newValue){
             System.out.println("done!" + newValue.toString());
             if(newValue != State.SUCCEEDED){
                 return;
             }
             
             //request 200 ok
             Weather weather = parse(webEngine.getDocument());
             
             StringBuilder locationText = new StringBuilder();
             locationText.append("<br>")
                     .append(weather.city)
                     .append(", ")
                     .append(weather.region)
                     .append(" ")
                     .append(weather.country)
                     .append("</b><br />\n");
             String timeOfWeatherTextDiv = "<b id=\"timeOfWeatherText\">"
                     + weather.dateTimeStr + "</b><br/>\n";
             String countdownText = "<b id=\"countdown\"></b><br />\n";
            webView.getEngine().loadContent(fullHtml + locationText.toString() +
            timeOfWeatherTextDiv +
            countdownText +
            weather.htmlDescription);
            System.out.println(fullHtml + locationText.toString() +
            timeOfWeatherTextDiv +
            countdownText +
            weather.htmlDescription);
            timeToRefresh.playFromStart();
             
         }
     });
     root.getChildren().addAll(webView);
     
     stage.setScene(scene);
     stage.show();
     
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private static String obtainAttribute(NodeList nodelist, String attribute){
        String attr = nodelist
                .item(0)
                .getAttributes()
                .getNamedItem(attribute)
                .getNodeValue()
                .toString();
        return attr;
    }
    
    private static Weather parse(Document doc){
        NodeList currWeatherLocation =
                doc.getElementsByTagName("http://xml.weather.yahoo.com/ns/rss/1.0\", \"location\")");
        Weather weather = new Weather();
        weather.city = obtainAttribute(currWeatherLocation, "city");
        weather.region = obtainAttribute(currWeatherLocation, "region");
        weather.country = obtainAttribute(currWeatherLocation, "country");
        NodeList currWeatherCondition =
        doc.getElementsByTagNameNS("http://xml.weather.yahoo.com/ns/rss/1.0", "condition");
        weather.dateTimeStr = obtainAttribute(currWeatherCondition, "date");
        weather.currentWeatherText = obtainAttribute(currWeatherCondition, "text");
        weather.temperature = obtainAttribute(currWeatherCondition, "temp");
        String forcast = doc.getElementsByTagName("description")
        .item(1)
        .getTextContent();
        weather.htmlDescription = forcast;
        return weather;
    }
}
class Weather {
String dateTimeStr;
String city;
String region;
String country;
String currentWeatherText;
String temperature;
String htmlDescription;
}
