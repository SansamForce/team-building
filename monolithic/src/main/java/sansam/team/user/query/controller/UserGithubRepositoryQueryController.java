package sansam.team.user.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sansam.team.user.query.dto.UserGithubRepositoryQueryDTO;
import sansam.team.user.query.service.UserGithubRepositoryQueryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/query/user/GithubRepository")
@RequiredArgsConstructor
@Tag(name = "1-2. User GitHub Repository API", description = "유저 깃허브 레포지토리 API")
public class UserGithubRepositoryQueryController {

    private final UserGithubRepositoryQueryService userGithubRepositoryQueryService;

    @GetMapping("/{userSeq}")
    @Operation(summary = "자신의 깃허브 레포지토리 전체 조회")
    public List<UserGithubRepositoryQueryDTO> getAllGithubRepo(@PathVariable Long userSeq) {
        return userGithubRepositoryQueryService.getAllGithubRepo(userSeq);
    }
}
