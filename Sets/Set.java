
import java.util.ArrayList;
import java.util.List;

public class Set<T> implements SetADT<T> {

    private final ArrayList<T> set;

    public Set() {
        set = new ArrayList();
    }

    public Set(List<T> set) {
        this.set = new ArrayList();
        this.set.addAll(set);
    }

    @Override
    public void add(T value) {
        if (!set.contains(value))
            set.add(value);
    }

    @Override
    public T get(int index) {
        return set.get(index);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public void remove(T value) {
        set.remove(value);
    }

    @Override
    public SetADT<T> union(SetADT other) {
    for (int i = 0; i < set.size(); i++)
        other.add(set.get(i));
    return other;
    }

    @Override
    public SetADT<T> intersection(SetADT other) {
        Set temp = new Set();
        for (int i = 0; i < other.size(); i++)
            if (set.contains((T) other.get(i)))
                temp.add(other.get(i));
        return temp;
    }

    @Override
    public SetADT<T> difference(SetADT other) {
        Set temp = new Set((List<T>) set.clone());
        for (int i = 0; i < other.size(); i++)
            temp.remove(other.get(i));
        return temp;
    }

    @Override
    public String toString() {
        String temp = "";
        if (set.isEmpty())
            return "Empty";
        for (int i = 0; i < set.size(); i++)
            temp += set.get(i) + " ";
        return temp.trim();
    }
}
