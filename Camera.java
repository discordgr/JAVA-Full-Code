public class Camera extends ImageSound
{
	
	private String megapixel,opticalzoom,digitalzoom,screensize;
	private String type;
	
	public Camera(String code, String name, String year, String manufacturer, double price, String type, String megapixel,String opticalzoom,String digitalzoom,String screensize)
	{
		super(code,name,year,manufacturer,price);
		this.megapixel = megapixel;
		this.opticalzoom = opticalzoom;
		this.digitalzoom = digitalzoom;
		this.screensize = screensize;
		this.type=type;
	}
	
	public Camera(){}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	// All getters
	public String getMegapixel()
	{
		return this.megapixel;
	}
	
	public String getOpticalzoom()
	{
		return this.opticalzoom;
	}
	
	public String getDigitalzoom()
	{
		return this.digitalzoom;
	}
	
	public String getScreensize()
	{
		return this.screensize;
	}
	
	// All setters
	public void setMegapixel(String newmegapixel)
	{
		this.megapixel = newmegapixel;
	}
	
	public void setOpticalzoom(String newopticalzoom)
	{
		this.opticalzoom = newopticalzoom;
	}
	
	public void setDigitalzoom(String newdigitalzoom)
	{
		this.digitalzoom = newdigitalzoom;
	}
	
	public void setScreensize(String newscreensize)
	{
		this.screensize = newscreensize;
	}
	
	public String toString()
	{
	
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price + "\nType: " + type + "\nMegapixel: " + this.megapixel + "\nOpticalzoom: " + this.opticalzoom + "\nDigitalzoom: " + this.digitalzoom + 
			   "\nScreensize: " + this.screensize+ "\n";
	}
	
}