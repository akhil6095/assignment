package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

  private String id;
  private double price;

  @Override
  public String toString() {
    return id + "\t" + price;
  }
}
