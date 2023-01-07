package com.assignment.contorller;

import com.assignment.dto.AddProductRequest;
import com.assignment.dto.ShoppingCartDto;
import com.assignment.model.ShoppingCart;
import com.assignment.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

  private final ShoppingCartService shoppingCartService;

  @PostMapping
  public ResponseEntity<ShoppingCartDto> add(@RequestBody AddProductRequest addProductRequest) {
    return ResponseEntity.ok(shoppingCartService.add(addProductRequest));
  }

  @DeleteMapping
  public ResponseEntity<ShoppingCartDto> remove(@RequestParam("productId") String productId) {
    return ResponseEntity.ok(shoppingCartService.removeItem(productId));
  }

  @GetMapping
  public ResponseEntity<ShoppingCart> get() {
    return ResponseEntity.ok(shoppingCartService.checkout());
  }

  @GetMapping("checkout")
  public ResponseEntity<ShoppingCart> checkout() {
    return ResponseEntity.ok(shoppingCartService.checkout());
  }

  @GetMapping("clear")
  public ResponseEntity<String> clearCart() {
    shoppingCartService.clearCart();
    return ResponseEntity.ok("OK");
  }
}
