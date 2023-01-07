package com.assignment.contorller;

import com.assignment.model.Product;
import com.assignment.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;


  @GetMapping
  public ResponseEntity<List<Product>> get() {
    return ResponseEntity.ok(productService.getProducts());
  }


}
