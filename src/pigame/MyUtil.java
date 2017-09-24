package pigame;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyUtil {
	
	public static final String getMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes());
        String str_md5 = new BigInteger(1, md.digest()).toString(16);
        return str_md5;
	}

}
