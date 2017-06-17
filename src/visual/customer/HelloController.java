package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import model.product.Order;

public class HelloController {
	private static Order order = new Order();
	
	/***Methods***/
	
	//Order
	public static Order getOrder() {
		return order;
	}
	// Bouton "oui" pour connexion
	public void goToConnexion() throws IOException {
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("002 Connexion.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Connexion"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	//Bouton "non" pour l'accueil des clients non authentifié
	public void goToAcceuilNotAuth() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		order.setAuthCustomer(false);
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	//Bouton inscription pour aller à l'inscription
	public void goToInscription() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("003 Inscription.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Inscription"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}

}