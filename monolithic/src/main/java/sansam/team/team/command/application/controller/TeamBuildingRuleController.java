package sansam.team.team.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sansam.team.team.command.application.dto.TeamBuildingRuleDTO;
import sansam.team.team.command.application.service.TeamBuildingRuleService;
import sansam.team.team.command.domain.aggregate.entity.TeamBuildingRule;
import sansam.team.common.response.ResponseUtil;
import sansam.team.common.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/team/building-rule")
@RequiredArgsConstructor
@Tag(name = "3-1. Building rule API", description = "팀빌딩 규칙")
public class TeamBuildingRuleController {

    private final TeamBuildingRuleService buildingRuleService;

    @PostMapping
    @Operation(summary = "팀빌딩 규칙 생성", description = "팀 빌딩 규칙 가중치 생성 API")
    public ResponseEntity<ApiResponse<TeamBuildingRule>> createBuildingRule(@RequestBody TeamBuildingRuleDTO buildingRuleDTO) {
        TeamBuildingRule createdBuildingRule = buildingRuleService.createBuildingRule(buildingRuleDTO);
        return ResponseUtil.successResponse("Building rule created successfully", createdBuildingRule);
    }

    @PutMapping("/{ruleSeq}")
    @Operation(summary = "팀빌딩 규칙 수정", description = "팀 빌딩 규칙 가중치 수정 API")
    public ResponseEntity<ApiResponse<TeamBuildingRule>> updateBuildingRule(
            @PathVariable int ruleSeq,
            @RequestBody TeamBuildingRuleDTO buildingRuleDTO) {
        TeamBuildingRule updatedBuildingRule = buildingRuleService.updateBuildingRule(ruleSeq, buildingRuleDTO);
        return ResponseUtil.successResponse("Building rule updated successfully", updatedBuildingRule);
    }

    @DeleteMapping("/{ruleSeq}")
    @Operation(summary = "팀빌딩 규칙 삭제", description = "팀 빌딩 규칙 가중치 삭제 API")
    public ResponseEntity<ApiResponse<Void>> deleteBuildingRule(@PathVariable int ruleSeq) {
        buildingRuleService.deleteBuildingRule(ruleSeq);
        return ResponseUtil.successResponse("Building rule deleted successfully", null);
    }
}
