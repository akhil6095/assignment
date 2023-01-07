package com.assignment.service.promotionRules;

import com.assignment.model.CartItem;
import com.assignment.model.ShoppingCart;
import lombok.Data;

@Data
public class MultipleItemWithFixedPriceRule extends PromotionRule {

  private String productId;
  private int quantity;
  private double fixedPrice;

  public MultipleItemWithFixedPriceRule(String productId, int quantity,
      double fixedPrice) {
    super("Multiple item with fixed price");
    this.productId = productId;
    this.quantity = quantity;
    this.fixedPrice = fixedPrice;
  }

  public boolean isRuleApplicable(CartItem cartItem) {
    return cartItem != null && !cartItem.isPromotionApplied() && cartItem.getProduct().getId()
        .equals(this.productId) && cartItem.getQuantity() >= this.quantity;
  }

  @Override
  public void execute(ShoppingCart shoppingCart) {
    for (CartItem item : shoppingCart.getItems()) {
      if (!isRuleApplicable(item)) {
        continue;
      }
      int applicableUnitsForDiscount = item.getQuantity() / this.quantity;
      int nonDiscountedItemUnits = item.getQuantity() % this.quantity;
      item.setFinalPrice(
          applicableUnitsForDiscount * this.fixedPrice + nonDiscountedItemUnits * item.getProduct()
              .getPrice());
      item.setPromotionApplied(true);
    }
  }
}
