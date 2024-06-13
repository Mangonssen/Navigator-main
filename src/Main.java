public class Main {
    public static void main(String[] args) throws Exception {
        
        City ingolstadt = new City("Ingolstadt", 48.764001378240835, 11.42625484665524);
        City muenchen = new City("Muenchen", 48.13891855227781, 11.577266137987793);
        City nuernberg = new City("Nuernberg", 49.45061728516064, 11.076253152617218);
        City ulm = new City("Ulm", 48.40203485876449, 9.976324958249457);
        City stuttgart = new City("Stuttgart", 48.77593813959718, 9.17688481609677);
        City augsburg = new City("Augsburg", 48.3684765834842, 10.89671693335146);
        City regensburg = new City("Regensburg", 49.01662121288669, 12.092787603517024);
        City wuerzburg = new City("Wuerzburg", 49.79296114327296, 9.945390533206055);

        ingolstadt.toString();
        muenchen.toString();
        nuernberg.toString();
        ulm.toString();
        stuttgart.toString();
        augsburg.toString();
        regensburg.toString();
        wuerzburg.toString();

        ingolstadt.addConnection(muenchen);
        ingolstadt.addConnection(nuernberg);
        ingolstadt.addConnection(regensburg);
        ingolstadt.addConnection(augsburg);
        muenchen.addConnection(augsburg);
        ulm.addConnection(augsburg);
        stuttgart.addConnection(ulm);
        stuttgart.addConnection(nuernberg);
        stuttgart.addConnection(wuerzburg);
        regensburg.addConnection(muenchen);
        regensburg.addConnection(nuernberg);
        nuernberg.addConnection(ulm);

        System.out.println();
        System.out.println();

        for (Connection connection : stuttgart.getConnections()) {
            System.out.println(connection.getCity1().getName() + " -> " + connection.getCity2().getName());
        }

        System.out.println();
        System.out.println();
        
        System.out.println("Beste Route: " + stuttgart.getRouteTo(regensburg).toString());
        System.out.println(stuttgart.getConnections().get(0).getDistance());

        System.out.println(stuttgart.getConnections().get(0).getDistanceInKm(stuttgart, regensburg));
    }
}
