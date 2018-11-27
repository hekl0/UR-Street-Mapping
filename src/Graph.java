/**
 * Loc Bui
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Read data and Store information about Graph
 * Provide function to find shortest path
 **/
public class Graph {
    final double oo = Integer.MAX_VALUE;
    HashMap<String, Intersection> intersectionList;
    HashMap<String, String> trace;
    Set<String> roadIdChosen = new HashSet<>();

    void readData(String fileName) throws FileNotFoundException {
        intersectionList = new HashMap<>();

        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            String type = scanner.next();
            String id = scanner.next();
            String para1 = scanner.next();
            String para2 = scanner.next();

            if (type.equals("i"))
                intersectionList.put(id, new Intersection(id, Double.parseDouble(para1), Double.parseDouble(para2)));
            else {
                Intersection i1 = intersectionList.get(para1);
                Intersection i2 = intersectionList.get(para2);

                Road road = new Road(id, para2, haversineFunction(i1, i2));
                i1.addRoad(road);

                road = new Road(id, para1, haversineFunction(i1, i2));
                i2.addRoad(road);
            }
        }
    }

    void dijkstra(String startIntersection, String endIntersection) {
        trace = new HashMap<>();
        roadIdChosen = new HashSet<>();
        Heap heap = new Heap();

        heap.addElement(startIntersection, 0);

        while (heap.size > 0) {
            String intersectionId = heap.removeTop();

            if (intersectionId.equals(endIntersection)) break;

            for (Road road : intersectionList.get(intersectionId).roadList) {
                if (!heap.contatinIntersection(road.toIntersectionId)) {
                    heap.addElement(road.toIntersectionId, heap.getValue(intersectionId) + road.weight);
                    trace.put(road.toIntersectionId, road.roadId);
                } else if (heap.getValue(road.toIntersectionId) > heap.getValue(intersectionId) + road.weight) {
                    heap.updateValue(road.toIntersectionId, heap.getValue(intersectionId) + road.weight);
                    trace.replace(road.toIntersectionId, road.roadId);
                }
            }
        }

        if  (!heap.contatinIntersection(endIntersection)) {
            System.out.println("Can't find path");
            return;
        }

        List<String> path = new ArrayList<>();
        String intersectionId = endIntersection;
        while (!intersectionId.equals(startIntersection)) {
            path.add(intersectionId);
            roadIdChosen.add(trace.get(intersectionId));

            for (Road road : intersectionList.get(intersectionId).roadList)
                if (road.roadId.equals(trace.get(intersectionId))) {
                    intersectionId = road.toIntersectionId;
                    break;
                }
        }
        path.add(startIntersection);

        for (int i = path.size() - 1; i >= 0; i--)
            System.out.println(path.get(i));

        System.out.println("Distance traveled in miles: " + heap.getValue(endIntersection));
    }

    /**
     * Haversine function to find distance between two points.
     * Retrieved from: https://en.wikipedia.org/wiki/Haversine_formula
     **/
    double haversineFunction(Intersection i1, Intersection i2) {
        double earthRadius = 6356.752; //km
        return 2 * earthRadius * Math.asin(Math.sqrt(
                Math.pow(Math.sin((i2.latitude - i1.latitude) / 2), 2) + Math.cos(i1.latitude) * Math.cos(i2.latitude) * Math.pow(Math.sin((i2.longitude - i1.longitude) / 2), 2)
        ));
    }
}
