package visual.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
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
		orderTilePane.getChildren().clear();
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
			double total = 0;
			for (Product product : order.getProducts()) {
				total += product.getRecipe().getPrice();
				
				Group title = new Group();
				Text textSupTitle = new Text("x");
				textSupTitle.setFill(Color.RED);
				textSupTitle.setFont(new Font("Arial Black", 20));
				textSupTitle.setTranslateX(-30);
				textSupTitle.setOnMouseClicked(Event -> {
					order.getProducts().remove(product);
					orderTilePane.getChildren().clear();
					displayOrder();
				});
				title.getChildren().add(textSupTitle);
				
				//Si c'est une assiette
				if(product.getPlate()){
					Text textTitle = new Text("Plat "+product.getRecipe().getName()+" ("+product.getSize()+")  "+product.getRecipe().getPrice()+"€");
					textTitle.setFont(new Font("Arial Black",14));
					textTitle.setWrappingWidth(920);
					title.getChildren().add(textTitle);
					orderTilePane.getChildren().add(title);

				}else{ //Si c'est un sandwich
					Text textTitle = new Text("Sandwich "+product.getRecipe().getName()+" ("+product.getSize()+")  "+product.getRecipe().getPrice()+"€");
					textTitle.setFont(new Font("Arial Black",14));
					textTitle.setWrappingWidth(920);
					title.getChildren().add(textTitle);
					orderTilePane.getChildren().add(title);
				}
				if(product.getBread() != null){
					Text textBread = new Text("\t"+product.getBread().getName());
					textBread.setWrappingWidth(950);
					orderTilePane.getChildren().add(textBread);
				}
				for (Garnish garnish : product.getGarnishs()) {
					Text textGarnish = new Text("\t"+garnish.getName());
					textGarnish.setWrappingWidth(950);
					orderTilePane.getChildren().add(textGarnish);
				}
				for (Sauce sauce : product.getSauces()) {
					Text textSauce = new Text("\t"+sauce.getName());
					textSauce.setWrappingWidth(950);
					orderTilePane.getChildren().add(textSauce);
				}
				Line separation = new Line();
				separation.setStrokeWidth(1);
				separation.setEndX(950);
				orderTilePane.getChildren().add(separation);
			}
				
			//Pour l'affichage des menus
			for (Menu menu : order.getMenus()) {
				total += menu.getProduct().getRecipe().getPrice() + Menu.getMenuPrice();
				Group title = new Group();
				Text textSupTitle = new Text("x");
				textSupTitle.setFill(Color.RED);
				textSupTitle.setFont(new Font("Arial Black", 20));
				textSupTitle.setTranslateX(-30);
				textSupTitle.setOnMouseClicked(Event -> {
					order.getMenus().remove(menu);
					orderTilePane.getChildren().clear();
					displayOrder();
				});
				title.getChildren().add(textSupTitle);
				
				Text textMenu = new Text("Menu\t"+(menu.getProduct().getRecipe().getPrice()+Menu.getMenuPrice())+"€");
				textMenu.setFont(new Font("Arial Black",18));
				textMenu.setWrappingWidth(920);
				title.getChildren().add(textMenu);
				orderTilePane.getChildren().add(title);
				
				//Si c'est une assiette
				if(menu.getProduct().getPlate()){
					Text textTitle = new Text("Plat "+menu.getProduct().getRecipe().getName()+" ("+menu.getProduct().getSize()+")");
					textTitle.setFont(new Font("Arial Black",14));
					textTitle.setWrappingWidth(950);
					orderTilePane.getChildren().add(textTitle);

				}else{ //Si c'est un sandwich
					Text textTitle = new Text("Sandwich "+menu.getProduct().getRecipe().getName()+" ("+menu.getProduct().getSize()+")");
					textTitle.setFont(new Font("Arial Black",14));
					textTitle.setWrappingWidth(950);
					orderTilePane.getChildren().add(textTitle);
					
				}
				if(menu.getProduct().getBread() != null){
					Text textBread = new Text("\t"+menu.getProduct().getBread().getName());
					textBread.setWrappingWidth(950);
					orderTilePane.getChildren().add(textBread);
				}
				for (Garnish garnish : menu.getProduct().getGarnishs()) {
					Text textGarnish = new Text("\t"+garnish.getName());
					textGarnish.setWrappingWidth(950);
					orderTilePane.getChildren().add(textGarnish);
				}
				for (Sauce sauce : menu.getProduct().getSauces()) {
					Text textSauce = new Text("\t"+sauce.getName());
					textSauce.setWrappingWidth(950);
					orderTilePane.getChildren().add(textSauce);
				}
				
				Text textDrink = new Text("\t"+menu.getDrink().getName());
				textDrink.setWrappingWidth(950);
				orderTilePane.getChildren().add(textDrink);
				Text textDessert = new Text("\t"+menu.getDessert().getName());
				textDessert.setWrappingWidth(950);
				orderTilePane.getChildren().add(textDessert);
				
				Line separation = new Line();
				separation.setStrokeWidth(1);
				separation.setEndX(950);
				orderTilePane.getChildren().add(separation);
			}
			
			if(!(order.getMenus().size() == 0 && order.getProducts().size() ==0)){
				Text textTotal = new Text("TOTAL\t"+total+"€");
				textTotal.setFont(new Font("Arial Black",22));
				textTotal.setWrappingWidth(950);
				orderTilePane.getChildren().add(textTotal);
				
				Button b = new Button();
				b.setText("Commander à nouveau");
				b.setFont(new Font("Arial", 20));
				b.setOnAction(Event -> {
					Order o = new Order();
					o=order.clone();
					HelloController.setOrder(o);
					goToHome();
				});
				orderTilePane.getChildren().add(b);
				
				
				Line separations = new Line();
				separations.setStrokeWidth(3);
				separations.setEndX(950);
				orderTilePane.getChildren().add(separations);
			}
		}
	}
}
