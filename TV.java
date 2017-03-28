public class TV extends ImageSound
{
   
    private String type;
	private String dimension;
	private String port;
	
	public TV(String code, String name, String year, String manufacturer, double price,String resolution,  String type, String dimension, String port)
	{
		super(code,name,year,manufacturer,price,resolution);
	    this.dimension=dimension;
		this.port=port;
		this.type = type;
	}
	
	public TV(){}
	
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	// All getters
	public String getDimension(){
		return dimension;		
	}
	
	public double getDiscount() 
	{
		return discount;
	}
	
	public String getPort(){
		return port;
	}
	
	// All setters 
	
	
	public void setDimension(String dimension){
		this.dimension = dimension;
	}
	
	public void setPort(String port){
		this.port = port;
	}
	
	public String toString()
	{   
	
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price + "\nType: " + type + "\nResolution :" + resolution + "\nDimention: "+ dimension + "\nport: "+ port+ "\n";
	}
}	