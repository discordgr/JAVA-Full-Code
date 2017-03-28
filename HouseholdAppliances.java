public  class HouseholdAppliances extends Products1 
{
	
	protected final double discount = 0.4;
	protected String energysave;
	
	public HouseholdAppliances(String code, String name, String year, String manufacturer, double price, String energysave ) 
	{
		super(code, name, year, manufacturer, price);
		this.energysave = energysave;
	}
	
	public HouseholdAppliances(){}
	
	public double getDiscount() 
	{
		return discount;
	}
	
	public String getEnergySave()
	{
		return energysave;
	}
	
	public void setEnergySave(String energysave)
	{
		this.energysave = energysave;
	}
	
	public String toString()
	{
		return "\nEnergyclass: "+energysave;
	}
}