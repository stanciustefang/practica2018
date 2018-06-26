import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private Text t,t1;
    private double[] doubles1;
    @Override
    public void start(Stage primaryStage) {
        Button b=new Button("Alegeti vectorul de sortat");
        t=new Text();
        t1=new Text();
        primaryStage.setTitle("Bucket sort");
        b.setOnMouseClicked(getHandle(primaryStage));
        VBox vBox=new VBox();
        vBox.getChildren().addAll(b,t,t1);
        vBox.setAlignment(Pos.CENTER);
        BorderPane borderPane=new BorderPane();
        borderPane.setCenter(vBox);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setHeight(200);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
    public EventHandler<MouseEvent> getHandle(Stage s)
    {
        EventHandler<MouseEvent> eventHandler=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("Alegeti fisierul care contine vectorul de sortat");
                fileChooser.setInitialDirectory(new File("D:\\Projects\\BucketSort"));
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Fisiere .txt",".txt"));
                file=fileChooser.showOpenDialog(null);
                doubles1=getVectorFile();
                System.out.println(Arrays.toString(doubles1));
                if(doubles1!=null)
                {
                    t.setText("Inainte de sortare: "+Arrays.toString(getVectorFile()));
                    bucketSort=new BucketSort(doubles1);
                    t1.setText("Dupa sortare : "+bucketSort.getVector());
                    s.sizeToScene();
                }
            }
        };
        return eventHandler;
    }
    public double[] getVectorFile()
    {
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String [] elemente=br.readLine().split(" ");
            double[] doubles=new double[elemente.length];
            for(int i=0;i<elemente.length;i++)
            {
                try {
                    doubles[i]=Double.parseDouble(elemente[i]);
                }catch (NumberFormatException e)
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("A aparut erroare la citirea elementelor");
                    alert.showAndWait();
                }
            }
            return doubles;
        }
        catch (FileNotFoundException e)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Nu s-a gasit fisierul");
            alert.showAndWait();
        }
        catch (IOException ex)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("A aparut erroare la citirea din fisier");
            alert.showAndWait();
        }
        return null;
    }
}
