package visual.customer;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.product.ComponentManagement;
import model.product.composants.Bread;
import model.product.composants.Sauce;
import visual.customer.ControllerMenu;

public class ControllerSauces {
	
    @FXML
    private Pane SaucePane;

    @FXML
    private ImageView imageSauce;

    @FXML
    private Text textSauce;

    @FXML
    private CheckBox checkSauce;

    @FXML
    private Button suivant;

    @FXML
    private Button retour;
    
    
	public void goToGarnitures() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("009 Garnitures.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures"); 
		}
	public void next() throws IOException {
		if(ControllerMenu.getChoice()){ 
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("011 Boissons (Menu).fxml")) 
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Boissons");
		}
		else if(ControllerBonjour.getOrder().getAuthCustomer()){
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		}
		else{
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) 
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
		}
	}
}
