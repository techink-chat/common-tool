package chat.techink.common.template;

import chat.techink.common.http.RestApiResult;
import chat.techink.common.validator.ValidateContext;

/**
 * 业务执行模板
 *
 * @param <R> 请求
 * @param <T> 响应结果
 * @author jianxing.xjx
 */
public interface Callback<R, C extends ValidateContext, T> {

    /**
     * 准备上下文
     *
     * @param request 执行请求
     * @return
     */
    default C prepare(R request) {
        return null;
    }


    /**
     * 验证请求
     *
     * @param context
     */
    default void validate(C context) {
    }


    /**
     * 获取响应结果
     *
     * @param context
     * @return
     */
    RestApiResult<T> process(C context);

}
