/** Loc Bui **/
import java.io.FileNotFoundException;

public class StreetMap {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        graph.readData(args[0]);

        if (args.length > 2)
            if (args[1].equals("--direction"))
                graph.dijkstra(args[2], args[3]);
            else if (args[2].equals("--direction"))
                graph.dijkstra(args[3], args[4]);

        if (args[1].equals("--show"))
            new GUI(graph.intersectionList, graph.roadIdChosen);
    }
}
