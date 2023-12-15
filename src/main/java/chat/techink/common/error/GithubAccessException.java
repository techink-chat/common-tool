package chat.techink.common.error;

import chat.techink.common.error.code.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static chat.techink.common.error.code.ResultCodes.GITHUB_API_ACCESS_ERROR;


/**
 * @author xujianxing@techink.chat
 * @date 2023年12月01日 17:13
 */
public class GithubAccessException extends BusinessException {
    private static final ResultCode CODE = GITHUB_API_ACCESS_ERROR;

    private int status;


    public GithubAccessException(int status, Throwable throwable) {
        super(CODE.title(), throwable.getMessage(), CODE);
        this.status = status;
        this.detail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(status), throwable.getMessage());
    }


    public GithubAccessException(int status, String detail) {
        super(CODE.title(), detail, CODE);
        this.detail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(status), detail);
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
