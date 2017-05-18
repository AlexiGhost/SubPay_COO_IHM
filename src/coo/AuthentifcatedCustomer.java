package coo;

import java.util.ArrayList;
import java.util.List;

import coo.product.*;
import coo.product.composants.*;

public class AuthentifcatedCustomer {

	private String login;
    private String password;
    private int iceCubeNb;
    private Bread favoriteBread;
    private Sauce favoriteSauce;
    private Recipe favoriteRecipe;
    private Garnish favoriteGarnish;
    public List<Order> order = new ArrayList<Order> ();
    public List<String> allergen = new ArrayList<String> ();
    
    public void reorder() {
    	
    }

    public void editPreferences() {
    	
    }
}
