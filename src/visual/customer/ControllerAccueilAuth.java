package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class ControllerAccueilAuth {

	public void goToPayer() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("012 Payer.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Paiement"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	
	public void goToMenu() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("005 Menu.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Menu"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	
	public void goToAcceuilNotAuth() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Accueil"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
}
