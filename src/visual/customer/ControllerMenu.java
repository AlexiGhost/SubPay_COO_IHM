package visual.customer;

import java.io.IOException;
import model.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class ControllerMenu {
	private static boolean choice;
	
	public void goToPainWithMenu() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		choice = true;
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("007 Pain.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Pain"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface o�
																//tu vas. Ca permet de changer le titre de la fen�tre (et �a marche B)  )
		
	}
	
	public void goToPainWithoutMenu() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		choice = false;
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("007 Pain.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.Controller.setScene(acteur, "SUBPAY - Pain"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface o�
																//tu vas. Ca permet de changer le titre de la fen�tre (et �a marche B)  )
	}
	public static boolean getChoice() {
		return choice;
	}
}
