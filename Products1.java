public class Products1
{

	protected String code;
	protected String name;
	protected String year;
	protected String manufacturer;
	protected double price;
	protected final double discount = 0;
	private String imagePath;
	
	public Products1(String code, String name, String year, String manufacturer, double price){
		this.code = code;
		this.name = name;
		this.year = year;
		this.manufacturer = manufacturer;
		this.price = price;
	}
	
	public Products1(){}
	
	public String getCode(){
		return code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName (String name ){
		this.name = name;
	}
	
	public String getYear(){
		return year;
	}
	
	public void setYear (String year){
		this.year = year;
	}
	
	public String getManufacturer(){
		return manufacturer;
	}
	
	public void setManufacturer (String manufacturer){
		this.manufacturer = manufacturer;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice (double price){
		this.price = price;
	}
	
	public double getDiscount()
	{
		return discount;
	}
	
	public void setImagePath(String path)
	{
		imagePath = path;
	}
	
	
	public String getImagePath()
	{
		return imagePath;
	}
	
	public String getContents() {
		
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price;
	}
	
	@Override
	public String toString() {
		
		return "Code: "+ code +"\nModelName: " + name + "\nYear: " + year + 
			   "\nManufacturer: " + manufacturer + "\nPrice: " + price;
	}

	
}