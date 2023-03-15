package study;

import hellojpa.Member;
import hellojpa.RoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            //member.setId("id1");
            member.setUsername("B");
            em.persist(member);
            System.out.println("memberid=" + member.getId()); // 이 시점에 값 먼저 알 수 있다
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.clear();
        }


        emf.close();

    }
}