public class BRDVD extends ImageSound
{ 	
    private String type;
	private String format;

	public BRDVD(String code, String name, String year, String manufacturer, double price,String resolution, String type,   String format ) {
		
		super(code,name,year,manufacturer,price,resolution);
		this.format=format;
	    this.type=type;
	}
	
	public BRDVD(){}
	
	public String getType()
	{
		return type;
	} 
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getFormat()
	{
		return format;
	}
    
	public void setFormat(String newformat)
	{
		this.format = newformat;
	}
	
	public String toString()
	{
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price + "\nType: " + type  + "\nResolution: "+resolution+"\nformat: "+this.format+ "\n";
	}
}