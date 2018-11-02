package com.hiczp.bilibili.api.web;

import com.hiczp.bilibili.api.BaseUrlDefinition;
import com.hiczp.bilibili.api.interceptor.AddFixedHeadersInterceptor;
import com.hiczp.bilibili.api.interceptor.ErrorResponseConverterInterceptor;
import com.hiczp.bilibili.api.web.cookie.SimpleCookieJar;
import com.hiczp.bilibili.api.web.live.LiveService;
import okhttp3.Cookie;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Nonnull;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BilibiliWebAPI {
    private final BrowserProperties browserProperties;
    private final SimpleCookieJar cookieJar;

    private LiveService liveService;
    private LiveService liveServiceWithProxy;

    public BilibiliWebAPI(BrowserProperties browserProperties, Map<String, List<Cookie>> cookiesMap) {
        this.browserProperties = browserProperties;
        this.cookieJar = new SimpleCookieJar(cookiesMap);
    }

    public BilibiliWebAPI(SimpleCookieJar cookieJar) {
        this(BrowserProperties.defaultSetting(), cookieJar.getCookiesMap());
    }

    public BilibiliWebAPI(Map<String, List<Cookie>> cookiesMap) {
        this(BrowserProperties.defaultSetting(), cookiesMap);
    }

    public BilibiliWebAPI(BrowserProperties browserProperties, SimpleCookieJar cookieJar) {
        this(browserProperties, cookieJar.getCookiesMap());
    }

    public LiveService getLiveService() {
        if (liveService == null) {
            liveService = getLiveService(Collections.emptyList(), HttpLoggingInterceptor.Level.BASIC);
        }
        return liveService;
    }

    public LiveService getLiveService(String proxyIp, int proxyPort) {
        if (liveServiceWithProxy == null) {
            liveServiceWithProxy = getLiveService(Collections.emptyList(), HttpLoggingInterceptor.Level.BASIC, proxyIp, proxyPort);
        }
        return liveServiceWithProxy;
    }

    public LiveService getLiveService(@Nonnull List<Interceptor> interceptors, @Nonnull HttpLoggingInterceptor.Level logLevel) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder
                .cookieJar(cookieJar)
                .addInterceptor(new AddFixedHeadersInterceptor(
                        "User-Agent", browserProperties.getUserAgent()
                ))
                .addInterceptor(new ErrorResponseConverterInterceptor());

        interceptors.forEach(okHttpClientBuilder::addInterceptor);

        okHttpClientBuilder
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(logLevel));

        return new Retrofit.Builder()
                .baseUrl(BaseUrlDefinition.LIVE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
                .create(LiveService.class);
    }

    public LiveService getLiveService(@Nonnull List<Interceptor> interceptors, @Nonnull HttpLoggingInterceptor.Level logLevel, String proxyIp, int proxyPort) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder
                .cookieJar(cookieJar)
                .addInterceptor(new AddFixedHeadersInterceptor(
                        "User-Agent", browserProperties.getUserAgent()
                ))
                .addInterceptor(new ErrorResponseConverterInterceptor());

        interceptors.forEach(okHttpClientBuilder::addInterceptor);

        okHttpClientBuilder
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(logLevel));
        InetAddress inetAddress = null;
        try {
            String[] ipStr = proxyIp.split("\\.");
            byte[] ipBuf = new byte[4];
            for (int i = 0; i < 4; i++) {
                ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xff);
            }
            inetAddress = InetAddress.getByAddress(ipBuf);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, proxyPort);
        okHttpClientBuilder.proxy(new Proxy(Proxy.Type.SOCKS, inetSocketAddress));

        return new Retrofit.Builder()
                .baseUrl(BaseUrlDefinition.LIVE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
                .create(LiveService.class);
    }

    public SimpleCookieJar getCookieJar() {
        return cookieJar;
    }
}
