import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.File;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;


public class Main extends Application {



    public void start(Stage stage) throws IOException {


        stage.setWidth(600);
        stage.setHeight(400);
        stage.setMinWidth(300);
        stage.setMinHeight(300);
        stage.setTitle("Using FXML");
        
         FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Controller.fxml"));
         VBox root = loader.load();

         Scene scene = new Scene(root);
         stage.setScene(scene);

        stage.show();

    }

}
