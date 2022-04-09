package com.survivour.quiz.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Objects;

@Service
public class RestUtil {

    private final RestTemplateBuilder restTemplateBuilder;
    private final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;

    private String url;
    private HttpEntity<Object> requestEntity;

    private String password;
    private String certPath;

    public RestUtil(RestTemplateBuilder restTemplateBuilder, HttpComponentsClientHttpRequestFactory clientHttpRequestFactory) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.clientHttpRequestFactory = clientHttpRequestFactory;
    }

    public RestUtil setUrl(String url) {
        this.url = url;
        return this;
    }

    public RestUtil setRequest(Object request) {
        requestEntity = new HttpEntity<>(request);
        return this;
    }

    public RestUtil setRequest( HttpHeaders headers) {
        requestEntity = new HttpEntity<>(headers);
        return this;
    }

    public RestUtil setRequest(Object request, HttpHeaders headers) {
        requestEntity = new HttpEntity<>(request, headers);
        return this;
    }


    public RestUtil setCredentials(String password, String certPath) {
        this.certPath = certPath;
        this.password = password;
        return this;
    }



    public <T> ResponseEntity<T> get(Class<T> entityClass) throws Exception {
        return getRestTemplate().exchange(url, HttpMethod.GET, requestEntity, entityClass);
    }



    private RestTemplate getRestTemplate() throws Exception {
        if (Objects.nonNull(this.password) && Objects.nonNull(this.certPath)) {
            return getRestTemplateWithCert();
        }
        return getRestTemplateWithoutCert();
    }

    private RestTemplate getRestTemplateWithoutCert()
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(null, ( certificate, authType) -> true)
                .build();
        HttpClient client = HttpClients.custom().setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        HttpComponentsClientHttpRequestFactory factory = this.clientHttpRequestFactory;
        factory.setHttpClient(client);

        return restTemplateBuilder.requestFactory(() -> factory).build();
    }

    private RestTemplate getRestTemplateWithCert() throws Exception {
        char[] pass = this.password.toCharArray();

        SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(keyStore(certPath, pass), pass)
                .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();

        HttpClient client = HttpClients.custom().setSSLContext(sslContext).build();
        HttpComponentsClientHttpRequestFactory factory = this.clientHttpRequestFactory;
        factory.setHttpClient(client);
        return restTemplateBuilder.requestFactory(() -> factory).build();
    }

    private KeyStore keyStore( String filePath, char[] password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        File key = ResourceUtils.getFile(filePath);
        try (InputStream in = new FileInputStream(key)) {
            keyStore.load(in, password);
        }
        return keyStore;
    }
}
