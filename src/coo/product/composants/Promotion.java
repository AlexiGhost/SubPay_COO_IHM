package coo.product.composants;

import java.util.*;

public class Promotion {
	private String name;
    private List<String> recipes = new ArrayList<String>();
    private double percentage;
    private boolean authCustomer;
    
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
    public void addRecipe(String recipeName){
    	recipes.add(recipeName);
    }
    public void delRecipe(String recipeName){
    	int index;
    	for(String rec : recipes){
    		if(recipeName == rec){
    			index = recipes.indexOf(rec);
    			recipes.remove(index);
    		}
    	}
    }
    public void delRecipes(){
    	recipes.clear();
    }
    
//GETTERS/SETTERS
    //name
    public void setName(String name){
    	this.name = name;
    }
    public String getName(){
    	return name;
    }
    //recipes
    public List<String> getRecipes(){
		return recipes;
    }
    public void setRecipes(List<String> recipeList){
		recipes = recipeList;
    }
    //percentage
    public void setPercentage(double percentage){
    	this.percentage = percentage;
    }
    public double getPercentage(){
    	return percentage;
    }
    //authCustomer
    public void setAuthCustomer(boolean auth){
    	this.authCustomer = auth;
    }
    public boolean getAuthCustomer(){
    	return authCustomer;
    }
}

