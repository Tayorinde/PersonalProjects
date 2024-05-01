package Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Train {
    private double arrivalTime;
    private double departureTime;

    public Train(double arrivalTime, double duration) {
        this.arrivalTime = arrivalTime;
        this.departureTime = arrivalTime + duration;
    }

    public Train() {
		// TODO Auto-generated constructor stub
	}

	public double getArrivalTime() {
        return arrivalTime;
    }

    public double getDepartureTime() {
        return departureTime;
    }

}
