package sansam.team.team.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sansam.team.team.query.dto.TeamChatDTO;
import sansam.team.team.query.service.TeamChatQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team/chat")
@RequiredArgsConstructor
@Tag(name = "Team Chatting Query API", description = "팀 채팅방 조회 API")
public class TeamChatQueryController {

    private final TeamChatQueryService teamChatQueryService;

    @GetMapping
    @Operation(summary = "팀 채팅방 리스트 조회")
    public ResponseEntity<List<TeamChatDTO>> selectChatRoomList() {
        List<TeamChatDTO> teamChatList = teamChatQueryService.selectChatRoomList();
        return ResponseEntity.ok(teamChatList);
    }

    @GetMapping("/{teamChatSeq}")
    @Operation(summary = "팀 채팅방 입장")
    public String enterChatRoom(@PathVariable Long teamChatSeq, Model model) {
        TeamChatDTO teamChatDTO = teamChatQueryService.selectChatRoom(teamChatSeq);
        model.addAttribute("teamChatDTO", teamChatDTO);

        return "redirect:ws://localhost:8086/ws/chat?postId=" + teamChatSeq;
        // return "chat";
    }
}
