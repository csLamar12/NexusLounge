package Model;


/*
Add methods and attributes as needed, JUST COMMUNICATE BEFORE DOING SO!!!
 */
public class Users {
    protected int id;
    protected String name;
    protected String username;
    protected String password;
    protected String role;

    // Default constructor
    public Users() {
      this.id = 0;
      this.name = null;
      this.username = null;
      this.password = null;
      this.role = null;
    }

  /**
   * Primary Controller for the Users Class
   *
   * @param id  Unique ID number for a user
   * @param name  Name of the user (Nullable)
   * @param username  User's username (Nullable)
   * @param password  User's Password (Nullable)
   * @param role  User's role (Guest, Manager, Bartender)
   */
    public Users(int id, String name, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

  /**
   * Primary Controller for the Users Class (Without ID field)
   *
   * @param name  Name of the user (Nullable)
   * @param username  User's username (Nullable)
   * @param password  User's Password (Nullable)
   * @param role  User's role (Guest, Manager, Bartender)
   */
    public Users(String name, String username, String password, String role) {
      this.name = name;
      this.username = username;
      this.password = password;
      this.role = role;
    }

    // Copy Constructor
    public Users(Users copy) {
        this.id = copy.id;
        this.name = copy.name;
        this.username = copy.username;
        this.password = copy.password;
        this.role = copy.role;
    }

    //Getters
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        if(password.length() >= 8 && password.length() <= 12){
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be between 8 and 12 characters");
        }
    }

    public void setRole(String role){
        this.role = role;
    }
}
