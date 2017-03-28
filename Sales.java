public class Sales 
{
	
	private String scode, productsale,fullname,phone,date, totalcost;
	
	
	public Sales( String scode, String productsale, String fullname, String phone,String date,String totalcost )
	{
		this.scode = scode;
		this.productsale = productsale;
		this.fullname = fullname;
		this.phone = phone;
		this.date = date;
		this.totalcost = totalcost;
	}
	
	public Sales(){}
	// All getters
	
	public String getScode()
	{
		return this.scode;
	}
	
	public String getProductSale()
	{
		return this.productsale;
	}
	
	public String getFullName()
	{
		return this.fullname;
	}
	
	public String getPhone()
	{
		return this.phone;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public String getTotalCost()
	{
		return this.totalcost;
	}
	
	// All setters
	
	public void setScode(String newscode)
	{
		this.scode = newscode;
	}
	
	public void setProductSale(String newproductsale)
	{
		this.productsale = newproductsale;
	}
	
	public void setFullName(String newfullname)
	{
		this.fullname = newfullname;
	}
	
	public void setPhone(String newphone)
	{
		this.phone = newphone;
	}
	
	public void setDate(String newdate)
	{
		this.date = newdate;
	}
	
	public void setTotalCost(String newtotalcost)
	{
		this.totalcost = newtotalcost;
	}
	
	public String toString()
	{
		return "\nSales code: " + this.scode + "\nProduct to be saled: " + this.productsale + "\nFullname: " + this.fullname + "\nPhone number: " + this.phone + "\nDate: " + this.date + "\nTotalcost: " +this.totalcost + "\n";
	}
}