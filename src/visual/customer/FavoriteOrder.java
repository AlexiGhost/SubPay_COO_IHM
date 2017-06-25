package visual.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.Menu;
import model.product.Product;
import model.product.Order;
import model.product.composants.Garnish;
import model.product.composants.Sauce;

public class FavoriteOrder implements Initializable {
	@FXML
    private TilePane orderTilePane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayOrder();
	}

	public void goToHome(){
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void displayOrder(){
		//Pour l'affichage des produits
		for(Order order : SignUpController.getAuthCusto().getOrder()){
			for (Product product : order.getProducts()) {
				//Si c'est une assiette
				if(product.getPlate()){
					Text textSupTitle = new Text("x");
					textSupTitle.setFont(new Font("Arial Black",16));
					textSupTitle.setWrappingWidth(10);
					orderTilePane.getChildren().add(textSupTitle);
					//textSupTitle.setStyle("-fx setTextfield");;
					Text textTitle = new Text("Plat "+product.getRecipe().getName()+" ("+product.getSize()+")  "+product.getRecipe().getPrice()+"€");
					textTitle.setFont(new Font("Arial Black",16));
					textTitle.setWrappingWidth(250);
					orderTilePane.getChildren().add(textTitle);
					
				}else{
					Text textTitle = new Text("Sandwich "+product.getRecipe().getName()+" ("+product.getSize()+")  "+product.getRecipe().getPrice()+"€");
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
			for (Menu menu : order.getMenus()) {
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
					System.out.println("hello");
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
			Line separations = new Line();
			separations.setStrokeWidth(3);
			separations.setEndX(280);
			orderTilePane.getChildren().add(separations);
		}
	}

}
