package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class AuthHomeController {

	//TODO Afficher le nom et prénom du client connecté
	//TODO Afficher les promos, nouveautés, commandes enregistrées, commande en cours
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
	//Bouton "Déconnexion"
	public void goToAcceuilNotAuth() throws IOException {
		HelloController.getOrder().setAuthCustomer(false);
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) 
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
	}
	//Bouton "Allergènes"
	public void goToAllergen() throws IOException {
		AllergenController.setPreviousUI("004 Accueil.fxml");
		AllergenController.setPreviousTitle("SUBPAY - Accueil");
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("006 Allergies.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Allergènes");
	}
}
