/** Loc Bui **/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Heap class
 * Mapping intersectionID to integer using hashMap
 * With each integer ID:
 *      + store its original intersectionID in List "intersectionId"
 *      + store its shortest distance found in List "value"
 *      + store its position on heap in List "positionOnHeap"
 */
public class Heap {
    HashMap<String, Integer> hashMap;
    int size;
    List<Integer> heap;
    List<String> intersectionId;
    List<Double> value;
    List<Integer> positionOnHeap;

    public Heap() {
        hashMap = new HashMap<>();
        size = 0;
        heap = new ArrayList<>();
        intersectionId = new ArrayList<>();
        value = new ArrayList<>();
        positionOnHeap = new ArrayList<>();
    }

    /** Check if intersectionId has ever been in Heap **/
    boolean contatinIntersection(String intersectionId) {
        return hashMap.containsKey(intersectionId);
    }

    /** Get shortest distance found of intersectionID **/
    double getValue(String id) {
        return value.get(hashMap.get(id));
    }

    /** Update shortest distance found of intersectionID **/
    void updateValue(String id, double w) {
        value.set(hashMap.get(id), w);
        upHeap(positionOnHeap.get(hashMap.get(id)));
    }

    void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

        positionOnHeap.set(heap.get(i), i);
        positionOnHeap.set(heap.get(j), j);
    }

    void upHeap(int index) {
        if (index == 0) return;

        int par = (index - 1) / 2;
        if (value.get(heap.get(par)) <= value.get(heap.get(index)))
            return;

        swap(index, par);
        upHeap(par);
    }

    void downHeap(int index) {
        int child = index * 2 + 1;

        if (child >= size) return;
        if (child < size - 1)
            if (value.get(heap.get(child)) > value.get(heap.get(child + 1)))
                child++;
        if (value.get(heap.get(index)) <= value.get(heap.get(child)))
            return;

        swap(child, index);
        downHeap(child);
    }

    String removeTop() {
        int res = heap.get(0);

        swap(0, size-1);
        size--;
        downHeap(0);

        return intersectionId.get(res);
    }

    void addElement(String id, double w) {
        hashMap.put(id, intersectionId.size());
        if (heap.size() > size)
            heap.set(size, intersectionId.size());
        else
            heap.add(intersectionId.size());
        intersectionId.add(id);
        value.add(w);
        positionOnHeap.add(size);

        size++;
        upHeap(size-1);
    }
}
