
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public final class BlockingQueue<T> {

    private final Queue<T> queue;
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;

    public BlockingQueue(int size) {
        queue = new LinkedList();
        empty = new Semaphore(size);
        mutex = new Semaphore(1);
        full = new Semaphore(0);       
    }

    public T dequeue() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        T value = queue.remove();
        mutex.release();
        empty.release();
        return value;
    }

    public void enqueue(T t) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        queue.add(t);
        mutex.release();
        full.release();
    }
}
