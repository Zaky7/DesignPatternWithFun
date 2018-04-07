
public class CurrentConditionsDisplay implements Observer, DisplayElement{
     private float   temperature;
     private float   humidity;
     private Subject weatherData;
     
     
     public CurrentConditionsDisplay(Subject weatherData) {
    	this.weatherData = weatherData;
    	weatherData.registerObserver(this);
    }
     
    //updateCurrentConditionDisplay and Displaying 
	public void update(float temp, float humidity, float pressure) {
       this.temperature = temp;
       this.humidity    = humidity;
       display();
	}

	public void display() {
		System.out.println("Current conditions: "+ temperature + "F degrees "+ humidity + " % humidity");
	}
	    
}
