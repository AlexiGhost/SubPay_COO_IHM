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
import model.product.composants.Garnish;

public class ControllerGarnitures implements Initializable {
	private static List<Garnish> garnishList = new ArrayList<Garnish>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
    @FXML
    private TilePane garnishTile;
	
	
	public static List<Garnish> getGarnishList() {
		return garnishList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayGarnish();
	}
	
	public void displayGarnish() {
		for (Garnish garnish : garnishList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Group
			Group gar = new Group();
			gar.setTranslateX(20 * X);
			gar.setTranslateY(10 + Y);
			gar.setFocusTraversable(true);
			gar.setOnMouseClicked(MouseEvent -> myGarnishes(bordure, garnish));
			
			//Titre garniture
			Text title = new Text(garnish.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ garnish.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ garnishTile
			gar.getChildren().add(img);
			gar.getChildren().add(title);
			gar.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!garnish.getAvailability()) {
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
				gar.getChildren().add(succes);
			}
			garnishTile.getChildren().add(gar);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static void myGarnishes(Rectangle rec, Garnish G) {
		if(G.getAvailability()) {
			if(rec.getStroke().equals(Color.LIGHTGREEN)) {
				rec.setStroke(Color.RED);
				if(ControllerMenu.getChoice())
					ControllerMenu.getMenu().getProduct().addGarnish(G);
				else
					ControllerMenu.getProduct().addGarnish(G);
			}
			else if(rec.getStroke().equals(Color.RED)) {
				rec.setStroke(Color.LIGHTGREEN);
				if(ControllerMenu.getChoice())
					ControllerMenu.getMenu().getProduct().delGarnish(G);
				else
					ControllerMenu.getProduct().delGarnish(G);
			}
		}
	}	
	
	public void goToSauces() throws IOException {
		X = 1;
		Y = 0;
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("010 Sauces.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Sauces");
																
	}
	public void goToRecettes() throws IOException {
		X = 1;
		Y = 0;
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("008 Recettes.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
	}
}
