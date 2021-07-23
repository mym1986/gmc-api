package com.gmc.gmccoin.api.tron.controller;

import com.gmc.gmccoin.api.tron.dto.SendDTO;
import com.gmc.gmccoin.api.tron.dto.TronDTO;
import com.gmc.gmccoin.api.tron.service.TronService;
import com.gmc.gmccoin.common.dto.commons.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * TronController
 */
@Api(tags = {"Tron API"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/tron")
public class TronController {
    private final TronService tronService;

    @ApiOperation(value="지갑생성", response = TronDTO.class)
    @PostMapping("/createWallet")
    public Response createWallet(@RequestBody @Valid TronDTO tronDTO) {
        return Response.builder()
                .data(tronService.getTronAddress(tronDTO))
                .build();
    }

    @ApiOperation(value="토큰전송", response = SendDTO.class)
    @PostMapping("/sendToken")
    public Response sendToken(@RequestBody @Valid SendDTO sendDTO) {
        return Response.builder()
                .data(tronService.sendToken(sendDTO))
                .build();
    }

    @ApiOperation(value="잔액조회", response = SendDTO.class)
    @PostMapping("/getBalance")
    public Response getBalance(@RequestBody @Valid TronDTO tronDTO) {
        return Response.builder()
                .data(tronService.getBalance(tronDTO))
                .build();
    }

    @ApiOperation(value="트론전송", response = SendDTO.class)
    @PostMapping("/sendTransaction")
    public Response sendTransaction(@RequestBody @Valid SendDTO sendDTO) {
        return Response.builder()
                .data(tronService.sendTransaction(sendDTO))
                .build();
    }
}
