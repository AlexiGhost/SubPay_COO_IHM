package visual.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
	private static Rectangle	redOne = new Rectangle();
	
	@FXML
    private TilePane drinkTile;
	
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
			d.setOnMouseClicked(MouseEvent -> myDrink(bordure, drink));
			
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
			drinkTile.getChildren().add(d);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static void myDrink(Rectangle rec, Drink drink) {
		if(drink.getAvailability()) {
			rec.setStroke(Color.RED);
			if(redOne != null){
				redOne.setStroke(Color.LIGHTGREEN);
			}
			redOne = rec;
			MenuController.getMenu().setDrink(drink);
		}
	}	
	
	public static List<Drink> getDrinkList() {
		return drinkList;
	}
	
	
	public void goToSauces() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("010 Sauces.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Sauces"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	public void goToAccueil() throws IOException {
		if(HelloController.getOrder().getAuthCustomer()){
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
