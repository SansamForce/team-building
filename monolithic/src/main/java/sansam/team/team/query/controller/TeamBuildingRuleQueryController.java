package sansam.team.team.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sansam.team.team.query.dto.TeamBuildingRuleQueryDTO;
import sansam.team.team.query.service.TeamBuildingRuleQueryService;
import sansam.team.common.response.ResponseUtil;
import sansam.team.common.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team/building-rule")
@RequiredArgsConstructor
@Tag(name = "3-1. Building rule API", description = "팀빌딩 규칙")
public class TeamBuildingRuleQueryController {

    private final TeamBuildingRuleQueryService buildingRuleQueryService;

    @GetMapping
    @Operation(summary = "팀빌딩 규칙 전체 조회", description = "팀 빌딩 규칙 가중치에 대한 전체 조회")
    public ResponseEntity<ApiResponse<List<TeamBuildingRuleQueryDTO>>> getAllBuildingRules() {
        List<TeamBuildingRuleQueryDTO> buildingRules = buildingRuleQueryService.findAll();
        return ResponseUtil.successResponse("All building rules retrieved successfully", buildingRules);
    }

    @GetMapping("/{ruleSeq}")
    @Operation(summary = "특정 팀빌딩 규칙 조회", description = "특정 팀 빌딩 규칙 가중치에 대한 조회")
    public ResponseEntity<ApiResponse<TeamBuildingRuleQueryDTO>> getBuildingRule(@PathVariable long ruleSeq) {
        TeamBuildingRuleQueryDTO buildingRule = buildingRuleQueryService.findById(ruleSeq);
        return ResponseUtil.successResponse("Building rule retrieved successfully", buildingRule);
    }
}
