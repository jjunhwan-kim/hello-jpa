package jpabasic.hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "USER") // 테이블 이름이 클래스 이름과 다를경우
public class Member {

    @Id
    private Long id;

    // @Column(name = "username") // 데이터 베이스 Column 이름이 변수 이름과 다를 경우
    // @Column(unique = true, nullable = false, length = 10) // 제약조건 추가
    // @Column(name = "name", nullable = false, columnDefinition ="varchar(100) default 'EMPTY'")
    @Column(name ="name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifedDate;

    // LocalDate, LocalDateTime 은 하이버네이트가 알아서 매핑해줌
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob // 문자이면 CLOB 매핑, 나머지는 BLOB 매핑
    private String description;

    @Transient // DB 컬럼 매핑 안하고 메모리에서만 사용 할 경우, DB에 저장 및 조회 안함
    private int temp;

    // JPA Entitiy 는 기본생성자가 있어야 함
    // 엔티티는 반드시 파라미터가 없는 생성자가 있어야 하고, 이는 public 또는 protected 이어야 함
    public Member() {
    }
}
