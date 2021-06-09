package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityMappingTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            //member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            //Long findTeamId = findMember.getTeamId();
            //Team findTeam = em.find(Team.class, findTeamId);

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 업데이트
            //Team newTeam = em.find(Team.class, 100L);
            //findMember.setTeam(newTeam);

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
