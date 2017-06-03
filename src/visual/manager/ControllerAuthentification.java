package visual.manager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;

public class ControllerAuthentification {
	
	public void goToAccueil() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_AddEdit_Promo.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - Accueil");
	}
}
