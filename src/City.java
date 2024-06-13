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

    public void toString(City city) {
        if(city.getName() == "Ulm") {
            System.out.println(city.getName() + " \t\t" + city.getLatitude() + " \t" + city.getLongitude());
        } else {
        System.out.println(city.getName() + " \t" + city.getLatitude() + " \t" + city.getLongitude());
    }
    }

    public void addConnection (City cityToConnect) {
        
        if(this == cityToConnect) {
            System.out.println("Die Stadt kann nicht mit sich selbst verknüpft werden.");
        } else {
        Connection connection = new Connection(this, cityToConnect);
        this.getConnections().add(connection);
        cityToConnect.getConnections().add(connection);
        }
    }

    public ArrayList<Route> getRouteTo(City destination) {
        
        
        return getShortestRoute(this, destination);
    }

    public static Route getShortestRoute(City origin, City destination) {
    
    Route blankRoute = new Route();
    ArrayList<Route> allPossibleRoutes = new ArrayList<Route>();
    addAllRoutes(allPossibleRoutes, blankRoute, origin, destination, null);

    ArrayList<Route> routesOrderedByDistance = new ArrayList<Route>();

    for(Route route : allPossibleRoutes) {
        if(routesOrderedByDistance.isEmpty()) {
            routesOrderedByDistance.add(route);
        } else {
            for(int i = 0; i < routesOrderedByDistance.size(); i++) {
                if(route.totalDistance < routesOrderedByDistance.get(i).totalDistance) {
                    routesOrderedByDistance.add(i, route);
                    break;
                } else if (i == routesOrderedByDistance.size() - 1) {
                    routesOrderedByDistance.add(route);
                    break;
                }
            }
        }
    }
    allPossibleRoutes.toString();

    return routesOrderedByDistance.get(0);
    }
    
}

