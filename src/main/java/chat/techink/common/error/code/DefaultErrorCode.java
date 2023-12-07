package chat.techink.common.error.code;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 * @author xujianxing
 */

@AllArgsConstructor
public enum DefaultErrorCode implements ErrorCode {

    UNKNOWN_ERROR("未知异常", HttpStatus.INTERNAL_SERVER_ERROR, "", true),

    NOT_FOUND("资源未找到", HttpStatus.BAD_REQUEST, "", true),

    INVALID_PARAM("参数不合法", HttpStatus.BAD_REQUEST, "", true),

    API_RATE_LIMIT("API调用次数超过限制", HttpStatus.NOT_ACCEPTABLE, "", true),

    JSON_PARSE_ERROR("json字符串序列化/反序列化异常", HttpStatus.BAD_REQUEST, "", true),

    GITHUB_API_ACCESS_ERROR("访问Github API失败", HttpStatus.BAD_REQUEST, "", true),

    INVALID_GIT_REPO("不合法的Git仓库", HttpStatus.BAD_REQUEST, "", true),

    INVALID_MARKDOWN_HEADER("Markdown文件头信息缺少关键字段", HttpStatus.BAD_REQUEST, "地址为%s的Markdown文件头信息缺少关键字段",
            true),
    ;

    @Schema(description = "错误标题")
    private String title;


    @Schema(description = "http错误码")
    private HttpStatus httpStatus;

    @Schema(description = "错误详情")
    private String detail;

    @Schema(description = "错误详情是否可以对外展示")
    private boolean detailCanDisplay;

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
        return httpStatus;
    }

    @Override
    public boolean detailCanDisplay() {
        return detailCanDisplay;
    }
}
