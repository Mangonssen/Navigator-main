import java.util.ArrayList;

public class Route {

    private ArrayList<City> routeCities = new ArrayList<City>();
    private double totalDistance;

private Route() {   //DEFAULT CONSTRUCTOR
    this.totalDistance = 0;
}

private Route(ArrayList<City> routeCities, double totalDistance) { //COPY CONSTRUCTOR
    this.routeCities = new ArrayList<City>(routeCities);
    this.totalDistance = totalDistance;
}

public ArrayList<City> appendCity (City city, Connection connection) {
    
    this.routeCities.add(city);

    if(connection != null) {
        this.totalDistance += connection.getDistance(); //Einzelne Distanz zur gesamten hinzufuegen
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

public String toString(ArrayList<City> routeCities) {
    
    StringBuilder routeSB = new StringBuilder();
    for (City city : routeCities) {
        routeSB.append(city.getName() + " -> ");
    }
    routeSB.append("Gesamtdistanz: " + this.totalDistance + " km");

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
        
            City otherCity = connection.getOtherCity(currentCity);
            City nextCity = otherCity;
            if(!currentRoute.containsCity(nextCity)) {
                Route continuedRoute = new Route(currentRoute.routeCities, currentRoute.totalDistance);
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



