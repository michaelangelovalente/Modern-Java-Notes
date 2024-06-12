/**
 * Generic version of the Box class.
 * 
 * @param <T> the type of the vlaue being boxed
 */
public class Box<T> {
    // T stands for "Type"
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

}
