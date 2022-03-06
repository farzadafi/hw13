package service;

public interface Service<K> {

    void add();

    void delete(K k);

    void update(K k);

}
