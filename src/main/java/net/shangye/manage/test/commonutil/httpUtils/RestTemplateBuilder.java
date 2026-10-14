package net.shangye.manage.test.commonutil.httpUtils;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * <p>Title: restTemplate构造者 </p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2019</p >
 * <p>Company: Luculent</p >
 *
 * @author wangsongru
 * @version 7.0
 * @createTime 2021-11-02 14:57:00
 */
@SuppressWarnings("ALL")
public class RestTemplateBuilder {

    /**
     * httpClient连接管理对象
     */
    private final HttpClientConnectionManager manager;

    /**
     * 请求配置对象
     */
    private final RequestConfig config;

    /**
     * 请求配置对象构造者
     */
    private RequestConfig.Builder configBuilder;

    @Autowired
    private ApplicationContext context;

    public RestTemplateBuilder() {
        this.manager = context.getBean(HttpClientConnectionManager.class);
        this.config = context.getBean(RequestConfig.class);
    }

    /**
     * 构造RestTemplate对象
     *
     * @return RestTemplate RestTemplate对象
     * @author wangsongru
     * @createTime 2021-11-08 08:57:39
     */
    public RestTemplate build() {
        RequestConfig useConfig;
        if (configBuilder != null) {
            useConfig = configBuilder.build();
        } else {
            useConfig = config;
        }
        final HttpComponentsClientHttpRequestFactory factory = buildClientFactory(useConfig);
        final RestTemplate restTemplate = new RestTemplate(factory);

        for (HttpMessageConverter<?> messageConverter : restTemplate.getMessageConverters()) {
            if (messageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) messageConverter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }
        return restTemplate;
    }

    /**
     * 设置RestTemplate连接超时时间配置
     *
     * @param connectTimeout 连接超时时间
     * @return RestTemplateBuilder 自身
     * @author wangsongru
     * @createTime 2021-11-08 08:58:26
     */
    public RestTemplateBuilder connectTimeout(Integer connectTimeout) {
        final RequestConfig.Builder builder =
                RequestConfig.custom().setConnectTimeout(connectTimeout);
        this.setConfigBuilder(builder);
        return this;
    }

    /**
     * 设置RestTemplate请求超时时间配置
     *
     * @param requestTimeout 请求超时时间
     * @return RestTemplateBuilder 自身
     * @author wangsongru
     * @createTime 2021-11-08 08:59:25
     */
    public RestTemplateBuilder requestTimeout(Integer requestTimeout) {
        final RequestConfig.Builder builder =
                RequestConfig.custom().setConnectionRequestTimeout(requestTimeout);
        this.setConfigBuilder(builder);
        return this;
    }

    /**
     * 设置RestTemplate socket超时时间
     *
     * @param socketTimeout socket超时时间
     * @return RestTemplateBuilder 自身
     * @author wangsongru
     * @createTime 2021-11-08 09:00:41
     */
    public RestTemplateBuilder socketTimeout(Integer socketTimeout) {
        final RequestConfig.Builder builder =
                RequestConfig.custom().setSocketTimeout(socketTimeout);
        this.setConfigBuilder(builder);
        return this;
    }

    /**
     * 构建HttpClient工厂
     *
     * @param config 请求配置对象
     * @return HttpComponentsClientHttpRequestFactory 工厂类
     * @author wangsongru
     * @createTime 2021-11-08 09:01:33
     */
    private HttpComponentsClientHttpRequestFactory buildClientFactory(RequestConfig config) {
        final CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    private void setConfigBuilder(RequestConfig.Builder configBuilder) {
        this.configBuilder = configBuilder;
    }
}
