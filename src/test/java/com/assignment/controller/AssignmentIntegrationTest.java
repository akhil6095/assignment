package com.assignment.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AssignmentIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void before() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/cart/clear"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void given_product_when_fetched_returns_list()
      throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/product"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void given_user_add_product_to_cart_when_added_then_product_is_added_to_cart()
      throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"A\", \"quantity\": 5}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].productId", Matchers.is("A")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].quantity", Matchers.is(5)));
  }

  @Test
  public void given_scenario_1_user_add_products_to_cart_when_added_then_product_is_added_to_cart()
      throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"A\", \"quantity\": 5}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"B\", \"quantity\": 5}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"C\", \"quantity\": 1}"))
        .andExpect(MockMvcResultMatchers.status().isOk());

    mockMvc.perform(MockMvcRequestBuilders.get("/cart/checkout"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.cartTotal", Matchers.is(370.0)));

  }

  @Test
  public void given_scenario_2_user_add_products_to_cart_when_added_then_product_is_added_to_cart()
      throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"A\", \"quantity\": 3}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"B\", \"quantity\": 5}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"C\", \"quantity\": 1}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.post("/cart")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"productId\": \"D\", \"quantity\": 1}"))
        .andExpect(MockMvcResultMatchers.status().isOk());

    mockMvc.perform(MockMvcRequestBuilders.get("/cart/checkout"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.cartTotal", Matchers.is(280.0)));

  }

}
