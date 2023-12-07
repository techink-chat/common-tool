package chat.techink.common.error;

import chat.techink.common.error.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.ProblemDetail;

/**
 * 在执行业务过程中出现的异常
 *
 * @author xujianxing
 */
@AllArgsConstructor
@Data
public class BusinessException extends RuntimeException {


    @Getter
    private ErrorCode code;

    /**
     * 基于RFC 7807规范
     */
    @Getter
    private ProblemDetail problemDetail;

    public BusinessException(ErrorCode code) {
        BusinessException businessException = new BusinessException(code);
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(code.detail());
        businessException.setProblemDetail(problemDetail);
        this.code = code;
    }


    public BusinessException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(code.detail());
        this.setProblemDetail(problemDetail);
    }

    public BusinessException(ErrorCode code, Throwable cause, String detail) {
        super(cause);
        problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(detail);
    }


    public BusinessException(ErrorCode code, String detail) {
        super(detail);
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(detail);
        this.code = code;
        this.problemDetail = problemDetail;
    }

    public BusinessException(ErrorCode code, String detail, String... format) {
        super(detail);
        ProblemDetail problemDetail = ProblemDetail.forStatus(code.httpStatus());
        problemDetail.setDetail(String.format(detail, format));
        this.problemDetail = problemDetail;
    }


}
