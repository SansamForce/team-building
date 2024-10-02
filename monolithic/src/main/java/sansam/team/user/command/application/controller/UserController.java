package sansam.team.user.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sansam.team.common.response.ApiResponse;
import sansam.team.common.response.ResponseUtil;
import sansam.team.project.command.domain.aggregate.entity.Project;
import sansam.team.user.command.application.dto.*;
import sansam.team.user.command.application.service.UserService;
import sansam.team.user.command.domain.aggregate.entity.User;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "1. User API", description = "회원 API")
public class UserController {

    private final UserService userService;

    @PostMapping(value = {"/join"})
    @Operation(summary = "회원가입", description = "회원가입 API")
    public ResponseEntity<String> joinProcess(@RequestBody UserDTO.UserJoinDTO userJoinDTO) {
        boolean isJoinMember = userService.joinProcess(userJoinDTO);
        return ResponseEntity.status(isJoinMember ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                .body(isJoinMember ? "Join successful" : "Error during registration");
    }

    @PutMapping("/{userSeq}")
    @Operation(summary = "회원 수정 (마이페이지)", description = "회원 수정 API")
    public ApiResponse<?> updateMyPage(@PathVariable Long userSeq, @RequestBody UserUpdateRequestDTO request) {

        try {
            // Project 생성 요청
            User updateUser = userService.updateUser(userSeq, request);

            // 성공 응답 반환
            return ResponseUtil.successResponse("User updated successfully").getBody();
        } catch (IllegalArgumentException e) {
            // 실패 응답 반환 (예외 발생 시)
            return ResponseUtil.failureResponse(e.getMessage(), "USER_SEQ_NULL").getBody();
        } catch (Exception e) {
            // 그 외 예외 처리
            return ResponseUtil.exceptionResponse(e, "USER_UPDATE_ERROR").getBody();
        }
    }

}
