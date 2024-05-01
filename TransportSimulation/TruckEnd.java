package Part1;

public class TruckEnd extends Event{

	private double timeForTrip =0;

	public TruckEnd(double time,  int id) {
		super(time, id);
		// TODO Auto-generated constructor stub
	}
	 public double getTimeForTrip() {
	        return timeForTrip;
	    }

	    public void setTimeForTrip(double timeForTrip) {
	        this.timeForTrip = timeForTrip;
	    }
}
