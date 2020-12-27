package specification;

import tourism.GroupClimbers;
import tourism.GroupClimbers_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class GroupByMountainName implements Specification<GroupClimbers>{
    String mountainName;

    public GroupByMountainName(String mountainName) {
        Objects.requireNonNull(mountainName,"Name of the mountain must be not null");
        this.mountainName = mountainName;
    }

    @Override
    public Predicate getPredicate(Root<GroupClimbers> groupClimbersRoot, CriteriaBuilder builder) {
        Predicate predicate = builder.equal(groupClimbersRoot.get(GroupClimbers_.MOUNTAIN).get("name"),mountainName);
        return predicate;
    }
}
