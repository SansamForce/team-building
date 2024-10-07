package com.sansam.project.query.controller;


import com.sansam.project.common.response.ApiResponse;
import com.sansam.project.query.dto.AdminProjectMemberAllQueryDTO;
import com.sansam.project.query.dto.ProjectAllQueryDTO;
import com.sansam.project.query.dto.ProjectQueryDTO;
import com.sansam.project.query.service.ProjectMemberQueryService;
import com.sansam.project.query.service.ProjectQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@Tag(name = "2-5. Project Member API", description = "프로젝트 회원 API")
public class ProjectMemberQueryController {

    private final ProjectQueryService projectQueryService;
    private final ProjectMemberQueryService projectMemberQueryService;
    /**
     * 프로젝트 전체 조회 (사용자용)
     */
    @GetMapping
    @Operation(summary = "회원 별 프로젝트 전체 조회", description = "해당 사용자가 들어간 프로젝트 전체 조회")
    public ApiResponse<List<ProjectAllQueryDTO>> getAllProjectsForUser() {
        List<ProjectAllQueryDTO> projects = projectQueryService.getAllProjectsForUser(null);
        return ApiResponse.ofSuccess(projects);
    }

    /**
     * 프로젝트 상세 조회 (사용자용)
     */
    @GetMapping("/{projectSeq}")
    @Operation(summary = "회원 별 프로젝트 상세 조회", description = "해당 사용자가 들어간 프로젝트 상세 조회")
    public ApiResponse<ProjectQueryDTO> getProjectByIdForUser(@PathVariable Long projectSeq) {
        ProjectQueryDTO project = projectQueryService.getProjectByIdForUser(projectSeq);
        return ApiResponse.ofSuccess(project);
    }

    @GetMapping("/{projectSeq}/member")
    @Operation(summary = "프로젝트 별 멤버 리스트 조회", description = "프로젝트 별 멤버 리스트 조회")
    public ApiResponse<List<AdminProjectMemberAllQueryDTO>> getProjectMemberByProjectSeq(@PathVariable Long projectSeq) {
        List<AdminProjectMemberAllQueryDTO> projectMemberList = projectMemberQueryService.findProjectMembers(projectSeq);
        return ApiResponse.ofSuccess(projectMemberList);
    }
}