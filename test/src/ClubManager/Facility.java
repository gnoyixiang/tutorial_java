package ClubManager;

public class Facility implements Comparable<Facility>{
	private String name, description;

	public Facility(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Facility() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Facility [name=" + name + ", description=" + description + "]";
	}

	public void show() {
		System.out.println(toString());
	}

	@Override
	public int compareTo(Facility o) {
		return o.getName().compareTo(name);		
	}
	
}
