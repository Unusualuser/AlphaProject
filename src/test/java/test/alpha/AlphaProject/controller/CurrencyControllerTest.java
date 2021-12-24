package test.alpha.AlphaProject.controller;

import org.junit.jupiter.api.DisplayName;
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
public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Testing the status for an incorrect request")
    @Test
    public void incorrectRequestStatusTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/gif").param("currencyCode", "AAA"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("Testing the content type for an incorrect request")
    @Test
    public void incorrectRequestContentTypeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/gif").param("currencyCode", "BBB"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @DisplayName("Testing the content type for a correct request")
    @Test
    public void correctRequestContentTypeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/gif").param("currencyCode", "RUB"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.IMAGE_GIF));
    }

    @DisplayName("Testing the status for a correct request")
    @Test
    public void correctRequestStatusTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/gif").param("currencyCode", "RUB"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

