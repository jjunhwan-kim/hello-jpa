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
