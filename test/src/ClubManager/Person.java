package ClubManager;

public class Person {
	private String surname, firstname, lastname;

	public Person(String surname, String firstname, String lastname) {
		super();
		this.surname = surname;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Person() {
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Override
	public String toString() {
		return "Person [surname=" + surname + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	public void show() {
		System.out.println(toString());
	}
}
