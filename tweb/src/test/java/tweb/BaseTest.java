package tweb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.common.system.SpringApplicationContextUtil;

public class BaseTest {

	// 用来测试的算出密码password盐值加密后的结果
//	public static void main(String[] args) {
//		String saltSource = ConfigConstants.SALT_SOURCE;
//		String hashAlgorithmName = "MD5";
//		String credentials = "123456";
//		Object salt = new Md5Hash(saltSource);
//		int hashIterations = 1024;
//		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//		System.out.println(result);
//	}
	
	
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
//		RedisOperator operator = context.getBean("redisOperator", RedisOperator.class);
//		System.out.println(operator.get("111111"));
//		//operator.set("111111", "傻逼水水水水");
//	}
	
	public static void main(String[] args) {
		SpringApplicationContextUtil.getBean("redisOperator");
	}
}
