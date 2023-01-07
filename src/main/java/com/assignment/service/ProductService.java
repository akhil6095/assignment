package com.assignment.service;

import com.assignment.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private static final List<Product> PRODUCTS = new ArrayList() {{
    add(new Product("A", 50));
    add(new Product("B", 30));
    add(new Product("C", 20));
    add(new Product("D", 15));
  }};

  public List<Product> getProducts() {
    return PRODUCTS;
  }

  public Product getProduct(String id) {
    return PRODUCTS.stream().filter(p -> p.getId().equalsIgnoreCase(id)).findFirst().get();
  }

}
