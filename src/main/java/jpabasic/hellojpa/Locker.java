package jpabasic.hellojpa;

import javax.persistence.*;

@Entity
public class Locker {
    @Id @GeneratedValue
    Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
