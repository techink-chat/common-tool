package chat.techink.common.template;

import chat.techink.common.http.RestApiResult;
import chat.techink.common.validator.ValidateContext;

/**
 * 业务执行模板
 *
 * @param <R>
 * @param <T>
 * @author luoshi
 */
public interface Callback<R, C extends ValidateContext<R>, T> {

    C prepare(R request);


    void validate(C context);


    RestApiResult<T> process(C context) throws Throwable;

}
