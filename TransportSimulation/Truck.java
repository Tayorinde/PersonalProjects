package Part1;

public class Truck extends Vehicle {
	int id;

	public Truck(int id) {
		super(10, 30); // Max capacity of 10 and speed of 60 units per minute
		this.id =id;
	}

	@Override
	public String getType() {
		return "Truck";
	}

	public int getId() {
		return id;

	}
}