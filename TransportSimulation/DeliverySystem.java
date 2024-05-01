package Part1;

// import the libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// create a delivery system class
public class DeliverySystem {
	// create a PRIORITY QUUEUE of the events
	static PriorityQueue<Event> pq = new PriorityQueue<>();
// create a queue that stores waiting trucks
	static Queue<Truck> waitingTruck = new LinkedList<>();
// create a scanner object
	static Scanner scanner = new Scanner(System.in);
// the constants
	static String trainSchedule = "train_schedule.txt";
	static int totalPackages = 1500;
	static final int DISTANCE_TO_CROSS = 3000;
	static final int DISTANCE_FROM_CROSS = 27000;
	static int totalDistance = DISTANCE_FROM_CROSS + DISTANCE_TO_CROSS;
	static double simClock = 0;
	static boolean isBlocking = false;
	static double lastTruckEndTime = 0;
	static double avgTruck = 0;
	static ArrayList<Double> startTime = new ArrayList<>();
	static ArrayList<Double> totalTime = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
// create the objects
		Vehicle truck = new Truck(0);
		Vehicle drone = new Drone();

		double PERCENT_BY_DRONE = 25;

		int dronePackages = (int) Math.ceil(totalPackages * (PERCENT_BY_DRONE / 100));
		int truckPackages = totalPackages - dronePackages;

		int droneTrips = (int) Math.ceil(dronePackages / (double) drone.getMaxCapacity());
		int truckTrips = (int) Math.ceil(truckPackages / (double) truck.getMaxCapacity());

		int totalDroneTime = (60 + (3 * (droneTrips - 1)));
		System.out.println("with " + PERCENT_BY_DRONE + "% and " + totalPackages + ", ");
		System.out.println("There will be : ");
		System.out.println("-" + droneTrips + " drones");
		System.out.println("-" + truckTrips + " trucks");
		System.out.println();

		truckBegins(truckTrips);
		System.out.println("TRAIN SCHEDULE");
		System.out.println("--------------");
		readTrainScheduleFromFile(trainSchedule);
		System.out.println();
		startSimulation();
		scanner.close();
		System.out.println();
		System.out.println();

		System.out.println("STAT");

		System.out.println("---- ");
		int i = 0;
		double ttTime = 0;

		for (Double be : totalTime) {
			
			System.out.println("TRUCK #" + i + " total trip time: " + be);
			i++;
			ttTime = ttTime + be;
		}

		double number = ttTime / (double) truckTrips;
		System.out.println();
		System.out.println();

//		System.out.println("-------------------------");
		System.out.println("TRUCK AVG TRIP TIME: " + Math.round(number * 10) / 10.0);
		System.out.println("TRUCK TOTAL TIME: " + lastTruckEndTime);
		System.out.println();
		System.out.println();
		System.out.println("DRONE TRIP TIME: " + drone.getTimePerTrip(totalDistance) + " minutes");
		System.out.println("DRONE TOTAL TIME: " + totalDroneTime + " minutes");
		System.out.println();
		System.out.println();
		System.out.println("TOTAL TIME: " + lastTruckEndTime);
		System.out.println("BUILD SUCCESSFUL (total time: 0 seconds)");
//		TOTAL TIME: 2680.0 minutes
//		BUILD SUCCESSFUL (total time: 0 seconds)
		;
		// otherSimulation();
		scanner.close();
	}

	public static void truckBegins(int truckTrips) {
		for (int i = 0; i < truckTrips; i++) {
			double time = i * 15.0;

			TruckBegin tb = new TruckBegin(time, i);
			startTime.add(time);
			// offer the time of the truck in the priority queue
			pq.offer(tb);

			int timeToCrossing = DISTANCE_TO_CROSS / 30;
			double forthis = timeToCrossing + time;
			TruckAtCrossing tac = new TruckAtCrossing((forthis), i);
			pq.offer(tac);

		}
	}

	// for the moment the trucks get to the crossing

// this is for the simulation of the timer
	private static void startSimulation() {

		while (!pq.isEmpty()) {
			Event type = pq.poll();
			simClock = type.getTime();

			if (type instanceof TruckBegin) {
				System.out.println(type.getTime() + ": TRUCK #" + type.getId() + " begins journey");
			} else if (type instanceof TrainArrivesAtCrossing) {
				System.out.println(type.getTime() + ": TRAIN arrives at crossing");
				isBlocking = true;
			} else if (type instanceof TruckEnd) {
				System.out.println(type.getTime() + ": TRUCK #" + type.getId() + " completes journey");
				lastTruckEndTime = type.getTime();

				double totTime = type.getTime() - startTime.get(type.getId());

				totalTime.add(totTime);

			}

			else if (type instanceof TruckAtCrossing) {
				if (isBlocking || !waitingTruck.isEmpty()) {
					System.out.println(type.getTime() + ": TRUCK #" + type.getId() + "  waits at crossing");
					Truck t = new Truck(type.getId());
					waitingTruck.add(t);
				} else {
					System.out.println(type.getTime() + ": TRUCK #" + type.getId() + " crosses crossing");
					int timeFromCrossing = DISTANCE_FROM_CROSS / 30;
					double forthat = timeFromCrossing + type.getTime();
					TruckEnd te = new TruckEnd(forthat, type.getId());
					pq.offer(te);
				}

			} else if (type instanceof TrainLeavesCrossing) {
				isBlocking = false;
				System.out.println(type.getTime() + ": TRAIN leaves crossing");

				if (!waitingTruck.isEmpty()) {
					// I do not understand what POLL FIRST TRUCK means

					Truck qu = waitingTruck.poll();
					double truckCrosstime = type.getTime() + 1;

					TruckBeginsCrossing tbc = new TruckBeginsCrossing(truckCrosstime, qu.getId());
					pq.offer(tbc);
				}
				// System.out.println(type.getTime() + ": TRAIN leaves crossing");
			}

			else if (type instanceof TruckBeginsCrossing) {
				System.out.println(type.getTime() + ": TRUCK #" + type.getId() + " crosses crossing");

				int timeFromCrossing = DISTANCE_FROM_CROSS / 30;
				double forthat = timeFromCrossing + type.getTime();
				TruckEnd te = new TruckEnd(forthat, type.getId());
				pq.offer(te);

				if (!waitingTruck.isEmpty()) {
					// I do not understand what POLL FIRST TRUCK means

					Truck qu = waitingTruck.poll();
					double truckCrosstime = type.getTime() + 1;

					TruckBeginsCrossing tbc = new TruckBeginsCrossing(truckCrosstime, qu.getId());
					pq.offer(tbc);
				}
			}

		}
	}

	// train that reads in the file
	public static void readTrainScheduleFromFile(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(" ");
				double arrivalTime = Double.parseDouble(parts[0]);
				double duration = Double.parseDouble(parts[1]);
				double departureTime = arrivalTime + duration;

				Train train = new Train(arrivalTime, duration);
				// when the train gets to the crossing
				TrainArrivesAtCrossing tac = new TrainArrivesAtCrossing(arrivalTime, 0);
				pq.offer(tac);

				// this is for when the train is leaving the crossing
				TrainLeavesCrossing tlc = new TrainLeavesCrossing(departureTime, 0);
				pq.offer(tlc);
				System.out.println(arrivalTime + "-" + departureTime);

			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
