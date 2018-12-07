package com.kasiyanov.repository;

import com.kasiyanov.configuration.TestConfiguration;
import com.kasiyanov.model.AnOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class AnOrderRepositoryTest {

    @Autowired
    public AnOrderRepository anOrderRepository;

    @Test
    public void checkFindAllOrderNumbers() {
        anOrderRepository.save(AnOrder.builder()
                .number(1)
                .date(LocalDate.now())
                .price(1287.34)
                .build());
        anOrderRepository.save(AnOrder.builder()
                .number(1)
                .date(LocalDate.now())
                .price(1500.00)
                .build());
        anOrderRepository.save(AnOrder.builder()
                .number(2)
                .date(LocalDate.now())
                .price(154.18)
                .build());

        Set<Integer> allOrderNumbers = anOrderRepository.findAllOrderNumbers();
        Assert.assertEquals(2, allOrderNumbers.size());
    }

    @Test
    public void checkFindAllDates() {
        anOrderRepository.save(AnOrder.builder()
                .number(1)
                .date(LocalDate.of(2018, 9, 15))
                .price(1287.34)
                .build());
        anOrderRepository.save(AnOrder.builder()
                .number(1)
                .date(LocalDate.of(2018, 9, 15))
                .price(1500.00)
                .build());
        anOrderRepository.save(AnOrder.builder()
                .number(2)
                .date(LocalDate.of(2018, 10, 15))
                .price(154.18)
                .build());

        Set<LocalDate> allDates = anOrderRepository.findAllDates();
        Assert.assertEquals(2, allDates.size());
    }

}