package Part1;

public class Drone extends Vehicle {

    public Drone() {
        super(1, 500); // Max capacity of 1 and speed of 500 units per minute
    }

    @Override
    public String getType() {
        return "Drone";
    }
}
// having a little problem calculating the time the last drone landed