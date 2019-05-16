import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author DuQian
 * @Date 2019/3/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class MyTest {

    @Autowired
    private RedisTemplate template;

    @Test
    public void testString(){
        template.opsForValue().set("k1","v1");
        template.opsForValue().set("money","1000");
        template.opsForValue().increment("money",200);
        Object k1 = template.opsForValue().get("k1");
        Object money = template.opsForValue().get("money");
        System.out.println(money);
        System.out.println(k1);
    }

    @Test
    public void testHash(){
        template.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForHash().put("student","name","lq");
                operations.opsForHash().put("student","age","29");
                operations.exec();
                System.out.println(operations.opsForHash().get("student","name"));
                System.out.println(operations.opsForHash().get("student","age"));
                return "ok";
            }
        });
    }

    @Test
    public void testHashCode(){
        String s1 = "ABCDEa123abc";
        String s2 = "ABCDFB123abc";
        System.out.println(s1.hashCode());  // 165374702
        System.out.println(s2.hashCode());  // 165374702
    }

}
