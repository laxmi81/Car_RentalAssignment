package api_car_test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import java.util.TreeMap;
import java.util.TreeSet;
import com.github.tomakehurst.wiremock.WireMockServer;
import api.car.json.schema.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class car_test {
     
     WireMockServer wireMockServer;
    public void setupStub() {
    	wireMockServer.stubFor(get(urlEqualTo("/test"))
    	            .willReturn(aResponse()
    	                .withHeader("Content-Type", "appplication/json")
    	                .withStatus(200)
    	                .withBody("[\r\n" + 
    	                		"  {\r\n" + 
    	                		"    \"make\": \"Audi\",\r\n" + 
    	                		"    \"model\": \"A7\",\r\n" + 
    	                		"    \"vin\": \"09AGHY64352JITEG98K\",\r\n" + 
    	                		"    \"metadata\": {\r\n" + 
    	                		"      \"Color\": \"Black\",\r\n" + 
    	                		"      \"Notes\": \"Scratches on the front side of the car\"\r\n" + 
    	                		"    },\r\n" + 
    	                		"    \"perdayrent\": {\r\n" + 
    	                		"      \"Price\": 140,\r\n" + 
    	                		"      \"Discount\": 15\r\n" + 
    	                		"    },\r\n" + 
    	                		"    \"metrics\": {\r\n" + 
    	                		"      \"yoymaintenancecost\": 2190.76,\r\n" + 
    	                		"      \"depreciation\": 2256.43,\r\n" + 
    	                		"      \"rentalcount\": {\r\n" + 
    	                		"        \"lastweek\": 4,\r\n" + 
    	                		"        \"yeartodate\": 221\r\n" + 
    	                		"      }\r\n" + 
    	                		"    }\r\n" + 
    	                		"  },\r\n" + 
    	                		" {\r\n" + 
    	                		"    \"make\": \"honda\",\r\n" + 
    	                		"    \"model\": \"crv\",\r\n" + 
    	                		"    \"vin\": \"09AGHDGH56JITEG98K\",\r\n" + 
    	                		"    \"metadata\": {\r\n" + 
    	                		"      \"Color\": \"gray\",\r\n" + 
    	                		"      \"Notes\": \"Side color faded of the car\"\r\n" + 
    	                		"    },\r\n" + 
    	                		"    \"perdayrent\": {\r\n" + 
    	                		"      \"Price\": 120,\r\n" + 
    	                		"      \"Discount\": 20\r\n" + 
    	                		"    },\r\n" + 
    	                		"    \"metrics\": {\r\n" + 
    	                		"      \"yoymaintenancecost\": 3012.46,\r\n" + 
    	                		"      \"depreciation\": 2256.43,\r\n" + 
    	                		"      \"rentalcount\": {\r\n" + 
    	                		"        \"lastweek\": 4,\r\n" + 
    	                		"        \"yeartodate\": 221\r\n" + 
    	                		"      }\r\n" + 
    	                		"    }\r\n" + 
    	                		"  },\r\n" + 
    	                		" {\r\n" + 
    	                		"    \"make\": \"tesla\",\r\n" + 
    	                		"    \"model\": \"m12\",\r\n" + 
    	                		"    \"vin\": \"093453BHGTEG98K\",\r\n" + 
    	                		"    \"metadata\": {\r\n" + 
    	                		"      \"Color\": \"blue\",\r\n" + 
    	                		"      \"Notes\": \"Side mirror image is faded of the car\"\r\n" + 
    	                		"    },\r\n" + 
    	                		"    \"perdayrent\": {\r\n" + 
    	                		"      \"Price\": 100,\r\n" + 
    	                		"      \"Discount\": 12\r\n" + 
    	                		"    },\r\n" + 
    	                		"    \"metrics\": {\r\n" + 
    	                		"      \"yoymaintenancecost\": 3412.46,\r\n" + 
    	                		"      \"depreciation\": 2356.43,\r\n" + 
    	                		"      \"rentalcount\": {\r\n" + 
    	                		"        \"lastweek\": 3,\r\n" + 
    	                		"        \"yeartodate\": 221\r\n" + 
    	                		"      }\r\n" + 
    	                		"    }\r\n" + 
    	                		"  }\r\n" + 
    	                		"]")));
    	}

    @Before
    public void configureSystemUnderTest() {
    	 this.wireMockServer = new WireMockServer(options().port(9090));  
    	 this.wireMockServer.start();
    }
    
   @Test
	public void a_testTesla()
	{
		System.out.println("wire mock url- "+wireMockServer.baseUrl());
		setupStub();
		RestAssured.baseURI="http://localhost:9090";
		Response res=RestAssured.given().contentType("application/json").get("http://localhost:9090/test");
		//String response = get(wireMockRule.baseUrl()).asString()
		
		car_model[] listCar=res.as(car_model[].class);
		System.out.println("total cars= "+listCar.length);
		
		for (car_model car: listCar) {

	            if (car.metadata.Color.equalsIgnoreCase("blue")) {

	            	  System.out.println("blue car is =  " +car);
	            }
	        }	
	}
    
    
    @Test
	public void b_testLowRent_PriceOnly()
	{
		setupStub();
		RestAssured.baseURI="http://localhost:9090";
		Response res=RestAssured.given().contentType("application/json").get("http://localhost:9090/test");
		//String response = get(wireMockRule.baseUrl()).asString()
		
		car_model[] listCar=res.as(car_model[].class);
		
		TreeSet<Float> ts1 = new TreeSet<Float>();
	        for (car_model car: listCar) {
	        	ts1.add(car.perdayrent.Price);
	        }
        System.out.println("min price of car=  "+ts1.first());	
	}
    
    //price after discount low rent calculate
    @Test
	public void c_testLowRent_Price_discountOnly()
	{
		//wireMockRule.
		
	//	System.out.println("base url"+wireMockServer.baseUrl());
		setupStub();
		RestAssured.baseURI="http://localhost:9090";
		Response res=RestAssured.given().contentType("application/json").get("http://localhost:9090/test");
		//String response = get(wireMockRule.baseUrl()).asString()
		
		car_model[] listCar=res.as(car_model[].class);
		
		float Dis,F_rent;
		TreeMap<Float,Float> ts1 = new TreeMap<Float,Float>();
	        for (car_model car: listCar) {
	        	Dis=(car.perdayrent.Discount/100)*car.perdayrent.Price;
	        	F_rent=car.perdayrent.Price-Dis;
	        	ts1.put(Dis,F_rent );
	       }
	        System.out.println("min discount and car rent=  "+ts1.firstEntry().toString());
   	}
    /*//Find the highest revenue generating car. year over year maintenance cost + depreciation is the total expense
    per car for the full year for the rental car company.	The objective is to find those cars that produced the 
    highest profit in the last year*/

    @Test
   	public void d_highest_revenue()
   	{
   		//System.out.println("base url"+wireMockServer.baseUrl());
   		setupStub();
   		RestAssured.baseURI="http://localhost:9090";
   		Response res=RestAssured.given().contentType("application/json").get("http://localhost:9090/test");
   		//String response = get(wireMockRule.baseUrl()).asString()
   		
   		car_model[] listCar=res.as(car_model[].class);
   		
   		float total_expense;
   		TreeMap<Float,String> ts1 = new TreeMap<Float,String>();
   	        for (car_model car: listCar) {
   	        	total_expense=car.metrics.yoymaintenancecost+car.metrics.depreciation;
   	        	ts1.put(total_expense, car.make);
   	      
   	        }
   	        System.out.println("total_expense and car=  "+ts1.lastEntry().toString());   		
   	}
    @After
    public void stopWireMockServer() {
        this.wireMockServer.stop();
    }
}

