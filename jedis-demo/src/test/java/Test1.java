import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class Test1 {

    private Jedis jedis;

    @Before
    public void before(){
        jedis = new Jedis("192.168.133.128", 6379);
        jedis.auth("123");
    }

    @After
    public void after(){
        jedis.close();
    }

    /**
     * 测试连接
     */
    @Test
    public void t1() {
        String ping = jedis.ping();
        System.out.println("ping = " + ping);
    }

    /**
     * key通用操作
     */
    @Test
    public void t2() {
        //查看所有的Key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println("key = " + key);
        }

        //查看key的类型
        String type = jedis.type("zset1");
        System.out.println("type = " + type);

        //查看key是否存在
        boolean exists = jedis.exists("zset123");
        System.out.println("exists = " + exists);

        //查看key的剩余时间TTL
        long ttl = jedis.ttl("zset1");
        System.out.println("ttl = " + ttl);
    }

    /**
     * string字符串
     */
    @Test
    public void t3(){
        //设置string
        jedis.set("s1","111");
        //获取string
        String s1 = jedis.get("s1");
        System.out.println("s1 = " + s1);
    }

    /**
     * list列表
     */
    @Test
    public void t4(){
        //向左添加
        jedis.lpush("l1", "111", "222", "333");
        //获取列表
        List<String> list = jedis.lrange("l1", 0, -1);
        for (String s : list) {
            System.out.println("s = " + s);
        }
    }

    /**
     * set集合
     */
    @Test
    public void t5(){
        //添加元素
        jedis.sadd("set1", "hello", "age", "world");
        //获取所有元素
        Set<String> set = jedis.smembers("set1");
        for (String s : set) {
            System.out.println("s = " + s);
        }
    }

    /**
     * hash哈希
     */
    @Test
    public void t6(){
        //添加键值对
        jedis.hset("hash1", "name", "张三");
        //获取键的值
        String name = jedis.hget("hash1", "name");
        System.out.println("name = " + name);
    }

    /**
     * zset有序集合
     */
    @Test
    public void t7(){
        //添加元素 分数+值
        jedis.zadd("zset1",100, "a");
        jedis.zadd("zset1",50, "b");
        jedis.zadd("zset1",90, "c");
        //获取所有值
        List<String> zset = jedis.zrange("zset1", 0, -1);
        for (String s : zset) {
            System.out.println("s = " + s);
        }
    }
}
