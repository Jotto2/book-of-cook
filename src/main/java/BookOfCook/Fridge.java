package BookOfCook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Fridge {
    // *FIELDS
    private ArrayList<HashMap<String, Object>> foodInFridge = new ArrayList<HashMap<String, Object>>(); // an arraylist of hasmaps containing name, amount and unit of measurement ex.: [{"name": "tomat","amount": "3","unit":stk},{...}]
    private LocalDateTime lastUpdateOfFridge;                                                           // time of last fridge update

    // *CONSTRUCTOR
    public Fridge() {                                                       // updating lastUpdateOfFridge when fridge is created
        updateFridgeTime();
        System.out.println("Fridge created: " + getFormattedFridgeTime());  // printing time
    }

    // *GETTERS
    public ArrayList<HashMap<String, Object>> getFoodInFridge() {
        return foodInFridge;
    }

    public String getFormattedFridgeTime(){     // method for formatting lastUpdateOfFridge to a readable format
        return lastUpdateOfFridge.format(DateTimeFormatter.ofPattern("HH:mm - dd.MM"));
    }

    //*ADDING AND REMOVING FOOD 
    public void addFood(String name, double amount, String unit/*, Cookbook cookbook*/) {

        if(isFoodInFridge(name)){
            System.out.println("Food " + name + " is already in fridge.");
        }

        /* for (Recipe r : cookbook.getRecipes()) {
            for (HashMap<String, Object> i : r) {
                
            }
        } */    
        HashMap<String, Object> ingredient = new HashMap<String, Object>();
        ingredient.put("name", name.toLowerCase());     // adds the name of the ingredient to this ingredient hashmap
        ingredient.put("amount", amount);               // adds the amount key, value pair to this ingrdient hashmap, describes the amount of the ingredient
        ingredient.put("unit", unit);                   // adds the "unit" key, value pair to this ingredient hashmap, describes the unit of the ingredient
        foodInFridge.add(ingredient);                   // adds the ingredient to the list of ingredients
        updateFridgeTime();                             // updates lastUpdateOfFridge time
    }

    public void removeFood(String name) {
        boolean removedFood = false;

        for (int i = 0; i < foodInFridge.size(); i++){                          // iterates through foodInFridge
            if(foodInFridge.get(i).get("name") == name){                        // checks if food is equal to name
                System.out.println("Removing " + foodInFridge.get(i));          
                foodInFridge.remove(i);                                         // removes i food
                removedFood = true;
                updateFridgeTime();   
            }
        }

        if (!removedFood) {                                                     // checks if any food removed at all
            System.out.println("No food matching input '" + name + "' in fridge");
        }
    }

    public void emptyFridge() {
        foodInFridge.clear();       // empties the foodInFridge arrayList
        updateFridgeTime();         // updates lastUpdateOfFridge time
    }
    
    // *VALIDATION
    public boolean isFoodInFridge(String food){
        for (int i = 0; i < foodInFridge.size(); i++) {                         // iterates through foodInFridge
            if (foodInFridge.get(i).get("name").equals(food.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    //! *TIME er det noe vi trenger? 
    public void updateFridgeTime(){     // method for updating the lastUptadeOfFridge
        lastUpdateOfFridge = LocalDateTime.now(); 
    }

    @Override
    public String toString() {
        return "Fridge [foodInFridge=" + foodInFridge + ", lastUpdateOfFridge=" + lastUpdateOfFridge + "]";
    }

    public static void main(String[] args) {
        Fridge f = new Fridge();
        f.addFood("Tomat", 2, "stk");
        f.addFood("Pepperoni", 500, "gram");

        System.out.println(f);

        f.removeFood("pepperoni");

        System.out.println("Fridge was last updated at " + f.getFormattedFridgeTime());
    }
}
