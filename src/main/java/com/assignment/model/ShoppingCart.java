package com.assignment.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ShoppingCart {

  private List<CartItem> items = new ArrayList<>();
  private double cartTotal;

  public void addToCart(Product product, int quantity) {
    this.items.add(CartItem.builder().product(product).isPromotionApplied(false)
        .finalPrice(product.getPrice() * quantity
        ).
        quantity(quantity).build());
  }

  public double getCartTotal() {
    return this.items.stream().mapToDouble(item -> item.getFinalPrice()).sum();
  }

  public void deleteItem(String productId) {
    items.removeIf(item -> item.getProduct().getId().equals(productId));
  }


}
