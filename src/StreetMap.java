/**
 * Loc Bui
 **/

import java.io.FileNotFoundException;

public class StreetMap {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        graph.readData(args[0]);

        boolean isShow = false;
        boolean isDirections = false;
        String start = "";
        String end = "";

        for (int i = 0; i < args.length; i++)
            if (args[i].equals("--show"))
                isShow = true;
            else if (args[i].equals("--directions")) {
                isDirections = true;
                start = args[i + 1];
                end = args[i + 2];
            }

        if (isDirections)
            graph.dijkstra(start, end);

        if (isShow)
            new GUI(graph.intersectionList, graph.roadIdChosen);
    }
}
