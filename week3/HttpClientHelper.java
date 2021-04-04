package java0.week2.httpClient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientHelper {
	
	//one instance, reuse
	public static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private void close() throws IOException {
        httpClient.close();
    }
	
	private void sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }

    public static void main(String[] args) throws Exception {

    	HttpClientHelper obj = new HttpClientHelper();
    	
    	try {
    		String url = "http://localhost:8801/";
    		obj.sendGet(url);
    	} finally {
            obj.close();
        }

    }
}
