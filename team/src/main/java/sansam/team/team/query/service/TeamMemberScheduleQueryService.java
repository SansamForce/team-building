package sansam.team.team.query.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sansam.team.team.query.dto.TeamMemberScheduleQueryDTO;
import sansam.team.team.query.mapper.TeamMemberScheduleQueryMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamMemberScheduleQueryService {

    private final TeamMemberScheduleQueryMapper teamMemberScheduleQueryMapper;

    public List<TeamMemberScheduleQueryDTO> selectTeamMemberScheduleListByTeamSeq(long teamSeq) {
        return teamMemberScheduleQueryMapper.selectTeamMemberScheduleListByTeamSeq(teamSeq);
    }

}
