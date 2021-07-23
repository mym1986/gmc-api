package com.gmc.gmccoin.api.util;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SmsUtil {

    private final RestTemplate restTemplate;

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
        return headers;
    }

    public void sendSms() {
        try {

            String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL
//
//            for(HttpMessageConverter<?> messageConverter : restTemplate.getMessageConverters()) {
//                if(messageConverter instanceof AllEncompassingFormHttpMessageConverter) {
//                    ((AllEncompassingFormHttpMessageConverter) messageConverter).setCharset(Charset.forName("UTF-8"));
//                    ((AllEncompassingFormHttpMessageConverter) messageConverter).setMultipartCharset(Charset.forName("UTF-8"));
//                }
//            }
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("user_id", "ltj1975");
//            jsonObject.put("key", "1uhsi2xwt6f8s7c3mjptiot25ltvch4p");
//            jsonObject.put("msg", "인증번호 : 123456"); // 메세지 내용
//            jsonObject.put("receiver", "01055092715");// 수신번호
//            jsonObject.put("sender", "01072038008");// 발신번호
////            jsonObject.put("destination", "");// 수신인 %고객명% 치환
////            jsonObject.put("rdate", "");// 예약일자 - 20161004 : 2016-10-04일기준
////            jsonObject.put("rtime", "");// 예약시간 - 1930 : 오후 7시30분
////            jsonObject.put("testmode_yn", "");// Y 인경우 실제문자 전송X , 자동취소(환불) 처리
////            jsonObject.put("title", "인증번호");//  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)
//
//            HttpEntity<String> req = new HttpEntity<String>(jsonObject.toString(), this.getHeader());
//
//            String res = restTemplate.postForObject(sms_url, req, String.class);
//
//            System.out.println(res);
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode node = objectMapper.readTree(res);
//            System.out.println(node.get("message"));

            Map<String, String> sms = new HashMap<String, String>();

            sms.put("user_id", "ltj1975"); // SMS 아이디
            sms.put("key", "1uhsi2xwt6f8s7c3mjptiot25ltvch4p"); //인증키

            /******************** 인증정보 ********************/

            /******************** 전송정보 ********************/
            sms.put("msg", "%고객명%님. 안녕하세요. API TEST SEND"); // 메세지 내용
            sms.put("receiver", "01055092715"); // 수신번호
//            sms.put("destination", "01111111111|담당자,01111111112|홍길동"); // 수신인 %고객명% 치환
            sms.put("sender", "01072038008"); // 발신번호
//            sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
//            sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
//            sms.put("testmode_yn", "Y"); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
//            sms.put("title", "제목입력"); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)


            ////////////////////////////
            HttpPost post = new HttpPost(sms_url);
            StringBody user_id = new StringBody("ltj1975", ContentType.MULTIPART_FORM_DATA);
            StringBody key = new StringBody("1uhsi2xwt6f8s7c3mjptiot25ltvch4p", ContentType.MULTIPART_FORM_DATA);
            StringBody msg = new StringBody("%고객명%님. 안녕하세요. API TEST SEND", ContentType.MULTIPART_FORM_DATA);
            StringBody receiver = new StringBody("01055092715", ContentType.MULTIPART_FORM_DATA);
            StringBody sender = new StringBody("01072038008", ContentType.MULTIPART_FORM_DATA);
//
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("user_id", user_id);
            builder.addPart("key", key);
            builder.addPart("msg", msg);
            builder.addPart("receiver", receiver);
            builder.addPart("sender", sender);
            HttpEntity entity = builder.build();
//
            post.setEntity(entity);
//            HttpResponse response = client.execute(post);
//            /////////////////////////////
//
//
//            final String encodingType = "utf-8";
//            final String boundary = "____boundary____";
//
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//
//            builder.setBoundary(boundary);
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            builder.setCharset(Charset.forName(encodingType));
//
//            for(Iterator<String> i = sms.keySet().iterator(); i.hasNext();){
//                String key = i.next();
//                builder.addTextBody(key, sms.get(key)
//                        , ContentType.create("Multipart/related", encodingType));
//            }
//
//            HttpEntity entity = builder.build();
//
//            HttpClient client = HttpClients.createDefault();
//            HttpPost post = new HttpPost(sms_url);

//            post.setEntity(entity);

            HttpClient client = HttpClients.createDefault();
            HttpResponse res = client.execute(post);
            final String encodingType = "utf-8";
            String result = "";
            if(res != null){
                BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                String buffer = null;
                while((buffer = in.readLine())!=null){
                    result += buffer;
                }
                in.close();
            }

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
