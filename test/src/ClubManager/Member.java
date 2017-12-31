package ClubManager;

public class Member extends Person implements Comparable<Member>{
	private int memberNumber;
	
	public Member() {
		super();
	}

	public Member(String surname, String firstname, String lastname, int memberNumber) {
		super(surname, firstname, lastname);
		this.memberNumber = memberNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	@Override
	public String toString() {
		return "Member [surname=" + getSurname() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + ", memberNumber=" + memberNumber + "]";
	}

	@Override
	public int compareTo(Member o) {
		if(!super.getSurname().equals(o.getSurname())){
			return super.getSurname().compareTo(o.getSurname());
		}
		if(!super.getFirstname().equals(o.getFirstname())) {
			return super.getFirstname().compareTo(o.getFirstname());
		}
		return super.getLastname().compareTo(o.getLastname());
		
	}
	
	
	
}
