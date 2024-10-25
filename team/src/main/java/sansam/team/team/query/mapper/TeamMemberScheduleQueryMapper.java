package sansam.team.team.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import sansam.team.team.query.dto.TeamMemberScheduleQueryDTO;

import java.util.List;

@Mapper
public interface TeamMemberScheduleQueryMapper {
    List<TeamMemberScheduleQueryDTO> selectTeamMemberScheduleListByTeamSeq(long teamSeq);
}
