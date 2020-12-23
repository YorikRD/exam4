package specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Specification <T> //Root type
{ //
    Predicate getPredicate(Root<T> tRoot, CriteriaBuilder builder);
}
