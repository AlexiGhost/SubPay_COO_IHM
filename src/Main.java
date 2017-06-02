

import java.util.ArrayList;
import java.util.List;

import model.AuthentificatedCustomer;
import model.CustomerManagement;
import model.product.*;
import visual.*;

public class Main {

	public static void main(String[] args) {
		//ComponentManagement.importComponent("component.xml");
		//CustomerManagement.importCustomer("customer.xml");
		
		/*USE ONLY ONE OF THESE GUIThread (comment the other)*/
		//GUIThread guiThread = new GUIThread("client");
		GUIThread guiThread = new GUIThread("manager");
		
		testExportComponent();
		testExportCustomer();
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
		ComponentManagement.getPromotion("mercredi").addRecipe("poulet");
		ComponentManagement.getPromotion("mercredi").addRecipe("kebab");
		ComponentManagement.addGarnish("tomate", "titi");
		ComponentManagement.addGarnish("basilic", "titi");
		ComponentManagement.addGarnish("cheddar", "titi");
		ComponentManagement.addDrink("cola cola", "toto");
		ComponentManagement.addDessert("brownie", "truc marron");
		ComponentManagement.exportComponent("component.xml");
	}
	public static void testExportCustomer(){
		List<String> aList = new ArrayList<>();
		aList.add("aaa");
		aList.add("WTF");
		AuthentificatedCustomer customer1 = new AuthentificatedCustomer("0619355180", "lol", 1, ComponentManagement.getBread("mie"), ComponentManagement.getSauce("andalouse"), ComponentManagement.getGarnish("tomate"), ComponentManagement.getRecipe("poulet"), aList);
		CustomerManagement.addCustomer(customer1);
		CustomerManagement.exportCustomer("customer.xml");
		
	}
}
