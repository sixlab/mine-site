package tech.minesoft.mine.site.core.utils;

import tech.minesoft.mine.site.core.vo.ResultJson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtils {
    private static OkHttpClient client;
    public static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 请求，不含Header，含参数
     *
     * @param url    链接
     * @param method {@link RequestMethod}
     * @param data   参数
     * @return Result
     */
    public static ResultJson sendRequest(String url, RequestMethod method, Map<String, String> data) {
        return sendRequest(url, method, data, null);
    }

    /**
     * 请求，含Header，含参数
     *
     * @param url    链接
     * @param method {@link RequestMethod}
     * @param data   参数
     * @param header header
     * @return Result
     */
    public static ResultJson sendRequest(String url, RequestMethod method, Map<String, String> data, Map<String, String> header) {
        if (RequestMethod.POST.equals(method)) {
            return sendPost(url, data, header);
        } else {
            return sendGet(url, data, header);
        }
    }

    /**
     * POST 请求，Form 提交参数，不含Header
     *
     * @param url  链接
     * @param data 参数
     * @return Result
     */
    public static ResultJson sendPost(String url, Map<String, String> data) {
        return sendPost(url, data, null);
    }

    /**
     * POST 请求，Form 提交参数，含Header
     *
     * @param url    链接
     * @param data   参数
     * @param header header
     * @return Result
     */
    public static ResultJson sendPost(String url, Map<String, String> data, Map<String, String> header) {
        log.info("POST：" + url);
        log.info("参数：" + makeGetParam(data));
        log.info("header：" + makeGetParam(header));

        FormBody.Builder builder = new FormBody.Builder();
        if (!CollectionUtils.isEmpty(data)) {
            for (String key : data.keySet()) {
                if (StringUtils.hasLength(data.get(key))) {
                    builder.add(key, data.get(key));
                }
            }
        }

        Headers.Builder headersBuilder = new Headers.Builder();
        if (!CollectionUtils.isEmpty(header)) {
            for (String key : header.keySet()) {
                if (StringUtils.hasLength(header.get(key))) {
                    headersBuilder.add(key, header.get(key));
                }
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .post(builder.build())
                .build();

        Response response = null;
        Date date = new Date();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("url", e);
        }
        log.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * GET 请求，不含 Header，有参数。
     *
     * @param url  链接
     * @param data 参数
     * @return Result
     */
    public static ResultJson sendGet(String url, Map<String, String> data) {
        return sendGet(url, data, null);
    }

    /**
     * GET 请求，不含 Header，有参数。
     *
     * @param url    链接
     * @param data   参数
     * @param header header
     * @return Result
     */
    public static ResultJson sendGet(String url, Map<String, String> data, Map<String, String> header) {
        log.info("POST：" + url);
        log.info("参数：" + makeGetParam(data));
        log.info("header：" + makeGetParam(header));

        Headers.Builder headersBuilder = new Headers.Builder();
        if (!CollectionUtils.isEmpty(header)) {
            for (String key : header.keySet()) {
                headersBuilder.add(key, header.get(key));
            }
        }

        if (!CollectionUtils.isEmpty(data)) {
            url = makeGetUrl(url, data);
        }

        Request request = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .build();

        Response response = null;
        Date date = new Date();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("url", e);
        }
        log.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * POST 请求，json，不含Header
     *
     * @param url  链接
     * @param json json
     * @return Result
     */
    public static ResultJson sendPostJson(String url, String json) {
        return sendPostJson(url, json, null);
    }

    /**
     * POST 请求，json，含Header
     *
     * @param url    链接
     * @param json   json
     * @param header header
     * @return Result
     */
    public static ResultJson sendPostJson(String url, String json, Map<String, String> header) {
        log.info("POST：" + url);
        log.info("JSON：" + json);
        log.info("header：" + makeGetParam(header));

        Headers.Builder builder = new Headers.Builder();
        if (!CollectionUtils.isEmpty(header)) {
            for (String key : header.keySet()) {
                builder.add(key, header.get(key));
            }
        }

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(builder.build())
                .build();
        Response response = null;
        Date date = new Date();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("url", e);
        }
        log.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * POST 请求，xml，含Header
     *
     * @param url    链接
     * @param xml    xml
     * @param header header
     * @return Result
     */
    public static ResultJson sendPostXml(String url, String xml, Map<String, String> header) {
        log.info("POST：" + url);
        log.info("XML：" + xml);
        log.info("header：" + makeGetParam(header));

        Headers.Builder builder = new Headers.Builder();
        if (!CollectionUtils.isEmpty(header)) {
            for (String key : header.keySet()) {
                builder.add(key, header.get(key));
            }
        }

        RequestBody body = RequestBody.create(XML, xml);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(builder.build())
                .build();
        Response response = null;
        Date date = new Date();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("url", e);
        }
        log.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * 转换为 get 参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data 要转换的map
     * @return
     */
    public static String makeGetParam(Map<String, String> data) {
        return makeGetParam(data, false);
    }

    /**
     * 转换为 get 参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data      要转换的map
     * @param urlEncode 是否将 val 转码
     * @return
     */
    public static String makeGetParam(Map<String, String> data, boolean urlEncode) {
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(data)) {
            Iterator<String> iterator = data.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String val = data.get(key);
                sb.append(key);
                sb.append("=");
                if (urlEncode) {
                    sb.append(URLEncoder.encode(val));
                } else {
                    sb.append(val);
                }
                if (iterator.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 转换为 get 含参链接，url?key=val&k1=v1&k2=v2 格式
     *
     * @param url  链接
     * @param data 要转换的map
     * @return 链接
     */
    public static String makeGetUrl(String url, Map<String, String> data) {
        return url + "?" + makeGetParam(data, true);
    }
    public static ResultJson responseHandler(Response response) {
        return responseHandler(response, StandardCharsets.UTF_8);
    }

    public static ResultJson responseHandler(Response response, Charset charset) {
        ResultJson result = new ResultJson();

        if (response != null) {
            log.info("请求结束:" + response.request().url());
            log.info("请求code:" + response.code());
            log.info("请求message:" + response.message());

            result.addData("code", response.code());

            if (response.isSuccessful() && response.body() != null) {
                try {
                    String text = new String(response.body().bytes(), charset);

                    result.setStatus(Err.SUCCESS);
                    result.setMessage(text);
                } catch (IOException e) {
                    log.error("请求错误", e);
                    result.setStatus(Err.EXCEPTION);
                    result.setMessage("common.request.wrong");
                }
            } else {
                try {
                    if(response.body() != null){
                        result.setStatus(Err.EXCEPTION);
                        result.setMessage(response.body().string());
                        log.error("请求失败：" + result.getMessage());
                    }else{
                        result.setStatus(Err.EXCEPTION);
                        result.setMessage("common.request.error.null");
                        log.error("请求失败：" + result.getMessage());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    result.setStatus(Err.EXCEPTION);
                    result.setMessage("common.request.error.fail");
                }
            }
        }
        return result;
    }

    /**
     * 将Get请求参数字符串转换为 LinkedHashMap
     *
     * @param param get 字符串
     * @return LinkedHashMap
     */
    public static Map<String, String> param2map(String param) {
        Map<String, String> map = new LinkedHashMap<>();

        if (null != param) {
            String[] parameters = param.split("&");
            if (parameters.length > 0) {
                for (String parameter : parameters) {
                    String[] kv = parameter.split("=");
                    if (kv.length == 2) {
                        String key = kv[0];
                        String val = kv[1];
                        if (StringUtils.hasLength(key) && StringUtils.hasLength(val)) {
                            map.put(key, val);
                        }
                    }
                }
            }
        }

        return map;
    }
}
