package com.example.demo;


import com.example.demo.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirtyCheckingAndMergeTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("book1");
        /*
        변경 감지 : dirty checking
        커밋(flush) 시점에 엔티티 객체의 변경 사항이 DB에 저장됨
        */
        //TX commit


        /*
        준영속(transient) 엔티티 저장
        1. 변경 감지 : dirty checking
            준영속 엔티티를 @Transactional 메서드의 파라미터로 넘긴다.
            메서드 내부에서는 식별자(id) 값으로 영속 객체를 불러오고
            파라미터로 받은 준영속 객체의 값을 setter를 이용해서
            영속 객체에 넣어준다.
            메서드 내부의 코드가 모두 실행되면 트랜잭션이 커밋된다.
            엔티티 변경사항이 flush 된다.

            @Transactional
            void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
             Item findItem = em.find(Item.class, itemParam.getId()); //같은 엔티티를 조회한
            다.
             findItem.setPrice(itemParam.getPrice()); //데이터를 수정한다.
            }

            https://gmlwjd9405.github.io/2019/08/07/what-is-flush.html

        2. 병합 : merge
            준영속 상태의 엔티티를 영속 엔티티로 변경
            merger 메서드 동작과정은 위에 변경 감지 메서드와 동일하다. 파라미터로 받은 준영속성 엔티티의
            식별자 값으로 영속성 엔티티를 찾고 준영속성 엔티티의 값을 넣어준다. 그리고 영속성 엔티티를 반환한다.

            하지만 병합은 엔티티의 모든 필드를 교체한다. 만약 비영속성 엔티티의 필드 값이
            null이면 기존 영속 엔티티의 값 또한 null로 변경된다.

            비지니스 로직의 제약사항에 의해서 엔티티의 일부 필드만 업데이트 하는 경우가 많기 때문에
            *병합(merge) 사용은 피하는게 좋다.*

         */

    }
}
