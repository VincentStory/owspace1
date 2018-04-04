package com.app.vincent.owspace.net;

import android.content.Context;

import com.app.vincent.owspace.net.gsonconverter.CustomGsonConverterFactory;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import redroid.storage.AppPreferences;
import redroid.storage.UserPreferences;
import redroid.util.AppUtils;
import redroid.util.StringUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import timber.log.Timber;

/**
 * 网络请求配置类
 *
 * @author RobinVanYang created at 2018-01-22 14:15.
 */

public class Client {
    private Retrofit mRetrofit;

    private Context mApplicationContext;

    @Inject
    public Client(Context context) {
        this(true);
        mApplicationContext = context;
    }

    private Client(boolean useRxJava) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://static.owspace.com/")
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(getClient());
        if (useRxJava) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        mRetrofit = builder.build();
    }

    public <T> T createApi(Class<T> className) {
        return mRetrofit.create(className);
    }


    private String getUserToken() {
        return UserPreferences.getInstance(mApplicationContext).getToken();
    }

    private String getUserType() {
        return AppPreferences.getInstance(mApplicationContext).getAppType();
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                message -> Timber.tag("OkHttp").v(StringUtils.toPrettyJson(message))
        );

        Interceptor paramInterceptor = chain -> {
            Request originalRequest = chain.request();
            HttpUrl.Builder builder = originalRequest.url().newBuilder();
            builder.addQueryParameter("appCode", AppUtils.getVersionName(mApplicationContext));


            String userToken = getUserToken();
            if (StringUtils.notNull(userToken)) {
                builder.addQueryParameter("token", userToken);
            }
            builder.addQueryParameter("userType", getUserType());

            HttpUrl url = builder.build();
            Request.Builder requestBuilder = originalRequest.newBuilder().url(url);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

//        logging.setLevel(APIConstant.IS_DEV ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        SSLSocketFactory sslSocketFactory = null;

        try {
            // 这里直接创建一个不做证书串验证的TrustManager
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
        }

        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
//                .addInterceptor(new HeadInterceptor())
                .addInterceptor(logging)
                .addInterceptor(paramInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory)
                .build();
    }
}
