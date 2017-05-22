package model.product.composants;

public class Recipe extends Composant {
	
	private double price;
    private static final String[] categoryList = {"bof", "mouais", "ça passe"};
    private String category;
    /**Create a recipe*/
    public Recipe(String name, String photo, double price, String category) {
		super(name, photo);
		this.price = price;
		for(String cat : categoryList){
			if(cat == category){
				this.category = category;				
			}
		}
	}
    /**Return the price of the recipe*/
    public double getPrice(){
    	return price;
    }
    /**Set the price of the recipe*/
    public void setPrice(double price){
    	this.price=price;
    }
    /**Return the category of the recipe*/
    public String getCategory(){
    	return category;
    }
    /**Set the category of the recipe
     *<br/>Available categories : <b>Mouais / Bof / ça passe</b>*/
    public void setCategory(String category){
    	for(String cat : categoryList){
			if(cat == category){
				this.category = category;				
			}
		}
    }
}
