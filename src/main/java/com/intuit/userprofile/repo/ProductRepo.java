package com.intuit.userprofile.repo;

import com.intuit.userprofile.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author prajwal.kulkarni on 20/09/21
 */
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
