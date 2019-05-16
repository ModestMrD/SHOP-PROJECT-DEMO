import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author DuQian
 * @Date 2019/3/20
 */

public class RedisTest {

    @Test
    public void testString() {
        Jedis jedis = new Jedis("192.168.48.129", 6379);
        jedis.auth("123");
        jedis.set("k2", "v2");
        String k2 = jedis.get("k2");
        System.out.println(k2);
    }

    @Test
    public void testHash() {
        Jedis jedis = new Jedis("192.168.48.129", 6379);
        jedis.auth("123");
        jedis.hset("lbook", "price", "1000");
        jedis.hset("lbook", "name", "ylylbs");
        String name = jedis.hget("lbook", "name");
        System.out.println(name);
    }

    @Test
    public void testTable() {
        Jedis jedis = new Jedis("192.168.48.129", 6379);
        jedis.auth("123");
        jedis.lpush("table", "a", "b", "c", "d");
        Long table = jedis.llen("table");
        jedis.lpop("table");
    }

    @Test
    public void testSet() {
        Jedis jedis = new Jedis("192.168.48.129", 6379);
        jedis.auth("123");
        jedis.sadd("me", "girl", "games",
                "book", "movie", "music");
        jedis.sadd("zs", "boy", "music",
                "eat", "book", "movie");
        Set<String> sames = jedis.sinter("me", "zs");
        for (String same : sames) {
            System.out.println(same);
        }
    }

    @Test
    public void testZset() {
        Jedis jedis = new Jedis("192.168.48.129", 6379);
        jedis.auth("123");
        jedis.zadd("student", 95, "lq");
        jedis.zadd("student", 97, "qzj");
        jedis.zadd("student", 99, "hm");
        Set<String> students = jedis.zrange("student", 0, -1);
        for (String student : students) {
            System.out.println(student);
        }
    }

}