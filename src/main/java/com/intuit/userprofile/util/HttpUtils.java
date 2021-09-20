package com.intuit.userprofile.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author prajwal.kulkarni on 20/09/21
 */
public class HttpUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <I, O> O buildGenericPost(String url, I i, Class<O> classType, Map<String, String> headersMap) throws Exception {
        String content = null;
        try {
            content = objectMapper.writeValueAsString(i);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return makePostCall(url, content, classType, headersMap);
    }

    public static <O> O makePostCall(String url, String content, Class<O> classType, Map<String, String> headersMap) throws Exception {
        HttpPost httpPost = new HttpPost(String.valueOf(url));
        StringEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.addHeader("accept", "application/json");
        addHeaders(headersMap, httpPost);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        InputStream inputStream = null;
        int statusCode = 0;
        InputStream loggingStream = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                if (statusCode != 204) {
                    inputStream = httpResponse.getEntity().getContent();
                    byte[] bytes = getByteStream(inputStream);
                    loggingStream = new ByteArrayInputStream(bytes);
                    inputStream = new ByteArrayInputStream(bytes);
                    return objectMapper.readValue(inputStream, classType);
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (loggingStream != null) {
                    loggingStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpResponse != null) httpResponse.close();
            httpClient.close();
        }
        throw new Exception("Post call " + url + " failed with status code " + statusCode);
    }

    public static byte[] getByteStream(InputStream original) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while (true) {
            try {
                if (!((len = original.read(buffer)) > -1)) {
                    break;
                }
                baos.write(buffer, 0, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private static void addHeaders(Map<String, String> headersMap, HttpPost httppost) throws Exception {
        if (headersMap != null) {
            List<Map.Entry<String, String>> entryList = new ArrayList<>(headersMap.entrySet());
            for (Map.Entry<String, String> entry : entryList) {
                httppost.addHeader(entry.getKey(), entry.getValue());
                httppost.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }
}
