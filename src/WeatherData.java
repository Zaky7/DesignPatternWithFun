import java.util.ArrayList;

public class WeatherData implements Subject {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherData() {
    	observers = new ArrayList<Observer>();
    }
    
    //Adding an Observer
    public void registerObserver(Observer o) {
    	observers.add(o);
    }
    
    //Removing an Observer
    public void removeObserver(Observer o){
    	int i = observers.indexOf(o);
    	if(i>=0) {
    		observers.remove(i);
    	}
    }
      
    //Notify all Observers
    public void notifyObservers(){
    	for(Observer observer : observers) {
    		observer.update(temperature, humidity, pressure);
    	}
    }
    
    //Wrapper for notify Observer Object
    public void measurementsChanged() {
    	notifyObservers();
    }
    
    public void setMeasurements(float temp, float humidity, float pressure) {
    	this.temperature = temp;
    	this.humidity    = humidity;
    	this.pressure    = pressure;
    	measurementsChanged();
    }

}
