package com.sansam.project.command.domain.aggregate.entity;


import com.sansam.project.command.application.dto.AdminProjectMemberUpdateDTO;
import com.sansam.project.common.aggregate.DevelopType;
import com.sansam.project.common.aggregate.YnType;
import com.sansam.project.common.aggregate.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_project_member")
@Getter
@NoArgsConstructor
public class ProjectMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectMemberSeq;

    @Enumerated(EnumType.STRING)
    private YnType projectMemberDelYn = YnType.N;

    @Enumerated(EnumType.STRING)
    private YnType projectMentorYn = YnType.N;

    private Long userSeq;

    private Long projectSeq;

    @Enumerated(value = EnumType.STRING)
    private YnType projectMemberMajorYn;

    @Enumerated(value = EnumType.STRING)
    private DevelopType projectMemberDevelopType;

    private Long projectMemberCommitScore;

    public ProjectMember(Long userSeq, Long projectSeq) {
        this.userSeq = userSeq;
        this.projectSeq = projectSeq;
    }

    public static ProjectMember createEntity(Long userSeq, Long projectSeq ) {
        return new ProjectMember(userSeq, projectSeq);
    }

    public void modifyProjectMember(AdminProjectMemberUpdateDTO updateDTO) {
        if (updateDTO.getProjectMemberDelYn() != null) {
            this.projectMemberDelYn = updateDTO.getProjectMemberDelYn();
        }
        if (updateDTO.getProjectMentorYn() != null) {
            this.projectMentorYn = updateDTO.getProjectMentorYn();
        }
        if(updateDTO.getProjectInterest() != null) {
            this.projectMemberDevelopType = updateDTO.getProjectInterest();
        }
        if(updateDTO.getMajorYn() != null) {
            this.projectMemberMajorYn = updateDTO.getMajorYn();
        }
        if(updateDTO.getProjectMemberCommitScore()!= null) {
            this.projectMemberCommitScore = updateDTO.getProjectMemberCommitScore();
        }
    }
}