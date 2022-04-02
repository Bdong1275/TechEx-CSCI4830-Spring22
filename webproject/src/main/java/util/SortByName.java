package util;

import java.util.Comparator;

import dataModel.Reservation;

public class SortByName implements Comparator<Reservation>{
	
	//by first name in descending order
	public int compare(Reservation a, Reservation b) {
		
		return a.getFirstName().compareTo(b.getFirstName());
	}

}
