package com.kasiyanov.repository;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;
import com.kasiyanov.model.AnOrder_;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomAnOrderRepositoryImpl implements CustomAnOrderRepository {

    @Autowired
    private EntityManager entityManager;

    private List<Predicate> getPredicates(OrdersFilterDto ordersFilterDto, CriteriaBuilder cb, Root<AnOrder> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (ordersFilterDto.getOrderNumber() != null) {
            predicates.add(cb.equal(root.get(AnOrder_.number), ordersFilterDto.getOrderNumber()));
        }
        if (ordersFilterDto.getOrderDate() != null) {
            predicates.add(cb.equal(root.get(AnOrder_.DATE), ordersFilterDto.getOrderDate()));
        }
        if (ordersFilterDto.getMaxPrice() != null) {
            predicates.add(cb.le(root.get(AnOrder_.PRICE), ordersFilterDto.getMaxPrice()));
        }

        return predicates;
    }

    @Override
    public List<AnOrder> findAllByFilterNumberDateBuyer(OrdersFilterDto ordersFilterDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AnOrder> criteria = cb.createQuery(AnOrder.class);
        Root<AnOrder> root = criteria.from(AnOrder.class);

        List<Predicate> predicates = getPredicates(ordersFilterDto, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);

        criteria.select(root).where(predicateArray);

        return entityManager.createQuery(criteria)
                .setFirstResult((ordersFilterDto.getPageNumber() - 1) * ordersFilterDto.getPageLimit())
                .setMaxResults(ordersFilterDto.getPageLimit())
                .getResultList();
    }

    @Override
    public Long anOrdersQuantity(OrdersFilterDto ordersFilterDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<AnOrder> root = criteria.from(AnOrder.class);

        List<Predicate> predicates = getPredicates(ordersFilterDto, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);

        criteria.select(cb.count(root)).where(predicateArray);

        return entityManager.createQuery(criteria).getSingleResult();
    }
}
