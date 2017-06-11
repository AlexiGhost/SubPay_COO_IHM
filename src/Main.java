

import java.util.ArrayList;
import java.util.List;

import model.AuthentificatedCustomer;
import model.CustomerManagement;
import model.product.*;
import model.product.composants.Bread;
//TODO supprimer cette classe a la fin du projet
/**Cette classe reste juste pour faire des tests avec le modele*/
public class Main {
	public static void main(String[] args) throws Exception {
		//testExportComponent();
		ComponentManagement.importComponent("component.xml");
		testExportCustomer();
		
		CustomerManagement.importCustomer("customer.xml");	
	}
	
	//Ces actions sont effectués par le manager (indirectement via bouton)
	public static void testExportComponent(){ 
		ComponentManagement.addBread("avoine", "C:\\...");
		ComponentManagement.addBread("mie", "C:\\...");
		ComponentManagement.getBread("mie").addAllergen("levure");
		ComponentManagement.addBread("olive", "C:\\...");
		ComponentManagement.addSauce("andalouse", "C:\\...");
		ComponentManagement.addPromotion("mercredi", 5.5, false);
		ComponentManagement.addPromotion("auth", 1, true);
		ComponentManagement.addRecipe("poulet", "mouais", 214, "C:\\...");
		ComponentManagement.addGarnish("tomate", "titi");
		ComponentManagement.addGarnish("basilic", "titi");
		ComponentManagement.addGarnish("cheddar", "titi");
		ComponentManagement.addDrink("cola cola", "toto");
		ComponentManagement.addDessert("brownie", "truc marron");
		ComponentManagement.exportComponent("component.xml");
	}
	public static void testExportCustomer() throws Exception{
		AuthentificatedCustomer customer1 = new AuthentificatedCustomer();
		
		customer1.setPhoneNumber("0619355180");
		customer1.setPassword("test1995");
		Bread bread = new Bread();
		bread.setName("mie");
		customer1.setFavoriteBread(bread);
		customer1.setFavoriteSauce(ComponentManagement.getSauce("Mayonnaise"));
		customer1.addGarnish(ComponentManagement.getGarnish("Tomate"));
		customer1.addGarnish(ComponentManagement.getGarnish("Concombre"));
		customer1.addAllergen("WTF");
		customer1.addAllergen("gluten");
		CustomerManagement.addCustomer(customer1);
		CustomerManagement.exportCustomer("customer.xml");
		
	}
}
