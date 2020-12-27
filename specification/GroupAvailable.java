package specification;

import tourism.GroupClimbers;
import tourism.GroupClimbers_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GroupAvailable implements Specification<GroupClimbers> {
    boolean available;

    public GroupAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public Predicate getPredicate(Root<GroupClimbers> groupClimbersRoot, CriteriaBuilder builder) {
        Predicate predicate = builder.equal(groupClimbersRoot.get(GroupClimbers_.AVAILABLE_TO_JOIN),available);
        return predicate;
    }
}
