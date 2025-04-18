package com.mokinder.TrackerProject.config;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import javax.net.ssl.*;
import java.net.http.HttpClient;
import java.security.cert.X509Certificate;


@Configuration
public class SSLUtilities {

    public static void disableCertificateValidation() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    @Bean
//    public RestTemplate restTemplate() {
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)  // Disables SSL certificate verification
//                .build();
//
//        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
//    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}

