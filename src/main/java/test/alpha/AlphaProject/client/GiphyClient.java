package test.alpha.AlphaProject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import test.alpha.AlphaProject.model.dto.GiphyTO;

@FeignClient(name = "giphyClient", url = "${gif.api.random}")
public interface GiphyClient {
    @GetMapping("?api_key=${gif.app.key}&tag={tag}")
    ResponseEntity<GiphyTO> getRandomGifByTag(@PathVariable("tag") String tag);
}
