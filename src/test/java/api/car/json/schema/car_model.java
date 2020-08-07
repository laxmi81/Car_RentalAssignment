package api.car.json.schema;

public class car_model {
	public String make;
	public String model;
	public String vin;
	/*public String Color;
	public String Notes;*/
	/*public float Price;
	public float Discount;
*/	/*public float yoymaintenancecost;
	public float depreciation;
	public int lastweek;
	public int yeartodate;*/
	
	public Metadata metadata;
	public Perdayrent perdayrent;
	public Metrics metrics;
	public car_model()
	{
		
	}
	@Override
    public String toString() {
return make;

    }
	/*@Override
		public java.lang.String toString() {
			// TODO Auto-generated method stub
			return "name="+ this.name + "mode==="+this.model+this.color;
		}*/

	/*public car_model_list(String make,String model,String vin,Metadata metadata,float Price, float Discount, float yoymaintenancecost, float depreciation,int lastweek,int yeartodate )
	{
		this.make=make;
		this.model=model;
		this.vin=vin;
		metadata=metadata;
		
		this.Price=Price;
		this.Discount=Discount;
		this.yoymaintenancecost=yoymaintenancecost;
		this.depreciation=depreciation;
		this.lastweek=lastweek;
		this.yeartodate=yeartodate;*/
		
	//}
	public String getmake()
	{
		return make;
	}
}
