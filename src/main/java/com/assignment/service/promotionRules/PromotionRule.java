package com.assignment.service.promotionRules;

import com.assignment.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PromotionRule {

  private String name;

  public abstract void execute(ShoppingCart shoppingCart);
}
