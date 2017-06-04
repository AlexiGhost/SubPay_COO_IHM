package visual.manager;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;

public abstract class ControllerAccueil {
    
	//ATTRIBUTES
	
    @FXML
    private ListView<?> L_Recipe;

    @FXML
    private ListView<?> L_Bread;

    @FXML
    private ListView<?> L_Garnish;

    @FXML
    private ListView<?> L_Sauce;

    @FXML
    private ListView<?> L_Drink;

    @FXML
    private ListView<?> L_Dessert;

    @FXML
    private TableView<?> T_Promotion;
    @FXML
    private TableColumn<?, ?> TC_PromotionName;
    @FXML
    private TableColumn<?, ?> TC_PromotionDate;
    @FXML
    private TableColumn<?, ?> TC_PromotionCategory;
    @FXML
    private TableColumn<?, ?> TC_PromotionRecipe;

    //METHODS
    
    @FXML
    void addBread(ActionEvent event) {

    }

    @FXML
    void addDessert(ActionEvent event) {

    }

    @FXML
    void addDrink(ActionEvent event) {

    }

    @FXML
    void addGarnish(ActionEvent event) {

    }

    @FXML
    void addPromotion(ActionEvent event) {

    }

    @FXML
    void addRecipe(ActionEvent event) {

    }

    @FXML
    void addSauce(ActionEvent event) {

    }

    @FXML
    void delBread(ActionEvent event) {

    }

    @FXML
    void delDessert(ActionEvent event) {

    }

    @FXML
    void delDrink(ActionEvent event) {

    }

    @FXML
    void delGarnish(ActionEvent event) {

    }

    @FXML
    void delPromotion(ActionEvent event) {

    }

    @FXML
    void delRecipe(ActionEvent event) {

    }

    @FXML
    void delSauce(ActionEvent event) {

    }

    @FXML
    void editBread(ActionEvent event) {

    }

    @FXML
    void editDessert(ActionEvent event) {

    }

    @FXML
    void editDrink(ActionEvent event) {

    }

    @FXML
    void editGarnish(ActionEvent event) {

    }

    @FXML
    void editPromotion(ActionEvent event) {

    }

    @FXML
    void editRecipe(ActionEvent event) {

    }

    @FXML
    void editSauce(ActionEvent event) {

    }

    @FXML
    void goToAuthentification(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("01_Authentification.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - Authentification");
	}
}
