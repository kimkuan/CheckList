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
                return criteriaBuilder.notEqual(root.get("pcode") , 0);
//                return criteriaBuilder.and(root.in(list));
            }
        };
    }

//    public static Specification<Coffeemachine> eqPressure(final List<String> pressureFilter) {
//        return new Specification<Coffeemachine>() {
//            @Override
//            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate equalPredicate = criteriaBuilder.conjunction();
//                for(String pressure: pressureFilter) {
//                    criteriaBuilder.or(criteriaBuilder.equal(root.get("pressure"), pressure));
//                }
//                return equalPredicate;
//            }
//        };
//    }

    public static Specification<Coffeemachine> betweenHeatTime(final int heatTime) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("heatTime"), 3, heatTime);
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

    public static Specification<Coffeemachine> betweenWater(final Double water1, final Double water2) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate rangePredicate = criteriaBuilder.between(root.get("waterVolume"), water1, water2);
                return rangePredicate;
            }
        };
    }


    public static Specification<Coffeemachine> ltWater(Double s) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("waterVolume"), s);
            }
        };
    }

    public static Specification<Coffeemachine> gtWater(Double s) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("waterVolume"), s);
            }
        };
    }

    public static Specification<Coffeemachine> betweenPrice(int price1, int price2) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate rangePredicate = criteriaBuilder.between(root.get("price"), price1, price2);
                return rangePredicate;
            }
        };
    }

    public static Specification<Coffeemachine> ltPrice(int i) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), i);
            }
        };
    }

    public static Specification<Coffeemachine> gtPrice(int i) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), i);
            }
        };
    }
}
