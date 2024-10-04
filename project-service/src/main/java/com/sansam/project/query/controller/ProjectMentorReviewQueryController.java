package com.sansam.project.query.controller;


import com.sansam.project.common.response.ApiResponse;
import com.sansam.project.query.dto.*;
import com.sansam.project.query.service.ProjectMentorReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "2-6. Project Mentor Review API", description = "프로젝트 강사 평가 API")
public class ProjectMentorReviewQueryController {

    private final ProjectMentorReviewQueryService projectMentorReviewQueryService;

    /* 강사 - 프로젝트 내 회원 평가한 내용 전체 조회 */
    @GetMapping("/project/{projectSeq}/mentor/review")
    @Operation(summary = "프로젝트 별 강사 평가 전체 조회 API", description = "강사가 프로젝트 내 회원 평가한 모든 내용을 조회합니다.")
    public ApiResponse<List<ProjectMentorReviewAllQueryDTO>> getAllProjectMentorReviews() {
        List<ProjectMentorReviewAllQueryDTO> reviews = projectMentorReviewQueryService.getAllProjectMentorReview();
        return ApiResponse.ofSuccess(reviews);
    }

    /* 강사 - 프로젝트 내 회원 평가한 내용 상세 조회 */
    @GetMapping("/project/{projectSeq}/mentor/review/{mentorReviewSeq}")
    @Operation(summary = "프로젝트 별 강사 평가 상세 조회 API", description = "강사가 프로젝트 내 특정 회원을 평가한 내용을 조회합니다.")
    public ApiResponse<ProjectMentorReviewQueryDTO> getProjectMentorReviewByIdForMentor(@PathVariable Long mentorReviewSeq) {
        ProjectMentorReviewQueryDTO review = projectMentorReviewQueryService.getProjectMentorReviewByIdForMentor(mentorReviewSeq);
        return ApiResponse.ofSuccess(review);
    }

    @GetMapping("/project/mentor/review/{projectMemberSeq}")
    public ApiResponse<List<ProjectMentorReviewDTO>> findMentorReviewByProjectMemberSeq(@PathVariable Long projectMemberSeq) {
        List<ProjectMentorReviewDTO> review = projectMentorReviewQueryService.getProjectMentorReviewByProjectMember(projectMemberSeq);
        return ApiResponse.ofSuccess(review);
    }

    /* 회원 - 프로젝트 내 강사 평가 전체 조회 */
    @GetMapping("/user/{userSeq}/mentor/review")
    @Operation(summary = "프로젝트 별 회원의 강사 평가 전체 조회 API", description = "회원이 프로젝트 내 자신이 받은 모든 강사의 평가를 전체 조회합니다.")
    public ApiResponse<List<ProjectMentorReviewAllUserQueryDTO>> getProjectMentorReviewAllByIdForUser() {
        List<ProjectMentorReviewAllUserQueryDTO> reviews = projectMentorReviewQueryService.getProjectMentorReviewAllByIdForUser();
        return ApiResponse.ofSuccess(reviews);
    }

    /* 회원 - 프로젝트 내 강사 평가 상세 조회 */
    @GetMapping("/user/{userSeq}/mentor/review/{mentorReviewSeq}")
    @Operation(summary = "프로젝트 별 회원의 강사 평가 조회 API", description = "회원이 프로젝트 내 자신이 받은 강사의 평가를 상세 조회합니다.")
    public ApiResponse<ProjectMentorReviewUserQueryDTO> getProjectMentorReviewByIdForUser(@PathVariable Long mentorReviewSeq) {
        ProjectMentorReviewUserQueryDTO review = projectMentorReviewQueryService.getProjectMentorReviewByIdForUser(mentorReviewSeq);
        return ApiResponse.ofSuccess(review);
    }
}