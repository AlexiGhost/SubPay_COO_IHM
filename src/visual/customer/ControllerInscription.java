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
    
    private AuthentificatedCustomer authCusto = new AuthentificatedCustomer();
	
    //authCusto
    public AuthentificatedCustomer getAuthCusto() {
		return authCusto;
	}
    public void setAuthCusto(AuthentificatedCustomer authCusto) {
		this.authCusto = authCusto;
	}
    //V�rifier mot de passe
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
    //Bouton "Inscription"
	public void goToAcceuilAuth() throws Exception {
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
			ControllerBonjour.getOrder().setAuthCustomer(true); 
			T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
			System.out.println("Enregistr� !");
			//Changement d'interface 
			Group acteur = new Group();
			acteur.getChildren().add(
			FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		}
		else{
			BackgroundFill bfRed = new BackgroundFill(Paint.valueOf("red"),null,null);
			Background bRed = new Background(bfRed);
			BackgroundFill bfTrans = new BackgroundFill(null,null,null);
			Background bTrans = new Background(bfTrans);
			//TODO Enlever le rouge des erreurs suivantes
			if(!checkPassword()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("RED"));
				PF_password.setBackground(bRed);
			}
			else if(!checkPasswordConfirmation()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
				PF_password.setBackground(bTrans);
				//Erreurs
				PF_PasswordConfirmation.setBackground(bRed);
			}
			else if(!checkMail()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
				PF_password.setBackground(bTrans);
				PF_PasswordConfirmation.setBackground(bTrans);
				//Erreurs
				TF_Mail.setBackground(bRed);
				T_MailPhoneMissing.setText("Votre adresse mail n'est pas conforme");
			}
			else if(!checkPhone()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
				PF_password.setBackground(bTrans);
				PF_PasswordConfirmation.setBackground(bTrans);
				TF_Mail.setBackground(bTrans);
				//Erreurs
				TF_Phone.setBackground(bRed);
				T_MailPhoneMissing.setText("Votre num�ro de t�l�phone portable n'est pas conforme");
			}
			else if(!mailPhoneChoiceMade()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
				PF_password.setBackground(bTrans);
				PF_PasswordConfirmation.setBackground(bTrans);
				TF_Mail.setBackground(bTrans);
				TF_Phone.setBackground(bTrans);
				//Erreurs
				T_MailPhoneMissing.setText("Vous devez cocher au moins une des cases ci-dessus");
			}
			else if(!mailChoiceCorrect()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
				PF_password.setBackground(bTrans);
				PF_PasswordConfirmation.setBackground(bTrans);
				TF_Mail.setBackground(bTrans);
				TF_Phone.setBackground(bTrans);
				//Erreurs
				CB_MailChoice.setBackground(bRed);
				T_MailPhoneMissing.setText("Vous devez renseigner votre adresse mail");
			}
			else if(!phoneChoiceCorrect()){
				T_PasswordCondition.setFill(javafx.scene.paint.Paint.valueOf("black"));
				PF_password.setBackground(bTrans);
				PF_PasswordConfirmation.setBackground(bTrans);
				TF_Mail.setBackground(bTrans);
				TF_Phone.setBackground(bTrans);
				CB_MailChoice.setBackground(bTrans);
				//Erreurs
				CB_PhoneChoice.setBackground(bRed);
				T_MailPhoneMissing.setText("Vous devez renseigner votre num�ro de t�l�phone");
			}
		}
			
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
