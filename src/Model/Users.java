package Model;

public class Users
{
  protected int id;
  protected String name;
  protected String username;
  protected String password;
  protected String role;

  //Primary Constructor
  public Users(int id, String name, String username, String password, String role) 
  {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  //Getters
  public int getId() 
  {
    return id;
  }

  public String getName() 
  {
    return name;
  }

  public String getUsername() 
  {
    return username;
  }

  public String getPassword() 
  {
    return password;
  }

  public String getRole() 
  {
    return role;
  }

  //Setters
  public void setId(int id) 
  {
    this.id = id;
  }

  public void setName(String name) 
  {
    this.name = name;
  }

  public void setUsername(String username) 
  {
    this.username = username;
  }

  public void setPassword(String password) 
  {
    if(password.length() >= 8 && password.length() <= 12)
    {
      this.password = password;
    }
    else
    {
      throw new IllegalArgumentException("Password must be between 8 and 12 characters");
    }
  }

  public void setRole(String role) 
  {
    this.role = role;
  }
}
