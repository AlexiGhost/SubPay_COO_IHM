package visual.manager;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;

public class ControllerAuthentification {
	
	@FXML
	private TextField TF_Login;
	@FXML
	private Button BTN_Connection;	
	@FXML
	private PasswordField TF_Password;

	public void goToAccueil() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_AddEdit_Promo.fxml"))); //Page4 le temps de fixer les prob de la 2. Alexi
		visual.Controller.setScene(acteur, "SUBPAY - Accueil");
	}
	

}
