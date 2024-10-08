package sansam.team.user.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sansam.team.common.response.ApiResponse;
import sansam.team.common.response.ResponseUtil;
import sansam.team.user.query.dto.UserInfoResponseDTO;
import sansam.team.user.query.mapper.UserInfoQueryMapper;

@Service
public class UserInfoQueryService {

    private final UserInfoQueryMapper userInfoQueryMapper;

    @Autowired
    public UserInfoQueryService(UserInfoQueryMapper userInfoQueryMapper) {
        this.userInfoQueryMapper = userInfoQueryMapper;
    }

    public UserInfoResponseDTO getUserInfo(Long userSeq) {
        return userInfoQueryMapper.findUserInfoByUserSeq(userSeq);
    }

}
