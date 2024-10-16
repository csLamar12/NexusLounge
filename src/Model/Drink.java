//Author: Danielle Johns
package Model;

// The Drink class represents a drink with an ID, name, and whether it is alcoholic or not.
public class Drink {

    // Private fields to store the drink's unique ID, name, and alcoholic status
    private int drinkId;
    private String name;
    private boolean isAlcoholic;

    // Constructor to initialize a Drink object with specific values for drinkId, name, and isAlcoholic
    public Drink(int drinkId, String name, boolean isAlcoholic) {
        this.drinkId = drinkId;             // Unique identifier for the drink
        this.name = name;                   // Name of the drink
        this.isAlcoholic = isAlcoholic;     // Boolean indicating if the drink contains alcohol
    }

    // Getter method to retrieve the drink's ID
    public int getId() {
        return drinkId;
    }

    // Setter method to update the drink's ID
    public void setId(int id) {
        this.drinkId = id;
    }

    // Getter method to retrieve the name of the drink
    public String getName() {
        return name;
    }

    // Setter method to update the name of the drink
    public void setName(String name) {
        this.name = name;
    }

    // Getter method to check if the drink is alcoholic
    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    // Setter method to update the alcoholic status of the drink
    public void setAlcoholic(boolean isAlcoholic) {
        this.isAlcoholic = isAlcoholic;
    }

    // Overriding the toString() method to provide a string representation of a Drink object
    @Override
    public String toString() {
        return "Drink [id=" + drinkId + ", name=" + name + ", isAlcoholic=" + isAlcoholic + "]";
    }
}
