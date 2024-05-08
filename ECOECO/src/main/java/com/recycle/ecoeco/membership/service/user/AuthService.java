package com.recycle.ecoeco.membership.service.user;

import com.recycle.ecoeco.membership.model.dao.user.MypageMapper;
import com.recycle.ecoeco.membership.model.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService implements UserDetailsService {

    private final MypageMapper mypageMapper;
    private final MyPageService myPageService;


    public AuthService(MypageMapper mypageMapper, MyPageService myPageService) {
        this.mypageMapper = mypageMapper;
        this.myPageService = myPageService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info("userId : {}", userId);

        UserInfoDTO user = mypageMapper.findByUserId(userId);

        log.info("user : {}", user);

        System.out.println("userId : {}" +  userId);
        System.out.println("user : {}" +  user);

        if (user == null) throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");

        return user;
    }
}
