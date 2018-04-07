# :sunglasses: Observer Pattern
> A real example would be subscriber to a magazine service. Whenever there is new magazine in the store , the subscriber get notified for the same.

Defination
> Defines a dependency between objects so that whenever an object changes its state, all its dependents are notified.

Scenario: Here we are implementing a weather Station with DisplayElement as Observer. We Define 3 Interfaces 
* Subject (for weatherData) 
* DisplayElement (for differentDisplay)
* Observer (for differentDisplay).

```java
//Subject
public  interface Subject {
public void registerObserver(Observer o);
public void removeObserver(Observer o);
public void notifyObservers();
}

//Observer
public  interface Observer {
public  void update(float  temp, float  humidity, float  pressure);
}

//DisplayElement
public interface DisplayElement {
public void display();
}

```
Now in order to implement Observer pattern here, we make weather station as the **Subject** and displayElements as the **observer**. Whenever measurements changed we will notify all Observers. 


```java
public class WeatherData implements Subject {
	private ArrayList<Observer> observers;
	private  float  temperature;
	private  float  humidity;
	private  float  pressure;

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

	//On updation of code we will notify all the Observers
	public void setMeasurements(float temp, float humidity, float pressure) {
	this.temperature = temp;
	this.humidity  = humidity;
	this.pressure  = pressure;
	measurementsChanged();
	}
}

```

In currentConditionDisplay (a displayElement:pager:  for showing pressure and humidity reading). 
and we are using **composition** for registering our observer using *weatherData* and implementing
the Observer and DisplayElement Interface.



```java
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
```


Using our implementation for Weather Station.

```java
public class WeatherStation {
  public static void main(String[] args) {
     WeatherData weatherData = new WeatherData();
    CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
    weatherData.setMeasurements(80, 65, 30.4f);  
  }
}
```

Output
>Current conditions: 80.0F degrees 65.0 % humidity