package sansam.team.project.command.application.dto;

import lombok.*;
import sansam.team.project.command.domain.aggregate.BoardStatus;
import sansam.team.project.command.domain.aggregate.entity.ProjectBoard;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminProjectBoardUpdateDTO {

    private String projectBoardTitle;
    private String projectBoardContent;
    private int projectBoardHeadCount;
    private String projectBoardImgUrl;
    private LocalDateTime projectBoardStartDate;
    private LocalDateTime projectBoardEndDate;
    private BoardStatus boardStatus;
    private LocalDateTime projectStartDate;
    private LocalDateTime projectEndDate;

    public AdminProjectBoardUpdateDTO(ProjectBoard projectBoard) {
    }
}