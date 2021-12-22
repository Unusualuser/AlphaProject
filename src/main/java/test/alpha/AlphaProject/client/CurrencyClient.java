package test.alpha.AlphaProject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import test.alpha.AlphaProject.model.dto.CurrencyTO;

@FeignClient(name = "currencyClient", url = "${currency.api.historical}")
public interface CurrencyClient {
    @GetMapping("/{date}.json?app_id=${currency.app.key}&base=${currency.base}")
    ResponseEntity<CurrencyTO> getCurrencyByDate(@PathVariable("date") String date);
}
