package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceContextTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            // Test 1 - 비영속상태 -> 영속상태
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member); // 비영속->영속, 1차 캐시에 저장, 트랜잭션 커밋할 때 DB에 SQL을 보냄
            //em.detach(member); // 영속->비영속
            System.out.println("=== AFTER ===");

            // 1차 캐시에서 조회, 1차 캐시에 엔티티가 존재하므로 DB에서 조회하지 않음, DB에 Select SQL을 보내지 않음
            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            /*
            // Test 2 - 엔티티 조회
            // DB에서 첫 조회시 1차캐시에 저장하므로 그 이후 부터는 1차캐시에서 조회, DB에 SQL을 1번 보냄
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            // 같은 트랜잭션 안에서 영속 엔티티의 동일성이 보장됨
            System.out.println("result == " + (findMember1 == findMember2));
            */

            /*
            // Test 3 - 엔티티 수정
            // find 후 값을 바꾸기만 하면 수정됨, 트랜잭션 커밋할 때 변경 감지
            Member member = em.find(Member.class, 101L);
            member.setName("ZZZZZ");
            */

            /*
            // Test 4 - 플러시(영속성 컨텍스트의 변경내용을 데이터베이스에 반영)
            // 1. 변경감지(1차 캐시에 엔티티의 스냅샷과 비교)
            // 2. 수정된 엔티티 쓰기지연 SQL 저장소에 등록
            // 3. 쓰기지연 SQL 저장소의 쿼리를 데이터베이스에 전송
            Member member = new Member();
            member.setId(200L);
            em.persist(member);

            em.flush(); // 해당 시점에 SQL 쿼리 전송
            System.out.println("==========");
            */

            /*
            // Test 5 - 준영속 상태
            Member member = em.find(Member.class, 200L);
            member.setName("AAAAA");

            em.detach(member);

            // 준영속 상태
            // 영속성 컨텍스트에서 member가 빠지므로, DB에 수정내용이 반영되지 않음
            // em.detach(member) : member만 영속성 컨텍스트에서 삭제
            // em.clear() : 영속성 컨텍스트 초기화
            // em.close() : 영속성 컨텍스트 종료
            */

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
