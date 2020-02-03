package Cashier;

import Administrator.*;
import Cashier.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Benny
 */
public class cashier extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root;        
        root = FXMLLoader.load(getClass().getResource("cashier_panel.fxml"));
        Scene scene = new Scene(root);
       scene.setFill(Color.TRANSPARENT);
      stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
