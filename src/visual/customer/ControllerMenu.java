package visual.customer;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import model.product.Menu;
import model.product.Product;

public class ControllerMenu {
	private static boolean choice;
	private static Product product;
	private static Menu menu;
	
	public void goToPainWithMenu() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		choice = true;
		menu = new Menu();
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("007 Pain.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Pain"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
		
	}
	
	public void goToPainWithoutMenu() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		product = new Product();
		choice = false;
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("007 Pain.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Pain"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	public static boolean getChoice() {
		return choice;
	}
}
