package visual.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.*;

public class DrinkController implements Initializable {
	private static List<Drink> drinkList = new ArrayList<Drink>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
	
	@FXML
    private TilePane drinkTile;
	
	@FXML
    private Slider SL_IceCube;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayDrinks();
	}
	
	public void displayDrinks() {
		for (Drink drink : drinkList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Group
			Group d = new Group();
			d.setTranslateX(20 * X);
			d.setTranslateY(10 + Y);
			d.setFocusTraversable(true);
			d.setOnMouseClicked(MouseEvent -> goToHomeWithOrder(drink));
			
			//Titre garniture
			Text title = new Text(drink.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ drink.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ garnishTile
			d.getChildren().add(img);
			d.getChildren().add(title);
			d.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!drink.getAvailability()) {
				bordure.setOpacity(0.3);
				bordure.setFill(Color.YELLOWGREEN);
				title.setOpacity(0.3);
				img.setOpacity(0.3);
				Text succes = new Text("Victime de\nson succès");
				succes.setFont(new Font("Arial Black", 14));
				succes.setFill(Color.BLACK);
				succes.setOpacity(1);
				succes.setLayoutX(27);
				succes.setLayoutY(25);
				d.getChildren().add(succes);
			}
			
			//Si c'est une nouveauté
			if(drink.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				d.getChildren().add(nouveau);
			}
			
			drinkTile.getChildren().add(d);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static List<Drink> getDrinkList() {
		return drinkList;
	}
	
	
	public void goToSauces() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("010 Sauces.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Sauces"); 
	}
	public void goToHome(){
		Group acteur = new Group();
		if(HelloController.getOrder().getAuthCustomer()){ //Si le client est authentifié
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Authentifié");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{ //S'il n'y a pas de client authentifié
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void goToHomeWithOrder(Drink d){
		if(SL_IceCube.getValue() < 1){
			int dialogResult = JOptionPane.showConfirmDialog (null, "Etes-vous sûr de ne pas vouloir de glaçon ?","Warning", JOptionPane.YES_NO_OPTION);
	    	if(dialogResult == JOptionPane.YES_OPTION){
	    		MenuController.getMenu().setDrink(d);
	    		MenuController.getMenu().setIceCubeNb(Math.toIntExact(Math.round(SL_IceCube.getValue())));
	    		goToHome();
	    	}
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "Commande Validée"); 
			MenuController.getMenu().setDrink(d);
    		MenuController.getMenu().setIceCubeNb(Math.toIntExact(Math.round(SL_IceCube.getValue())));
    		goToHome();
		}
	}
	

}
