package com.gmc.gmccoin.api.users.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest
class UserServiceImplTest {


    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
        return headers;
    }

    public void sendSms() {
        try {

//            RestTemplate restTemplate = new RestTemplate();
//            String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("user_id", "ltj1975");
//            jsonObject.put("key", "1uhsi2xwt6f8s7c3mjptiot25ltvch4p");
//
//            jsonObject.put("msg", "인증번호 : 123456"); // 메세지 내용
//            jsonObject.put("receiver", "010-5509-2715");// 수신번호
//            jsonObject.put("destination", "");// 수신인 %고객명% 치환
//            jsonObject.put("sender", "010-7203-8008");// 발신번호
//            jsonObject.put("rdate", "");// 예약일자 - 20161004 : 2016-10-04일기준
//            jsonObject.put("rtime", "");// 예약시간 - 1930 : 오후 7시30분
//            jsonObject.put("testmode_yn", "");// Y 인경우 실제문자 전송X , 자동취소(환불) 처리
//            jsonObject.put("title", "인증번호");//  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)
//
//            HttpEntity<String> req = new HttpEntity<String>(jsonObject.toString(), this.getHeader());
//
//            String res = restTemplate.postForObject(sms_url, req, String.class);
//
//            System.out.println(res);
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode node = objectMapper.readTree(res);
//            System.out.println(node.get("message"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Test() {
        sendSms();
    }
}