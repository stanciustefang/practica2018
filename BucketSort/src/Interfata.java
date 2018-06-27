import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.util.Arrays;

public class Interfata extends Application {
    private File file;
    private BucketSort bucketSort;
    private Text t, t1;
    private TextArea textArea;
    private double[] doubles1;
    private boolean neg;
    @Override
    public void start(Stage primaryStage) {
        Button b = new Button("Alegeti vectorul de sortat");
        t = new Text();
        t1 = new Text();
        textArea=new TextArea();
        textArea.setMinSize(400,300);
        textArea.setMaxSize(400,300);
        textArea.setWrapText(true);
        primaryStage.setTitle("Bucket sort");
        b.setOnMouseClicked(getHandle(primaryStage));
        VBox vBox = new VBox();
        vBox.setSpacing(30);
        vBox.getChildren().addAll(b,textArea);
        vBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.show();
    }

    public EventHandler<MouseEvent> getHandle(Stage s) {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Alegeti fisierul care contine vectorul de sortat");
                fileChooser.setInitialDirectory(new File("D:\\facultate\\practicaAnul2\\practica2018\\BucketSort"));
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Fisiere .txt", ".txt"));
                file = fileChooser.showOpenDialog(null);
                doubles1 = getVectorFile();
                System.out.println(Arrays.toString(doubles1));
                if (doubles1 != null) {
                    bucketSort = new BucketSort(doubles1,neg);
                    textArea.appendText("Inainte de sortare: " + Arrays.toString(getVectorFile())+"\r\nDupa sortare: " + bucketSort.getVector()+"\r\n\r\n");
                }
            }
        };
        return eventHandler;
    }

    public double[] getVectorFile() {
        if (file != null)
        {
            neg=false;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String[] elemente = br.readLine().split(" ");
                double[] doubles = new double[elemente.length];
                for (int i = 0; i < elemente.length; i++) {
                    try {
                        doubles[i] = Double.parseDouble(elemente[i]);
                        if(Double.parseDouble(elemente[i])<0)
                            neg=true;
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("A aparut erroare la citirea elementelor");
                        alert.showAndWait();
                    }
                }
                return doubles;
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Nu s-a gasit fisierul");
                alert.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("A aparut erroare la citirea din fisier");
                alert.showAndWait();
            }
        }
        return null;
    }
}
