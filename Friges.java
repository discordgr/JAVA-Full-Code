public class Friges extends HouseholdAppliances
{
	private String type;
	private float FrigeSpaceUp;
	private float FrigeSpaceDown;
	
	public Friges(String code, String name, String year, String manufacturer, double price, String energysave,  String type, float FrigeSpaceUp, float FrigeSpaceDown)
	{
		super(code, name, year, manufacturer, price, energysave );
		this.FrigeSpaceUp = FrigeSpaceUp;
		this.FrigeSpaceDown = FrigeSpaceDown;
		this.type=type;
	}	
		
	public Friges(){}	
		
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
		
	public float getFrigeSpaceUp()
	{
		return FrigeSpaceUp;
	}
	
	public float getFrigeSpaceDown()
	{
		return FrigeSpaceDown;
	}
	
	public void setFrigeSpaceUp(float FrigeSpaceUp)
	{
		this.FrigeSpaceUp = FrigeSpaceUp;
	}
	
	public void setFrigeSpaceDown(float FrigeSpaceDown)
	{
		this.FrigeSpaceDown = FrigeSpaceDown;
	}
	
	public String toString()
	{
	
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
		   "\nManufacturer: " + manufacturer + "\nPrice: " + price + "\nEnergyclass: " + energysave + "\nFrigeType: " + this.type + "\nFridge refrigeration Space:  "+ this.FrigeSpaceUp + "\nFridge Main Space: " + this.FrigeSpaceDown + "\n";
	}
}