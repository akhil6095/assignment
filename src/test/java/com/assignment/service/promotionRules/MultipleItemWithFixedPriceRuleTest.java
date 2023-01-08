package com.assignment.service.promotionRules;

import com.assignment.dto.AddProductRequest;
import com.assignment.model.CartItem;
import com.assignment.model.ShoppingCart;
import com.assignment.service.ProductService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultipleItemWithFixedPriceRuleTest {


  private ProductService productService;
  private MultipleItemWithFixedPriceRule multipleItemWithFixedPriceRule;

  @BeforeEach
  public void before() {
    productService = new ProductService();
  }


  @Test
  public void test_execute() {
    multipleItemWithFixedPriceRule = new MultipleItemWithFixedPriceRule("A", 3, 130);
    List<AddProductRequest> addProductRequests = Arrays.asList(new AddProductRequest("A", 3));
    ShoppingCart shoppingCart = createShoppingCart(addProductRequests);
    multipleItemWithFixedPriceRule.execute(shoppingCart);
    Matchers.equalTo(shoppingCart.getCartTotal()).matches(130.0);
  }

  @Test
  public void test_execute_with_multiple_items() {
    multipleItemWithFixedPriceRule = new MultipleItemWithFixedPriceRule("A", 4, 140);
    List<AddProductRequest> addProductRequests = Arrays.asList(new AddProductRequest("A", 5),
        new AddProductRequest("C", 1));
    ShoppingCart shoppingCart = createShoppingCart(addProductRequests);
    multipleItemWithFixedPriceRule.execute(shoppingCart);
    Matchers.equalTo(shoppingCart.getCartTotal()).matches(200.0);
  }

  private ShoppingCart createShoppingCart(List<AddProductRequest> addProductRequests) {
    ShoppingCart cart = new ShoppingCart();
    cart.setItems(addProductRequests.stream().map(
        apr -> CartItem.builder().product(productService.getProduct(apr.getProductId()))
            .quantity(apr.getQuantity()).build()).collect(
        Collectors.toList()));
    return cart;
  }

}
