/**
 */
package util;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import dataModel.Reservation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBDong {
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static List<Reservation> listReservations() {
		List<Reservation> resultList = new ArrayList<Reservation>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> reservations = session.createQuery("FROM Reservation").list();
			for (Iterator<?> iterator = reservations.iterator(); iterator.hasNext();) {
				Reservation reservation = (Reservation) iterator.next();
				resultList.add(reservation);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}

	public static List<Reservation> listReservations(String keyword) {
		List<Reservation> resultList = new ArrayList<Reservation>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println((Reservation) session.get(Reservation.class, 1));
			List<?> reservations = session.createQuery("FROM Reservation").list();
			for (Iterator<?> iterator = reservations.iterator(); iterator.hasNext();) {
				Reservation reservation = (Reservation) iterator.next();
				if (reservation.getFirstName().equals(keyword)) {
					resultList.add(reservation);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sortByTime(resultList);
	}

	public static void createReservation(String firstName, String lastName, String phone, String date,
			String timeOfArrival, Integer numberOfPeople) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Reservation(firstName, lastName, phone, date, timeOfArrival, numberOfPeople));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<String> readFile(String filePath) {
		List<String> contents = new ArrayList<String>();
		File file = new File(filePath);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				contents.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return contents;
	}

	public static List<Reservation> sortByName(List<Reservation> s) {

		Collections.sort(s, new SortByName());
		return s;
	}

	public static List<Reservation> sortByTime(List<Reservation> s) {

		Collections.sort(s, new SortByTime());
		return s;
	}
}
