package sample;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class connectionController {
    HttpClient client = null;
    public static String serverUrl = "https://127.0.0.1:8080/";

    public connectionController() {
        SSLContext sslcontext=null;
        try{
            sslcontext=new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy(){
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        }catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e){
            e.printStackTrace();
        }
        client= HttpClientBuilder.create().setSSLContext(sslcontext).
                setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
    }

    public JsonObject getData(String url, String[] parameterName, String[] parameterValue) {
        JsonObject parser = null;
        try {
            HttpPost post = new HttpPost(serverUrl + url);

            List<NameValuePair> urlParameters = new ArrayList<>();
            for (int i = 0; i < parameterName.length && i < parameterValue.length; i++) {
                urlParameters.add(new BasicNameValuePair(parameterName[i], parameterValue[i]));
            }
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String result = rd.readLine();
            JsonReader jsonReader = Json.createReader(new StringReader(result));
            parser = jsonReader.readObject();
            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser;
    }

    public InputStream getFile(String url, String[] parameterName, String[] parameterValue) {
        try {
            HttpPost post = new HttpPost(serverUrl + url);
            List<NameValuePair> urlParameters = new ArrayList<>();
            for (int i = 0; i < parameterName.length && i < parameterValue.length; i++) {
                urlParameters.add(new BasicNameValuePair(parameterName[i], parameterValue[i]));
            }

            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(post);
            return response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
