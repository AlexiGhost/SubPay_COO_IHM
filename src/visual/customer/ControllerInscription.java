package visual.customer;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import model.AuthentificatedCustomer;
import model.product.Order;

public class ControllerInscription {
	@FXML
    private Button valider;

    @FXML
    private Button accueil;

    @FXML
    private TextField TF_LastName;

    @FXML
    private TextField TF_FirstName;

    @FXML
    private PasswordField PF_password;

    @FXML
    private PasswordField PF_PasswordConfirmation;

    @FXML
    private TextField TF_Phone;

    @FXML
    private TextField TF_Mail;

    @FXML
    private CheckBox CB_PhoneChoice;

    @FXML
    private CheckBox CB_MailChoice;

    @FXML
    private Text T_PasswordCondition;

    @FXML
    private Text T_MailPhoneMissing;
    
    private AuthentificatedCustomer authCusto;
	
    //authCusto
    public AuthentificatedCustomer getAuthCusto() {
		return authCusto;
	}
    public void setAuthCusto(AuthentificatedCustomer authCusto) {
		this.authCusto = authCusto;
	}
    //Vérifier mot de passe
    public boolean checkPassword(){
    	try{
    		authCusto.setPassword(PF_password.getText());
    		return true;
    	}
    	catch(Exception e){
    		return false;
    	}
    }
    //Bouton "Inscription"
	public void goToAcceuilAuth() throws IOException {
		if(checkPassword()){
			ControllerBonjour.getOrder().setAuthCustomer(true); 
			Group acteur = new Group();
			acteur.getChildren().add(
			FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		}
		else
			T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("RED"));
	}
	//Bouton "Accueil"
	public void goToAcceuilNotAuth() throws IOException {
		ControllerBonjour.getOrder().setAuthCustomer(false);
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
	}
}
