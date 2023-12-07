package chat.techink.common.template;


import chat.techink.common.error.BusinessException;
import chat.techink.common.http.RestApiResult;
import chat.techink.common.validator.ValidateContext;
import lombok.extern.log4j.Log4j2;

import static chat.techink.common.error.code.DefaultErrorCode.UNKNOWN_ERROR;

@Log4j2
public class ProcessTemplate {

    /**
     * 业务模板引擎
     *
     * @param req
     * @param callback
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, C extends ValidateContext<R>, T> RestApiResult<T> process(R req, Callback<R, C, T> callback) {
        try {
            C context = callback.validate(callback.prepare(req));
            return callback.process(context);
        } catch (BusinessException businessException) {
            log.error("Business process error", businessException);
            throw businessException;
        } catch (Throwable cause) {
            throw new BusinessException(UNKNOWN_ERROR, cause);
        }
    }
}