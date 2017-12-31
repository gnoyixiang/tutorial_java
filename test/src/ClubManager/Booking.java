package ClubManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class Booking {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Member m;
	private Facility f;
	private Date startDate, endDate;

	/*
	 * Constructor
	 */
	public Booking(Member m, Facility f, Date startDate, Date endDate) throws BadBookingException {
		super();

		checkBooking(m, f, startDate, endDate);

		this.m = m;
		this.f = f;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	private void checkBooking(Member m, Facility f, Date startDate, Date endDate) throws BadBookingException {
		String eMsg = null;
		if (m == null) {
			eMsg += "member cannot be null\n";
		}
		if (f == null) {
			eMsg += "facility cannot be null\n";
		}
		if (startDate == null) {
			eMsg += "start date cannot be null\n";
		}
		if (endDate == null) {
			eMsg += "end date cannot be null\n";
		}
		if (startDate.after(endDate)) {
			eMsg += "start date cannot be before end date\n";
		}
		if (eMsg != null) {
			throw new BadBookingException(eMsg);
		}
	}

	/*
	 * Getters and Setters
	 */
	public Member getM() {
		return m;
	}

	public void setM(Member m) {
		this.m = m;
	}

	public Facility getF() {
		return f;
	}

	public void setF(Facility f) {
		this.f = f;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Booking [m=" + m + ", f=" + f + ", startDate=" + sdf.format(startDate) + ", endDate=" + sdf.format(endDate) + "]";
	}

	public boolean overlaps(Booking b) {
		if (b.getF() != f)
			return false;
		if (b.startDate.equals(startDate) || b.endDate.equals(endDate)
				|| !(b.endDate.before(startDate) || b.startDate.after(endDate))) {
			return true;
		}
		return false;
	}

}
