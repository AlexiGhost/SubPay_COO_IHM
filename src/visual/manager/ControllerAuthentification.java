package visual.manager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class ControllerAuthentification {
	
	public void toAccueil() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_AddEdit_Promo.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - Accueil");
	}
}
