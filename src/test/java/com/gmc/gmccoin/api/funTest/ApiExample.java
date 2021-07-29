package com.gmc.gmccoin.api.funTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ApiExample {

    private static final String API_URL = "http://fun-api.funex.net";   //제공된 도메인
    private static final String REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJmdW4tYWRtIiwiZXhwIjoxNjU4OTg3ODk1LCJpc3MiOiJPQkpFQ1QgQVBJIiwiaWF0IjoxNjI3NDUxODk1fQ.sIHznAZI3ouuBo7-unRraLv6czWSAiJZZ5yY_B-4iOs";
    private final String ACCESS_TOKEN_URL = "/private/auth/accessToken";
    private final String A101_URL = "/private/mem/a101";
    private final String A102_URL = "/private/mem/a102";
    private final String A103_URL = "/private/mem/a103";
    private final String A104_URL = "/private/mem/a104";

    private final String A201_URL = "/public/balance/";

    private final String A302_URL = "/private/bk/a302";
    private final String A303_URL = "/private/bk/a303";

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHeader(String Authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.set("Authorization", Authorization);
        return headers;
    }

    private String getAccessToken() throws Exception {
        HttpEntity<String> req = new HttpEntity<String>(null, this.getHeader(REFRESH_TOKEN));
        String res = restTemplate.postForObject(API_URL + ACCESS_TOKEN_URL, req, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        return node.get("data").get("access_token").textValue();
    }

    @Test
    public void A101() throws Exception {

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("tx_id", "test_txid_1234");
        map.add("user_id", "test123");
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, this.getHeader(getAccessToken()));

        String res = restTemplate.postForObject(API_URL + A101_URL, req, String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));
    }

    @Test
    public void A102() throws Exception {

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("tx_id", "test_txid_1234");
        map.add("user_id", "test1234");
        map.add("user_pw", "password123");
        map.add("user_name", "테스트");
        map.add("user_email", "test@test.com");
        map.add("user_nation", "82");
        map.add("user_phone", "0123456789");
        map.add("user_bank", "testbank");
        map.add("user_num", "1234567890-12312");
        map.add("user_holder", "테스터");
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, this.getHeader(getAccessToken()));

        String res = restTemplate.postForObject(API_URL + A102_URL, req, String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));
    }

    @Test
    public void A103() throws Exception {

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("tx_id", "test_txid_1234");
        map.add("user_id", "test1234");
        map.add("user_pw", "password123");
        map.add("user_name", "테스트123");
        map.add("user_email", "test@test.com");
        map.add("user_nation", "82");
        map.add("user_phone", "0123456789");
        map.add("user_bank", "testbank");
        map.add("user_num", "1234567890-12312");
        map.add("user_holder", "테스터");
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, this.getHeader(getAccessToken()));

        String res = restTemplate.postForObject(API_URL + A103_URL, req, String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));
    }

    @Test
    public void A104() throws Exception {

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("tx_id", "test_txid_1234");
        map.add("user_id", "test1234");
        map.add("delete_type", "R");
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, this.getHeader(getAccessToken()));

        String res = restTemplate.postForObject(API_URL + A104_URL, req, String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));
    }

    @Test
    public void A201() throws Exception {
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(null, this.getHeader(getAccessToken()));

        String res = restTemplate.getForObject(API_URL + A201_URL + "TGiq4doMa4fuzqKuSuwQmhvv3M98BRi8UJ", String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));

        float tokenBalance = node.get("data").get("token").floatValue();

        System.out.println(tokenBalance);
    }

    @Test
    public void A302() throws Exception {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("tx_id", "test_txid_1234");
        map.add("user_id", "test1234");
        map.add("to_address", "TFCnv6GJZ2fM5P7HKSS6CidM9Ha7PziG33");
        map.add("balance", "1");
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, this.getHeader(getAccessToken()));

        String res = restTemplate.postForObject(API_URL + A302_URL, req, String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));

        if(node.get("success").booleanValue()) {
            String tx_id = node.get("data").get("tx_id").textValue();
            System.out.println(tx_id);
        }

    }

    @Test
    public void A303() throws Exception {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("tx_id", "c1847f38-ac5e-4885-aed9-f1493b7ccba8");
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, this.getHeader(getAccessToken()));

        String res = restTemplate.postForObject(API_URL + A303_URL, req, String.class);
        System.out.println(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(res);
        System.out.println(node.get("success"));
        System.out.println(node.get("code"));
        System.out.println(node.get("message"));
        System.out.println(node.get("errors"));

        if(node.get("success").booleanValue()) {
            String data = node.get("data").textValue();
            System.out.println(data);
        }

    }
}
