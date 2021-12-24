package test.alpha.AlphaProject.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test.alpha.AlphaProject.WireMockConfig;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class CurrencyClientTest {
    @Autowired
    private WireMockServer mockCurrencyService;

    @Autowired
    private CurrencyClient currencyClient;

    @BeforeEach
    void setUp() throws IOException {
        CurrencyMocks.setupMockCurrencyResponse(mockCurrencyService);
    }

    @DisplayName("Testing not null for a correct date")
    @Test
    public void correctDateNotNullTest() {
        Assertions.assertNotNull(currencyClient.getCurrencyByDate("2021-12-24"));
    }

    @DisplayName("Testing for correct TO")
    @Test
    public void correctTOTest() {
        Assertions.assertEquals(currencyClient.getCurrencyByDate("2021-12-24").getBody().getRates().get("RUB"), 73.3851);
    }
}
