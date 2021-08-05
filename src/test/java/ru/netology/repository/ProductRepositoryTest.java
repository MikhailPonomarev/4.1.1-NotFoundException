package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();

    private Product first = new Book(1, "Тестирование ПО. Базовый курс", 1000, "Святослав Куликов");
    private Product second = new Book(2, "Тестирование dot com", 950, "Роман Савин");
    private Product third = new Book(3, "Тестирование черного ящика", 1100, "Борис Бейзер");
    private Product fourth = new Smartphone(4, "Iphone 11 128 Black", 55000, "Apple");
    private Product fifth = new Smartphone(5, "Galaxy S21 5G 256 Silver", 96000, "Samsung");
    private Product sixth = new Smartphone(6, "Mi 10T Pro 256", 44000, "Xiaomi");

    @BeforeEach
    public void addProducts() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
    }

    @Test
    public void removeNotExistingItem() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(7);
        });
    }

    @Test
    public void removeExistingItem() {
        repository.removeById(2);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first,third,fourth,fifth,sixth};

        assertArrayEquals(expected,actual);
    }

}