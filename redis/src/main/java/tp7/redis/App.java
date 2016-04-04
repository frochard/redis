package tp7.redis;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )throws InterruptedException 
	{
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		
		System.err.println(value);	
		System.out.println(jedis.get("---------------------"));
		
		/*		Jedis jedis = new Jedis("localhost");*/
		System.out.println(jedis.get("counter"));
		jedis.incr("counter");
		System.out.println(jedis.get("counter"));
		
		System.out.println(jedis.get("---------------------"));		
		String cacheKey = "cachekey";
//		Jedis jedis = new Jedis("localhost");
		// adding a new key
		jedis.set(cacheKey, "cached value");
		// setting the TTL in seconds
		jedis.expire(cacheKey, 15);
		// Getting the remaining ttl
		System.out.println("TTL:" + jedis.ttl(cacheKey));
		Thread.sleep(1000);
		System.out.println("TTL:" + jedis.ttl(cacheKey));
		// Getting the cache value
		System.out.println("Cached Value:" + jedis.get(cacheKey));

		// Wait for the TTL finishs
		Thread.sleep(15000);

		// trying to get the expired key
		System.out.println("Expired Key:" + jedis.get(cacheKey));
		System.out.println(jedis.get("---------------------"));
		 cacheKey = "languages";
		//Jedis jedis = new Jedis("localhost");
		// Adding a set as value

		jedis.sadd(cacheKey, "Java");
		jedis.sadd(cacheKey, "C#");
		jedis.sadd(cacheKey, "Python");// SADD

		// Getting all values in the set: SMEMBERS
		System.out.println("Languages: " + jedis.smembers(cacheKey));
		// Adding new values
		jedis.sadd(cacheKey, "Java");
		jedis.sadd(cacheKey, "Ruby");
		// Getting the values... it doesn't allow duplicates
		System.out.println("Languages: " + jedis.smembers(cacheKey));

		System.out.println(jedis.get("---------------------"));

		
		
	}

}
