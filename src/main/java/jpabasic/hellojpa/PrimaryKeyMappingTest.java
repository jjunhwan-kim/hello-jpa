package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PrimaryKeyMappingTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");


            System.out.println("========");
            // Identity 전략은 DB에 Insert SQL 쿼리를 전송해야 PK가 할당되므로, em.persist() 호출시점에 즉시 SQL 전송하고 DB에서 키 조회
            // 따라서 트랜잭션 커밋할 때 모아서 SQL 전송이 불가능 함

            // Sequence 전략은 트랜잭션 커밋할 때 모아서 SQL 전송 가능함
            // em.persist() 호출시점에 DB 에서 시퀀스 오브젝트의 다음 값을 가져오고, 1차 캐시에 저장
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);


            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            System.out.println("========");

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
