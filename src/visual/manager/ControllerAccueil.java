package visual.manager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public abstract class ControllerAccueil {
    
	public void goToAuthentification() throws IOException {
		Group acteur = new Group();
		
    	acteur.getChildren().add(
    			FXMLLoader.load(getClass().getResource("01_Authentification.fxml"))
    			);
		visual.Controller.setScene(acteur, "SUBPAY - Authentification");
	}
}
