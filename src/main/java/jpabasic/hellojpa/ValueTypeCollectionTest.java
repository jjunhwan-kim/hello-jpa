package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueTypeCollectionTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            AddressEntity address1 = new AddressEntity("old1", "street", "10000");
            AddressEntity address2 = new AddressEntity("old2", "street", "10000");
            member.getAddressHistory().add(address1);
            member.getAddressHistory().add(address2);

            // 값 타입 컬렉션은 DB에서 다른 테이블이지만 member를 persist시 같이 DB에 저장됨
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===================================");
            // 값 타입 컬렉션은 기본적으로 지연로딩임
            Member findMember = em.find(Member.class, member.getId());

            List<AddressEntity> addressHistory = findMember.getAddressHistory();
            for (AddressEntity address : addressHistory) {
                System.out.println("address = " + address.getAddress().getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            // homeCity -> newCity 수정시 Address 오브젝트를 새로운 오브젝트로 교체해야함
            Address homeAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode()));

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // Address 테이블에서 Member의 로우를 다 지우고 다시 삽입, 따라서 old2와 newCity1을 insert함
            address1 = em.find(AddressEntity.class, address1.getId());
            findMember.getAddressHistory().remove(address1);
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));

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
