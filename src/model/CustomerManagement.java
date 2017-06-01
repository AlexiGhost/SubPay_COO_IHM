package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.product.*;
import model.product.composants.*;

public class CustomerManagement {
	private static List<AuthentificatedCustomer> customers = new ArrayList<>();
	
//ADD/EDIT/DEL
	/**Add a customer to the customerList*/
	public static void addCustomer(AuthentificatedCustomer aCustomer){
		customers.add(aCustomer);
	}
	/**Delete a customer from the customerList*/
	public static void delCustomer(String login){
		for (AuthentificatedCustomer aCustomer : customers) {
			if(aCustomer.getLogin() == login){
				customers.remove(aCustomer);
			}
		}
	}
	
//GETTERS/SETTERS
	/**Return the customers list*/
	public static List<AuthentificatedCustomer> getCustomers() {
		return customers;
	}
	
//IMPORT/EXPORT
	/**Export customers to a XML file*/
	public static void exportCustomer(String xmlFile) {
		Element racine = new Element("Customers");
		Document Dcustomers = new Document(racine);
		String login;
		String password;
		Integer iceCubeNb;
		Bread favoriteBread;
		Sauce favoriteSauce;
		Recipe favoriteRecipe;
		Garnish favoriteGarnish;
		List<Order> orderList = null;
		List<String> allergenList = null;
		try {
			//BREAD
			for(AuthentificatedCustomer customer : customers){
				//recuperation infos
				login = customer.getLogin();
				password = customer.getPassword();
				iceCubeNb = customer.getIceCubeNb();
				favoriteBread = customer.getFavoriteBread();
				favoriteGarnish = customer.getFavoriteGarnish();
				favoriteRecipe = customer.getFavoriteRecipe();
				favoriteSauce = customer.getFavoriteSauce();
				orderList = customer.getOrder();
				allergenList = customer.getAllergens();
				//creation d'une branche
				Element eCustomer = new Element("customer");
				racine.addContent(eCustomer);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "AuthentificatedCustomer");
				eCustomer.setAttribute(classe);
				//ajout login
				Element eLogin = new Element("login");
				eLogin.setText(login);
				eCustomer.addContent(eLogin);
				//ajout password
				Element ePassword = new Element("password");
				ePassword.setText(password);
				eCustomer.addContent(ePassword);
				//ajout iceCubeNb
				Element eIceCube = new Element("iceCubeNb");
				eIceCube.setText(iceCubeNb.toString());
				eCustomer.addContent(eIceCube);
				//ajout fBread
				Element eFBread = new Element("fBread");
				eFBread.setText(favoriteBread.getName());
				eCustomer.addContent(eFBread);
				//ajout fSauce
				Element eFSauce = new Element("fSauce");
				eFSauce.setText(favoriteSauce.getName());
				eCustomer.addContent(eFSauce);
				//ajout fGarnish
				Element eFGarnish = new Element("fGarnish");
				eFGarnish.setText(favoriteGarnish.getName());
				eCustomer.addContent(eFGarnish);
				//ajout fRecipe
				Element eFRecipe = new Element("fRecipe");
				eFRecipe.setText(favoriteRecipe.getName());
				eCustomer.addContent(eFRecipe);
				//ajout allergenes
				Element eAllergens = new Element("allergens");
				for (String allergen : allergenList) {
					Element eAllergen = new Element("allergen");
					eAllergen.setText(allergen);
					eAllergens.addContent(eAllergen);
				}
				eCustomer.addContent(eAllergens);

				
			}
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(Dcustomers, new FileOutputStream(xmlFile));
		}
		catch (IOException e) {}
	}

	/**Import customers from a XML file*/
	public static void importCustomer(String xmlFile){
		customers.clear();
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		Element racine = null;
		try {
			document = sxb.build(new File(xmlFile));
			racine = document.getRootElement();
		} catch (JDOMException | IOException e) {}
		String login;
		String password;
		Integer iceCubeNb;
		Bread favoriteBread = null;
		Sauce favoriteSauce = null;
		Recipe favoriteRecipe = null;
		Garnish favoriteGarnish = null;
		List<String> allergenList = new ArrayList<>();
		
		List<Element> customers = racine.getChildren("customer");
		for (Element customer : customers) {
			login = customer.getChildText("login");
			password = customer.getChildText("password");
			iceCubeNb = Integer.valueOf(customer.getChildText("iceCubeNb"));
			//creation pain
			for (Bread bread : ComponentManagement.getBreads()) {
				if(bread.getName().equals(customer.getChildText("fBread"))){
					favoriteBread = bread;
				}
			}
			//creation sauce
			for (Sauce sauce : ComponentManagement.getSauces()) {
				if(sauce.getName().equals(customer.getChildText("fSauce"))){
					favoriteSauce = sauce;
				}
			}
			//creation garniture
			for (Garnish garnish : ComponentManagement.getGarnishs()) {
				if(garnish.getName().equals(customer.getChildText("fGarnish"))){
					favoriteGarnish = garnish;
				}
			}
			//creation recette
			for (Recipe recipe : ComponentManagement.getRecipes()) {
				if(recipe.getName().equals(customer.getChildText("fRecipe"))){
					favoriteRecipe = recipe;
				}
			}
			//ajout allergenes
			Element allergens = customer.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				allergenList.add(allergen.getText());
			}
			//creation customer
			AuthentificatedCustomer tCustomer = new AuthentificatedCustomer(login, password, iceCubeNb, favoriteBread, favoriteSauce, favoriteGarnish, favoriteRecipe, allergenList);
			addCustomer(tCustomer);
		}
	}
}
