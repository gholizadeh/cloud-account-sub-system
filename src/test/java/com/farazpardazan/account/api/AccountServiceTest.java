package com.farazpardazan.account.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void processCreationForm() throws Exception {
        String requestJson= "{\"type\": \"SHORT_INVESTMENT\", \"accountNo\": 8734583483,\"name\": \"test\",\"startDate\": \"2019-02-05T22:08:28.097832\",\"currency\": {\"id\": 1},\"status\": \"ACTIVE\",\"branch\": {\"id\": 1},\"openerUser\": {\"id\": 1},\"accountOwners\": [{\"client\": {\"type\": \"LEGAL_CLIENT\",\"id\": 1},\"subscriptionRate\": 100}]}";
        MvcResult mvcResult = mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals("1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void processUpdateForm() throws Exception {
        String requestJson= "{\"id\":1, \"version\":0, \"type\": \"SHORT_INVESTMENT\", \"accountNo\": 8734583484,\"name\": \"test\",\"startDate\": \"2019-02-05T22:08:28.097832\",\"currency\": {\"id\": 1},\"status\": \"ACTIVE\",\"branch\": {\"id\": 2},\"openerUser\": {\"id\": 1},\"accountOwners\": [{\"client\": {\"type\": \"LEGAL_CLIENT\",\"id\": 1},\"subscriptionRate\": 100}]}";
        mockMvc.perform(put("/account").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.accountNo", is(8734583484L)));
    }
}