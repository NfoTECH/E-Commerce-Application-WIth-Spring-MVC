package com.fortunate.week7tasknfotech.repository;


import com.fortunate.week7tasknfotech.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repo;

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Google Pixel 6 Pro - 5G Android Phone Smartphone");
        product.setCategory("Phone");
        product.setPrice(1572234.0);
        product.setQuantity(100);
        product.setImage("Google Pixel 6 Pro - 5G Android Phone Smartphone.jpeg");

        Product savedProduct = repo.save(product);
        Product existProduct = entityManager.find(Product.class, savedProduct.getId());

        assertThat(existProduct.getName()).isEqualTo(product.getName());
    }
}
