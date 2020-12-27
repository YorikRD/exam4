package specification;

import tourism.Climber;
import tourism.Climber_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClimberInAgeOf implements Specification<Climber> {
    private int minAge;
    private int maxAge;

    public ClimberInAgeOf(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    public Predicate getPredicate(Root<Climber> climberRoot, CriteriaBuilder builder) {
        Predicate more = builder.ge(climberRoot.get(Climber_.AGE),minAge);
        Predicate less =builder.lt(climberRoot.get(Climber_.AGE),maxAge);
        Predicate requred = builder.and(more,less);
        return requred;
    }
}
