package repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<K> {

    K findById(int id);

    List<K> findAll();

}
