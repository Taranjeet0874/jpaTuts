package com.taranxsi.jpaTutorial.jpaTuts;

import com.taranxsi.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.taranxsi.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	void getRepositoryTitleLike() {
		List<ProductEntity> entities = productRepository.findByTitleLike("%Nes%");
		System.out.println(entities);
	}


	@Test
	void getRepositoryTitleContaining() {
		List<ProductEntity> entities = productRepository.findByTitleContaining("Pep");
		System.out.println(entities);
	}

    @Test
    void getRepositorySingle() {
        Optional<ProductEntity> productEntity = productRepository.findByTitleAndPrice("Pepsi", BigDecimal.valueOf(14.4));
        productEntity.ifPresent(System.out::println);
    }

}