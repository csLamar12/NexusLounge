package Model;

import java.util.Date;

public class Orders
  {
    private int orderId;
    private int guestId;
    private int bartenderId;
    private Date orderDate;
    private String orderStatus;

    //Primary Constructor
    public Order(int orderId, int guestID, int bartenderID, Date orderDate, String orderStatus) 
    {
        this.orderId = orderId;
        this.guestID = guestID;
        this.bartenderID = bartenderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    //Getters
    public int getOrderId() 
    {
        return orderId;
    }

    public int getGuestId() 
    {
        return guestId;
    }

    public int getBartenderId() 
    {
        return bartenderId;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }

    //Setters
    public void setOrderId(int orderId) 
    {
        this.orderId = orderId;
    }

    public void setGuestId(int guestId) 
    {
        this.guestId = guestId;
    }

    public void setBartenderId(int bartenderId) 
    {
        this.bartenderId = bartenderId;
    }

    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(String orderStatus) 
    {
        if(orderStatus.equals("open") || orderStatus.equals("served")
        {
            this.orderStatus = orderStatus;
        }
        else
        {
            throw new IllegalArgumentException("Invalid order status. Must be 'open' or 'served'.");
        }  
    }
  }
