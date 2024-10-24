package sansam.team.project.command.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import sansam.team.security.util.SecurityUtil;
import sansam.team.project.command.application.dto.ProjectApplyMemberRequestDTO;
import sansam.team.project.command.domain.aggregate.entity.ProjectApplyMember;
import sansam.team.project.command.domain.repository.ProjectApplyMemberRepository;
import sansam.team.project.command.domain.repository.ProjectBoardRepository;
import sansam.team.project.command.mapper.ProjectApplyMemberMapper;
import sansam.team.user.query.dto.CustomUserDTO;

@Service
@RequiredArgsConstructor
public class ProjectApplyMemberService {

    private final ProjectApplyMemberRepository projectApplyMemberRepository;
    private final ProjectBoardRepository projectBoardRepository;


    @Transactional
    public ProjectApplyMember applyForProject(ProjectApplyMemberRequestDTO applyMemberDTO) {

        // SecurityContext에서 현재 인증된 사용자(User 객체) 추출
        CustomUserDTO user = SecurityUtil.getAuthenticatedUser();

        // 추출한 User의 userSeq가 null이 아닌지 확인
        if(ObjectUtils.isEmpty(user.getUserSeq())){
            throw new IllegalArgumentException("User Seq is null");
        }

        ProjectApplyMember projectApplyMember = ProjectApplyMemberMapper.toEntity(user.getUserSeq(), applyMemberDTO);

        projectApplyMemberRepository.save(projectApplyMember);

        return projectApplyMember;
    }

    @Transactional
    public void cancelApplication(Long projectApplyMemberSeq) {

        // SecurityContext에서 현재 인증된 사용자(User 객체) 추출
        CustomUserDTO user = SecurityUtil.getAuthenticatedUser();

        // 추출한 User의 userSeq가 null이 아닌지 확인
        if(ObjectUtils.isEmpty(user.getUserSeq())){
            throw new IllegalArgumentException("User Seq is null");
        }

        ProjectApplyMember projectApplyMember= projectApplyMemberRepository.findById(projectApplyMemberSeq)
                        .orElseThrow(() -> new IllegalArgumentException("Project apply member not found"));

        projectApplyMemberRepository.deleteById(projectApplyMemberSeq);

    }
}
