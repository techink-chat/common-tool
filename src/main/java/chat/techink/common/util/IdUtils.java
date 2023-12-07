package chat.techink.common.util;

import com.google.common.hash.Hashing;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author xujianxing@sensetime.com
 * @date 2023年12月03日 11:22
 */
public class IdUtils {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static char[] CHARS = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String id(String input) {
        // 计算MurmurHash128位哈希
        byte[] hashBytes = Hashing.murmur3_128().hashString(input, StandardCharsets.UTF_8).asBytes();
        // 将byte数组转换为BigInteger
        BigInteger hashBigInt = new BigInteger(1, hashBytes);
        // 将大整数转换为62进制
        return base62(hashBigInt);
    }

    public static String id(String input, int seed) {
        // 计算MurmurHash128位哈希
        byte[] hashBytes = Hashing.murmur3_128(seed).hashString(input, StandardCharsets.UTF_8).asBytes();
        // 将byte数组转换为BigInteger
        BigInteger hashBigInt = new BigInteger(1, hashBytes);
        // 将大整数转换为62进制
        return base62(hashBigInt);
    }

    public static String base62(BigInteger value) {
        StringBuilder sb = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] quotrem = value.divideAndRemainder(BigInteger.valueOf(62));
            sb.append(CHARS[quotrem[1].intValue()]);
            value = quotrem[0];
        }
        return sb.reverse().toString();
    }
}
