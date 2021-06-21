package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            //member.setTeam(team);     // 연관관계의 주인인 Member.team에 team을 설정해야 함
            member.changeTeam(team);    // 연관관계 편의 메소드
            em.persist(member);

            //team.addMember(member);   // 연관관계 편의 메소드

            //team.getMembers().add(member);    // team의 members 컬렉션에 member 설정

            //em.flush();
            //em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("==========");
            //System.out.println("members = " + findTeam); // stack over flow 발생 무한루프
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("==========");

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
