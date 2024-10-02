package sansam.team.project.command.domain.repository;

import sansam.team.project.command.domain.aggregate.entity.ProjectMember;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository {

    ProjectMember save(ProjectMember projectMember);

    Optional<ProjectMember> findById(Long projectMemberSeq);

    List<ProjectMember> findAllByProjectSeq(Long projectSeq);
}
