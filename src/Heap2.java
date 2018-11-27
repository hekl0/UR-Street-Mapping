import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Heap2 {
    int size;
    List<String> heap;
    HashMap<String, Double> value;
    HashMap<String, Integer> positionOnHeap;

    public Heap2() {
        heap = new ArrayList<>();
        value = new HashMap<>();
        positionOnHeap = new HashMap<>();
        size = 0;
    }

    void swap(int i, int j) {
        String temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

        positionOnHeap.replace(heap.get(i), i);
        positionOnHeap.replace(heap.get(j), j);
    }

    double getValue(String id) {
        return value.get(id);
    }

    void updateValue(String id, double w) {
        value.replace(id, w);
        upHeap(positionOnHeap.get(id));
    }

    void upHeap(int index) {
        if (index == 0) return; //if is root, return

        int par = (index - 1) / 2;
        if (getValue(heap.get(par)) < getValue(heap.get(index)))
            return;

        swap(index, par);
        upHeap(par);
    }

    void downHeap(int index) {
        int child = index * 2 + 1;

        if (child >= size) return;
        if (child < size - 1)
            if (getValue(heap.get(child + 1)) < getValue(heap.get(child)))
                child++;
        if (getValue(heap.get(index)) < getValue(heap.get(child)))
            return;

        swap(child, index);
        downHeap(child);
    }

    String removeTop() {
        String res = heap.get(0);

        swap(0, size-1);
        heap.remove(size-1);
        size--;
        downHeap(0);

        return res;
    }

    void addElement(String id, double w) {
        value.put(id, w);
        heap.add(id);
        size++;
        positionOnHeap.put(id, size-1);
        upHeap(size-1);
    }
}
