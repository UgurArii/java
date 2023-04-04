 
package chart;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author uğur
 */


public class Chart extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       PieChart pieChart = new PieChart();
pieChart.setData(getChartData());
primaryStage.setTitle("PieChart");
StackPane root = new StackPane();
root.getChildren().add(pieChart);
primaryStage.setScene(new Scene(root, 400, 250));
primaryStage.show();
    }
    
    private ObservableList<PieChart.Data> getChartData() {
ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
answer.addAll(new PieChart.Data("java", 15.57),
new PieChart.Data("C", 6.97),
new PieChart.Data("C++", 4.55),
new PieChart.Data("C#", 3.58),
new PieChart.Data("Python", 3.45),
new PieChart.Data("PHP", 3.38),
new PieChart.Data("Visual Basic .NET", 3.25));
return answer;
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
