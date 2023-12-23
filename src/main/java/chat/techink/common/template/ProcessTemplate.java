package chat.techink.common.template;


import chat.techink.common.error.BusinessException;
import chat.techink.common.http.RestApiResult;
import chat.techink.common.validator.ValidateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static chat.techink.common.error.code.ResultCodes.COMMON_FAIL;


/**
 * @author xujianxing
 */
public class ProcessTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTemplate.class);

    /**
     * 业务模板引擎
     *
     * @param req
     * @param callback
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, C extends ValidateContext, T> RestApiResult<T> process(R req, Callback<R, C, T> callback) {
        try {
            C context = callback.prepare(req);
            callback.validate(context);
            return callback.process(context);
        } catch (BusinessException businessException) {
            LOGGER.error("业务处理出现异常...", businessException);
            throw businessException;
        } catch (Throwable cause) {
            throw new BusinessException(COMMON_FAIL, cause);
        }
    }
}