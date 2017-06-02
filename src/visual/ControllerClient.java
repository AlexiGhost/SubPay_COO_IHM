package visual;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerClient extends Application {
	
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
    	
<<<<<<< HEAD
    	Parent acteur = (Parent) loader.load(getClass().getResource("customer/001 Bonjour.fxml"));
=======
    	Parent acteur = (Parent) loader.load(getClass().getResource("manager/Accueil.fxml"));
>>>>>>> 9fd227ae7e637757b5af4ba2b3e5bc37d097b14b
    	
    	Scene scene = new Scene(acteur, 1280.0, 720.0);
    	
    	//switchScene(stage);
    	stage.setTitle("SUBPAY-Accueil");
        stage.setScene(scene);
        stage.show();
    }
	
	/**Launch the client interface*/
    public static void initialize() {
    	launch();
    }
	public void switchScene(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        Parent P = (Parent) loader.load(getClass().getResource("customer/004 Accueil.fxml"));
        Scene scene = new Scene(P);
        stage.setScene(scene);
        stage.show();
    }
    

}
