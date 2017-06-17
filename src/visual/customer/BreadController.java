package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class BreadController {
	
	public void goToRecettes() throws IOException {
			Group acteur = new Group();
			acteur.getChildren().add( 
					FXMLLoader.load(getClass().getResource("008 Recettes.fxml"))
					);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
	}
	
	public void goToMenu() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("005 Menu.fxml")) 
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
	}
}
