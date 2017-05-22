package coo.product.composants;

import java.util.*;

public class Promotion {
	private String name;
    private List<String> recipes = new ArrayList<String>();
    private double percentage;
    private boolean authCustomer;
    /**Create a promotion*/
    public Promotion(String name, double percentage, boolean auth) {
    	this.name = name;
    	this.percentage = percentage;
    	authCustomer = auth;
	}
    public Promotion(String name, double percentage, boolean auth, List<String> recipeList) {
    	this(name, percentage, auth);
    	setRecipes(recipeList);
	}
//RecipesList editor    
    /**Add a recipe of the promotion*/
    public void addRecipe(String recipeName){
    	recipes.add(recipeName);
    }
    /**Delete a recipe of the promotion*/
    public void delRecipe(String recipeName){
    	int index;
    	for(String rec : recipes){
    		if(recipeName == rec){
    			index = recipes.indexOf(rec);
    			recipes.remove(index);
    		}
    	}
    }
    /**Delete all recipes of the promotion*/
    public void delRecipes(){
    	recipes.clear();
    }
    
//GETTERS/SETTERS
    //name
    /**Set the name of the promotion*/
    public void setName(String name){
    	this.name = name;
    }
    /**Return the name of the promotion*/
    public String getName(){
    	return name;
    }
    //recipes
    /**Return the recipe list of the promotion*/
    public List<String> getRecipes(){
		return recipes;
    }
    /**Define the recipe list of the promotion*/
    public void setRecipes(List<String> recipeList){
		recipes = recipeList;
    }
    //percentage
    /**Set the percentage of the promotion*/
    public void setPercentage(double percentage){
    	this.percentage = percentage;
    }
    /**Return the name of the promotion*/
    public double getPercentage(){
    	return percentage;
    }
    //authCustomer
    /**Set the availability for the non-authentified customer of the promotion
     * <br/> <b>true</b> = authentified customers ONLY
     * <br/> <b>false</b> = all customers*/
    public void setAuthCustomer(boolean auth){
    	this.authCustomer = auth;
    }
    /**Return the availability for the non-authentified customer of the promotion*/
    public boolean getAuthCustomer(){
    	return authCustomer;
    }
}

