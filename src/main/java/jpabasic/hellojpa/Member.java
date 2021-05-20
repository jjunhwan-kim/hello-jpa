package jpabasic.hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "USER") // 테이블 이름이 클래스 이름과 다를경우
public class Member {

    @Id
    private Long id;

//    @Column(name = "username") // 데이터 베이스 Column 이름이 변수 이름과 다를 경우
    private String name;

    // JPA Entitiy 는 기본생성자가 있어야 함
    // 엔티티는 반드시 파라미터가 없는 생성자가 있어야 하고, 이는 public 또는 protected 여야 한다.
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
