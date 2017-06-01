package model;

import java.util.ArrayList;
import java.util.List;

import model.product.*;
import model.product.composants.*;

public class AuthentificatedCustomer {

	private String login;
    private String password;
    private int iceCubeNb;
    private Bread favoriteBread;
    private Sauce favoriteSauce;
    private Recipe favoriteRecipe;
    private Garnish favoriteGarnish;
    private List<Order> order = new ArrayList<Order> ();
    private List<String> allergens = new ArrayList<String> ();

    public AuthentificatedCustomer(String login, String password, int iceCubNb, Bread fBread, Sauce fSauce, Garnish fGarnish, Recipe fRecipe, List<String> allergens) {
    	this.login = login;
    	this.password = password;
    	this.iceCubeNb = iceCubNb;
    	this.favoriteBread = fBread;
    	this.favoriteSauce = fSauce;
    	this.favoriteGarnish = fGarnish;
    	this.favoriteRecipe = fRecipe;
    	this.order = order;
    	this.allergens = allergens;
	}
    
   //GETTERS/SETTERS
    /**Return the login of the customer*/
    public String getLogin() {
		return login;
	}
    /**Set the login of the customer*/
	public void setLogin(String login) {
		this.login = login;
	}
	/**Return the password of the customer
	 * <br/><b>TODO upgrade security</b>*/
	public String getPassword() {
		return password;
	}
	/**Set the password of the customer*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**Return the ice cube number of the customer*/
	public int getIceCubeNb() {
		return iceCubeNb;
	}
	/**Set the ice cube number of the customer*/
	public void setIceCubeNb(int iceCubeNb) {
		this.iceCubeNb = iceCubeNb;
	}
	/**Return the favorite bread of the customer*/
	public Bread getFavoriteBread() {
		return favoriteBread;
	}
	/**Set the favorite bread of the customer*/
	public void setFavoriteBread(Bread favoriteBread) {
		this.favoriteBread = favoriteBread;
	}
	/**Return the favorite sauce of the customer*/
	public Sauce getFavoriteSauce() {
		return favoriteSauce;
	}
	/**Set the favorite sauce of the customer*/
	public void setFavoriteSauce(Sauce favoriteSauce) {
		this.favoriteSauce = favoriteSauce;
	}
	/**Return the favorite recipe of the customer*/
	public Recipe getFavoriteRecipe() {
		return favoriteRecipe;
	}
	/**Set the favorite recipe of the customer*/
	public void setFavoriteRecipe(Recipe favoriteRecipe) {
		this.favoriteRecipe = favoriteRecipe;
	}
	/**Return the favorite garnish of the customer*/
	public Garnish getFavoriteGarnish() {
		return favoriteGarnish;
	}
	/**Set the favorite garnish of the customer*/
	public void setFavoriteGarnish(Garnish favoriteGarnish) {
		this.favoriteGarnish = favoriteGarnish;
	}
	/**Return the order list of the customer*/
	public List<Order> getOrder() {
		return order;
	}
	/**Set the order list of the customer*/
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	/**Return the allergens list of the customer*/
	public List<String> getAllergens() {
		return allergens;
	}
	/**Set the allergens list of the customer*/
	public void setAllergens(List<String> allergens) {
		this.allergens = allergens;
	}
    
    
}
