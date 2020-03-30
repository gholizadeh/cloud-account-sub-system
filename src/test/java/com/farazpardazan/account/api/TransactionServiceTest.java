package com.farazpardazan.account.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void processDeposit() throws Exception {
        String requestJson= "{\"amount\": \"100000\",\"type\": \"DEPOSIT\",\"currency\": {\"id\": 1},\"description\": \"Card to Account Tr\",\"date\": \"2019-02-05T22:08:28.097832\",\"referenceNo\": \"123423\",\"returned\": \"false\",\"user\":{\"id\" : \"4\"}}";
        MvcResult mvcResult = mockMvc.perform(post("/transaction/2").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals("1", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void processTransfer() throws Exception {
        String requestJson= "{\"amount\": \"10\",\"currency\": {\"id\": 2},\"description\": \"Card to Account Tr\",\"date\": \"2019-02-05T22:08:28.097832\",\"referenceNo\": \"123423\",\"returned\": \"false\",\"user\":{\"id\" : \"4\"}}";
        mockMvc.perform(post("/transaction/account-transfer/2/3").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }
}