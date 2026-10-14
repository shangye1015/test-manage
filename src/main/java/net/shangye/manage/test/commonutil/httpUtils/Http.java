package net.shangye.manage.test.commonutil.httpUtils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Title: Http请求工具类 </p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2019</p >
 * <p>Company: Luculent</p >
 *
 * @author wangsongru
 * @version 7.0
 * @createTime 2021-11-02 13:59:00
 */
@SuppressWarnings("unused")
public class Http {

    private static final Logger log = LoggerFactory.getLogger(Http.class);

    /**
     * restTemplate构造者
     */
    private final RestTemplateBuilder builder = new RestTemplateBuilder();

    /**
     * 自定义的请求头集合
     */
    private Map<String, String> heads = new HashMap<>();

    /**
     * 自定义http请求次数 默认一次
     */
    private Integer redo = 1;

    /**
     * 设置重试次数，返回对象自身
     *
     * @param redo 重试次数
     * @return Http 对象自身
     * @author wangsongru
     * @createTime 2021-11-05 17:51:26
     */
    public Http redo(Integer redo) {
        this.redo = redo;
        return this;
    }

    /**
     * 设置自定义请求头，返回对象自身
     *
     * @param heads 自定义请求头集合
     * @return Http 对象自身
     * @author wangsongru
     * @createTime 2021-11-05 17:51:26
     */
    public Http head(Map<String, String> heads) {
        this.heads = heads;
        return this;
    }

    /**
     * 设置连接超时时间，返回对象自身
     *
     * @param connectTimeout 连接超时时间
     * @return Http 对象自身
     * @author wangsongru
     * @createTime 2021-11-05 17:51:26
     */
    public Http connectTimeout(Integer connectTimeout) {
        this.builder.connectTimeout(connectTimeout);
        return this;
    }

    /**
     * 设置请求超时时间，返回对象自身
     *
     * @param requestTimeout 请求超时时间
     * @return Http 对象自身
     * @author wangsongru
     * @createTime 2021-11-05 17:51:26
     */
    public Http requestTimeout(Integer requestTimeout) {
        this.builder.requestTimeout(requestTimeout);
        return this;
    }

    /**
     * 设置http的socket超时时间，返回对象自身
     *
     * @param socketTimeout socket超时时间
     * @return Http 对象自身
     * @author wangsongru
     * @createTime 2021-11-05 17:51:26
     */
    public Http socketTimeout(Integer socketTimeout) {
        this.builder.socketTimeout(socketTimeout);
        return this;
    }

    /**
     * httpGet方法
     *
     * @param url 请求url
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:53:31
     */
    public JSONObject get(String url) {
        final RestTemplate restTemplate = builder.build();
        return execute(restTemplate, url, HttpMethod.GET, null, null, heads);
    }

    /**
     * httpGet方法
     *
     * @param url       请求url
     * @param uriParams get参数
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:53:31
     */
    public JSONObject get(String url, Map<String, Object> uriParams) {
        final RestTemplate restTemplate = builder.build();
        return execute(restTemplate, url, HttpMethod.GET, uriParams, null, heads);
    }

    /**
     * httpPost方法
     *
     * @param url 请求url
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:53:31
     */
    public JSONObject post(String url) {
        final RestTemplate restTemplate = builder.build();
        return execute(restTemplate, url, HttpMethod.POST, null, null, heads);
    }

    /**
     * httpPost方法
     *
     * @param url  请求url
     * @param body post请求body对象
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:53:31
     */
    public <T> JSONObject post(String url, T body) {
        final RestTemplate restTemplate = builder.build();
        return execute(restTemplate, url, HttpMethod.POST, null, body, heads);
    }


    /**
     * httpPost方法
     *
     * @param url       请求url
     * @param uriParams post请求url的参数
     * @param body      post请求body对象
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:53:31
     */
    public <T> JSONObject post(String url, Map<String, Object> uriParams, T body) {
        final RestTemplate restTemplate = builder.build();
        return execute(restTemplate, url, HttpMethod.POST, uriParams, body, heads);
    }

    /**
     * 执行http请求
     *
     * @param restTemplate restTemplate对象
     * @param url          请求url
     * @param method       请求方式
     * @param uriParams    url内的参数
     * @param body         body参数
     * @param heads        自定义的head参数
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:55:44
     */
    private <T> JSONObject execute(RestTemplate restTemplate, String url, HttpMethod method,
                                   Map<String, Object> uriParams, T body,
                                   Map<String, String> heads) {
        final HttpHeaders httpHeaders = getDefaultHeader();
        heads.forEach(httpHeaders::add);
        final HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);

        JSONObject result = null;
        int i = 1;
        while (i <= redo) {
            log.info("尝试第{}次请求, url:{}", i, url);
            result = doExecute(restTemplate, url, method, uriParams, body, httpHeaders, httpEntity);
            if (result != null) {
                break;
            }
            i++;
        }

        return result;
    }

    /**
     * 立即执行http请求
     *
     * @param restTemplate restTemplate对象
     * @param url          请求url
     * @param method       请求方式
     * @param uriParams    url内的参数
     * @param body         body参数
     * @param httpHeaders  自定义的head参数
     * @param httpEntity   httpEntity对象
     * @return JSONObject 返回结果
     * @author wangsongru
     * @createTime 2021-11-05 17:55:44
     */
    private <T> JSONObject doExecute(RestTemplate restTemplate, String url, HttpMethod method,
                                     Map<String, Object> uriParams, T body,
                                     HttpHeaders httpHeaders, HttpEntity<Object> httpEntity) {
        try {
            final ResponseEntity<String> result =
                    uriParams == null || uriParams.isEmpty() ?
                            restTemplate.exchange(url, method, httpEntity, String.class) :
                            restTemplate.exchange(url, method, httpEntity, String.class, uriParams);
            final HttpStatus statusCode = result.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                final String resultBody = result.getBody();
                final JSONObject resultJson = new JSONObject(resultBody);
                if (resultJson.optBoolean("success")) {
                    log.info("请求url{}返回{}", url, resultJson);
                    return resultJson;
                } else {
                    log.error("请求url{}返回结果出错，uriParams:{},body:{},head:{},result:{}",
                            url, getNoNullLog(uriParams), getNoNullLog(body), getNoNullLog(httpHeaders),
                            resultJson);
                }
            }
        } catch (Exception e) {
            log.error("请求url{}出错，uriParams:{},body:{},head:{}",
                    url, getNoNullLog(uriParams), getNoNullLog(body), getNoNullLog(httpHeaders), e);
        }
        return null;
    }

    /**
     * 安全的返回不为空的日志对象输出
     *
     * @param strObj 需要日志输出的对象
     * @return String 日志内容
     * @author wangsongru
     * @createTime 2021-11-05 17:58:11
     */
    private <T> String getNoNullLog(T strObj) {
        return strObj == null ? "" : strObj.toString();
    }

    /**
     * 默认构建的头对象，里面默认带有LiEMS请求Authorization
     *
     * @return HttpHeaders 请求头对象
     * @author wangsongru
     * @createTime 2021-11-05 17:58:46
     */
    public static HttpHeaders getDefaultHeader() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "SignSDK.signApp()"); // TODO: 签名
        return httpHeaders;
    }

    /**
     * 构建请求url
     *
     * @param uri    原地址
     * @param params 参数列表
     * @return String uri 真实的uri
     * @author wangsongru
     * @createTime 2021-12-03 17:56:33
     */
    public static String buildGetUri(String uri, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return uri;
        }
        String actualUri = uri + "?";
        final String paramsStr = params.keySet().stream()
                .map(k -> k + "=" + params.get(k))
                .collect(Collectors.joining("&"));
        return actualUri + paramsStr;
    }

}