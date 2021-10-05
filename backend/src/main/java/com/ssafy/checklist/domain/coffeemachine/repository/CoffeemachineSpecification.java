package com.ssafy.checklist.domain.coffeemachine.repository;

import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CoffeemachineSpecification {

    public static Specification<Coffeemachine> all(){
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p = criteriaBuilder.conjunction();
                List<Coffeemachine> list = new ArrayList<>();
                return criteriaBuilder.and(root.in(list));
            }
        };
    }

    public static Specification<Coffeemachine> eqPressure(final String pressure) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("pressure"), pressure);
                return equalPredicate;
            }
        };
    }
}
