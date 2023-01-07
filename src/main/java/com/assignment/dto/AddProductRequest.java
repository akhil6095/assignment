package com.assignment.dto;

import lombok.Data;

@Data
public class AddProductRequest {

  private String productId;
  private int quantity;
}
