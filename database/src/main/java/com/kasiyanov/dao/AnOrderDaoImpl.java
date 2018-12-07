package com.kasiyanov.dao;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;
import com.kasiyanov.model.AnOrder_;
import com.kasiyanov.model.QAnOrder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Cleanup;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.kasiyanov.connection.ConnectionManager.getSession;

public class AnOrderDaoImpl extends BaseDaoImpl<Long, AnOrder> implements AnOrderDao {

    private static final AnOrderDao INSTANCE = new AnOrderDaoImpl();

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
        @Cleanup Session session = getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AnOrder> criteria = cb.createQuery(AnOrder.class);
        Root<AnOrder> root = criteria.from(AnOrder.class);

        List<Predicate> predicates = getPredicates(ordersFilterDto, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);

        criteria.select(root).where(predicateArray);

        return session.createQuery(criteria)
                .setFirstResult((ordersFilterDto.getPageNumber() - 1) * ordersFilterDto.getPageLimit())
                .setMaxResults(ordersFilterDto.getPageLimit())
                .list();
    }

    @Override
    public Long anOrdersQuantity(OrdersFilterDto ordersFilterDto) {
        @Cleanup Session session = getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<AnOrder> root = criteria.from(AnOrder.class);

        List<Predicate> predicates = getPredicates(ordersFilterDto, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);

        criteria.select(cb.count(root)).where(predicateArray);

        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public Set<Integer> getAllOrderNumbers() {
        @Cleanup Session session = getSession();
        List<Integer> orderNumbersList = new JPAQuery<AnOrder>(session)
                .select(QAnOrder.anOrder.number)
                .from(QAnOrder.anOrder)
                .fetch();
        return new HashSet<>(orderNumbersList);
    }

    @Override
    public Set<LocalDate> getAllDates() {
        @Cleanup Session session = getSession();
        List<LocalDate> dates = new JPAQuery<AnOrder>(session)
                .select(QAnOrder.anOrder.date)
                .from(QAnOrder.anOrder)
                .fetch();
        return new HashSet<>(dates);
    }

    public static AnOrderDao getINSTANCE() {
        return INSTANCE;
    }

}
