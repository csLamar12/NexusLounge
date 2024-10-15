import package Model;

import java.util.Date;

public class Guests extends Users
{
  private Date dob;

  //Primary Constructor
  public Guest(int id, String name, String username, String password, String role, Date dob) 
  {
    super(id, name, username, password, "Guest");
    this.dob = dob;
  }

  // Getters and Setters
  public Date getDob()
  {
    return dob;
  }

  public void setDob(Date dob)
  {
    this.dob = dob;
  }
}
