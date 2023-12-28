package ra.bussiness;

import java.util.List;

public interface IBussiness<T, K, S> {
    List<T> getAll();
    boolean create(T t);
    boolean update(T t);
    T findById(K k);
    T findByName(S s);
}
