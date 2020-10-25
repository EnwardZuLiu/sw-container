package com.safeweb.cluster;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.LocalDirectorySSLConfig;
import com.github.dockerjava.okhttp.OkDockerHttpClient;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * author liuzm
 * createTime 2020/10/11 12:39
 */
public class DockerClientFactory {

    public static DockerClient getClientByHost(String hostName) {
        var config = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost(String.format("tcp://%s:2376", hostName))
            .withDockerTlsVerify(true)
            .withDockerCertPath("/var/safeweb/certs/")
            .build();

        var sslConfig = new LocalDirectorySSLConfig("/var/safeweb/certs/");
        var sslContext = sslConfig.getSSLContext();

        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };

        try {
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        var httpClient= new OkDockerHttpClient.Builder()
            .connectTimeout(2000)
            .dockerHost(URI.create(String.format("tcp://%s:2376", hostName)))
            .readTimeout(100000)
            .sslConfig(sslConfig)
            .build();

        return DockerClientImpl.getInstance(config, httpClient);
    }

}
