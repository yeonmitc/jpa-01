package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Member {
    // 기본키를 데이터베이스에게 위임 MYSQL -> 내가 값을 셋팅하면 에러 발생
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 객체는 username이라고 쓰고 싶은데 db는 name 이라고 쓰고 싶을때  , nullable false 로 하면 not null 조건이 된다
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;
    // integer 로 하면 가장 적절한 숫자타입이 만들어진다

    private Integer age;

    // db에서는 enum이 없다
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 날짜 타입 적고 싶으면 temporalType 주면 된다

    // 보통 자바랑 데이터베이스에서 사용하는 날짜가 다르기 때문에 날짜 mapping
    // 도 따로 줘야한다 TemporalType 눌러보면    DATE, TIME, TIMESTAMP; 이렇게 3가지 타입이 있다
    //@Temporal(TemporalType.TIMESTAMP)
   // private Date createdDate;
   // @Temporal(TemporalType.TIMESTAMP)
    //private Date lastModifiedDate;

    // java 8 이후에는 LocalDate 는 알아서 인식해줘서 저거 필요없음
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    // 큰 문자열 타입을 넣고 싶으면 Lob 사용하면 된다
    @Lob
    private String description;

    // db에 저장하고 싶지 않는 데이터 변수 값
@Transient
    private int temp;

}
