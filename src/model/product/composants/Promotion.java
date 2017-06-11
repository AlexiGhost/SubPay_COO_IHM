package model.product.composants;

public class Promotion {
	private String name;
    private String recipe;
    private String category;
    private double percentage;
    private boolean authCustomer;
    private String date;
    /**Create a promotion*/
    public Promotion(String name, double percentage, boolean auth) {
    	this.name = name;
    	this.percentage = percentage;
    	authCustomer = auth;
	}
    public Promotion(String name, double percentage, boolean auth, String recipe, String cat, String endDate) {
    	this(name, percentage, auth);
    	setRecipe(recipe);
    	setCategory(cat);
    	setDate(endDate);
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
    //recipe
    /**Set the recipe of the promotion*/
    public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
    /**Return the recipe of the promotion*/
    public String getRecipe() {
		return recipe;
	}
    //category
    /**Set the category of the promotion*/
    public void setCategory(String category){
    	this.category = category;
    }
    /**Return the category of the promotion*/
    public String getCategory(){
    	return category;
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
    //date
    /**Set the end date of the promotion*/
    public void setDate(String date){
    	this.date = date;
    }
    /**Return the end date of the promotion*/
    public String getDate(){
    	return date;
    }
}

