package Dao;

import specification.Specification;
import tourism.Climber;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClimberDao implements Dao<Climber, Integer> {
    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);
    }

    @Override
    public void update(Climber climber) {
        manager.merge(climber);

    }

    @Override
    public Climber getByPk(Integer integer) {
       return manager.find( Climber.class,integer);
    }

    @Override
    public void delete(Climber climber) {
        manager.remove(climber);
    }

    @Override
    public void deleteByPrkey(Integer integer) {
        Climber climber = getByPk(integer);
        if (climber != null){
            delete(climber);
        }
    }

    @Override
    public List<Climber> getBySpecification(Specification<Climber> specification) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery =criteriaBuilder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        Predicate condition = specification.getPredicate(root, criteriaBuilder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Climber> getAll() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery =criteriaBuilder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        criteriaQuery.select(root);
        TypedQuery<Climber> query = manager.createQuery(criteriaQuery);
        List<Climber> climbers =query.getResultList();
        return climbers;

    }
    public List<Climber> getAllprimitive() {
        Query query = manager.createQuery("SELECT c FROM climbers c");
        List<Climber> climberList =(List<Climber>) query.getResultList();
        return climberList;
    }

}
