package com.assignment.service.promotionRules;

import com.assignment.model.CartItem;
import com.assignment.model.ShoppingCart;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class DifferentItemFixedPriceRule extends PromotionRule {

  private List<String> productIds;
  private double fixePrice;

  public DifferentItemFixedPriceRule(List<String> productIds, double fixePrice){
    super("");
    this.productIds = productIds;
    this.fixePrice = fixePrice;
  }

  public boolean isRuleApplicable(ShoppingCart shoppingCart) {
    return shoppingCart != null && shoppingCart.getItems() != null
        && shoppingCart.getItems().size() != 0
        && shoppingCart.getItems().stream().map(p -> p.getProduct().getId())
        .collect(Collectors.toList()).containsAll(productIds);
  }

  @Override
  public void execute(ShoppingCart shoppingCart) {
    if (!isRuleApplicable(shoppingCart)) {
      return;
    }
    List<CartItem> applicableItems = shoppingCart.getItems().stream()
        .filter(item -> productIds.contains(item.getProduct().getId())).collect(
            Collectors.toList());
    for (int i = 0; i < applicableItems.size(); i++) {
      applicableItems.get(i).setPromotionApplied(true);
      applicableItems.get(i).setFinalPrice(0);
      if (i == applicableItems.size() - 1) {
        applicableItems.get(i).setFinalPrice(fixePrice);
      }
    }
  }
}
