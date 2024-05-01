package Part1;

public class Other implements Comparable<Other> {
	private double time;

	private int id;

	public Other(double time, int id) {
		this.time = time;

		this.id = id;
	}

	public double getTime() {
		return time;
	}

	public int getId() {
		return id;
	}
// I am not sure whether this is part of what should be
//	TruckBegin tb = new TruckBegin(time, id);
//	
//	TruckBeginsCrossing tbc = new TruckBeginsCrossing(time, id);
//	
//
//	TruckAtCrossing tac = new TruckAtCrossing(time, id);
	@Override
	public int compareTo(Other o) {
		// TODO Auto-generated method stub
		
		if(this.time <o.time) {
			return -1;
		} else if(this.time >o.time) {
			return 1;
		} else {
			return 0;
		}
	}

	//@Override
	// this should involve the comparison

	// other methods

}
