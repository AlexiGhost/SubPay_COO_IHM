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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.product.ComponentManagement;
import model.product.Product;
import model.product.composants.*;
import model.product.*;
//TODO Afficher la commande en cours
//TODO Gérer les allergènes 
//TODO Gérer les commande enregistrées
public class HomeController implements Initializable {

	private static 	List<Promotion>	ListPromo = new ArrayList<Promotion>();
	private static 	List<Composant>	ListNew = new ArrayList<Composant>();
	private static 	boolean 		newPromo = false;
	private static 	Composant		selectedComponent;
	private static  String			selectedCategorie = null;
	
    @FXML
    private 		TilePane 				promoTiled;
    @FXML
    private 		TilePane 				newTiled;
    @FXML
    private 		TilePane 				orderTilePane;
 
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectedCategorie = null;
		affichePromo();
		displayOrder();
	}
	
	public static List<Composant> getListNew() {
		return ListNew;
	}
	public static List<Promotion> getListPromo() {
		return ListPromo;
	}
	
	public static boolean getNewPromo(){
		return newPromo;
	}
	
	public static String getSelectedCategorie() {
		return selectedCategorie;
	}
	
	public static Composant getSelectedComponent() {
		return selectedComponent;
	}
	
	public void affichePromo() {
		for (Promotion promotion : ListPromo) {
			//Group
			Group promo = new Group();
			promo.setFocusTraversable(true);
			promo.setOnMouseClicked(MouseEvent -> {
				try {
					if(!promotion.getRecipe().equals("")){
						Recipe recipe = new Recipe();
						for (Recipe recipeMana : ComponentManagement.getRecipes()) {
							if(recipeMana.getName().equals(promotion.getRecipe()))
								recipe = recipeMana;
						}
						newPromoChosen(recipe);
					}
					else{
						newPromoCatChosen(promotion.getCategory());
					}						
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Titre promotion
			Text title = new Text(promotion.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Pourcentage
			Text percent = new Text("-" + String.valueOf(promotion.getPercentage()) + " % \n");
			percent.setFont(new Font("Arial Black", 30));
			percent.setFill(Color.FORESTGREEN);
			percent.setLayoutY(30);
			percent.setLayoutX(20);
			
			//Descriptif
			Text comment = new Text("sur " + promotion.getCategory() + promotion.getRecipe() + " !");
			comment.setLayoutX(8);
			comment.setLayoutY(50);
			
			//Exclu Client Authentifié
			if(promotion.getAuthCustomer()) {
				Text client = new Text("Connectez-vous \npour en profiter !");
				client.setFill(Color.SANDYBROWN);
				client.setLayoutX(30);
				client.setLayoutY(70);
				client.setFont(Font.font("Arial", FontWeight.BOLD, 12));
				promo.getChildren().add(client);
			}
			
			//MAJ promoTiled
			promo.getChildren().add(comment);
			promo.getChildren().add(percent);
			promo.getChildren().add(title);
			promo.getChildren().add(bordure);
			promoTiled.getChildren().add(promo);
		}
		
		for (Composant nouveaute : ListNew) {
			//Group
			Group nouveau = new Group();
			nouveau.setFocusTraversable(true);
			nouveau.setOnMouseClicked(MouseEvent -> {
				try {
					newPromoChosen(nouveaute);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 150);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Titre nouveauté
			Text title = new Text(nouveaute.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image nouveauté
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+nouveaute.getPhoto()));
			img.setFitHeight(150);
			img.setFitWidth(150);
			nouveau.getChildren().add(img);
			
			//MAJ promoTiled
			nouveau.getChildren().add(title);
			nouveau.getChildren().add(bordure);
			newTiled.getChildren().add(nouveau);
			
		}
	}
	
	public void displayOrder(){
		//Pour l'affichage des produits
		for (Product product : HelloController.getOrder().getProducts()) {
			//Si c'est une assiette
			if(product.getPlate()){
				Text textTitle = new Text("Plat "+product.getRecipe().getName()+" ("+product.getSize()+")\t"+product.getRecipe().getPrice()+"€");
				textTitle.setFont(new Font("Arial Black",16));
				textTitle.setWrappingWidth(280);
				orderTilePane.getChildren().add(textTitle);

			}else{
				Text textTitle = new Text("Sandwich "+product.getRecipe().getName()+" ("+product.getSize()+")\t"+product.getRecipe().getPrice()+"€");
				textTitle.setFont(new Font("Arial Black",16));
				textTitle.setWrappingWidth(280);
				orderTilePane.getChildren().add(textTitle);
				
				Text textBread = new Text("\t"+product.getBread().getName());
				textBread.setWrappingWidth(280);
				orderTilePane.getChildren().add(textBread);
			}
			for (Garnish garnish : product.getGarnishs()) {
				Text textGarnish = new Text("\t"+garnish.getName());
				textGarnish.setWrappingWidth(280);
				orderTilePane.getChildren().add(textGarnish);
			}
			for (Sauce sauce : product.getSauces()) {
				Text textSauce = new Text("\t"+sauce.getName());
				textSauce.setWrappingWidth(280);
				orderTilePane.getChildren().add(textSauce);
			}
			Line separation = new Line();
			separation.setStrokeWidth(1);
			separation.setEndX(280);
			orderTilePane.getChildren().add(separation);
		}
			
		//Pour l'affachige des menus
		for (Menu menu : HelloController.getOrder().getMenus()) {
			Text textMenu = new Text("Menu\t"+(menu.getProduct().getRecipe().getPrice()+Menu.getMenuPrice())+"€");
			textMenu.setFont(new Font("Arial Black",18));
			textMenu.setWrappingWidth(280);
			orderTilePane.getChildren().add(textMenu);
			
			//Si c'est une assiette
			if(menu.getProduct().getPlate()){
				Text textTitle = new Text("Plat "+menu.getProduct().getRecipe().getName()+" ("+menu.getProduct().getSize()+")");
				textTitle.setFont(new Font("Arial Black",16));
				textTitle.setWrappingWidth(280);
				orderTilePane.getChildren().add(textTitle);

			}else{ //Si c'est un sandwich
				Text textTitle = new Text("Sandwich "+menu.getProduct().getRecipe().getName()+" ("+menu.getProduct().getSize()+")");
				textTitle.setFont(new Font("Arial Black",16));
				textTitle.setWrappingWidth(280);
				orderTilePane.getChildren().add(textTitle);
				
				Text textBread = new Text("\t"+menu.getProduct().getBread().getName());
				textBread.setWrappingWidth(280);
				orderTilePane.getChildren().add(textBread);
			}
			for (Garnish garnish : menu.getProduct().getGarnishs()) {
				Text textGarnish = new Text("\t"+garnish.getName());
				textGarnish.setWrappingWidth(280);
				orderTilePane.getChildren().add(textGarnish);
			}
			for (Sauce sauce : menu.getProduct().getSauces()) {
				Text textSauce = new Text("\t"+sauce.getName());
				textSauce.setWrappingWidth(280);
				orderTilePane.getChildren().add(textSauce);
			}
			
			Text textDrink = new Text("\t"+menu.getDrink().getName());
			textDrink.setWrappingWidth(280);
			orderTilePane.getChildren().add(textDrink);
			Text textDessert = new Text("\t"+menu.getDessert().getName());
			textDessert.setWrappingWidth(280);
			orderTilePane.getChildren().add(textDessert);
			
			Line separation = new Line();
			separation.setStrokeWidth(1);
			separation.setEndX(280);
			orderTilePane.getChildren().add(separation);
		}
		
	}
	
	public void newPromoChosen(Composant c) throws IOException{
		if(c.getAvailability()){
			selectedComponent = c;
			newPromo = true;
			goToMenu();
		}
	}
	public void newPromoCatChosen(String cat) throws IOException{
		newPromo = true;
		selectedCategorie = cat;
		goToMenu();
	}
	
	
	public void goToConnexion() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("002 Connexion.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Connexion"); 
	}
	
	public void goToInscription() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("003 Inscription.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Inscription");
	}
	
	public void goToMenu() throws IOException { 
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("005 Menu.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
	}
		
	public void goToPayer() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("012 Payer.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Paiement");
	}
	public void goToAllergen() throws IOException {
		AllergenController.setPreviousUI("004 Accueil.fxml");
		AllergenController.setPreviousTitle("SUBPAY - Accueil");
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("006 Allergies.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Allergènes");
	}
}
