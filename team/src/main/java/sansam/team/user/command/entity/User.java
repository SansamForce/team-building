package sansam.team.user.command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.security.core.GrantedAuthority;
import sansam.team.user.command.enums.RoleType;
import sansam.team.user.command.enums.StatusType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name="user_id", nullable = false)
    private String id;

    @Column(name="user_name", nullable = false)
    private String name;

    @Column(name="user_nickname", nullable = false)
    private String nickname;

    @Column(name="user_password", nullable = false)
    private String password;

    @Column(name="user_auth", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType auth = RoleType.MEMBER; // 기본값을 직접 지정

    @Column(name="user_phone")
    private String phone;

    @Column(name="user_email")
    private String email;

    @Column(name="user_birth_date")
    private String birthDate;

    @Column(name="user_gender")
    private String gender;

    @Column(name="user_github_id")
    private String githubId;

    @Column(name="user_propile_img")
    private String propileImg;

    @Enumerated(EnumType.STRING)
    @Column(name="user_status")
    private StatusType status;

    @Column(name = "user_pwd_mod_date", insertable = false)
    private LocalDateTime pwdModDate;

    @Column(name = "reg_date", updatable = false)
    private LocalDateTime regDate;

    @Column(name = "mod_date", insertable = false)
    private LocalDateTime modDate;

    @Column(name = "user_ban_date", insertable = false)
    private LocalDateTime banDate;

    @Column(name = "del_date", insertable = false)
    private LocalDateTime delDate;

    @Transient
    private String token;

    public <E> User(String name, String s1, Collection<? extends GrantedAuthority> es) {
        this.name = name;
        es = es;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            public String getAuthority() {
                return getAuth().getRole();
            }
        });

        return collection;
    }

}
