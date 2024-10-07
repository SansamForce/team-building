package com.sansam.team.command.infrastructure.dto;

import com.sansam.team.common.aggregate.DevelopType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGithubRepositoryDTO {
    private String userRepositoryUrl;
    private String userRepositoryName;
    private DevelopType developType;
}
