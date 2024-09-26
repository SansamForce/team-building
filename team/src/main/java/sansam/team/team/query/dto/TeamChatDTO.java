package sansam.team.team.query.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamChatDTO {
    private long teamChatSeq;
    private long teamSeq;
    private String teamChatName;
    private String teamChatComment;

    private String regDate;
}
