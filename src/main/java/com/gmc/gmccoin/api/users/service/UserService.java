package com.gmc.gmccoin.api.users.service;

import com.gmc.gmccoin.api.users.dto.SignDTO;
import com.gmc.gmccoin.common.dto.users.UserDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    /**
     * 회원 가입
     * @param signDTO
     * @return
     */
    UserDTO signUp(SignDTO signDTO);

    /**
     * 로그인
     * @param signDTO
     * @return
     */
    UserDTO signIn(SignDTO signDTO, HttpServletResponse response);

    /**
     * 비밀번호 변경
     * @param signDTO
     * @return
     */
    UserDTO changePassword(SignDTO signDTO, HttpServletResponse response);

    /**
     * 유저 조회
     * @param signDTO
     * @return
     */
    UserDTO getUser(SignDTO signDTO);

    /**
     * 유저 수정
     * @param signDTO
     * @return
     */
    UserDTO updateUser(SignDTO signDTO);


    List<UserDTO> getRecommender(SignDTO signDTO);
}
