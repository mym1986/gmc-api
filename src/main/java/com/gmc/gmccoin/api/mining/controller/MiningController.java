package com.gmc.gmccoin.api.mining.controller;

import com.gmc.gmccoin.api.mining.dto.MiningDTO;
import com.gmc.gmccoin.api.mining.service.MiningService;
import com.gmc.gmccoin.common.dto.commons.Response;
import com.gmc.gmccoin.common.dto.users.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * MiningController
 */
@Api(tags = {"채굴 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/mining")
public class MiningController {

    private final MiningService miningService;

    @ApiOperation(value="채굴상태 반영", response = UserDTO.class)
    @PostMapping("/updateMiningStatus")
    public Response updateMiningStatus(@RequestBody @Valid MiningDTO miningDTO) {
        return Response.builder()
                .data(miningService.updateMiningStatus(miningDTO))
                .build();
    }

    @ApiOperation(value="채굴상태 조회", response = UserDTO.class)
    @PostMapping("/getMiningStatus")
    public Response getMiningStatus(@RequestBody @Valid MiningDTO miningDTO) {
        return Response.builder()
                .data(miningService.getMiningStatus(miningDTO))
                .build();
    }

    @ApiOperation(value="채굴 top 100 조회", response = UserDTO.class)
    @PostMapping("/getMiningTop100")
    public Response getMiningTop100() {
        return Response.builder()
                .data(miningService.getMiningTop100())
                .build();
    }
}
