import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FlightDatabase database = new FlightDatabase();
        database.checkIfFlightExists("Paris", "Madrid");
        database.displayFlightsFromCity("Paris");
        database.displayFlightsToCity("Warsaw");
        Flight cheapestFlight = database.getCheapestFlight();
        System.out.println("Cheapest flight: " + cheapestFlight.getDetails());
        ArrayList<Journey> journeys =
                database.getFlights("Paris", "Porto");
        System.out.println(journeys);


    }
}
class  Journey {
    Flight first;
    Flight second;
    public Journey (Flight first, Flight second) {
        this.first= first;
        this.second= second;
    }
    public  String toString() {
        return "Flight from " + first.departure + " to " + second.arrival + " with stop at " + first.arrival + " costs " +(first.price + second.price);
    }
}

class Flight {
    String departure;
    String arrival;
    int price;

    public Flight(String departure, String arrival, int price) {
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public String getDetails() {
        return "flight from " + this.departure + " to " + this.arrival + " costs " + this.price;
    }
}

class FlightDatabase {
    ArrayList<Flight> flights = new ArrayList<Flight>();

    public FlightDatabase() {
        this.flights.add(new Flight("Berlin", "Tokyo", 1800));
        this.flights.add(new Flight("Paris", "Berlin", 79));
        this.flights.add(new Flight("Warsaw", "Paris", 120));
        this.flights.add(new Flight("Madrid", "Berlin", 200));
        this.flights.add(new Flight("Berlin", "Warsaw", 77));
        this.flights.add(new Flight("Paris", "Madrid", 180));
        this.flights.add(new Flight("Porto", "Warsaw", 412));
        this.flights.add(new Flight("Madrid", "Porto", 102));
        this.flights.add(new Flight("Warsaw", "Madrid", 380));
    }


    public ArrayList<Flight> getFlightsFromCity(String city) {
        ArrayList<Flight> results = new ArrayList<Flight> ();
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (city.equals(flight.departure)) {
                results.add(flight);
            }

        }
        return results;
    }

    public ArrayList<Journey> getFlights(String start, String end) {
        ArrayList<Flight> starting = getFlightsFromCity(start);
        ArrayList<Flight> ending = getFlightsToCity(end);
        ArrayList<Journey> results = new
                ArrayList<Journey>();
        for (Flight first : starting ){
            for ( Flight second : ending) {
                if (first.arrival.equals(second.departure)) {
                    results.add(new Journey(first, second));
                }
            }
        }
        return results;
    }


    public Flight getCheapestFlight () {
        Flight cheapestFlight = null;
        for (Flight flight : this.flights) {
            if (cheapestFlight == null || flight.price <cheapestFlight.price) {
                cheapestFlight = flight;
            }
        }
        return cheapestFlight;
    }
    public ArrayList<Flight> getFlightsToCity(String city) {
        ArrayList<Flight> results = new ArrayList<Flight>();
        for (int i = 0; i < this.flights.size(); i++) {
            Flight flight = this.flights.get(i);
            if (city.equals(flight.arrival)) {
                results.add(flight);
            }

        }
        return results;
    }
    public void displayFlights(ArrayList<Flight> flights){
        for (Flight flight : flights){
            System.out.println(flight.getDetails());
        }
    }
    public void  displayFlightsFromCity (String city) {
        ArrayList<Flight> results = getFlightsFromCity(city);
        displayFlights(results);
    }
    public void  displayFlightsToCity (String city) {
        ArrayList<Flight> results = getFlightsToCity(city);
        displayFlights(results);
    }
    public void checkIfFlightExists (String start, String end){
        boolean exists = false ;
        for (Flight flight : flights) {
            if (flight.departure.equals(start) && flight.arrival.equals(end)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Flight exist");
        }
        else {
            System.out.println("Flight does not exist");
        }
    }
}