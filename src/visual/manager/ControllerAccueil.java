package visual.manager;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;

public abstract class ControllerAccueil {
    
    @FXML
    private Button BTN_Disconnect;
    @FXML
    private TableView<?> T_Recipe;
    @FXML
    private TableColumn<?, ?> TC_NomRecipe;
    @FXML
    private MenuItem BTN_EditRecipe;
    @FXML
    private MenuItem BTN_DelRecipe;
    @FXML
    private Button BTN_AddRecipe;
    @FXML
    private TableView<?> T_Garnish;
    @FXML
    private TableColumn<?, ?> TC_NomGarnish;
    @FXML
    private MenuItem BTN_EditGarnish;
    @FXML
    private MenuItem BTN_DelGarnish;
    @FXML
    private Button BTN_AddGarnish;
    @FXML
    private TableView<?> T_Bread;
    @FXML
    private TableColumn<?, ?> TC_NomBread;
    @FXML
    private MenuItem BTN_EditBread;
    @FXML
    private MenuItem BTN_DelBread;
    @FXML
    private Button BTN_AddBread;
    @FXML
    private TableView<?> T_Sauce;
    @FXML
    private TableColumn<?, ?> TC_NomSauce;
    @FXML
    private MenuItem BTN_EditSauce;
    @FXML
    private MenuItem BTN_DelSauce;
    @FXML
    private Button BTN_AddSauce;
    @FXML
    private TableView<?> T_Drink;
    @FXML
    private TableColumn<?, ?> TC_NomDrink;
    @FXML
    private MenuItem BTN_EditDrink;
    @FXML
    private MenuItem BTN_DelDrink;
    @FXML
    private Button BTN_AddDrink;
    @FXML
    private TableView<?> T_Dessert;
    @FXML
    private TableColumn<?, ?> TC_NomDessert;
    @FXML
    private MenuItem BTN_EditDessert;
    @FXML
    private MenuItem BTN_DelDessert;
    @FXML
    private Button BTN_AddDessert;
    @FXML
    private TableView<?> T_Promo;
    @FXML
    private TableColumn<?, ?> TC_PromoName;
    @FXML
    private TableColumn<?, ?> TC_PromoDate;
    @FXML
    private TableColumn<?, ?> TC_PromoCategory;
    @FXML
    private TableColumn<?, ?> TC_PromoRecipe;
    @FXML
    private MenuItem BTN_EditPromo;
    @FXML
    private MenuItem BTN_DelPromo;
    @FXML
    private Button BTN_AddPromo;

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
    void addPromo(ActionEvent event) {

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
    void delPromo(ActionEvent event) {

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
    void editPromo(ActionEvent event) {

    }

    @FXML
    void editRecipe(ActionEvent event) {

    }

    @FXML
    void editSauce(ActionEvent event) {

    }

	public void goToAuthentification() throws IOException {
		Group acteur = new Group();
		
    	acteur.getChildren().add(
    			FXMLLoader.load(getClass().getResource("01_Authentification.fxml"))
    			);
		visual.Controller.setScene(acteur, "SUBPAY - Authentification");
	}
}
