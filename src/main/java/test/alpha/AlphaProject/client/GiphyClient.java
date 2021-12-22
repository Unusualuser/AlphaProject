package test.alpha.AlphaProject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import test.alpha.AlphaProject.model.dto.GifTO;

@FeignClient(name = "giphyClient", url = "${gif.api.random}")
public interface GiphyClient {
    @GetMapping("?api_key=${gif.app.key}&tag={tag}")
    ResponseEntity<GifTO> getRandomGifByTag(@PathVariable("tag") String tag);
}
