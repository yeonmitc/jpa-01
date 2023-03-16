package study;

import hellojpa.Child;
import hellojpa.Member;
import hellojpa.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            // 원래는 이렇게 다 한개한개 영속성에 추가해야한다
            // 한개라도 안주면 에러발생 근데 @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL)
            // 추가하면 parent 만 추가해도 자동으로 child 도 같이 영속성 객체로 올려줌
            em.persist(parent);
          //  em.persist(child1);
          //  em.persist(child2);

            // 나는 코드를 짤 때 parent 중심으로 짜고 싶어
            // parent 가 자동으로 child 를 관리해줬으면 좋겠어
            // 이때 사용하는게 cascade

            tx.commit();

        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.clear();
        }


        emf.close();

    }

    private static void printMemberAndTeam(Member member){
        System.out.println("member=" + member.getUsername());
    }


}