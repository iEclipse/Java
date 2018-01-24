
public interface SetADT<E> {
    void add(E value);
    E get(int index);
    int size();
    void remove(E value);
    SetADT union(SetADT<E> other);
    SetADT intersection(SetADT<E> other);
    SetADT difference(SetADT<E> other);
}
