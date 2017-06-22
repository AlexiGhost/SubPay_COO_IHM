package visual.customer;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import model.AuthentificatedCustomer;
import model.CustomerManagement;

public class SignUpController {
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
    
    private static AuthentificatedCustomer authCusto;
	
    //authCusto
    public static AuthentificatedCustomer getAuthCusto() {
		return authCusto;
	}
    public static void setAuthCusto(AuthentificatedCustomer custom) {
		authCusto = custom;
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
    //Verifie confirmation mot de passe
    public boolean checkPasswordConfirmation(){
    	if(PF_PasswordConfirmation.getText().equals(PF_password.getText()))
    		return true;
    	return false;
    }
    //Verifie conformite du mail
    public boolean checkMail(){
    	if(!TF_Mail.getText().equals("")){
    		
	    	try{
	    		authCusto.setMail(TF_Mail.getText());
	    		return true;
	    	}
	    	catch(Exception e){
	    		return false;
	    	}
    	}
    	else{
    		return true;
    	}
    }
  //Verifie conformite du telephone
    public boolean checkPhone(){
    	if(!TF_Phone.getText().equals("")){
	    	try{
	    		authCusto.setPhoneNumber(TF_Phone.getText());
	    		return true;
	    	}
	    	catch(Exception e){
	    		return false;
	    	}
    	}
    	else
    		return true;
    }
    //Verifie si une des check box cochee
    public boolean mailPhoneChoiceMade(){
    	if(CB_PhoneChoice.selectedProperty().get() || CB_MailChoice.selectedProperty().get())
    		return true;
    	return false;
    }
    //Verifie si le champs du mail est renseigne quand le mail est choisi
    public boolean mailChoiceCorrect(){
    	if(CB_MailChoice.selectedProperty().get()){
    		if(TF_Mail.getText().equals(""))
    			return false;
    		return true;
    	}
    	return true;
    }
  //Verifie si le champs du telephone est renseigne quand le telephone est choisi
    public boolean phoneChoiceCorrect(){
    	if(CB_PhoneChoice.selectedProperty().get()){
    		if(TF_Phone.getText().equals(""))
    			return false;
    		return true;
    	}
    	return true;
    }
    /**Efface les affichages d'erreurs*/
    public void clearError(){
    	BackgroundFill bfTrans = new BackgroundFill(null,null,null);
    	Background bTrans = new Background(bfTrans);
    	//Effacer les affichages d'erreurs
		T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
		PF_password.setBackground(bTrans);
		PF_PasswordConfirmation.setBackground(bTrans);
		TF_Mail.setBackground(bTrans);
		TF_Phone.setBackground(bTrans);
		CB_MailChoice.setBackground(bTrans);
		CB_PhoneChoice.setBackground(bTrans);
		T_MailPhoneMissing.setText("");
    }
    //Bouton "Inscription"
	public void goToAcceuilAuth() throws Exception {
		BackgroundFill bfRed = new BackgroundFill(Paint.valueOf("red"),null,null);
		Background bRed = new Background(bfRed);
		authCusto = new AuthentificatedCustomer();
		if(checkPassword() && checkPasswordConfirmation() && checkMail() && checkPhone() && mailPhoneChoiceMade() && mailChoiceCorrect() && phoneChoiceCorrect()){
			//Enregistrer les informations sur le client
			authCusto.setLastName(TF_LastName.getText());
			authCusto.setFirstName(TF_FirstName.getText());
			authCusto.setPassword(PF_password.getText());
			if(!TF_Mail.getText().equals(""))
				authCusto.setMail(TF_Mail.getText());
			if(CB_MailChoice.selectedProperty().get())
				authCusto.setMailChoice(true);
			if(!TF_Phone.getText().equals(""))
				authCusto.setPhoneNumber(TF_Phone.getText());
			if(CB_PhoneChoice.selectedProperty().get())
				authCusto.setPhoneChoice(true);
			//Enregistrer le client
			CustomerManagement.addCustomer(authCusto);
			CustomerManagement.exportCustomer("customer.xml");
			HelloController.getOrder().setAuthCustomer(true); 
			//Changement d'interface 
			Group acteur = new Group();
			acteur.getChildren().add(
			FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		}
		else{
			if(!checkPassword()){
				clearError();
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("RED"));
				PF_password.setBackground(bRed);
			}
			else if(!checkPasswordConfirmation()){
				clearError();
				PF_PasswordConfirmation.setBackground(bRed);
			}
			else if(!checkMail()){
				clearError();
				TF_Mail.setBackground(bRed);
				T_MailPhoneMissing.setText("Votre adresse mail n'est pas conforme");
			}
			else if(!checkPhone()){
				clearError();
				TF_Phone.setBackground(bRed);
				T_MailPhoneMissing.setText("Votre numéro de téléphone portable n'est pas conforme");
			}
			else if(!mailPhoneChoiceMade()){
				clearError();
				T_MailPhoneMissing.setText("Vous devez cocher au moins une des cases ci-dessus");
			}
			else if(!mailChoiceCorrect()){
				clearError();
				CB_MailChoice.setBackground(bRed);
				T_MailPhoneMissing.setText("Vous devez renseigner votre adresse mail");
			}
			else if(!phoneChoiceCorrect()){
				clearError();
				CB_PhoneChoice.setBackground(bRed);
				T_MailPhoneMissing.setText("Vous devez renseigner votre numéro de téléphone");
			}
		}
			
	}
	//Bouton "Accueil"
	public void goToAcceuilNotAuth() throws IOException {
		HelloController.getOrder().setAuthCustomer(false);
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("004 Accueil.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
	}
}
