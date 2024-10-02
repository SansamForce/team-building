package sansam.team.common.websocket.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Document(collection = "chatMember")
public class TeamChatMemberDTO {

    private Long teamChatSeq;
    private Long teamMemberSeq;
    private String teamMemberNickname;
}
