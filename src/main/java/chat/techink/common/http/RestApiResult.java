package chat.techink.common.http;

import chat.techink.common.error.BusinessException;
import chat.techink.common.error.code.ResultCode;
import chat.techink.common.error.code.ResultCodes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author xujianxing
 * @date 2023年11月26日 00:13
 * 基于RFC 7807规范的错误码
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestApiResult<T> {
    @JsonIgnore
    private HttpHeaders headers;

    @Schema
    private String code;

    private String title;

    private T content;

    @JsonIgnore
    private HttpStatus status;

    /**
     * Representation for an RFC 7807 problem detail.
     */
    @Nullable
    private ProblemDetail detail;


    private RestApiResult(Builder<T> builder) {
        this.headers = new HttpHeaders();
        this.code = builder.code;
        this.title = builder.title;
        this.content = builder.content;
        this.status = builder.status;
        this.detail = builder.detail;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public T getContent() {
        return content;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Nullable
    public ProblemDetail getDetail() {
        return detail;
    }


    public static RestApiResult<Void> error(ProblemDetail detail, ResultCode code) {
        Builder builder = new Builder();
        builder.detail(detail).code(code.code()).title(code.title());
        return builder.build();
    }

    public static RestApiResult<Void> error(HttpStatus status, ResultCode code, String detail) {
        Builder builder = new Builder();
        builder.status(status);
        builder.code(code.code()).title(code.title());
        builder.detail(ProblemDetail.forStatusAndDetail(status, detail));
        return builder.build();
    }

    public static RestApiResult<Void> error(BusinessException ex) {
        Builder builder = new Builder();
        builder.title(ex.getCode().title());
        if (StringUtils.hasText(ex.getTitle())) {
            builder.title(ex.getTitle());
        }
        builder.code(ex.getCode().code());
        builder.status(ex.getCode().httpStatus());
        builder.detail(ex.getDetail());
        return builder.build();
    }

    public static RestApiResult error(Response response) throws IOException {
        Builder builder = new Builder();
        HttpStatus status = HttpStatus.valueOf(response.code());
        ResponseBody body = response.body();
        builder.status(status).title(status.getReasonPhrase());
        builder.detail(ProblemDetail.forStatusAndDetail(status, body.string()));
        return builder.build();
    }

    public static <T> RestApiResult<T> empty(String detail) {
        Builder builder = new Builder();
        builder.status(HttpStatus.BAD_REQUEST).title(HttpStatus.BAD_REQUEST.getReasonPhrase());
        builder.detail(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail));
        return builder.build();
    }


    public static <T> RestApiResult success(T content) {
        Builder builder = new Builder().status(HttpStatus.OK)
                .title(HttpStatus.OK.getReasonPhrase());
        if (content != null) {
            builder.content(content);
        }
        return builder.build();
    }

    public static RestApiResult success() {
        return success(null);
    }


    public static class Builder<T> {
        private String code;
        private String title;
        private T content;
        private HttpHeaders headers;
        private HttpStatus status;
        private ProblemDetail detail;

        public Builder() {
            headers = new HttpHeaders();
            code = ResultCodes.SUCCESS.code();
            title = ResultCodes.SUCCESS.title();
            status = ResultCodes.SUCCESS.httpStatus();
        }


        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> title(String title) {
            this.title = title;
            return this;
        }

        public Builder<T> content(T content) {
            this.content = content;
            return this;
        }

        public Builder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> header(String key, String value) {
            headers.add(key, value);
            return this;
        }

        public Builder<T> detail(ProblemDetail detail) {
            this.detail = detail;
            return this;
        }

        public RestApiResult<T> build() {
            return new RestApiResult<>(this);
        }
    }

}
