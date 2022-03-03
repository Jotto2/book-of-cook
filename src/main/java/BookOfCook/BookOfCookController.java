package BookOfCook;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BookOfCookController {
    private ArrayList<Recipe> recipes;

    @FXML
    private Label number;

    @FXML
    private GridPane recipeGrid;

    //*INITIALIZATION
    public void initialize(){
        initializeDummyRecipes();
        initializeRecipeGrid();
    }

    private void initializeRecipeGrid() {
        for (Recipe recipe : recipes) {
            Recipe r = recipe; //copy recipe
            recipeGrid.add(createRecipeBody(r), recipes.indexOf(recipe) % 3,  recipes.indexOf(recipe) / 3);
        }
    }

    private void initializeDummyRecipes(){
        recipes = new ArrayList<Recipe>();
        recipes.addAll(List.of(
            new Recipe("Pasta", 4),
            new Recipe("Pizza", 2),
            new Recipe("Sushi", 1),
            new Recipe("Hamburger", 2),
            new Recipe("Lasagne", 3),
            new Recipe("Pancakes", 1),
            new Recipe("Spaghetti", 3),
            new Recipe("Taco", 1)
        ));
    }

    //*DYNAMIC CREATION OF RECIPE COMPONENTS IN GRID
    private Pane createRecipeBody(Recipe recipe){
        //creates pane for each recipe
        Pane body = new Pane();

        //adds children
        body.getChildren().add(createViewButton(recipe));
        body.getChildren().add(createRecipeHeader(recipe));

        //styling
        body.getStyleClass().clear();
        body.getStyleClass().add("recipe-body");
        body.setMaxWidth(Double.MAX_VALUE);
        body.setMaxHeight(Double.MAX_VALUE);

        return body;
    }

    private Button createViewButton(Recipe recipe){
        //creates a button with "View" text
        Button viewbtn = new Button("View");

        //sets style
        viewbtn.getStyleClass().clear();
        viewbtn.getStyleClass().add("standard-button");
        viewbtn.setLayoutX(80);
        viewbtn.setLayoutY(170);

        //sets action
        viewbtn.setOnAction(e -> {
            System.out.println("View " + recipe.getName() + " recipe");
            //?viewRecipe(recipe);
        });

        return viewbtn;
    }
    
    private Label createRecipeHeader(Recipe recipe){
        //creates a label with recipe name
        Label label = new Label(recipe.getName());

        //sets style
        label.getStyleClass().clear();
        label.getStyleClass().add("recipe-header");
        label.setLayoutX(80);
        label.setLayoutY(10);

        return label;
    }

    //*BUTTON ACTIONS
    public void generateRandom(ActionEvent event) {
    System.out.println("Add button was clicked");
    //     Random rand = new Random();
    //     int myRandom = rand.nextInt(100) + 1;
    //     number.setText(Integer.toString(myRandom));

    //     //midlertidig add pane to grid
    //     //addRecipeToGrid();
    }
}