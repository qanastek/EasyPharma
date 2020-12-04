package application.models.Abstracts;

import java.util.ArrayList;

import application.models.Interfaces.Observer;

public abstract class Subject {
	
	ArrayList<Observer> obs = new ArrayList<Observer>();
	
	public void add(Observer o) {
		obs.add(o);
	}
	
	public void del(Observer o) {
		obs.remove(o);		
	}
	
	public void alertAll() {
		
		for (Observer o : obs) {
			
			o.update();
		}
	}

}
