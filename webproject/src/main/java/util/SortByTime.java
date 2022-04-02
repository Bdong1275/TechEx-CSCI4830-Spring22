package util;

import java.util.Comparator;

import dataModel.Reservation;

public class SortByTime implements Comparator<Reservation>{

	public int compare(Reservation a, Reservation b) {

		return a.getDate().compareTo(b.getDate());
	}
}
