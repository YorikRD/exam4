package Dao;


//import jpa.specification.Specification;

import auxilary.SolitaryManager;
import specification.Specification;
import tourism.Climber;
import tourism.PrimeID;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface Dao <T ,PrKey> {
    default void add(T t){
        EntityManager manager = SolitaryManager.getManager();
        manager.getTransaction().begin();
        manager.persist(t);
        manager.getTransaction().commit();
    }
    default void update(T t){
        EntityManager manager = SolitaryManager.getManager();
        manager.getTransaction().begin();
        manager.merge(t);
        manager.getTransaction().commit();
    }
    T getByPk(PrKey prKey);
    default void delete(T t){
        EntityManager manager = SolitaryManager.getManager();
        manager.getTransaction().begin();
        manager.remove(t);
        manager.getTransaction().commit();
    }
    void deleteByPrkey(PrKey prKey);
    List<T> getBySpecification(Specification<T> specification);
    List<T> getAll();

}
