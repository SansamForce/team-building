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
import sansam.team.team.query.dto.TeamFindByIdResponse;
import sansam.team.team.query.dto.TeamResponse;
import sansam.team.team.query.service.TeamQueryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
@Tag(name = "3-3. Team API", description = "팀 API")
public class TeamQueryController {

    private final TeamQueryService teamQueryService;

    @GetMapping
    @Operation(summary = "팀 리스트 조회")
    public ApiResponse<List<TeamResponse>> selectTeamList() {
        List<TeamResponse> teamList = teamQueryService.selectTeamList();
        return ApiResponse.ofSuccess("Team list retrieved successfully", teamList);
    }

    @GetMapping("/{teamSeq}")
    @Operation(summary = "팀 조회")
    public ApiResponse<TeamFindByIdResponse> selectTeamById(@PathVariable Long teamSeq) {
        TeamFindByIdResponse team = teamQueryService.selectTeamById(teamSeq);
        return ApiResponse.ofSuccess("Team retrieved successfully", team);
    }
}
