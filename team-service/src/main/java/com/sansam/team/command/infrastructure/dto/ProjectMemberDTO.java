package com.sansam.team.command.infrastructure.dto;

import com.sansam.team.common.aggregate.DevelopType;
import com.sansam.team.common.aggregate.YnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberDTO {
    private Long userSeq;
    private Long projectMemberSeq;
    private DevelopType projectMemberDevelopType;
    private YnType projectMemberMajorYn;
}
