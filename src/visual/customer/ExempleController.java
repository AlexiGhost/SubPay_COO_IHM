package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

/* Exemple de Controller pour une interface quelconque 
 * Mimi ---> tu pourras supprimer, d�s que tu auras compris
 * En tous les cas, tous tes Controller doivent �tre dans le dossier "customer"
 * Bon courage ! =)
 * */
public class ExempleController {
	
	public void toAccueil() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("04_AddEdit_Promo.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface o�
																//tu vas. Ca permet de changer le titre de la fen�tre (et �a marche B)  )
	}
}
