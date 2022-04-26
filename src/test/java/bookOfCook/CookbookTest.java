package bookOfCook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import BookOfCook.Category;
import BookOfCook.Cookbook;
import BookOfCook.Recipe;

public class CookbookTest {
    //JUnit tests for Cookbook class
    private Recipe pizza;
    private Recipe hamburger;
    private Category kjøtt;
    private Category burger;
    private Category italiensk;
    private Cookbook cookbook;
    private ArrayList<Category> tCategories;

    @BeforeEach
    public void setup() {
        cookbook = new Cookbook();
        HashMap<String, String> ost = new HashMap<String, String>() {{
            put("name", "ost");
            put("amount", "1.0");
            put("unit", "kg");
        }};
        HashMap<String, String> melk = new HashMap<String, String>() {{
            put("name", "melk");
            put("amount", "2.0");
            put("unit", "L");
        }};

        HashMap<String, String> tomat = new HashMap<String, String>() {{
            put("name", "tomat");
            put("amount", "5.0");
            put("unit", "stk");
        }};

        italiensk = new Category("italiensk");
        burger = new Category("burger");
        kjøtt = new Category("kjøtt");

        pizza = new Recipe("Pizza", 2, "Pizza er godt", "45 minutter", new ArrayList<HashMap<String, String>>(Arrays.asList(ost, melk)), new ArrayList<Category>(Arrays.asList(italiensk)), new ArrayList<String>(Arrays.asList("Tiss i en kopp", "Kok øving")));
        hamburger = new Recipe("Hamburger", 1, "Hambur er godt", "30 minutter", new ArrayList<HashMap<String, String>>(Arrays.asList(ost, melk, tomat)), new ArrayList<Category>(Arrays.asList(kjøtt, burger)), new ArrayList<String>(Arrays.asList("Tiss i en kopp", "Kok øving", "blabla")));
        
        tCategories = new ArrayList<Category>(Arrays.asList(italiensk, burger, kjøtt));
    }

    @Test
    @DisplayName("Test add recipe and recipeAmount")
    public void RecipeContainment() {
        cookbook.addRecipe(pizza);
        assertTrue(cookbook.getRecipes().contains(pizza));
        assertFalse(cookbook.getRecipes().contains(hamburger));
        cookbook.addRecipe(hamburger);
        assertTrue(cookbook.getAmount() == 2);
        assertTrue(cookbook.getRecipes().size() == 2);

        //throw exceptions if recipe is duplicate
        assertThrows(IllegalArgumentException.class, () -> cookbook.addRecipe(pizza));
        assertThrows(NullPointerException.class, () -> cookbook.addRecipe(null));
    }

    @Test
    @DisplayName("Test remove recipe")
    public void recipeRemoval() {
        cookbook.addRecipe(pizza);
        cookbook.addRecipe(hamburger);

        //check if throw exception if recipe not in cookbook
        cookbook.removeRecipe(hamburger);
        assertThrows(IllegalArgumentException.class, () -> cookbook.removeRecipe(hamburger));

        assertFalse(cookbook.getRecipes().contains(hamburger));
        assertTrue(cookbook.getRecipes().contains(pizza));
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        Cookbook cookbook = new Cookbook();
        assertTrue(cookbook.getRecipes().isEmpty());
    }

    @Test
    @DisplayName("Test searchRecipes")
    public void testSearchRecipe() {
        cookbook.addRecipe(pizza);
        cookbook.addRecipe(hamburger);
        assertFalse(cookbook.searchRecipes("Pizza").contains(pizza));
        assertTrue(cookbook.searchRecipes("Pizza").contains(hamburger));
    }

    @Test
    @DisplayName("Test filterByCategories")
    public void testfilterByCategories() {
        pizza.addCategory(italiensk);
        pizza.addCategory(burger);
        hamburger.addCategory(italiensk);
        cookbook.addRecipe(pizza);
        cookbook.addRecipe(hamburger);
        assertTrue(cookbook.filterByCategories(cookbook.getRecipes(), tCategories).contains(pizza));
        tCategories.remove(italiensk);
        assertFalse(cookbook.filterByCategories(cookbook.getRecipes(), tCategories).contains(pizza));
    }

    @Test
    @DisplayName("Test collectCategories")
    public void testcollectCategories() {
        cookbook.addRecipe(pizza);
        pizza.addCategory(italiensk);
        cookbook.collectCategories();
        assertEquals(cookbook.getCategories(), new ArrayList<>(Arrays.asList(italiensk)));
    }

    @Test
    @DisplayName("Test filter")
    public void testcollectCategories() {
        cookbook.addRecipe(pizza);
        pizza.addCategory(italiensk);
        cookbook.collectCategories();
        assertEquals(cookbook.getCategories(), new ArrayList<>(Arrays.asList(italiensk)));
    }
}
