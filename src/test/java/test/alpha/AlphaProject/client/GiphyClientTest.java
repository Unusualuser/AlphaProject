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
public class GiphyClientTest {
    @Autowired
    private WireMockServer mockGiphyService;

    @Autowired
    private GiphyClient giphyClient;

    @BeforeEach
    void setUp() throws IOException {
        GiphyMocks.setupMockGiphyResponse(mockGiphyService);
    }

    @DisplayName("Testing not null with broke tag")
    @Test
    public void brokeTagNotNullTest() {
        Assertions.assertNotNull(giphyClient.getRandomGifByTag("broke"));
    }

    @DisplayName("Testing for correct URL from TO with broke tag")
    @Test
    public void brokeTagCorrectURLTest() {
        Assertions.assertEquals(
                giphyClient.getRandomGifByTag("broke").getBody().getGifURL(),
                "https://media4.giphy.com/media/JomoJVOVBbvURzSmEY/giphy.gif?cid=99ce8308fb5f9e8e42a2336506945b0963b14d20912f3278&rid=giphy.gif&ct=g");
    }

    @DisplayName("Testing not null with rich tag")
    @Test
    public void richTagNotNullTest() {
        Assertions.assertNotNull(giphyClient.getRandomGifByTag("rich"));
    }

    @DisplayName("Testing for correct URL from TO with rich tag")
    @Test
    public void richTagCorrectURLTest() {
        Assertions.assertEquals(
                giphyClient.getRandomGifByTag("rich").getBody().getGifURL(),
                "https://media0.giphy.com/media/rC0tqZs7j0hEMTm8Lv/giphy.gif?cid=99ce8308b9e848c2162cdf9d9b2da9e654fb1a31d19c27c8&rid=giphy.gif&ct=g");
    }
}
