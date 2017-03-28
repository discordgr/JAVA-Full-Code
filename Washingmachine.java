public class Washingmachine extends HouseholdAppliances
{
	private int bends;
	private String washingspace;
	
	public Washingmachine(String code, String name, String year, String manufacturer, double price, String energysave, String washingspace, int bends )
	{
		super(code,name,year,manufacturer,price,energysave);
		this.washingspace = washingspace;
		this.bends = bends;
	}
	
	public Washingmachine(){}
	
	// All getters
	public String getWashingSpace()
	{
		return this.washingspace;
	}
	
	public int getBends()
	{
		return this.bends;
	}
	
	// All setters
	public void setWashingSpace(String newwashingspace)
	{
		this.washingspace = newwashingspace;
	}
	
	public void setBends(int newbends)
	{
		this.bends = newbends;
	}
	
	public String toString()
	{
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price + "\nEnergyclass: "+energysave + "\nWashingspace: "+ this.washingspace + "\nBends: "+ this.bends + "\n";
    }
}