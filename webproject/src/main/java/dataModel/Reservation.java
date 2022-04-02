package dataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "date")
	private String date;

	@Column(name = "timeOfArrival")
	private String timeOfArrival;

	@Column(name = "numberOfPeople")
	private Integer numberOfPeople;

	public Reservation(String firstName, String lastName, String phone, String date, String timeOfArrival,
					   Integer numberOfPeople) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.date = date;
		this.timeOfArrival = timeOfArrival;
		this.numberOfPeople = numberOfPeople;
	}

	public Reservation() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeOfArrival() {
		return timeOfArrival;
	}

	public void setTimeOfArrival(String timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	@Override
	public String toString() {
		return "Reservation: " + this.id + ", " + this.firstName + ", " + this.lastName + ", " + this.phone + ", "
				+ this.date + ", " + this.timeOfArrival + ", " + this.numberOfPeople;
	}
}