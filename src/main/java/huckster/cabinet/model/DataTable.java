package huckster.cabinet.model;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */
public class DataTable<T> {
    private T data;

    public DataTable(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
