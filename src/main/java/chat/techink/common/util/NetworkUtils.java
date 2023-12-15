package chat.techink.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xujianxing@sensetime.com
 * @date 2023年12月01日 18:21
 */
public class NetworkUtils {
    private static final Pattern URL_REG = Pattern.compile("^(((ht|f)tps?):\\/\\/)?[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$");
    public static Pattern IPV4_REG = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");

    public static String replaceIPv4(String input) {
        Matcher matcher = IPV4_REG.matcher(input);
        // 循环查找并替换IP地址
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, "current ip");
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static boolean checkURL(String url) {
        return URL_REG.matcher(url).matches();
    }
}
