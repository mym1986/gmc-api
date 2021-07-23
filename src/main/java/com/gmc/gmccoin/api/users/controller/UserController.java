package com.gmc.gmccoin.api.users.controller;

import com.gmc.gmccoin.api.users.dto.SignDTO;
import com.gmc.gmccoin.api.users.service.UserService;
import com.gmc.gmccoin.api.util.SmsUtil;
import com.gmc.gmccoin.common.dto.commons.Response;
import com.gmc.gmccoin.common.dto.users.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * UserController
 */
@Api(tags = {"회원 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SmsUtil smsUtil;

    @ApiOperation(value="회원가입", response = UserDTO.class)
    @PostMapping("/signUp")
    public Response signUp(@RequestBody @Valid SignDTO signDTO) {
        return Response.builder()
                .data(userService.signUp(signDTO))
                .build();
    }

    @ApiOperation(value="로그인", response = UserDTO.class)
    @PostMapping("/signIn")
    public Response signIn(@RequestBody SignDTO signDTO, HttpServletResponse response) {
        return Response.builder()
                .data(userService.signIn(signDTO, response))
                .build();
    }

    @ApiOperation(value="sms전송", response = UserDTO.class)
    @PostMapping("/sendSms")
    public Response sendSms() {
        smsUtil.sendSms();
        return Response.builder()
                .build();
    }

    @ApiOperation(value="비밀번호 변경", response = UserDTO.class)
    @PostMapping("/changePassword")
    public Response changePassword(@RequestBody SignDTO signDTO, HttpServletResponse response) {
        return Response.builder()
                .data(userService.changePassword(signDTO, response))
                .build();
    }

    @ApiOperation(value="getUser", response = UserDTO.class)
    @PostMapping("/getUser")
    public Response getUser(@RequestBody SignDTO signDTO, HttpServletResponse response) {
        return Response.builder()
                .data(userService.getUser(signDTO))
                .build();
    }

    @ApiOperation(value="updateUser", response = UserDTO.class)
    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody SignDTO signDTO, HttpServletResponse response) {
        return Response.builder()
                .data(userService.updateUser(signDTO))
                .build();
    }

    @ApiOperation(value="추천유저 조회", response = UserDTO.class)
    @PostMapping("/getRecommender")
    public Response getMiningTop100(@RequestBody SignDTO signDTO) {
        return Response.builder()
                .data(userService.getRecommender(signDTO))
                .build();
    }

}