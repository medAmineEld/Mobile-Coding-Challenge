package challenge.coding.mobile.mobile_coding_challenge.api;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public  static final String BASE_URL = "https://api.github.com";
    public static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if(retrofit == null)
        {
            try {
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                        client(getHttpClient()).
                        addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return retrofit;
    }

    public static OkHttpClient getHttpClient() throws NoSuchAlgorithmException {


        SSLContext sslcontext=null;
        try {
            sslcontext = SSLContext.getInstance("TLSv1.2");
            sslcontext.init(null, null, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        try {
            trustManagerFactory.init((KeyStore) null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
        SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
         OkHttpClient o = new OkHttpClient.Builder()
                 .sslSocketFactory(NoSSLv3Factory,trustManager)
                .build();
         return o;
    }

}
