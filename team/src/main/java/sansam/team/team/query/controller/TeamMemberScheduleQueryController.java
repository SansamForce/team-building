package sansam.team.team.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sansam.team.common.response.ApiResponse;
import sansam.team.team.query.dto.TeamMemberScheduleQueryDTO;
import sansam.team.team.query.service.TeamMemberScheduleQueryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/team/{teamSeq}/memberSchedule")
@RequiredArgsConstructor
@Tag(name = "3-4-2. TeamMember Schedule API", description = "회원 별 진행상황 API")
public class TeamMemberScheduleQueryController {

    private final TeamMemberScheduleQueryService teamMemberScheduleQueryService;

    @GetMapping
    @Operation(summary = "팀원 일정 조회", description = "팀원 일정 API")
    public ApiResponse<List<TeamMemberScheduleQueryDTO>> selectTeamMemberScheduleListByTeamSeq(@PathVariable long teamSeq) {
        List<TeamMemberScheduleQueryDTO> memberScheduleList = teamMemberScheduleQueryService.selectTeamMemberScheduleListByTeamSeq(teamSeq);
        return ApiResponse.ofSuccess("Team member schedule list retrieved successfully", memberScheduleList);
    }

}
