package chat.techink.common.error.code;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


/**
 * @author xujianxing
 */

@Schema(description = "通用响应码", enumAsRef = true)
public enum ResultCodes implements ResultCode {
    /**
     * 成功
     */
    SUCCESS("成功", OK, "", true),
    AUTHENTICATION_FAILED("认证失败", UNAUTHORIZED, "", true),
    PARAM_NOT_VALID("参数无效", BAD_REQUEST, "", true),

    COMMON_FAIL("请求失败", INTERNAL_SERVER_ERROR, "", true),

    NOT_FOUND("资源未找到", BAD_REQUEST, "", true),


    API_RATE_LIMIT("API调用次数超过限制", HttpStatus.NOT_ACCEPTABLE, "", true),

    JSON_PARSE_ERROR("json字符串序列化/反序列化异常", BAD_REQUEST, "", true),

    GITHUB_API_ACCESS_ERROR("访问Github API失败", BAD_REQUEST, "", true),

    GIT_REPO_NOT_VALID("不合法的Git仓库", BAD_REQUEST, "", true),

    INVALID_MARKDOWN_HEADER("Markdown文件头信息缺少关键字段", BAD_REQUEST, "地址为%s的Markdown文件头信息缺少关键字段",
            true),
    ;


    private String title;


    private HttpStatus status;


    private String detail;


    private boolean returnDetail;

    @Override
    public String code() {
        return name();
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String detail() {
        return detail;
    }


    @Override
    public HttpStatus httpStatus() {
        return status;
    }

    @Override
    public boolean returnDetail() {
        return returnDetail;
    }


    public void format(String format, String... params) {
        this.detail = String.format(format, params);
    }

    ResultCodes(String title, HttpStatus status, String detail,
                boolean returnDetail) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.returnDetail = returnDetail;
    }
}
