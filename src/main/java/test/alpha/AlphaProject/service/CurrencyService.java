package test.alpha.AlphaProject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.alpha.AlphaProject.client.CurrencyClient;
import test.alpha.AlphaProject.client.GifClient;
import test.alpha.AlphaProject.client.GiphyClient;
import test.alpha.AlphaProject.exceptions.GifRequestException;
import test.alpha.AlphaProject.exceptions.IncorrectCurrencyCodeException;
import test.alpha.AlphaProject.model.dto.CurrencyTO;
import test.alpha.AlphaProject.model.dto.GiphyTO;
import test.alpha.AlphaProject.exceptions.CurrencyRequestException;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final GiphyClient giphyClient;
    private final GifClient gifClient;
    private final String gifTagHigher;
    private final String gifTagLower;
    private final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    public CurrencyService(CurrencyClient currencyClient,
                           GiphyClient giphyClient,
                           GifClient gifClient,
                           @Value("${gif.tag.today.currency.higher}") String gifTagHigher,
                           @Value("${gif.tag.today.currency.lower}") String gifTagLower) {
        this.currencyClient = currencyClient;
        this.giphyClient = giphyClient;
        this.gifClient = gifClient;
        this.gifTagHigher = gifTagHigher;
        this.gifTagLower = gifTagLower;
    }

    public ResponseEntity<byte[]> getGifByCurrencyCode(String currencyCode) throws CurrencyRequestException, GifRequestException, IncorrectCurrencyCodeException {
        boolean isTodayRateHigher = isTodayRateHigher(currencyCode);
        GiphyTO giphyTO;
        try {
            if (isTodayRateHigher)
                giphyTO = giphyClient.getRandomGifByTag(gifTagHigher).getBody();
            else
                giphyTO = giphyClient.getRandomGifByTag(gifTagLower).getBody();

            return gifClient.getGifByURL(URI.create(giphyTO.getGifURL()));
        }
        catch (Exception e) {
            LOGGER.warn("getGifByCurrencyCode: ", e);
            throw new GifRequestException();
        }
    }

    public boolean isTodayRateHigher(String currencyCode) throws CurrencyRequestException, IncorrectCurrencyCodeException {
        String todayFormatDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String yesterdayFormatDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ResponseEntity<CurrencyTO> todayCurrencyResponse;
        ResponseEntity<CurrencyTO> yesterdayCurrencyResponse;
        try {
            todayCurrencyResponse = currencyClient.getCurrencyByDate(todayFormatDate);
            yesterdayCurrencyResponse = currencyClient.getCurrencyByDate(yesterdayFormatDate);
        }
        catch (Exception e) {
            LOGGER.warn("isTodayRateHigher: ", e);
            throw new CurrencyRequestException();
        }

        try {
            double todayCurrencyRate = Objects.requireNonNull(todayCurrencyResponse.getBody()).getRates().get(currencyCode);
            double yesterdayCurrencyRate = Objects.requireNonNull(yesterdayCurrencyResponse.getBody()).getRates().get(currencyCode);

            return todayCurrencyRate > yesterdayCurrencyRate;
        }
        catch (Exception e) {
            LOGGER.warn("isTodayRateHigher: ", e);
            throw new IncorrectCurrencyCodeException();
        }
    }
}
