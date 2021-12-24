package test.alpha.AlphaProject.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class GiphyMocks {
    public static void setupMockGiphyResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.anyUrl())
                .withQueryParam("tag", equalTo("rich"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        CurrencyMocks.class.getClassLoader().getResourceAsStream("payload/giphyRichResponse.json"),
                                        defaultCharset()))));
        mockService.stubFor(WireMock.get(WireMock.anyUrl())
                .withQueryParam("tag", equalTo("broke"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        CurrencyMocks.class.getClassLoader().getResourceAsStream("payload/giphyBrokeResponse.json"),
                                        defaultCharset()))));
    }
}
