package visual.manager;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class ControllerAuthentification {
//------------------------------------------------
//Identifiants du manager
	String user = "admin";
	String password = "admin";
//------------------------------------------------
	@FXML private TextField TF_Login;
	@FXML private PasswordField TF_Password;
	@FXML private Button BTN_Connection;	
	@FXML private Text TEXT_ERROR;
	
	
	public void goToAccueil() throws IOException {
		if(TF_Password.getText().equals(user) && TF_Password.getText().equals(password)){
			Group acteur = new Group();
			acteur.getChildren().add(
					FXMLLoader.load(getClass().getResource("02_Accueil.fxml")));
			visual.ControllerManager.setScene(acteur, "SUBPAY - Accueil");
		} else {
			TEXT_ERROR.setText("Nom d'utilisateur ou mot de passe erronné");
		}
	}
	

}
