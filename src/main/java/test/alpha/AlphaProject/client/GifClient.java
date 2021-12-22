package test.alpha.AlphaProject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "gifClient", url = "https://placeholder")
public interface GifClient {
    @GetMapping
    ResponseEntity<byte[]> getGifByURL(URI url);
}
