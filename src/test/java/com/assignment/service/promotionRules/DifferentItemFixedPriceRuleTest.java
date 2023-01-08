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

public class DifferentItemFixedPriceRuleTest {

  private ProductService productService;
  private DifferentItemFixedPriceRule differentItemFixedPriceRule;

  @BeforeEach
  public void before() {
    productService = new ProductService();
  }

  @Test
  public void test_isRuleApplicable_expect_true() {

    differentItemFixedPriceRule = new DifferentItemFixedPriceRule(Arrays.asList("A", "B"), 45);
    List<AddProductRequest> addProductRequests = Arrays.asList(new AddProductRequest("A", 1),
        new AddProductRequest("B", 1));
    ShoppingCart shoppingCart = createShoppingCart(addProductRequests);
    boolean ruleApplicable = differentItemFixedPriceRule.isRuleApplicable(shoppingCart);
    Matchers.equalTo(ruleApplicable).matches(true);
  }

  @Test
  public void test_isRuleApplicable_expect_false() {
    differentItemFixedPriceRule = new DifferentItemFixedPriceRule(Arrays.asList("A", "B"), 45);
    List<AddProductRequest> addProductRequests = Arrays.asList(new AddProductRequest("A", 1),
        new AddProductRequest("C", 1));
    ShoppingCart shoppingCart = createShoppingCart(addProductRequests);
    boolean ruleApplicable = differentItemFixedPriceRule.isRuleApplicable(shoppingCart);
    Matchers.equalTo(ruleApplicable).matches(false);
  }

  @Test
  public void test_execute() {
    differentItemFixedPriceRule = new DifferentItemFixedPriceRule(Arrays.asList("A", "B"), 45);
    List<AddProductRequest> addProductRequests = Arrays.asList(new AddProductRequest("A", 1),
        new AddProductRequest("B", 1));
    ShoppingCart shoppingCart = createShoppingCart(addProductRequests);
    differentItemFixedPriceRule.execute(shoppingCart);
    Matchers.equalTo(shoppingCart.getCartTotal()).matches(45.0);
  }

  @Test
  public void test_execute_when_rule_not_applicable() {
    differentItemFixedPriceRule = new DifferentItemFixedPriceRule(Arrays.asList("A", "B"), 45);
    List<AddProductRequest> addProductRequests = Arrays.asList(new AddProductRequest("A", 1),
        new AddProductRequest("C", 1));
    ShoppingCart shoppingCart = createShoppingCart(addProductRequests);
    differentItemFixedPriceRule.execute(shoppingCart);
    Matchers.equalTo(shoppingCart.getCartTotal()).matches(70.0);
  }

  private ShoppingCart createShoppingCart(List<AddProductRequest> addProductRequests) {
    ShoppingCart cart = new ShoppingCart();
    cart.setItems(addProductRequests.stream().map(apr -> CartItem.builder().product(productService.getProduct(apr.getProductId())).quantity(apr.getQuantity()).build()).collect(
        Collectors.toList()));
    return cart;
  }

}
