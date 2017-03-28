public class ImageSound extends Products1
{
	protected final double discount = 0.25;
	protected String resolution;
	private boolean flag = false;
	
	public ImageSound(String code, String name, String year, String manufacturer, double price  ) 
	{
		super(code, name, year, manufacturer, price);
	}
	
	public ImageSound(){}
	
	public ImageSound (String code, String name, String year, String manufacturer, double price , String resolution)
	{
		super(code, name, year, manufacturer, price);
		this.resolution=resolution;
		this.flag = true;
	}
	
	public double getDiscount()
	{
		return discount;
	}
	
	public String getResolution()
	{
		return resolution;
	}
	
	public void setResolution(String newresolution)
	{
		this.resolution = newresolution;
	}
	
}