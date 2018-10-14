package tweb;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.ztx.world.common.constants.Constants;

public class BaseTest {

	// 用来测试的算出密码password盐值加密后的结果
	public static void main(String[] args) {
		String saltSource = Constants.SALT_SOURCE;
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		Object salt = new Md5Hash(saltSource);
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
}
