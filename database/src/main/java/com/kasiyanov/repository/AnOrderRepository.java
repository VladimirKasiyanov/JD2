package com.kasiyanov.repository;

import com.kasiyanov.model.AnOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Set;

public interface AnOrderRepository extends CrudRepository<AnOrder, Long>, CustomAnOrderRepository {

    @Query("select a.number from AnOrder a group by a.number")
    Set<Integer> findAllOrderNumbers();

    @Query("select a.date from AnOrder a group by a.date")
    Set<LocalDate> findAllDates();
}
