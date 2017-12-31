package ClubManager;

import java.util.*;
import java.util.Map.Entry;
import java.sql.*;

public class Club {
	/*
	 * SQL Attributes
	 */
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rset;

	/*
	 * Class Attributes
	 */
	private static int CURRENT_NUMBER;
	private List<Member> members;
	private HashMap<String, Facility> facilities;
	private int memberCount;
	private int facilityCount;

	/*
	 * Constructor
	 */
	public Club() {
		refreshMembers();
		refreshFacilities();
	}

	/*
	 * Getters
	 */
	public static int getCURRENT_NUMBER() {
		return CURRENT_NUMBER;
	}

	public HashMap<String, Facility> getFacilities() {
		return facilities;
	}

	public int getFacilityCount() {
		return facilityCount;
	}

	public List<Member> getMembers() {
		return members;
	}

	public int getMemberCount() {
		return memberCount;
	}

	/*
	 * Private methods
	 */
	private void refreshMembers() {
		members = getAllMembers();
		memberCount = members.size();
		CURRENT_NUMBER = members.get(memberCount - 1).getMemberNumber();
	}

	private void refreshFacilities() {
		facilities = getAllFacilites();
		facilityCount = facilities.size();
	}

	private List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		
		try {
			// need to catch SQLException
			conn = DriverManager.getConnection(Utils.SQL_URL, Utils.SQL_USER, Utils.SQL_PW);
			// allocate statement in the connection
			stmt = conn.createStatement();
			String getAllMembers = "select * from member";
			rset = stmt.executeQuery(getAllMembers);
			while (rset.next()) {
				Member m = new Member(rset.getString("surName"), rset.getString("firstName"),
						rset.getString("secondName") == null ? "" : rset.getString("secondName"),
						rset.getInt("memberID"));
				members.add(m);
				// System.out.println(m);
			}
		} catch (SQLException e) {
			members = null;
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return members;
	}

	private HashMap<String, Facility> getAllFacilites() {
		HashMap<String, Facility> facilities;
		try {
			// need to catch SQLException
			conn = DriverManager.getConnection(Utils.SQL_URL, Utils.SQL_USER, Utils.SQL_PW);
			// allocate statement in the connection
			stmt = conn.createStatement();
			facilities = new HashMap<>();
			String getAllFacilities = "select * from facility";
			rset = stmt.executeQuery(getAllFacilities);
			while (rset.next()) {
				Facility f = new Facility(rset.getString("facilityName"), rset.getString("description"));
				facilities.put(f.getName(), f);
			}
		} catch (SQLException e) {
			facilities = null;
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return facilities;
	}

	private void closeConnection() {
		try {
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Public methods
	 */
	public void showMembers() {
		for (Member m : members) {
			System.out.println(m);
		}
	}

	public void showFacilities() {
		for (Entry<String, Facility> f : facilities.entrySet()) {
			System.out.println(f.getValue());
		}
	}

	public Facility getFacility(String name) {
		return facilities.get(name);
	}

	public boolean addFacility(Facility f) {
		boolean success = false;
		try {
			// need to catch SQLException
			conn = DriverManager.getConnection(Utils.SQL_URL, Utils.SQL_USER, Utils.SQL_PW);
			// allocate statement in the connection
			String insertFacility = "insert into facility(facilityName, description) values (?, ?)";
			pstmt = conn.prepareStatement(insertFacility);
			pstmt.setString(1, f.getName());
			pstmt.setString(2, f.getDescription());
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			refreshFacilities();
			closeConnection();
		}
		return success;

	}

	public boolean removeFacility(Facility f) {
		boolean success = false;
		try {
			conn = DriverManager.getConnection(Utils.SQL_URL, Utils.SQL_USER, Utils.SQL_PW);
			String removeFacility = "delete from facility where facilityName = ?";
			pstmt = conn.prepareStatement(removeFacility);
			pstmt.setString(1, f.getName());
			int rowsRemoved = pstmt.executeUpdate();
			if (rowsRemoved > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			refreshFacilities();
			closeConnection();
		}
		return success;
	}

	public boolean addMember(Member m) {
		boolean success = false;
		try {
			// need to catch SQLException
			conn = DriverManager.getConnection(Utils.SQL_URL, Utils.SQL_USER, Utils.SQL_PW);
			// allocate statement in the connection
			String insertMember = "insert into member(memberID, surName, firstName, secondName) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertMember);
			pstmt.setInt(1, m.getMemberNumber());
			pstmt.setString(2, m.getSurname());
			pstmt.setString(3, m.getFirstname());
			pstmt.setString(4, m.getLastname());
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			refreshMembers();
			closeConnection();
		}
		return success;

	}

	@SuppressWarnings("unused")
	private boolean removeMember(Member m) {
		boolean success = false;
		try {
			conn = DriverManager.getConnection(Utils.SQL_URL, Utils.SQL_USER, Utils.SQL_PW);
			String removeMember = "delete from member where memberID = ?";
			pstmt = conn.prepareStatement(removeMember);
			pstmt.setInt(1, m.getMemberNumber());
			int rowsRemoved = pstmt.executeUpdate();
			if (rowsRemoved > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			refreshMembers();
			closeConnection();
		}
		return success;

	}

	/*
	 * Main method
	 */
	public static void main(String args[]) {
		Club c = new Club();
/*		c.showMembers();
		System.out.println();
		//Member m = new Member("wang", "yx", "", Club.getCURRENT_NUMBER() + 1);
		System.out.println("--Adding member--");
		System.out.println();
		System.out.println(c.addMember(m));
		System.out.println();
		System.out.println(c.getMembers().get(c.getMemberCount() - 1));
		System.out.println();
		System.out.println("--Removing member--");
		System.out.println();
		System.out.println(c.removeMember(m));
		System.out.println();
		c.showMembers();
		System.out.println();
		
		c.showFacilities();
		System.out.println();
		Facility f = new Facility("library", "NEX");
		System.out.println("--Adding facility");
		System.out.println();
		System.out.println(c.addFacility(f));
		System.out.println();
		//System.out.println(c.getFacilities().get(f.getName()));
		c.showFacilities();
		System.out.println("--Removing facility");
		System.out.println();
		System.out.println(c.removeFacility(f));
		System.out.println();
		c.showFacilities();
		*/
		System.out.println("here");
		c.showMembers();
		System.out.println();
		c.showFacilities();
		System.out.println();
		Collections.sort(c.getMembers());
		
		c.showMembers();
		System.out.println();
		c.showFacilities();
		System.out.println();
	}

}
