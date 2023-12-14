package chat.techink.common.error;

import chat.techink.common.error.code.ResultCode;
import chat.techink.common.error.code.ResultCodes;
import org.springframework.http.ProblemDetail;

/**
 * 2023/12/13
 * <p></p>
 *
 * @author jianxing.xjx
 */

public class AuthenticationException extends BusinessException {
    private static final ResultCode CODE = ResultCodes.AUTHENTICATION_FAILED;

    public AuthenticationException(String title) {
        super(CODE, title);
        this.detail = ProblemDetail.forStatusAndDetail(code.httpStatus(), code.detail());
    }

    public AuthenticationException(String title, String detail) {
        super(title, detail, CODE);
    }

    @Override
    public ResultCode getCode() {
        return code;
    }

    @Override
    public ProblemDetail getDetail() {
        return detail;
    }
}
