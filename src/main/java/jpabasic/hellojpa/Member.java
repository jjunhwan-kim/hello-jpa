package jpabasic.hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "USER") // 테이블 이름이 클래스 이름과 다를경우

@SequenceGenerator(
        name="member_seq_generator",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 50)
/*
@TableGenerator(
        name="member_seq_generator",
        table = "my_sequences",
        pkColumnValue = "member_seq", allocationSize = 1)
*/
public class Member {

    // 직접 할당
    @Id
    // 자동 생성(identity, sequence, table, auto)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name", nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member() {
    }
}
