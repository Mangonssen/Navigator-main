public class Connection {

    private City city1;
    private City city2;
    private double distance;
    
    public Connection(City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;
        final double distance = getDistanceInKm(city1, city2);
    }

    public City getCity1() {
        return city1;    
    }

    public City getCity2() {
        return city2;
    }

    public double getDistance() {
        return distance;
    }


    private double getDistanceInKm(City city1, City city2) {
        double lat1 = city1.getLatitude();                  // lat1 auf den Wert von getLatitude() in der City-Klasse setzen
        double lon1 = city1.getLongitude();                 // lon1 auf den Wert von getLongitude() in der City-Klasse setzen
        double lat2 = city2.getLatitude();                  // lat2 auf den Wert von getLatitude() in der City-Klasse setzen
        double lon2 = city2.getLongitude();                 // lon2 auf den Wert von getLongitude() in der City-Klasse setzen
        double earthRadius = 6371;                          // Erdradius in km
        double dLat = degreesToRadians(lat2 - lat1);        // dLat auf den Wert von lat2 - lat1 setzen (Unterschied + Umrechnung in Radian)
        double dLon = degreesToRadians(lon2 - lon1);        // dLon auf den Wert von lon2 - lon1 setzen (Unterschied + Umrechnung in Radian)
        lat1 = degreesToRadians(lat1);                      // lat1 auf den Wert von lat1 in Radian setzen
        lat2 = degreesToRadians(lat2);                      // lat2 auf den Wert von lat2 in Radian setzen
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }

    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public City getOtherCity(City city) {
        if(city == city1) {
            return city2;
        } else if(city == city2) {
            return city1;
        } else {
            return null; // eingegebene Stadt stimmt mit keiner der beiden "Verbindungsstädten" überein
        }
    }
}
