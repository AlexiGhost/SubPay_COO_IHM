package visual.customer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerBonjour {

	public void goToConnexion() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("002 Connexion.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Connexion"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
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
	
	public void goToInscription() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("003 Inscription.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Inscription"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}

}