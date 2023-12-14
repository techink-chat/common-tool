package chat.techink.common.error.code;


import org.springframework.http.HttpStatus;

/**
 * @author xujianxing
 * 错误码
 */
public interface ResultCode {

    /**
     * 获取错误码。主要是方便国际化
     *
     * @return
     */
    String code();

    /**
     * 获取错误标题
     *
     * @return
     */
    String title();

    String detail();


    /**
     * 获取Http状态码
     *
     * @return
     */
    HttpStatus httpStatus();


    /**
     * 错误详情是否可以对外展示
     *
     * @return
     */
    boolean returnDetail();

}
