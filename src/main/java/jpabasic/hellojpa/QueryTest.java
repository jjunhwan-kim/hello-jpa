package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class QueryTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member m = new Member();
            m.setUsername("member1");
            em.persist(m);
            /*
            // JPQL
            List<Member> result = em.createQuery("select m from Member m where m.username like '%kim%'", Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member = " + member);
            }
            */
            /*
            // Criteria
            // Criteria 사용준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            // 루트 클래스(조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);

            // 쿼리생성
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));

            List<Member> resultList = em.createQuery(cq).getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }
            */

            // flush가 먼저 호출되어 Insert SQL이 전송됨

            // Native SQL
            List<Member> resultList = em.createNativeQuery("select member_id, city, street, zipcode, username from member", Member.class).getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

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
