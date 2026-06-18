import java.io.*;
import java.util.*;
import java.time.Duration;
import java.time.Instant;

class Vehicle {
    private int spotSize;
    public Vehicle(int spotSize) {
        this.spotSize = spotSize;
    }
    public int getSpotSize() {
        return this.spotSize;
    }
}
// 因为所有车型都有 spotSize，因此 spotSize 可以放在父类里，然后提供一个 public get 方法。
// 创建子类对象时，这个子类对象内部包含了父类对象（方法和参数）
class Limo extends Vehicle {
    public Limo() {
        super(2);
    }
}

class SemiTruck extends Vehicle {
    public SemiTruck() {
        super(3);
    }
}

class Car extends Vehicle {
    public Car() {
        super(1);
    }
}

class Driver {
    private int id;
    private Vehicle vehicle;
    private static int counter = 0;
    private String name;
    public Driver(Vehicle vehicle, String name) {
        this.vehicle = vehicle;
        this.id = counter++;
        this.name = name;
    }
    public Vehicle getVehicle() {
        return this.vehicle;
    }
    public int getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}

class Payment {
    private final int amount;
    private final Driver driver;
    public Payment(int amount, Driver driver) {
        this.amount = amount;
        this.driver = driver;
    }
    public void print() {
        System.out.println(driver.getName() + " pays " + amount);
    }
}

class PaymentProcessor {
    private List<Payment> payments;
    public PaymentProcessor() {
        this.payments = new ArrayList<>();
    }
    public void createPayment(int amount, Driver driver) {
        try {
            Payment payment = new Payment(amount, driver);
            payments.add(payment);
            payment.print();
        } catch (Exception e) {
            throw new RuntimeException("payment failed ", e);
        }
    }
}

class ParkingFloor {
    private int[] parkingSpots; // 0: empty, 1: occupied
    private HashMap<Vehicle, int[]> vehicleMap;
    public ParkingFloor(int length) {
        parkingSpots = new int[length];
        vehicleMap = new HashMap<>();
    }

    public boolean parkAVehicle(Vehicle vehicle) {
        int left = 0;
        for (int i = 0; i < parkingSpots.length; i++) {
            if (parkingSpots[i] != 0) {
                left = i + 1;
            }
            if (i - left + 1 == vehicle.getSpotSize()) {
                vehicleMap.put(vehicle, new int[]{left, i});
                for (int j = left; j <= i; j++) {
                    parkingSpots[j] = 1;
                }
                return true;
            }
        }
        return false;
    }

    public void removeAVehicle(Vehicle vehicle) {
        if (vehicleMap.containsKey(vehicle)) {
            int[] spot = vehicleMap.get(vehicle);
            int l = spot[0];
            int r = spot[1];
            for (int i = l; i <= r; i++) {
                parkingSpots[i] = 0;
            }
            vehicleMap.remove(vehicle);
        }
    }

    public int[] getParkingSpots() {
        return this.parkingSpots;
    }

    public int[] getVehicleSpots(Vehicle vehicle) {
        return this.vehicleMap.get(vehicle);
    }
}

class ParkingGarage {
    private ParkingFloor[] parkingFloors;
    public ParkingGarage(int numOfFloor, int floorSize) {
        parkingFloors = new ParkingFloor[numOfFloor];
        for (int i = 0; i < numOfFloor; i++) {
            parkingFloors[i] = new ParkingFloor(floorSize);
        }
    }
    public boolean parkAVehicle(Vehicle vehicle) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.parkAVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }
    public boolean removeAVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : this.parkingFloors) {
            if (floor.getVehicleSpots(vehicle) != null) {
                floor.removeAVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
}

class ParkingSystem {
    private ParkingGarage parkingGarage;
    private int hourlyRate;
    private Map<Integer, Instant> timeParked;    // map driverId to time that they parked
    private PaymentProcessor paymentProcessor;

    public ParkingSystem(ParkingGarage parkingGarage, int hourlyRate) {
        this.parkingGarage = parkingGarage;
        this.hourlyRate = hourlyRate;
        this.timeParked = new HashMap<>();
        this.paymentProcessor = new PaymentProcessor();
    }

    public boolean parkVehicle(Driver driver) {
        Instant currentTime = Instant.now();
        boolean isParked = this.parkingGarage.parkAVehicle(driver.getVehicle());
        if (isParked) {
            this.timeParked.put(driver.getID(), currentTime);
        }
        return isParked;
    }

    public boolean removeVehicle(Driver driver) {
        if (!this.timeParked.containsKey(driver.getID())) {
            return false;
        }
        Instant currentTime = Instant.now();
        long minutes = Duration.between(this.timeParked.get(driver.getID()), currentTime).toMinutes();
        int hoursParked = (int) Math.ceil(minutes / 60.0);
        paymentProcessor.createPayment(hoursParked * this.hourlyRate, driver);

        this.timeParked.remove(driver.getID());
        return this.parkingGarage.removeAVehicle(driver.getVehicle());
    }
}

class Solution {
    public static void main(String[] args) {
        ParkingGarage garage = new ParkingGarage(3, 2);
        ParkingSystem system = new ParkingSystem(garage, 5);

        Driver d1 = new Driver(new Car(), "Alice");
        Driver d2 = new Driver(new SemiTruck(), "Bob");

        System.out.println(system.parkVehicle(d1)); // true
        System.out.println(system.parkVehicle(d2)); // false（占 3 位，每层只有 2 位）

        System.out.println(system.removeVehicle(d1)); // true（会触发一笔 0 元支付，因为刚停就取）
        System.out.println(system.removeVehicle(d2)); // false（没停成，无记录）
    }
}
