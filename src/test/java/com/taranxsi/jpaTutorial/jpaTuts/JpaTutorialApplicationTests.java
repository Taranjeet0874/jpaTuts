package com.taranxsi.jpaTutorial.jpaTuts;

import com.taranxsi.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.taranxsi.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository() {
		List<ProductEntity> entites = productRepository.findAll();
		System.out.println(entites);
	}

	@Test
	void getRepositorybyTitle() {
		List<ProductEntity> entites = productRepository.findByTitle("Pepsi");
		System.out.println(entites);
	}

	@Test
	void getRepositorybyCreatedAt() {
		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024, 1 , 1, 0, 0, 0));
		System.out.println(entities);
	}

	@Test
	void getRepositorybyQuantityAndPrice() {
		List<ProductEntity> entities = productRepository.findByQuantityAndPrice(4, BigDecimal.valueOf(23.45));
		System.out.println(entities);
	}

	@Test
	void testUpdateProductAndModifiedAt() {
		// Create a new product
		ProductEntity productEntity = ProductEntity.builder()
				.sku("test-update-123")
				.title("Test Product for Update")
				.price(BigDecimal.valueOf(50.00))
				.quantity(10)
				.build();
		
		ProductEntity savedProduct = productRepository.save(productEntity);
		LocalDateTime initialModifiedAt = savedProduct.getModifiedAt();
		
		// Wait a moment to ensure timestamp difference
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		// Update the product
		savedProduct.setPrice(BigDecimal.valueOf(75.00));
		savedProduct.setQuantity(15);
		ProductEntity updatedProduct = productRepository.save(savedProduct);
		
		// Verify the update and timestamp
		assert updatedProduct.getPrice().equals(BigDecimal.valueOf(75.00));
		assert updatedProduct.getQuantity() == 15;
		assert updatedProduct.getModifiedAt().isAfter(initialModifiedAt);
		
		System.out.println("Updated product: " + updatedProduct);
		System.out.println("Initial modifiedAt: " + initialModifiedAt);
		System.out.println("Updated modifiedAt: " + updatedProduct.getModifiedAt());
	}

}
