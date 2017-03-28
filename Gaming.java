public class Gaming extends Products1
{
	private String proccessor,graphics,sound,capacity;
	private String type;
	protected final double discount = 0.10;
	
	public Gaming (String code, String name, String year, String manufacturer, double price, String type, String proccessor, String graphics, String sound, String capacity)
	{
		super(code,name,year,manufacturer,price);
		this.type = type;
		this.proccessor = proccessor;
		this.graphics = graphics;
		this.sound = sound;
		this.capacity = capacity;
	}
	
	public Gaming(){}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getProccessor()
	{
		return this.proccessor;
	}
	
	public String getGraphics()
	{
		return this.graphics;
	}
	
	public String getSound()
	{
		return this.sound;
	}
	
	public String getCapacity()
	{
		return this.capacity;
	}
	
	public double getDiscount()
	{
		return discount;
	}
	
	public void setProccessor(String newproccessor)
	{
		this.proccessor = newproccessor;
	}
	
	public void setGraphics(String newgraphics)
	{
		this.graphics = newgraphics;
	}
	
	public void setSound(String newsound)
	{
		this.sound = newsound;
	}
	
	public void setCapacity(String newcapacity)
	{
		this.capacity = newcapacity;
	}
	
	public String toString() 
	{
		
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price + "\nType: " + type + "\nProccessor: " + this.proccessor + "\nGraphichs: " + this.graphics + "\nSound: " + this.sound + 
			   "\nCapacity: " + this.capacity + "\n";
	}
}