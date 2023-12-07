package chat.techink.common.http;

import chat.techink.common.error.code.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
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
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestApiResult<T> {
    @Nullable
    private String title;

    private String code;

    @JsonIgnore
    private HttpStatus status;

    @Nullable
    @Schema(description = "当http状态码为200时")
    private T content;
    @Nullable
    @Schema(description = "当http状态码为非200时错误详情。基于RFC 7807规范")
    private ProblemDetail detail;

    public static RestApiResult<Void> error(ProblemDetail detail, ErrorCode code) {
        RestApiResultBuilder builder = new RestApiResultBuilder();
        builder.detail(detail).code(code.code()).title(code.title());
        return builder.build();
    }

    public static RestApiResult<Void> error(HttpStatus status, ErrorCode code, String detail) {
        RestApiResultBuilder builder = new RestApiResultBuilder();
        builder.status(status);
        builder.code(code.code()).title(code.title());
        builder.detail(ProblemDetail.forStatusAndDetail(status, detail));
        return builder.build();
    }

    public static RestApiResult error(Response response) throws IOException {
        RestApiResultBuilder builder = new RestApiResultBuilder();
        HttpStatus status = HttpStatus.valueOf(response.code());
        ResponseBody body = response.body();
        builder.status(status).title(status.getReasonPhrase());
        builder.detail(ProblemDetail.forStatusAndDetail(status, body.string()));
        return builder.build();
    }

    public static <T> RestApiResult<T> empty(String detail) {
        RestApiResultBuilder builder = new RestApiResultBuilder();
        builder.status(HttpStatus.BAD_REQUEST).title(HttpStatus.BAD_REQUEST.getReasonPhrase());
        builder.detail(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail));
        return builder.build();
    }


    public boolean ok() {
        return this.status == HttpStatus.OK;
    }

    public static <T> RestApiResult ok(T content) {
        RestApiResultBuilder builder = new RestApiResultBuilder();
        return builder.status(HttpStatus.OK).title(HttpStatus.OK.getReasonPhrase()).content(content).build();
    }


}
