package chat.techink.common.http;

import chat.techink.common.error.code.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * @author xujianxing
 * @date 2023年11月26日 00:13
 * 基于RFC 7807规范的错误码
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestApiResult<T> {
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
        this.code = builder.code;
        this.title = builder.title;
        this.content = builder.content;
        this.status = builder.status;
        this.detail = builder.detail;
    }

    public static RestApiResult<Void> error(ProblemDetail detail, ErrorCode code) {
        Builder builder = new Builder();
        builder.detail(detail).code(code.code()).title(code.title());
        return builder.build();
    }

    public static RestApiResult<Void> error(HttpStatus status, ErrorCode code, String detail) {
        Builder builder = new Builder();
        builder.status(status);
        builder.code(code.code()).title(code.title());
        builder.detail(ProblemDetail.forStatusAndDetail(status, detail));
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


    public boolean success() {
        return this.status == HttpStatus.OK;
    }

    public static <T> RestApiResult success(T content) {
        Builder builder = new Builder();
        return builder.status(HttpStatus.OK)
                .title(HttpStatus.OK.getReasonPhrase())
                .content(content)
                .build();
    }


    public static class Builder<T> {
        private String code;
        private String title;
        private T content;
        private HttpStatus status;
        private ProblemDetail detail;

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

        public Builder<T> detail(ProblemDetail detail) {
            this.detail = detail;
            return this;
        }

        public RestApiResult<T> build() {
            return new RestApiResult<>(this);
        }
    }

}
