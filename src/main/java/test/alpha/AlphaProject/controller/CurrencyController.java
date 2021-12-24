package test.alpha.AlphaProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.alpha.AlphaProject.exceptions.IncorrectCurrencyCodeException;
import test.alpha.AlphaProject.service.CurrencyService;

@RestController
@RequestMapping("api")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/gif")
    public ResponseEntity<byte[]> getGifByCurrencyCode(@RequestParam String currencyCode) {
        try {
            return currencyService.getGifByCurrencyCode(currencyCode);
        } catch (IncorrectCurrencyCodeException e) {
            LOGGER.warn("/gif: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.warn("/gif: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
