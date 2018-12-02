package com.kasiyanov.dao;

import com.kasiyanov.model.Buyer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class BuyerDaoImplTest {

    @Test
    public void CheckSaveBuyer() {
        Buyer buyer = new Buyer("Konstantine",
                "Konstantinov",
                "Konstantine",
                "konstantine",
                "konstantine@mail.ru",
                "375-44-222-19-67",
                "Козлова",
                54,
                25);
        Long id = BuyerDaoImpl.getInstance().save(buyer);
        Assert.assertNotNull(id);
    }

    @Test
    public void CheckFindBuyerById() {
        Buyer buyer = new Buyer("Stas",
                "Stasov",
                "Stas",
                "stas",
                "stas@mail.ru",
                "375-29-116-45-67",
                "Независимости",
                123,
                45);
        Long id = BuyerDaoImpl.getInstance().save(buyer);
        Buyer savedBuyer = BuyerDaoImpl.getInstance().find(id);
        Assert.assertNotNull(savedBuyer);
    }

    @Test
    public void CheckUpdateBuyer() {
        Buyer buyer = new Buyer("Ivan",
                "Ivanov",
                "Ivan",
                "ivan",
                "ivan@tutbyu",
                "375-33-782-57-61",
                "Рокосовского",
                162,
                15);
        Long savedId = BuyerDaoImpl.getInstance().save(buyer);
        Buyer savedBuyer = BuyerDaoImpl.getInstance().find(savedId);

        savedBuyer.setStreet("Свердлова");
        BuyerDaoImpl.getInstance().update(savedBuyer);

        Buyer updatedBuyer = BuyerDaoImpl.getInstance().find(savedId);

        Assert.assertEquals("Свердлова", updatedBuyer.getStreet());
    }

    @Test
    public void CheckDeleteBuyer() {
        Buyer buyer = new Buyer("Petr",
                "Petrov",
                "Petr",
                "petr",
                "petr@rambler.ru",
                "375-29-271-38-15",
                "Пушкина",
                34,
                106);
        Long savedId = BuyerDaoImpl.getInstance().save(buyer);
        Buyer savedBuyer = BuyerDaoImpl.getInstance().find(savedId);
        BuyerDaoImpl.getInstance().delete(savedBuyer);

        Buyer deletedBuyer = BuyerDaoImpl.getInstance().find(savedId);

        Assert.assertNull(deletedBuyer);
    }

    @Test
    public void checkGetAll() {
        Buyer buyer1 = new Buyer("Vasily",
                "Vasechkin",
                "Vasily",
                "vasily",
                "vasily@mail.ru",
                "375-44-333-44-55",
                "Хоружей",
                62,
                71);
        Buyer buyer2 = new Buyer("Vitaly",
                "Vitaliev",
                "Vitaly",
                "vitaly",
                "vitaly@gmail.com",
                "375-29-518-22-17",
                "Столетова",
                96,
                13);

        Long savedBuyer1 = BuyerDaoImpl.getInstance().save(buyer1);
        Long savedBuyer2 = BuyerDaoImpl.getInstance().save(buyer2);

        List<Buyer> buyers = BuyerDaoImpl.getInstance().findAll();

        Assert.assertEquals(2, buyers.size());
    }
}