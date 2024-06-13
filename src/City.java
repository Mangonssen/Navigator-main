import java.util.ArrayList;

public class City {

    private String name;
    private double latitude;
    private double longitude;
    private ArrayList<Connection> connections = new ArrayList<Connection>();

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public String toString() {

        String blank = " ";

        if(this.getName() == "Ulm") {
            System.out.println(this.getName() + " \t\t" + this.getLatitude() + " \t" + this.getLongitude());
        } else {
        System.out.println(this.getName() + " \t" + this.getLatitude() + " \t" + this.getLongitude());
    }
        return blank;
    }

    public void addConnection (City cityToConnect) {
        
        if(this == cityToConnect) {
            System.out.println("Die Stadt kann nicht mit sich selbst verknüpft werden.");
        } else {
        Connection connection1 = new Connection(this, cityToConnect);
        this.getConnections().add(connection1);
        Connection connection2 = new Connection(cityToConnect, this);
        cityToConnect.getConnections().add(connection2);
        }
    }

    public Route getRouteTo(City destination) {

        Route route = new Route(); 

        route = Route.getShortestRoute(this, destination); //kürzeste Route

        return route;


    }

    
    
}

