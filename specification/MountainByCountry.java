package specification;

import tourism.Mountain;
import tourism.Mountain_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class MountainByCountry implements Specification<Mountain> {
    String country;

    public MountainByCountry(String country) {
        Objects.requireNonNull(country,"Name of the mountain must be not null");
        this.country = country;
    }

    @Override
    public Predicate getPredicate(Root<Mountain> mountainRoot, CriteriaBuilder builder) {
        Predicate predicate = builder.equal(mountainRoot.get(Mountain_.country), country);
        return predicate;
    }
}
