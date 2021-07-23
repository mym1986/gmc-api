package com.gmc.gmccoin.api.users.service;

import com.gmc.gmccoin.api.mining.repository.MiningRepository;
import com.gmc.gmccoin.api.users.dto.SignDTO;
import com.gmc.gmccoin.api.users.repository.UserRepo;
import com.gmc.gmccoin.api.util.AES256Util;
import com.gmc.gmccoin.common.common.exception.ApiException;
import com.gmc.gmccoin.common.common.exception.ErrorCode;
import com.gmc.gmccoin.common.dto.users.UserDTO;
import com.gmc.gmccoin.common.model.mining.Mining;
import com.gmc.gmccoin.common.model.users.User;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


/**
 * UserServiceImpl
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final AES256Util aes256Util;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final MiningRepository miningRepository;

    @Value("${cookie.maxAge}")
    private String maxAge;

    @Transactional
    @Override
    public UserDTO  signUp(SignDTO signDTO) {

        User u = this.userRepo.findByEmail(signDTO.getEmail());
        if(u != null) {
            throw new ApiException(ErrorCode.NOT_MATCH);
        }

        //STEP2. 저장
        User user = User.builder()
                .email(signDTO.getEmail())
                .password(Hashing.sha256()
                    .hashString(signDTO.getPassword(), StandardCharsets.UTF_8)
                    .toString())
                .phone(signDTO.getPhone())
                .pin(signDTO.getPin())
                .recommender(signDTO.getRecommender())
                .userId(signDTO.getUserId())
                .build();

        miningRepository.save(Mining.builder().email(signDTO.getEmail()).amount(0L).build());

        UserDTO savedUser = this.modelMapper.map(userRepo.save(user), UserDTO.class);

        return savedUser;
    }

    @Override
    public UserDTO signIn(SignDTO signDTO, HttpServletResponse response) {
        // 암호화, 복호화는 decrypt
        //log.info("{}", this.aes256Util.encrypt(signDTO));


        User user = this.userRepo.findByEmail(signDTO.getEmail());

        if(user == null) {
            throw new ApiException("이메일을 입력해 주십시오.", ErrorCode.ID_NOT_FOUND);
        }

        if(user.getPassword().equals(Hashing.sha256().hashString(signDTO.getPassword(), StandardCharsets.UTF_8).toString())){
            UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
            // create cookies
//            Cookie cookie = new Cookie("user", this.aes256Util.encrypt(user.getEmail()));
//            cookie.setMaxAge(Integer.parseInt(maxAge));
//            cookie.setPath("/");
//            response.addCookie(cookie);
            return userDTO;
        }

        throw new ApiException("비밀번호가 일치하지 않습니다.", ErrorCode.PW_NOT_FOUND);

    }

    @Override
    public UserDTO changePassword(SignDTO signDTO, HttpServletResponse response) {
        if(signDTO.getPassword() != null && !signDTO.getPassword().equals(signDTO.getConfirmPassword())){
            throw new ApiException("확인 입력한 패스워드가 틀립니다.",ErrorCode.INTERNAL_SERVER_ERROR);
        }
        User user = this.userRepo.findByEmail(signDTO.getEmail());
        if(!user.getPassword().equals(Hashing.sha256().hashString(signDTO.getOldPassword(), StandardCharsets.UTF_8).toString())){
            return this.modelMapper.map(User.builder().build(), UserDTO.class);
        }
        if(signDTO.getPassword() != null && !signDTO.getPassword().equals("")) {
            user.setPassword(Hashing.sha256().hashString(signDTO.getPassword(), StandardCharsets.UTF_8).toString());
        }
        this.userRepo.save(user);

        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUser(SignDTO signDTO) {
        User user = this.userRepo.findByEmail(signDTO.getEmail());
        if(user != null) {
            return this.modelMapper.map(user, UserDTO.class);
        } else {
            return new UserDTO();
        }

    }

    @Override
    public UserDTO updateUser(SignDTO signDTO) {
        User user = this.userRepo.findByEmail(signDTO.getEmail());
        user.setPhone(signDTO.getPhone());
        user.setPin(signDTO.getPin());
        this.userRepo.save(user);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getRecommender(SignDTO signDTO) {
        User user = this.userRepo.findByEmail(signDTO.getEmail());
        List<User> recommenderList = this.userRepo.findByRecommenderOrderByCreatedAtDesc(user.getUserId());
        return recommenderList.stream().map(recommender -> this.modelMapper.map(recommender, UserDTO.class)).collect(Collectors.toList());
    }


}