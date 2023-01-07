package com.assignment.config;

import com.assignment.service.promotionRules.DifferentItemFixedPriceRule;
import com.assignment.service.promotionRules.MultipleItemWithFixedPriceRule;
import com.assignment.service.promotionRules.PromotionRule;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesConfig {

  @Bean("multipleItemWithFixedPriceRuleForA")
  public PromotionRule multipleItemWithFixedPriceRuleForA() {
    return new MultipleItemWithFixedPriceRule("A", 3, 130);
  }

  @Bean("multipleItemWithFixedPriceRuleForB")
  public PromotionRule multipleItemWithFixedPriceRuleForB() {
    return new MultipleItemWithFixedPriceRule("B", 2, 45);
  }

  @Bean("differentItemFixedPriceCAndD")
  public PromotionRule differentItemFixedPriceCAndD() {
    return new DifferentItemFixedPriceRule(Arrays.asList("C", "D"), 30);
  }

}
