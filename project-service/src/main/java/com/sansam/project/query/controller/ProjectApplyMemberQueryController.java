package com.sansam.project.query.controller;


import com.sansam.project.common.response.ApiResponse;
import com.sansam.project.query.dto.ApplyMemberQueryDTO;
import com.sansam.project.query.service.ProjectApplyMemberQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project/board/apply")
@RequiredArgsConstructor
@Tag(name = "2-2. Project Board Apply Member API", description = "프로젝트 게시물 신청 회원 API")
public class ProjectApplyMemberQueryController {

    private final ProjectApplyMemberQueryService projectApplyMemberQueryService;

    // 특정 회원이 신청한 프로젝트 목록 전체 조회 API
    @GetMapping()
    @Operation(summary = "프로젝트 신청 리스트 조회", description = "해당 회원이 신청한 프로젝트 리스트 조회")
    public ApiResponse<List<ApplyMemberQueryDTO>> getMyProjects() {
        List<ApplyMemberQueryDTO> projects = projectApplyMemberQueryService.findProjectsByCurrentUser();
        return ApiResponse.ofSuccess(projects);
    }
}