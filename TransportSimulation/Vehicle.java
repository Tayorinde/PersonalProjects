package Part1;
public abstract class Vehicle {
    private int maxCapacity;
    private int speed;
    final int DISTANCE_TO_CROSS = 3000;
	final int DISTANCE_FROM_CROSS = 27000;

    public Vehicle(int maxCapacity, int speed) {
        this.maxCapacity = maxCapacity;
        this.speed = speed;
    }

    public static int getDISTANCE_TO_CROSS() {
		return 3000;
    	
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTimePerTrip(int distance) {
        return distance / speed;
    }
    public int getDistanceToCross() {
		// TODO Auto-generated method stub
		return 0;
	}

    public abstract String getType();
}
