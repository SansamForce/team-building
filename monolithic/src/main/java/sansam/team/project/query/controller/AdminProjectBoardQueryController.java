package sansam.team.project.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sansam.team.project.query.dto.ProjectApplyMemberQueryDTO;
import sansam.team.project.query.dto.ProjectBoardAllQueryDTO;
import sansam.team.project.query.dto.AdminProjectBoardDTO;
import sansam.team.project.query.service.ProjectBoardQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/project/board")
@RequiredArgsConstructor
@Tag(name = "2-1. Project Board Admin API", description = "프로젝트 게시물 관리자 API")
public class AdminProjectBoardQueryController {

    private final ProjectBoardQueryService projectBoardQueryService;

    /* 프로젝트 게시물 전체 조회 */
    @GetMapping
    @Operation(summary = "프로젝트 게시물 전체 조회", description = "프로젝트 게시물 전체 조회 API (관리자만 가능)")
    public List<ProjectBoardAllQueryDTO> getAllProjectBoards() {
        return projectBoardQueryService.getAllProjectBoards(); // ResponseEntity 생략하고 DTO 반환
    }

    /* 프로젝트 게시물 상세 조회 */
    @GetMapping("/{projectBoardSeq}")
    @Operation(summary = "프로젝트 게시물 상세 조회", description = "프로젝트 게시물 상세 조회 API (관리자만 가능)")
    public AdminProjectBoardDTO getProjectBoardById(@PathVariable Long projectBoardSeq) {
        return projectBoardQueryService.getProjectBoardByIdForAdmin(projectBoardSeq); // ResponseEntity 생략하고 DTO 반환
    }

    /* 프로젝트 신청 회원 리스트 조회 */
    @GetMapping("/{projectBoardSeq}/apply-member")
    @Operation(summary = "프로젝트 게시물 신청 회원 리스트 조회", description = "프로젝트 게시물 신청 회원 리스트 조회 API (관리자만 가능)")
    public List<ProjectApplyMemberQueryDTO> getApplyMembers(
            @PathVariable Long projectBoardSeq) {
        return projectBoardQueryService.findApplyMembersByProjectBoardSeq(projectBoardSeq); // ResponseEntity 생략하고 DTO 반환
    }
}
