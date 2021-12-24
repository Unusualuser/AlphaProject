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
import java.net.URI;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class GifClientTest {
    @Autowired
    private WireMockServer mockGifService;

    @Autowired
    private GifClient gifClient;

    @BeforeEach
    void setUp() throws IOException {
        GifMocks.setupMockGifResponse(mockGifService);
    }

    @DisplayName("Testing not null")
    @Test
    public void correctDateNotNullTest() {
        Assertions.assertNotNull(gifClient.getGifByURL(
                URI.create("https://media1.giphy.com/media/xThta97Q4vJvuBptLy/giphy.gif?cid=99ce830805e7853bec702b766252522130fb98d2550bb351&rid=giphy.gif&ct=g"))
        );
    }
}
