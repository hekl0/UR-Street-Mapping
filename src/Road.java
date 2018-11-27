/** Loc Bui **/
public class Road {
    String roadId;
    String toIntersectionId;
    double weight;

    public Road(String roadId, String toIntersectionId, double weight) {
        this.roadId = roadId;
        this.toIntersectionId = toIntersectionId;
        this.weight = weight;
    }
}
