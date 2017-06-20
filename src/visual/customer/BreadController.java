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
import model.product.Drink;
import model.product.composants.Bread;

public class BreadController implements Initializable {
	private static List<Bread> breadList = new ArrayList<Bread>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
	
	@FXML
    private TilePane breadTile;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayBreads();
	}
	
	public void displayBreads() {
		for (Bread bread : breadList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Group
			Group b = new Group();
			b.setTranslateX(20 * X);
			b.setTranslateY(10 + Y);
			b.setFocusTraversable(true);
			b.setOnMouseClicked(MouseEvent -> goToRecipe(bread));
			
			//Titre Pain
			Text title = new Text(bread.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image Pain
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ bread.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ breadTile
			b.getChildren().add(img);
			b.getChildren().add(title);
			b.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!bread.getAvailability()) {
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
				b.getChildren().add(succes);
			}
			breadTile.getChildren().add(b);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static List<Bread> getBreadList() {
		return breadList;
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
	
	public void goToRecipe(Bread b) {
		if(b.getAvailability()){
			if(MenuController.getChoice())
				MenuController.getMenu().getProduct().setBread(b);
			else
				MenuController.getProduct().setBread(b);
			Group acteur = new Group();
			if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getClass().getName().equals("model.product.composants.Recipe")){
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("009 Garnitures.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("008 Recettes.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void goToMenu() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("005 Menu.fxml")) 
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
	}
}
