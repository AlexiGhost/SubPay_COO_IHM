package visual.customer;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.AuthentificatedCustomer;
import model.CustomerManagement;

public class ControllerConnexion {
    @FXML
    private Button connexion;

    @FXML
    private Button inscription;

    @FXML
    private TextField TF_Username;

    @FXML
    private TextField PF_password;

    @FXML
    private Text TEXT_ERROR;
	
    //Bouton "Connexion" pour aller à l'accueil authentifié
	public void goToAcceuilAuth() {
		System.out.println("hello");
		System.out.println(PF_password.getText());
		try{
		for (AuthentificatedCustomer customer : CustomerManagement.getCustomers()) {
            if((customer.getMail().equals(TF_Username.getText()) || customer.getPhoneNumber().equals(TF_Username.getText())) && customer.getPassword().equals(PF_password.getText())){
            	System.out.println("bon");
            	ControllerBonjour.getOrder().setAuthCustomer(true); //On enregistre l'authentification du client
        		Group acteur = new Group();
        		acteur.getChildren().add(
        		FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
        		); 
        		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
            } else {
            	TEXT_ERROR.setText("Identifiant ou mot de passe erronnés");
            	System.out.println(TF_Username.getText());
            }
        }
		}catch(IOException e){}
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
