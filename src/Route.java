import java.util.ArrayList;

public class Route {

    private ArrayList<City> routeCities;
    private double totalDistance;

public Route() {   //DEFAULT CONSTRUCTOR
    this.routeCities = new ArrayList<>();
    this.totalDistance = 0;
}

public Route(Route original) { //COPY CONSTRUCTOR
    this.routeCities = new ArrayList<>(original.routeCities);
    this.totalDistance = original.totalDistance;
}

public ArrayList<City> getRouteCities() {
    return routeCities;
}

public double getTotalDistance() {
    return totalDistance;
}   

public ArrayList<City> appendCity (City city, Connection connection) {
    
    routeCities.add(city);

    if(connection != null) {
        totalDistance += connection.getDistanceInKm(city, connection.getOtherCity(city)); //Einzelne Distanz zur gesamten hinzufuegen
    }

    return routeCities;
}

public boolean containsCity (City city) {

    for (City routeCity : routeCities) {
        if (routeCity.equals(city)) {
            return true;
        }
    }
    return routeCities.contains(city);
}

public String toString() {
    
    StringBuilder routeSB = new StringBuilder();
    for (City city : this.getRouteCities()) {
        
        routeSB.append(city.getName());
        
        if (city != this.getRouteCities().get(this.getRouteCities().size() - 1)) {
            routeSB.append(" -> ");
        }
    }


    routeSB.append("; Gesamtdistanz: " + this.getTotalDistance() + " km");

    return routeSB.toString();
}


private static void addAllRoutes(ArrayList<Route> allPossibleRoutes, Route currentRoute, City currentCity, City destination, Connection connection) {

    currentRoute.appendCity(currentCity, connection);

    if(currentCity == destination) {
        allPossibleRoutes.add(currentRoute);
        return;
    } else {
        
        ArrayList<Connection> possibleNextCities = currentCity.getConnections();

        for (int i = 0; i < possibleNextCities.size(); i++){
        
            City otherCity = possibleNextCities.get(i).getOtherCity(currentCity);
            
            if(!currentRoute.containsCity(otherCity)) {
                Route continuedRoute = new Route(currentRoute);
                addAllRoutes(allPossibleRoutes, continuedRoute, otherCity, destination, possibleNextCities.get(i));

                allPossibleRoutes.add(continuedRoute);
        
            }
        }
    }
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
                if(route.totalDistance < routesOrderedByDistance.get(i).totalDistance) { //Wenn die Route kuerzer ist als die Route an der Stelle i
                    routesOrderedByDistance.add(i, route);
                    break;
                } else if (i == routesOrderedByDistance.size() - 1) { //Wenn die Route die letzte Route in der Liste ist
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



