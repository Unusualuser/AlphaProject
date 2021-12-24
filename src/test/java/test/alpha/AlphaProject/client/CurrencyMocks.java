package test.alpha.AlphaProject.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class CurrencyMocks {
    public static void setupMockCurrencyResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/2021-12-24.json?app_id=5260f18be4b346a4a83c93c8f094fef1&base=USD"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        CurrencyMocks.class.getClassLoader().getResourceAsStream("payload/currencyResponse.json"),
                                        defaultCharset()))));
    }
}
