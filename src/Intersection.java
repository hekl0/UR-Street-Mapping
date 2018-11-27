/** Loc Bui **/
import java.util.ArrayList;
import java.util.List;

public class Intersection {
    String id;
    double latitude;
    double longitude;

    List<Road> roadList;

    public Intersection(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;

        roadList = new ArrayList<>();
    }

    public void addRoad(Road road) {
        roadList.add(road);
    }
}
