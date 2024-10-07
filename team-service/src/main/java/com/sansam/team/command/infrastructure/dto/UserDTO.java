package com.sansam.team.command.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userSeq;
    private String userGithubId;
    private Long userCareerYears; // 경력 년수
    private Long userCareerMonths; // 경력 월수
}
