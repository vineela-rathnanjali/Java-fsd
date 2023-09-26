package TaxCalculationApplication;
import java.util.*;

class Property {
    int id;
    double builtUpArea;
    double basePrice;
    int age;
    boolean inCity;
    double propertyTax;

    public Property(int id, double builtUpArea, double basePrice, int age, boolean inCity) {
        this.id = id;
        this.builtUpArea = builtUpArea;
        this.basePrice = basePrice;
        this.age = age;
        this.inCity = inCity;
    }

    public void calculatePropertyTax() {
        if (inCity) {
            propertyTax = (builtUpArea * age * basePrice) + (0.5 * builtUpArea);
        } else {
            propertyTax = builtUpArea * age * basePrice;
        }
    }

    public String toString() {
        return String.format("%d     |%.2f         |%.2f    |%d            |%s        |%.2f",
                id, builtUpArea, basePrice, age, inCity ? "Y" : "N", propertyTax);
    }
}

class Vehicle {
    String registrationNo;
    String brand;
    double maxVelocity;
    int numSeats;
    String vehicleType;
    double purchaseCost;
    double vehicleTax;

    public Vehicle(String registrationNo, String brand, double maxVelocity, int numSeats, String vehicleType, double purchaseCost) {
        this.registrationNo = registrationNo;
        this.brand = brand;
        this.maxVelocity = maxVelocity;
        this.numSeats = numSeats;
        this.vehicleType = vehicleType;
        this.purchaseCost = purchaseCost;
    }

    public void calculateVehicleTax() {
        vehicleTax = 0.0; 
    }

    public String toString() {
        return String.format("%s            |%s    |%.0f            |%d             |%s        |%.2f     |%.2f",
                registrationNo, brand, maxVelocity, numSeats, vehicleType, purchaseCost, vehicleTax);
    }
}

public class TaxCalculationApp {
    private static double calculateTotalPropertyTax(List<Property> properties) {
        double totalPropertyTax = 0.0;
        for (Property prop : properties) {
            prop.calculatePropertyTax();
            totalPropertyTax += prop.propertyTax;
        }
        return totalPropertyTax;
    }

    private static double calculateTotalVehicleTax(List<Vehicle> vehicles) {
        double totalVehicleTax = 0.0;
        for (Vehicle veh : vehicles) {
            veh.calculateVehicleTax();
            totalVehicleTax += veh.vehicleTax;
        }
        return totalVehicleTax;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Property> properties = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        int propertyIdCounter = 1;

        System.out.println("+--------------------------------+");
        System.out.println("| WELCOME TO TAX CALCULATION APP |");
        System.out.println("+--------------------------------+");
        System.out.println("PLEASE LOGIN TO CONTINUE");
        System.out.print("USERNAME - ");
        String username = scanner.nextLine();
        System.out.print("PASSWORD - ");
        String password = scanner.nextLine();

        if (!username.equals("admin") || !password.equals("admin123")) {
            System.out.println("Login failed. Incorrect username or password.");
            return;
        }

        while (true) {
            System.out.println("1. PROPERTY TAX");
            System.out.println("2. VEHICLE TAX");
            System.out.println("3. TOTAL");
            System.out.println("4. EXIT");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("1. ADD PROPERTY DETAILS");
                    System.out.println("2. CALCULATE PROPERTY TAX");
                    System.out.println("3. DISPLAY ALL PROPERTIES");
                    System.out.println("4. BACK TO MAIN MENU");
                    int propertyChoice = scanner.nextInt();
                    scanner.nextLine();  

                    switch (propertyChoice) {
                        case 1:
                            System.out.println("ENTER THE PROPERTY DETAILS ");
                            System.out.print("ENTER THE BASE VALUE OF LAND: ");
                            double baseValue = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("ENTER THE BUILT-UP AREA OF LAND: ");
                            double builtUpArea = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("ENTER THE AGE OF LAND IN YEARS: ");
                            int age = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("IS THE LAND LOCATED IN CITY? (Y: YES, N: NO): ");
                            String inCityInput = scanner.nextLine();
                            boolean inCity = inCityInput.equalsIgnoreCase("Y");
                            Property property = new Property(propertyIdCounter++, builtUpArea, baseValue, age, inCity);
                            properties.add(property);
                            System.out.println("Property details added successfully!");
                            break;

                        case 2:
                            System.out.println("==========================================================================");
                            System.out.println("ID    | BUILT-UP AREA  | BASE PRICE | AGE (YEARS) | IN CITY | PROPERTY TAX");
                            System.out.println("==========================================================================");
                            for (Property prop : properties) {
                                prop.calculatePropertyTax();
                                System.out.println(prop);
                            }
                            System.out.println("==========================================================================");    
                            System.out.print("ENTER THE PROPERTY ID TO CALCULATE THE TAX: ");
                            int propertyId = scanner.nextInt();
                            scanner.nextLine();
                            for (Property prop : properties) {
                                if (prop.id == propertyId) {
                                    System.out.printf("PROPERTY TAX FOR PROPERTY ID %d IS %.2f\n", prop.id, prop.propertyTax);
                                    break;
                                }
                            }
                            break;

                        case 3:
                            System.out.println("==========================================================================");
                            System.out.println("ID   | BUILT-UP AREA  | BASE PRICE | AGE (YEARS) | IN CITY | PROPERTY TAX");
                            System.out.println("==========================================================================");
                            for (Property prop : properties) {
                                System.out.println(prop);
                            }
                            System.out.println("==========================================================================");    
                            break;

                        case 4:
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("1. ADD VEHICLE DETAILS");
                    System.out.println("2. CALCULATE VEHICLE TAX");
                    System.out.println("3. DISPLAY ALL VEHICLES");
                    System.out.println("4. BACK TO MAIN MENU");

                    int vehicleChoice = scanner.nextInt();
                    scanner.nextLine();  

                    switch (vehicleChoice) {
                        case 1:
                            System.out.print("ENTER THE VEHICLE REGISTRATION NUMBER: ");
                            String regNo = scanner.nextLine();
                            System.out.print("ENTER BRAND OF THE VEHICLE: ");
                            String brand = scanner.nextLine();
                            System.out.print("ENTER THE MAXIMUM VELOCITY OF THE VEHICLE (KMPH): ");
                            double maxVelocity = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("ENTER CAPACITY (NUMBER OF SEATS) OF THE VEHICLE: ");
                            int seats = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("CHOOSE THE TYPE OF THE VEHICLE:");
                            System.out.println("1. PETROL DRIVEN");
                            System.out.println("2. DIESEL DRIVEN");
                            System.out.println("3. CNG/LPG DRIVEN");
                            System.out.print("ENTER YOUR CHOICE: ");
                            int typeChoice = scanner.nextInt();
                            scanner.nextLine();
                            String vehicleType;
                            switch (typeChoice) {
                                case 1:
                                    vehicleType = "PETROL";
                                    break;
                                case 2:
                                    vehicleType = "DIESEL";
                                    break;
                                case 3:
                                    vehicleType = "CNG/LPG";
                                    break;
                                default:
                                    System.out.println("Invalid choice. Defaulting to PETROL.");
                                    vehicleType = "PETROL";
                                    break;
                            }
                            System.out.print("ENTER THE PURCHASE COST OF THE VEHICLE: ");
                            double purchaseCost = scanner.nextDouble();
                            scanner.nextLine();
                            Vehicle vehicle = new Vehicle(regNo, brand, maxVelocity, seats, vehicleType, purchaseCost);
                            vehicles.add(vehicle);
                            System.out.println("Vehicle details added successfully!");
                            break;

                        case 2:
                            System.out.println("========================================================================================================");
                            System.out.println("REGISTRATION NO | BRAND      | MAX. VELOCITY | NO. OF SEATS | VEHICLE TYPE | PURCHASE COST | VEHICLE TAX");
                            System.out.println("========================================================================================================");
                            for (Vehicle veh : vehicles) {
                                veh.calculateVehicleTax();
                                System.out.println(veh);
                            System.out.println("========================================================================================================");    
                            }
                            System.out.print("ENTER THE REGISTRATION NO OF VEHICLE TO CALCULATE THE TAX: ");
                            String regNoToCalcTax = scanner.nextLine();
                            for (Vehicle veh : vehicles) {
                                if (veh.registrationNo.equals(regNoToCalcTax)) {
                                    System.out.printf("VEHICLE TAX FOR REGISTRATION NO %s IS %.2f\n", veh.registrationNo, veh.vehicleTax);
                                    break;
                                }
                            }
                            break;

                        case 3:
                            System.out.println("========================================================================================================");
                            System.out.println("REGISTRATION NO | BRAND       | MAX. VELOCITY | NO. OF SEATS | VEHICLE TYPE | PURCHASE COST | VEHICLE TAX");
                            System.out.println("========================================================================================================");
                            for (Vehicle veh : vehicles) {
                                System.out.println(veh);
                            System.out.println("========================================================================================================");
                            }
                            break;

                        case 4:
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("1. PROPERTY TAX");
                    System.out.println("2. VEHICLE TAX");
                    System.out.println("3. TOTAL");
                    System.out.println("4. EXIT");
                    System.out.print("Enter your choice: ");
                    int totalChoice = scanner.nextInt();
                    scanner.nextLine();  

                    switch (totalChoice) {
                        case 1:
                            System.out.println("PROPERTY TAX");
                            double totalPropertyTax = calculateTotalPropertyTax(properties);
                            System.out.printf("Property tax: %d\n", properties.size());
                            System.out.printf("TAX: %.2f\n", totalPropertyTax);
                            break;

                        case 2:
                            System.out.println("VEHICLE TAX");
                            double totalVehicleTax = calculateTotalVehicleTax(vehicles);
                            System.out.printf("Vehicle tax: %d\n", vehicles.size());
                            System.out.printf("TAX: %.2f\n", totalVehicleTax);
                            break;

                        case 3:
                            System.out.println("TOTAL");
                            double totalTax = calculateTotalPropertyTax(properties) + calculateTotalVehicleTax(vehicles);
                            System.out.printf("Property Quantity: %d\n", properties.size());
                            System.out.printf("Vehicle Quantity: %d\n", vehicles.size());
                            System.out.printf("Total tax of Properties and Vehicles: %.2f\n", totalTax);
                            break;

                        case 4:
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("THANKS VISIT AGAIN.");
                    return;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
