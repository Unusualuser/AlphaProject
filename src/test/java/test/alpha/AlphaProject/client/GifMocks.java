package test.alpha.AlphaProject.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class GifMocks {
    public static void setupMockGifResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(""))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.IMAGE_GIF_VALUE)
                        .withBody(
                                copyToString(
                                        CurrencyMocks.class.getClassLoader().getResourceAsStream("payload/gifResponse.gif"),
                                        defaultCharset()))));
    }
}
