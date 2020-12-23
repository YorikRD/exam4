package Dao;


//import jpa.specification.Specification;

import specification.Specification;

import java.util.List;

public interface Dao <T,PrKey> {
    void add(T t);
    void update(T t);
    T getByPk(PrKey prKey);
    void delete(T t);
    void deleteByPrkey(PrKey prKey);
    List<T> getBySpecification(Specification<T> specification);
    List<T> getAll();

}
