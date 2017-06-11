package visual.customer;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import model.AuthentificatedCustomer;
import model.CustomerManagement;

public class ControllerConnexion {
    @FXML
    private TextField TF_Username;

    @FXML
    private TextField TF_Password;
	
    //Bouton "Connexion" pour aller à l'accueil authentifié
	public void goToAcceuilAuth() throws IOException {
		for (AuthentificatedCustomer customer : CustomerManagement.getCustomers()) {
            if(customer.getLogin().equals(TF_Username.getText()) && customer.getPassword().equals(TF_Password.getText())){
            	ControllerBonjour.getOrder().setAuthCustomer(true); //On enregistre l'authentification du client
        		Group acteur = new Group(); //Pas touche
        		acteur.getChildren().add( //Pas touche
        		FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
        		); //Pas touche
        		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
            } else {
                //TODO afficher une erreur (je m'en occuperais)
            }
        }
	}
	
	public void goToInscription() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("003 Inscription.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Inscription"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	
}
