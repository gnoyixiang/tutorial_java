package ClubManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingRegister {
	private HashMap<Facility, ArrayList<Booking>> bookingRegister;

	public BookingRegister() {
		bookingRegister = new HashMap<Facility, ArrayList<Booking>>();
	}

	public HashMap<Facility, ArrayList<Booking>> getBookingRegister() {
		return bookingRegister;
	}
	
	public boolean addBooking(Member m, Facility f, Date startdate, Date enddate) throws BadBookingException {
		Booking b = new Booking(m, f, startdate, enddate);
		ArrayList<Booking> fbooking = bookingRegister.get(f);
		if (fbooking != null) {
			// check overlap
			for (Booking booking : fbooking) {
				if (booking.overlaps(b)) {
					throw new BadBookingException("overlap in booking");
				}
			}
			return fbooking.add(b);
		}
		bookingRegister.put(f, new ArrayList<Booking>());
		return bookingRegister.get(f).add(b);

	}

	public boolean removeBooking(Booking b) {
		ArrayList<Booking> fbooking = bookingRegister.get(b.getF());
		if (fbooking != null) {
			return fbooking.remove(b);
		}
		return false;
	}

	public ArrayList<Booking> getBookings(Facility f, Date startdate, Date enddate) {
		ArrayList<Booking> fbooking = bookingRegister.get(f);
		ArrayList<Booking> getBookings = new ArrayList<>();
		for (Booking b : fbooking) {
			if (!(b.getStartDate().before(startdate) || b.getStartDate().after(enddate))) {
				getBookings.add(b);
			}
		}
		return getBookings;
	}

	public void showBookings() {
		for (Map.Entry<Facility, ArrayList<Booking>> br : bookingRegister.entrySet()) {
			System.out.println(br.getKey() + ":");
			ArrayList<Booking> bookings = br.getValue();
			for (Booking b : bookings) {
				System.out.println(b);
			}
			System.out.println();
		}
	}

	public void showBookings(ArrayList<Booking> bookings) {
		for (Booking b : bookings) {
			System.out.println(b);
		}
	}

	public void showBookings(Facility f) {
		ArrayList<Booking> bookings = bookingRegister.get(f);
		for (Booking b : bookings) {
			System.out.println(b);
		}
	}

	public static void main(String args[]) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		BookingRegister br = new BookingRegister();
		Club c = new Club();
		try {
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("17/12/2017"),
					sdf.parse("19/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("21/12/2017"),
					sdf.parse("22/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("23/12/2017"),
					sdf.parse("23/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("24/12/2017"),
					sdf.parse("24/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("25/12/2017"),
					sdf.parse("25/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("26/12/2017"),
					sdf.parse("26/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("27/12/2017"),
					sdf.parse("27/12/2017"));
			br.addBooking(c.getMembers().get(1), c.getFacility("Gym"), sdf.parse("28/12/2017"),
					sdf.parse("28/12/2017"));
			System.out.println();
			br.showBookings();
			System.out.println();
			br.showBookings(br.getBookings(c.getFacility("Gym"), sdf.parse("24/12/2017"), sdf.parse("31/12/2017")));
			System.out.println();
			br.showBookings();
			System.out.println();
			System.out.println(br.removeBooking(new Booking(c.getMembers().get(1), c.getFacility("Gym"),
					sdf.parse("23/12/2017"), sdf.parse("23/12/2017"))));
			System.out.println(br.removeBooking(new Booking(c.getMembers().get(1), c.getFacility("Swimming Pool"),
					sdf.parse("23/12/2017"), sdf.parse("23/12/2017"))));
			System.out.println(br.removeBooking(br.getBookingRegister().get(c.getFacility("Gym")).get(2)));
			System.out.println();
			br.showBookings();
			System.out.println();

		} catch (BadBookingException e) {
			System.out.println(e.getMessage());
		}

	}


}
