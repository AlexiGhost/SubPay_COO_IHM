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
    
    /*public void initSauces() {
    	for(Sauce sauce : ComponentManagement.getSauces()){
    		this.textSauce.setText(sauce.getName());
    		this.imageSauce.setId(sauce.getPhoto());
    	}
        
    }Je sais pas encore comment importer des données*/
    
	public void goToGarnitures() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("009 Garnitures.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	public void next() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		if(ControllerMenu.getChoice()){ 
			Group acteur = new Group(); //Pas touche
			acteur.getChildren().add( //Pas touche
			FXMLLoader.load(getClass().getResource("011 Boissons (Menu).fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
			); //Pas touche
			visual.ControllerClient.setScene(acteur, "SUBPAY - Boissons"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
		}
		else if(ControllerBonjour.getOrder().getAuthCustomer()){
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		}
		else{
			Group acteur = new Group(); //Pas touche
			acteur.getChildren().add( //Pas touche
			FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
			); //Pas touche
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																	//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
		}
	}
}
