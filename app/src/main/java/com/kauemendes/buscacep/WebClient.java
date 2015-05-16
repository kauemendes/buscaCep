package com.kauemendes.buscacep;

/**
 * Created by kauemendes on 3/25/15.
 */
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {

    private final String url;
    private DefaultHttpClient httpClient;
    private HttpPost httpPost;
    private HttpResponse response;

    public WebClient(String url){
        this.url = url;
    }

    public String obterEndereco(){
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
