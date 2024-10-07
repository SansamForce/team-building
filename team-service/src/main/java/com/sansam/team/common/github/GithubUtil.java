package com.sansam.team.common.github;

import com.sansam.team.command.infrastructure.dto.UserGithubRepositoryDTO;
import com.sansam.team.common.aggregate.DevelopType;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GithubUtil {

    private final GitHub github;

    public GithubUtil(@Value("${github.token}") String token) throws IOException {
        this.github = new GitHubBuilder().withOAuthToken(token).build();
        this.github.checkApiUrlValidity();
    }

    // 사용자의 레포지토리 URL로 백엔드, 프론트엔드 커밋 개수를 분석
    public Map<DevelopType, Integer> analyzeCommitCountByDevelopType(List<UserGithubRepositoryDTO> repositories, String userGithubId) throws IOException {
        Map<DevelopType, Integer> commitCountsByInterest = new HashMap<>();

        for (UserGithubRepositoryDTO repo : repositories) {
            String repoUrl = repo.getUserRepositoryUrl();
            DevelopType developType = repo.getDevelopType();

            try {
                //이름 추출
                String repoFullName = extractRepoFullNameFromUrl(repoUrl);

                if (repoFullName != null) {
                    int commitCount = getCommitCountByInterestType(repoFullName, userGithubId, developType);
                    commitCountsByInterest.put(developType, commitCountsByInterest.getOrDefault(developType, 0) + commitCount);

                } else {
                    log.warn("유효하지 않은 레포지토리 URL: {}", repoUrl);
                }
            } catch (Exception e) {
                log.error("리포지토리 {}에서 커밋 이력을 분석하는 중 오류 발생: {}", repoUrl, e.getMessage());
            }
        }
        log.info("커밋정보 "+ commitCountsByInterest);
        return commitCountsByInterest;
    }

    private int getCommitCountByInterestType(String repoFullName, String userGithubId, DevelopType interestType) throws IOException {
        int commitCount = 0;

        GHRepository repository = github.getRepository(repoFullName);

        // 포크된 리포지토리 제외
        if (repository.isFork()) return commitCount;

        try {
            PagedIterable<GHCommit> commits = repository.queryCommits().author(userGithubId).list();

            // 커밋 개수 카운팅
            for (GHCommit commit : commits) {
                commitCount++;
            }
        } catch (GHException e) {
            log.error("리포지토리 {}에서 커밋을 가져오는 중 오류 발생: {}", repoFullName, e.getMessage());
        }

        return commitCount;
    }

    // URL에서 레포지토리 이름 추출
    private String extractRepoFullNameFromUrl(String repoUrl) {
        if (repoUrl != null && repoUrl.startsWith("https://github.com/")) {
            String[] parts = repoUrl.split("/");
            if (parts.length >= 5) {
                return parts[3] + "/" + parts[4];
            }
        }
        return null;
    }
}
