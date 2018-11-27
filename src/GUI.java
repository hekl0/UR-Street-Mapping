/** Loc Bui **/
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Drawing Graph
 */
public class GUI extends JPanel {
    HashMap<String, Intersection> intersectionList;
    double latitudeMin, latitudeMax, longitudeMin, longitudeMax;
    Set<String> roadIdChosen;

    public GUI(HashMap<String, Intersection> list, Set<String> roadIdChosen) throws HeadlessException {
        this.intersectionList = list;
        this.roadIdChosen = roadIdChosen;

        latitudeMin = 180;
        latitudeMax = -180;
        longitudeMin = 180;
        longitudeMax = -180;
        for (String key : intersectionList.keySet()) {
            Intersection intersection = intersectionList.get(key);

            latitudeMin = Math.min(latitudeMin, intersection.latitude);
            latitudeMax = Math.max(latitudeMax, intersection.latitude);
            longitudeMin = Math.min(longitudeMin, intersection.longitude);
            longitudeMax = Math.max(longitudeMax, intersection.longitude);
        }

        JFrame frame = new JFrame();
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);
    }

    int getCanvasLatitude(double value) {
        return (int) ((latitudeMax - value) * this.getHeight() / (latitudeMax - latitudeMin));
    }

    int getCanvasLongitude(double value) {
        return (int) ((value - longitudeMin) * this.getWidth() / (longitudeMax - longitudeMin));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        for (String key : intersectionList.keySet()) {
            Intersection intersection = intersectionList.get(key);

            for (Road road : intersection.roadList) {
                g2.drawLine(
                        getCanvasLongitude(intersection.longitude),
                        getCanvasLatitude(intersection.latitude),
                        getCanvasLongitude(intersectionList.get(road.toIntersectionId).longitude),
                        getCanvasLatitude(intersectionList.get(road.toIntersectionId).latitude));

                /**highlight chosen road with color red**/
                if (roadIdChosen.contains(road.roadId)) {
                    g2.setColor(new Color(255, 0, 0));
                    g2.setStroke(new BasicStroke(3));

                    g2.drawLine(
                            getCanvasLongitude(intersection.longitude),
                            getCanvasLatitude(intersection.latitude),
                            getCanvasLongitude(intersectionList.get(road.toIntersectionId).longitude),
                            getCanvasLatitude(intersectionList.get(road.toIntersectionId).latitude));

                    g2.setColor(new Color(0, 0, 0));
                    g2.setStroke(new BasicStroke(1));
                }
            }
        }
    }
}
