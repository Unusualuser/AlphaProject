package test.alpha.AlphaProject.model.dto;

import java.util.Map;

public class GiphyTO {
    Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getGifURL() {
        String images = data.get("images").toString();
        int urlStartIndex = images.indexOf("url=", images.indexOf("original={")) + 4;
        int urlEndIndex = images.indexOf(",", urlStartIndex);
        String url = images.substring(urlStartIndex, urlEndIndex);
        return url;
    }
}