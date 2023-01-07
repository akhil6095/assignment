package com.assignment.service;

import com.assignment.dto.AddProductRequest;
import com.assignment.dto.CartItemDto;
import com.assignment.dto.ShoppingCartDto;
import com.assignment.model.CartItem;
import com.assignment.model.Product;
import com.assignment.model.ShoppingCart;
import com.assignment.service.promotionRules.PromotionRule;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

  private final ProductService productService;
  private final List<PromotionRule> promotionRules;
  private ShoppingCart shoppingCart;

  public ShoppingCartDto add(AddProductRequest addProductRequest) {

    if (shoppingCart == null) {
      shoppingCart = new ShoppingCart();
    }

    Product product = productService.getProduct(addProductRequest.getProductId());

    if (product == null) {
      throw new IllegalArgumentException(
          "Invalid product id : " + addProductRequest.getProductId());
    }

    Optional<CartItem> existingInCart = shoppingCart.getItems().stream().filter(
            item -> item.getProduct().getId().equalsIgnoreCase(addProductRequest.getProductId()))
        .findFirst();

    if (existingInCart.isPresent()) {
      for (CartItem item : shoppingCart.getItems()) {
        if (item.getProduct().getId().equalsIgnoreCase(addProductRequest.getProductId())) {
          item.setQuantity(item.getQuantity() + addProductRequest.getQuantity());
          break;
        }
      }
    } else {
      shoppingCart.addToCart(product, addProductRequest.getQuantity());
    }
    return transform(shoppingCart);
  }

  private ShoppingCartDto transform(ShoppingCart shoppingCart) {
    return new ShoppingCartDto(shoppingCart.getItems().stream()
        .map(item -> new CartItemDto(item.getProduct().getId(), item.getQuantity())).collect(
            Collectors.toList()));
  }

  public ShoppingCart checkout() {
    promotionRules.forEach(promotionRule -> promotionRule.execute(shoppingCart));
    return shoppingCart;
  }

  public ShoppingCartDto removeItem(String productId) {
    shoppingCart.getItems().removeIf(item -> item.getProduct().getId().equalsIgnoreCase(productId));
    return transform(shoppingCart);
  }

  public void clearCart() {
    shoppingCart = null;
  }

}
