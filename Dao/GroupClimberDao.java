package Dao;

import auxilary.SolitaryManager;
import specification.Specification;
import tourism.Climber;
import tourism.GroupClimbers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public class GroupClimberDao implements Dao<GroupClimbers, Integer>{
    private EntityManager manager = SolitaryManager.getManager();





    @Override
    public void add(GroupClimbers groupClimbers) {
        manager.getTransaction().begin();
        manager.persist(groupClimbers);
        manager.getTransaction().commit();
    }

    @Override
    public void update(GroupClimbers groupClimbers) {
        manager.getTransaction().begin();
        manager.merge(groupClimbers);
        manager.getTransaction().commit();

    }

    @Override
    public GroupClimbers getByPk(Integer integer) {
        return manager.find(GroupClimbers.class,integer);
    }

    @Override
    public void delete(GroupClimbers groupClimbers) {
        manager.getTransaction().begin();
        manager.remove(groupClimbers);
        manager.getTransaction().commit();
    }

    @Override
    public void deleteByPrkey(Integer integer) {
        GroupClimbers forRemoving = getByPk(integer);
        delete(forRemoving);
    }

    @Override
    public List<GroupClimbers> getBySpecification(Specification<GroupClimbers> specification) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<GroupClimbers> criteriaQuery = criteriaBuilder.createQuery(GroupClimbers.class);
        Root<GroupClimbers> groot =criteriaQuery.from(GroupClimbers.class);
        Predicate desired = specification.getPredicate(groot,criteriaBuilder);
        criteriaQuery.where(desired);
        return manager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<GroupClimbers> getAll() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<GroupClimbers> criteriaQuery = criteriaBuilder.createQuery(GroupClimbers.class);
        Root<GroupClimbers> root = criteriaQuery.from(GroupClimbers.class);
        criteriaQuery.select(root);
        TypedQuery<GroupClimbers> query = manager.createQuery(criteriaQuery);
        List<GroupClimbers> groupClimbersList = query.getResultList();
        return groupClimbersList;
    }
}
