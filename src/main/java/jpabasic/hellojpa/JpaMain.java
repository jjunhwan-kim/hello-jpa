package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
/*
            // Create
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);
*/
/*
            // Read
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
*/
/*
            // Delete
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
*/
/*
            // Update
            Member findMember = em.find(Member.class, 2L);
            findMember.setName("HelloJPA");
*/
/*
            // JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
*/
/*
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member); // 비영속->영속, 1차 캐시에 저장
            //em.detach(member); // 영속->비영속
            System.out.println("=== AFTER ===");

            Member findMember = em.find(Member.class, 101L);  // 1차 캐시에서 조회

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
*/
/*
            // DB에서 조회하여 1차 캐시에 저장
            Member findMember1 = em.find(Member.class, 101L);
            // 1차 캐시에서 조회, 따라서 SQL Select 쿼리는 1번만 출력됨
            Member findMember2 = em.find(Member.class, 101L);
            // 영속 엔티티의 동일성 보장
            System.out.println("result = " + (findMember1 == findMember2));
*/
/*
            Member member1 = new Member(151L, "A");
            Member member2 = new Member(161L, "B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("====================");
*/
/*
            // 엔티티 수정, 변경 감지
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
*/
/*
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush(); // flush 호출시 쿼리 출력

            System.out.println("====================");
*/
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            //em.detach(member);  // 영속성 컨텍스트에서 빠지므로, 변경내용이 DB에 반영안됨
            em.clear();

            Member member2 = em.find(Member.class, 150L);

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
