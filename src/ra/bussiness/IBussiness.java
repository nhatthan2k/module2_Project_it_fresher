package ra.bussiness;

import java.util.List;

public interface IBussiness<T, K, S, I> {
    List<T> getAll(I i);

    boolean create(T t);

    boolean update(T t);

    T findById(K k);

    T findByName(S s);

    List<T> searchName(S s, I i);
}
