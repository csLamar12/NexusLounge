import package Model;

public class Manager extends Users
{
  public Manager(int id, String name, String username, String password, String role)
  {
    super(id, name, username, password, "Manager");
  }
}
