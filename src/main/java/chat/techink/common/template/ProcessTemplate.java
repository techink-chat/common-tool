package chat.techink.common.template;


import chat.techink.common.error.BusinessException;
import chat.techink.common.http.RestApiResult;
import chat.techink.common.validator.ValidateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static chat.techink.common.error.code.ResultCode.COMMON_FAIL;


public class ProcessTemplate {
    private final static Logger logger = LoggerFactory.getLogger(ProcessTemplate.class);

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
            C context = callback.prepare(req);
            callback.validate(context);
            return callback.process(context);
        } catch (BusinessException businessException) {
            logger.error("Business process error", businessException);
            throw businessException;
        } catch (Throwable cause) {
            throw new BusinessException(COMMON_FAIL, cause);
        }
    }
}