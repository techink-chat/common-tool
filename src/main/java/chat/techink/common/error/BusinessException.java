package chat.techink.common.error;

import chat.techink.common.error.code.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ProblemDetail;

/**
 * 在执行业务过程中出现的异常
 *
 * @author xujianxing
 */
@Schema(name = "基础异常")
public class BusinessException extends RuntimeException {
    private String title;
    protected ResultCode code;

    /**
     * 基于RFC 7807规范
     */
    protected ProblemDetail detail;

    public String getTitle() {
        return title;
    }

    public BusinessException(ResultCode code, ProblemDetail detail) {
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(String title, String detail, ResultCode code) {
        super(title + ":" + detail);
        this.title = title;
        this.code = code;
        this.detail = ProblemDetail.forStatusAndDetail(code.httpStatus(), detail);
    }

    public BusinessException(String title, ResultCode code, ProblemDetail detail) {
        super(title);
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(String message, Throwable cause, ResultCode code, ProblemDetail detail) {
        super(message, cause);
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(Throwable cause, ResultCode code, ProblemDetail detail) {
        super(cause);
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResultCode code, ProblemDetail detail) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.detail = detail;
    }


    private String getMessage(String title, String detail) {
        return title + ":" + detail;
    }

    public ResultCode getCode() {
        return code;
    }


    public ProblemDetail getDetail() {
        return detail;
    }

    public void setDetail(ProblemDetail detail) {
        this.detail = detail;
    }

    public BusinessException(ResultCode code) {
        BusinessException businessException = new BusinessException(code);
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(code.detail());
        businessException.setDetail(problemDetail);
        this.code = code;
    }


    public BusinessException(ResultCode code, Throwable cause) {
        super(cause);
        this.code = code;
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(code.detail());
        this.setDetail(problemDetail);
    }

    public BusinessException(ResultCode code, Throwable cause, String detail) {
        super(cause);
        this.detail = ProblemDetail.forStatus(code.httpStatus());
        this.detail.setDetail(detail);
    }


    public BusinessException(ResultCode code, String detail) {
        super(detail);
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(detail);
        this.code = code;
        this.detail = problemDetail;
    }

    public BusinessException(ResultCode code, String detail, String... format) {
        super(detail);
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(String.format(detail, format));
        this.detail = problemDetail;
    }


}
