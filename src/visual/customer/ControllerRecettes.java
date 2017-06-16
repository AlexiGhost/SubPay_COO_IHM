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
import model.product.composants.Recipe;

public class ControllerRecettes  implements Initializable{
	
    @FXML
    private TilePane bofTile;

    @FXML
    private TilePane mouaisTile;

    @FXML
    private TilePane caPasseTile;
	
	private static List<Recipe> bofList = new ArrayList<Recipe>();
	private static List<Recipe> mouaisList = new ArrayList<Recipe>();
	private static List<Recipe> caPasseList = new ArrayList<Recipe>();
	private static Rectangle	redOne = new Rectangle();
	
	public static List<Recipe> getBofList() {
		return bofList;
	}
	public static List<Recipe> getMouaisList() {
		return mouaisList;
	}
	public static List<Recipe> getCaPasseList() {
		return caPasseList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayRecipe(bofList, bofTile);
		displayRecipe(mouaisList, mouaisTile);
		displayRecipe(caPasseList, caPasseTile);
	}
	
	public static void displayRecipe(List<Recipe> listRecipe, TilePane tilePane){
		for (Recipe recipe : listRecipe) {
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Group
			Group r = new Group();
			r.setFocusTraversable(true);
			r.setOnMouseClicked(MouseEvent -> redRectangle(bordure,recipe));
			
			//Titre nouveauté
			Text title = new Text(recipe.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image nouveauté
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+recipe.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			r.getChildren().add(img);
			
			//MAJ promoTiled
			r.getChildren().add(title);
			r.getChildren().add(bordure);
			tilePane.getChildren().add(r);
		}
		
	}
	
	public static void redRectangle(Rectangle redStroke, Recipe recipe) {
		redStroke.setStroke(Color.RED);
		if(redOne != null){
			redOne.setStroke(Color.LIGHTGREEN);
		}
		redOne = redStroke;
		if(ControllerMenu.getChoice()){
			ControllerMenu.getMenu().getProduct().setRecipe(recipe);		
		}
		else{
			ControllerMenu.getProduct().setRecipe(recipe);	
		}
	}	

	public void goToPain() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("007 Pain.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Pain"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
	
	public void goToGarnitures() throws IOException { //Au lieu de "toAccueil", tu dois mettre to + [InterfaceDeDestination]
		Group acteur = new Group(); //Pas touche
		acteur.getChildren().add( //Pas touche
		FXMLLoader.load(getClass().getResource("009 Garnitures.fxml")) //Ici, il faut changer le fichier fxml (la string en fait)
		); //Pas touche
		visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures"); //Ici, il faut laisser "SUBPAY" et changer "Accueil" selon l'interface où
																//tu vas. Ca permet de changer le titre de la fenêtre (et ça marche B)  )
	}
}
