package jpabasic.hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProxyLoadingTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            team.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            /*
            // em.find는 Eager로 설정했을 때, join을 사용해서 SQL이 한번 나감
            Member m = em.find(Member.class, member1.getId());

            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("================");
            System.out.println("teamName = " + m.getTeam().getName()); // 초기화
            System.out.println("================");
            */

            // JPQL은 SQL로 변환되면 "select * from Member" 로 일단 변환되어 DB로 전송됨
            // 그런데 Eager 설정 이므로 "select * from Team where team_id = xxx" SQL이 이후에 전송됨
            // Member의 Team이 각각 다른 FK일 경우 Member가 N개일 경우 N + 1개의 SQL이 전송됨
            // N + 1 문제는 1번의 최초 쿼리에 의해서 N개의 추가 쿼리가 나가는 것을 말함
            //List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList()
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
