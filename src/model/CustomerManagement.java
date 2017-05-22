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
	
//IMPORT/EXPORT
	/**Export components to a XML file*/
	public static void exportCustomer(String xmlFile) {
		Element racine = new Element("Customers");
		Document Dcustomers = new Document(racine);
		String login;
		String password;
		int iceCubeNb;
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
				
			}
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(Dcustomers, new FileOutputStream(xmlFile));
		}
		catch (IOException e) {}
	}

	/**Import components from a XML file*/
	/*public static void importComponent(String xmlFile){
		breads.clear();
		garnishs.clear();
		sauces.clear();
		recipes.clear();
		drinks.clear();
		desserts.clear();
		promotions.clear();
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		Element racine = null;
		try {
			document = sxb.build(new File(xmlFile));
			racine = document.getRootElement();
		} catch (JDOMException | IOException e) {}
		String name;
		String photo;
		Boolean available;
		Double price;
		String category;
		Double percentage;
		Boolean authCustomer;
		//BREAD
		List<Element> listImport = racine.getChildren("bread");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation pain
			breads.add(new Bread(name, photo));
			int index = breads.size()-1;
			//ajout disponibilité
			breads.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				breads.get(index).addAllergen(allergen.getText());
			}
		}
		//GARNISH
		listImport = racine.getChildren("garnish");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation garniture
			garnishs.add(new Garnish(name, photo));
			int index = garnishs.size()-1;
			//ajout disponibilité
			garnishs.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				garnishs.get(index).addAllergen(allergen.getText());
			}
		}
		//SAUCE
		listImport = racine.getChildren("sauce");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation sauce
			sauces.add(new Sauce(name, photo));
			int index = sauces.size()-1;
			//ajout disponibilité
			sauces.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				sauces.get(index).addAllergen(allergen.getText());
			}
		}
		//RECIPE
		listImport = racine.getChildren("recipe");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			price = Double.valueOf(component.getChildText("price"));
			category = component.getChildText("category");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation recette
			recipes.add(new Recipe(name, photo, price, category));
			int index = recipes.size()-1;
			//ajout disponibilité
			recipes.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				recipes.get(index).addAllergen(allergen.getText());
			}
			//ajout prix
			recipes.get(index).setPrice(price);
			//ajout categorie
			recipes.get(index).setCategory(category);			
		}
		//DRINK
		listImport = racine.getChildren("drink");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation boisson
			drinks.add(new Drink(name, photo));
			int index = drinks.size()-1;
			//ajout disponibilité
			drinks.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				drinks.get(index).addAllergen(allergen.getText());
			}
		}
		//DESSERT
		listImport = racine.getChildren("dessert");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation dessert
			desserts.add(new Dessert(name, photo));
			int index = desserts.size()-1;
			//ajout disponibilité
			desserts.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				desserts.get(index).addAllergen(allergen.getText());
			}
		}
		//PROMOTION
		listImport = racine.getChildren("promo");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			percentage = Double.valueOf(component.getChildText("percentage"));
			authCustomer = Boolean.valueOf(component.getChildText("authCustomerOnly"));
			//creation promotion
			promotions.add(new Promotion(name, percentage, authCustomer));
			int index = promotions.size()-1;
			//ajout authCustomer
			promotions.get(index).setAuthCustomer(authCustomer);
			//ajout listRecipes
			Element recipes = component.getChild("recipes");
			List<Element> listRecipe = recipes.getChildren();
			for (Element recipe : listRecipe) {
				promotions.get(index).addRecipe(recipe.getText()); 					
			}
			//ajout %age
			promotions.get(index).setPercentage(percentage);
		}
	}*/
}
