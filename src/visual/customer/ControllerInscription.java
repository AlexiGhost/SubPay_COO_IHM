package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import model.product.Order;

public class ControllerInscription {
	
	public void goToAcceuilAuth() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Order order = new Order(true); //Je n'arrive pas à utiliser l'objet ailleurs que dans cette méthode
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Accueil Auth"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	
	public void goToAcceuilNotAuth() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Order order = new Order(false);
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Accueil"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
}
