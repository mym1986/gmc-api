package com.gmc.gmccoin.api.tron.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmc.gmccoin.api.tron.dto.SendDTO;
import com.gmc.gmccoin.api.tron.dto.TronDTO;
import com.gmc.gmccoin.api.tron.dto.TronResponseDTO;
import com.gmc.gmccoin.api.tron.repository.TronRepo;
import com.gmc.gmccoin.api.util.TronHex;
import com.gmc.gmccoin.common.common.exception.ApiException;
import com.gmc.gmccoin.common.common.exception.ErrorCode;
import com.gmc.gmccoin.common.model.address.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * UserServiceImpl
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TronServiceImpl implements TronService{

    private final TronRepo tronRepo;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    final private ObjectMapper objectMapper;
    final private TronHex tronHex;

    @Value("${tronapi.url}")
    private String tronapiUrl;

    @Value("${tronapi.assetId}")
    private String assetId;

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    @Override
    public TronResponseDTO getTronAddress(TronDTO tronDTO) {
        Address address = tronRepo.findByEmail(tronDTO.getEmail());
        TronDTO dto;
        if(address == null) {
            HttpEntity<String> req = new HttpEntity<String>(Strings.EMPTY, this.getHeader());
            ResponseEntity<String> res = null;
            try {
                res = restTemplate.exchange(tronapiUrl + "/wallet/generateaddress", HttpMethod.POST, req, String.class);
                //HashMap resultMap = (HashMap) res.getBody();
                JsonNode node = objectMapper.readTree(res.getBody());
                address = Address.builder()
                        .email(tronDTO.getEmail())
                        .address(node.get("address").asText())
                        .hexAddress(node.get("hexAddress").asText())
                        .privateKey(node.get("privateKey").asText())
                        .build();
                dto = this.modelMapper.map(tronRepo.save(address), TronDTO.class);
            } catch (Exception e) {
                throw new ApiException(ErrorCode.INTERNAL_SERVER_ERROR);
            }

        } else {
            dto = this.modelMapper.map(address, TronDTO.class);
        }
        return this.modelMapper.map(dto, TronResponseDTO.class);
    }

    @Override
    public SendDTO sendToken(SendDTO sendDTO) {
        Address address = tronRepo.findByEmail(sendDTO.getEmail());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("privateKey", address.getPrivateKey());
            jsonObject.put("toAddress", tronHex.tronHex(sendDTO.getToAddress()));
            jsonObject.put("assetId", assetId);
            jsonObject.put("amount", (int)(sendDTO.getAmount() * 1000));

            //ApiWrapper wrapper = new ApiWrapper("grpc endpoint", "solidity grpc endpoint", "hex private key");

            HttpEntity<String> req = new HttpEntity<String>(jsonObject.toString(), this.getHeader());
            //ResponseEntity<String> res = null;

            String res = restTemplate.postForObject(tronapiUrl + "/wallet/easytransferassetbyprivate", req, String.class);
            JsonNode node = objectMapper.readTree(res);
            boolean result = false;
            if(node.get("result").get("result") != null) {
                result = node.get("result").get("result").asBoolean();
            }
            sendDTO.setSend(result);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return sendDTO;
    }

    @Override
    public TronResponseDTO getBalance(TronDTO tronDTO) {
        Address address = tronRepo.findByEmail(tronDTO.getEmail());
        if(address == null) {
            HttpEntity<String> req = new HttpEntity<String>(Strings.EMPTY, this.getHeader());
            ResponseEntity<String> res = null;
            try {
                res = restTemplate.exchange(tronapiUrl + "/wallet/generateaddress", HttpMethod.POST, req, String.class);
                JsonNode node = objectMapper.readTree(res.getBody());
                address = Address.builder()
                        .email(tronDTO.getEmail())
                        .address(node.get("address").asText())
                        .hexAddress(node.get("hexAddress").asText())
                        .privateKey(node.get("privateKey").asText())
                        .build();
                tronRepo.save(address);
            } catch (Exception e) {
                throw new ApiException(ErrorCode.INTERNAL_SERVER_ERROR);
            }

        }
        TronResponseDTO responseDTO;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("address", tronHex.tronHex(address.getAddress()));

            HttpEntity<String> req = new HttpEntity<String>(jsonObject.toString(), this.getHeader());

            String res = restTemplate.postForObject(tronapiUrl + "/wallet/getaccount", req, String.class);
            JsonNode node = objectMapper.readTree(res);


            if(node.get("balance") != null) {
                responseDTO = TronResponseDTO.builder().tronBalance(new BigDecimal(node.get("balance").asLong()).divide(new BigDecimal(1000000)).toPlainString()).build();
                responseDTO.setTronDollar(new BigDecimal(node.get("balance").asLong()).divide(new BigDecimal(1000000)).multiply(new BigDecimal(0.06)).setScale(3, RoundingMode.HALF_EVEN).toPlainString());
                responseDTO.setTronKrw(new BigDecimal(node.get("balance").asLong()).divide(new BigDecimal(1000000)).multiply(new BigDecimal(70)).setScale(1, RoundingMode.HALF_EVEN).toPlainString());
            } else {
                responseDTO = TronResponseDTO.builder().tronBalance("0").build();
                responseDTO.setTronDollar("0");
                responseDTO.setTronKrw("0");
            }

            if(node.get("assetV2") != null){
                for (JsonNode asset : node.get("assetV2")) {
                    if (asset.get("key").asText().equals("1000868")) {
                        responseDTO.setGmcBalance(new BigDecimal(asset.get("value").asLong()).divide(new BigDecimal(1000)).toPlainString());
                        responseDTO.setGmcDollar(new BigDecimal(asset.get("value").asLong()).divide(new BigDecimal(1000)).multiply(new BigDecimal(0.05)).setScale(3, RoundingMode.HALF_EVEN).toPlainString());
                        responseDTO.setGmcKrw(new BigDecimal(asset.get("value").asLong()).divide(new BigDecimal(1000)).multiply(new BigDecimal(60)).setScale(1, RoundingMode.HALF_EVEN).toPlainString());
                    }
                }
            } else {
                responseDTO.setGmcBalance("0");
                responseDTO.setGmcDollar("0");
                responseDTO.setGmcKrw("0");
            }
            responseDTO.setEmail(address.getEmail());
            responseDTO.setAddress(address.getAddress());
        } catch (Exception e) {
            throw new ApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return responseDTO;
    }

    @Override
    public SendDTO sendTransaction(SendDTO sendDTO) {
        Address address = tronRepo.findByEmail(sendDTO.getEmail());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("privateKey", address.getPrivateKey());
            jsonObject.put("toAddress", tronHex.tronHex(sendDTO.getToAddress()));
            jsonObject.put("amount", (int)(sendDTO.getAmount() * 1000000));

            //ApiWrapper wrapper = new ApiWrapper("grpc endpoint", "solidity grpc endpoint", "hex private key");

            HttpEntity<String> req = new HttpEntity<String>(jsonObject.toString(), this.getHeader());
            //ResponseEntity<String> res = null;

            String res = restTemplate.postForObject(tronapiUrl + "/wallet/easytransferbyprivate", req, String.class);
            JsonNode node = objectMapper.readTree(res);
            boolean result = false;
            if(node.get("result").get("result") != null) {
                result = node.get("result").get("result").asBoolean();
            }
            sendDTO.setSend(result);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return sendDTO;
    }
}
