public class Order
{
	
	private String ocode, productorder , fullname , phone , orderdate , arrivaldate ,cost;
	private boolean flag;
	
	public Order(String ocode,String productorder,String fullname,String phone,String orderdate,String arrivaldate,String cost ,boolean flag)
	{
		this.ocode=ocode;
		this.productorder=productorder;
		this.fullname=fullname;
	    this.phone=phone;
		this.orderdate=orderdate;
		this.arrivaldate=arrivaldate;
		this.cost=cost;
		this.flag=flag;
	
	}
	
	public Order(){}
	
	// All getters 
	public String getOcode()
	{
		return ocode;
	}
	
	public String getProductorder()
	{
		return productorder;
	}
	
	public String getFullname()
	{
		return fullname;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public String getOrderdate()
	{
		return orderdate;
	}
	
	public String getArrivaldate()
	{
		return arrivaldate;
	}
	
	public String getCost()
	{
		return cost;
	}
	
	// All setters
	public void setOcode(String ocode)
	{
		this.ocode = ocode;
	}
	
	public void setProductor(String productorder)
	{
		this.productorder = productorder;
	}
	
	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setOrderdate(String orderdate)
	{
		this.orderdate = orderdate;
	}
	
	public void setArrivaldate(String arrivaldate)
	{
		this.arrivaldate = arrivaldate;
	}
	
	public void setCost(String cost)
	{
		this.cost = cost;
	}
	
	// Get, Set Flag
	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
	
	public boolean getFlag()
	{
		return flag;
	}
	
	public String toString()
	{
		if (flag)
		{
		return "Order Code: " + this.ocode + "\nProduct to be ordered: " + this.productorder + "\nFullname: " + this.fullname + "\nPhone: " + this.phone + "\nOrderdate: " + this.orderdate + "\nArrivaldate: " + this.arrivaldate
			+ "\nCost: " + this.cost + "\nStatus: !Done";
		}
		else
		{
			return "Order Code: " + this.ocode + "\nProduct to be ordered: " + this.productorder + "\nFullname: " + this.fullname + "\nPhone: " + this.phone + "\nOrderdate: " + this.orderdate + "\nArrivaldate: " + this.arrivaldate
			+ "\nCost: " + this.cost + "\nStatus: Expected";
		}
	}
}