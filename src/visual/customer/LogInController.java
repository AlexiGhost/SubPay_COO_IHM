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

public class LogInController {
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
	
    /**
     * Permet d'accéder à la page d'accueil en cliquant sur le bouton "Connexion"
     */
	public void goToAcceuilAuth() {
		try{
		for (AuthentificatedCustomer customer : CustomerManagement.getCustomers()) {
            if((customer.getMail().equals(TF_Username.getText()) || customer.getPhoneNumber().equals(TF_Username.getText())) && customer.getPassword().equals(PF_password.getText())){
            	SignUpController.setAuthCusto(customer);
            	HelloController.getAllergenList().addAll(customer.getAllergens());
            	HelloController.getOrder().setAuthCustomer(true); //On enregistre l'authentification du client
        		Group acteur = new Group();
        		acteur.getChildren().add(
        		FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")) 
        		); 
        		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
            } else {
            	TEXT_ERROR.setText("Identifiant ou mot de passe erronnés");
            }
        }
		}catch(IOException e){}
	}
	
	public void goToInscription() {
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("003 Inscription.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Inscription");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToHome() {
		Group acteur = new Group();
		try {
			HelloController.getOrder().setAuthCustomer(false);
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
