package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class AuthHomeController {

	//TODO Afficher le nom et pr�nom du client connect�
	// Bouton "Payer"
	public void goToPayer() throws IOException { 
		Group acteur = new Group(); 
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("012 Payer.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Paiement");
	}
	//Bouton "Composer"
	public void goToMenu() throws IOException { 
		Group acteur = new Group(); 
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("005 Menu.fxml")) 
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
	}
	//Bouton "D�connexion"
	public void goToAcceuilNotAuth() throws IOException {
		HelloController.getOrder().setAuthCustomer(false);
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) 
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
	}
}
