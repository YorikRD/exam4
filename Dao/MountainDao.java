package Dao;

import auxilary.SolitaryManager;
import specification.Specification;
import tourism.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class MountainDao implements Dao<Mountain, Integer>{
   private EntityManager manager = SolitaryManager.getManager();

 @Override
 public void add(Mountain mountain) {
  manager.getTransaction().begin();
  manager.persist(mountain);
  manager.getTransaction().commit();
 }

 @Override
 public Mountain getByPk(Integer integer) {
  return manager.find(Mountain.class,integer);
 }

 @Override
 public void deleteByPrkey(Integer integer) {
  Mountain forRem = getByPk(integer);
  manager.getTransaction().begin();
  manager.remove(forRem);
  manager.getTransaction().commit();
 }

 @Override
 public List<Mountain> getBySpecification(Specification<Mountain> specification) {
  CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
  CriteriaQuery<Mountain> criteriaQuery= criteriaBuilder.createQuery(Mountain.class);
  Root<Mountain> root = criteriaQuery.from(Mountain.class);
  Predicate mPr = specification.getPredicate(root,criteriaBuilder);
  criteriaQuery.where(mPr);
  return  manager.createQuery(criteriaQuery).getResultList();
 }

 @Override
 public List<Mountain> getAll() {
  CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
  CriteriaQuery<Mountain> criteriaQuery = criteriaBuilder.createQuery(Mountain.class);
  Root<Mountain>root = criteriaQuery.from(Mountain.class);
  criteriaQuery.select(root);
  TypedQuery<Mountain> typedQuery = manager.createQuery(criteriaQuery);
  List<Mountain> mountains = typedQuery.getResultList();
  return mountains;
 }
}
