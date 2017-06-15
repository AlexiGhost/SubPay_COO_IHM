package model.product;

import java.util.ArrayList;
import java.util.List;

import model.product.composants.*;

public class Product {
	
    private int size;
    private Recipe recipe;
    public List<Sauce> sauces = new ArrayList<Sauce> ();
    public List<Garnish> garnishs = new ArrayList<Garnish> ();
    private Bread bread; 
    boolean plate;
    private int sizes[] = {15,30};
//constructors
    public Product() {
	}
    public Product(int size, Recipe recipe, Bread bread) {
    	for(int availableSize : sizes){
    		if(size == availableSize){
    			this.size = size;    			
    		}
    	}
    	if(size == 0) System.err.println("La taille indiqué n'est pas disponible"); //myriam EXCEPTION
    	this.recipe = recipe;
    	this.bread = bread;
	}
    public Product(int size, Recipe recipe, Bread bread, List<Sauce> sauces, List<Garnish> garnishs) {
    	this(size, recipe, bread); //appel au 1er constructeur
    	this.sauces = sauces;
    	this.garnishs = garnishs;
	}
//add/remove componant (list)
    //sauce
    public void addSauce(Sauce sauce){
    	this.sauces.add(sauce);
    }
    public void delSauces(){
    	sauces.clear();
    }
    public void delSauce(Sauce sauceName){
    	this.sauces.remove(sauceName);
    }
    //garnish
    public void addGarnish(Garnish garnish){
    	this.garnishs.add(garnish);
    }
    public void delGarnishs(){
    	garnishs.clear();
    }
    public void delGarnish(Garnish garnishName){
    	this.garnishs.remove(garnishName);
    }
    
//GETTERS / SETTERS
    //size
    public int getSize() {
		return size;
	}
    public void setSize(int size) {
		this.size = size;
	}
    //plate
    public boolean getPlate(){
    	return plate;
    }
    public void setPlate(boolean plate) {
		this.plate = plate;
	}
    //recipe
    public Recipe getRecipe() {
		return recipe;
	}
    public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
    //bread
    public Bread getBread() {
		return bread;
	}
    public void setBread(Bread bread) {
		this.bread = bread;
	}
    //sauce
    public List<Sauce> getSauces() {
		return sauces;
	}
    public void setSauces(List<Sauce> sauces) {
		this.sauces = sauces;
	}
    //garnish
    public List<Garnish> getGarnishs() {
		return garnishs;
	}
    public void setGarnishs(List<Garnish> garnishs) {
		this.garnishs = garnishs;
	}
}