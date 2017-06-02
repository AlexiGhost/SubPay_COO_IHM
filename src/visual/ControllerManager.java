package visual;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerManager extends Application {
	
	public void SwitchScene(Stage stage, String s) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	
    	Parent acteur = (Parent) loader.load(getClass().getResourceAsStream(s));
    	
    	Scene scene = new Scene(acteur, 1280.0, 720.0);
    	
    	stage.setTitle("SUBPAY-Accueil");
        stage.setScene(scene);
        stage.show();
	}
    
	public void start(Stage stage) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	
    	Parent acteur = (Parent) loader.load(getClass().getResource("manager/Authentification.fxml"));
    	
    	Scene scene = new Scene(acteur, 1280.0, 720.0);
    	
    	stage.setTitle("SUBPAY-Manager-Accueil");
        stage.setScene(scene);
        stage.show();
    }
	/**Launch the manager interface*/
    public static void initialize() {
    	launch();
    }
}
