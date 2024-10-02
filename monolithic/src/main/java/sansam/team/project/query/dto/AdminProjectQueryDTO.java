package sansam.team.project.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sansam.team.project.command.domain.aggregate.ProjectStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminProjectQueryDTO {

    private Long projectSeq;
    private String projectTitle;
    private String projectContent;
    private ProjectStatus projectStatus;
    private int projectHeadCount;
    private String projectImgUrl;
    private LocalDateTime projectStartDate;
    private LocalDateTime projectEndDate;
    private String projectAdminName;
}
