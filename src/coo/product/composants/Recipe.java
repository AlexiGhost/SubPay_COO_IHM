package coo.product.composants;

public class Recipe extends Composant {
	
	private double price;
    private static final String[] categoryList = {"bof", "mouais", "ça passe"};
    private String category;
    
    public Recipe(String name, String photo, double price, String category) {
		super(name, photo);
		this.price = price;
		for(String cat : categoryList){
			if(cat == category){
				this.category = category;				
			}
		}
	}
    
    public double getPrice(){
    	return price;
    }
    public void setPrice(double price){
    	this.price=price;
    }
    
    public String getCategory(){
    	return category;
    }
    public void setCategory(String category){
    	for(String cat : categoryList){
			if(cat == category){
				this.category = category;				
			}
		}
    }
}
