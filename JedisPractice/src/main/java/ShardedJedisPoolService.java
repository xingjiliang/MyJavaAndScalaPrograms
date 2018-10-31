import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import java.util.List;
import java.util.LinkedList;

public class ShardedJedisPoolService extends ShardedJedisPool{

    private static ShardedJedisPoolService shardedJedisPoolService = null;

    public ShardedJedisPoolService(JedisPoolConfig jedisPoolConfig, List<JedisShardInfo> jedisShardInfoList){
        super(jedisPoolConfig, jedisShardInfoList);
    }

    public static ShardedJedisPoolService getService(){
        if(shardedJedisPoolService == null){
            initService();
        }
        return shardedJedisPoolService;
    }

    public static void initService(){
        String host = "127.0.0.1";
        int port = 6379;
        String password = "abc";
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, 5000);
        jedisShardInfo.setPassword("abc");
        List<JedisShardInfo> jedisShardInfoList = new LinkedList<JedisShardInfo>();
        jedisShardInfoList.add(jedisShardInfo);
        shardedJedisPoolService = new ShardedJedisPoolService(jedisPoolConfig, jedisShardInfoList);
    }
}
