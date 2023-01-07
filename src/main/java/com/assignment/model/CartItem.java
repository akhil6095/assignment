package com.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {

  private Product product;
  private int quantity;
  private boolean isPromotionApplied;
  private double finalPrice;

}
