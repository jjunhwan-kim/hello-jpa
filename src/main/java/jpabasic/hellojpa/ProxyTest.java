package jpabasic.hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /*
            // ex1
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();


            //Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId()); // Proxy 오브젝트
            System.out.println("before findMember = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername()); // 이 시점에 영속성 컨텍스트에서 조회하여 Proxy 오브젝트 내부에 target 초기화
            System.out.println("after findMember = " + findMember.getClass());
            System.out.println("findMember.username = " + findMember.getUsername());

            */

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            /*
            // ex2
            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());

            // member1이 이미 1차 캐시에 있고, 한 트랜잭션 안에서 m1과 reference는 같아야 하므로
            // getReference 메소드 이지만 프록시 오브젝트가 아니라 원본 오브젝트가 반환됨
            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());

            // 한 트랜잭션 안에서 m1과 reference는 id가 같으므로 == 비교로 같은 오브젝트이어야 함
            System.out.println("a == a: " + (m1 == reference));
            */

            /*
            // ex3
            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());

            // em.find() 를 호출해도 프록시 오브젝트가 리턴됨
            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember.getClass());

            System.out.println("refMember == findMember: " + (refMember == findMember));
            */

            /*
            // ex4
            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());

            // refMember 프록시 오브젝트를 영속성 컨텍스트에서 제거
            //em.detach(refMember);
            em.clear();
            //em.close();

            // 프록시 초기화 실패 예외발생(org.hibernate.LazyInitializationException)
            System.out.println("refMember = " + refMember.getUsername());
            */


            Member refMember = em.getReference(Member.class, member1.getId());
            // 프록시 클래스 확인 방법
            System.out.println("refMember = " + refMember.getClass());
            //refMember.getUsername();    // 프록시 강제 초기화
            // 프록시 인스턴스의 초기화 여부 확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            Hibernate.initialize(refMember);
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            //Member m2 = em.getReference(Member.class, member2.getId());
            //logic(m1, m2);

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

    private static void logic(Member m1, Member m2) {
        // m1, m2가 proxy 오브젝트인지 모르기 때문에 타입 비교는 == 으로 하면 안됨, instanceof 사용해야함
        //System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));
        System.out.println("m1 == m2: " + (m1 instanceof Member));
        System.out.println("m1 == m2: " + (m2 instanceof Member));
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }


}
