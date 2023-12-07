package chat.techink.common.error.code;

import org.springframework.http.HttpStatus;


/**
 * @author xujianxing
 */
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


    private String title;


    private HttpStatus httpStatus;


    private String detail;


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

    DefaultErrorCode(String title, HttpStatus httpStatus, String detail, boolean detailCanDisplay) {
        this.title = title;
        this.httpStatus = httpStatus;
        this.detail = detail;
        this.detailCanDisplay = detailCanDisplay;
    }
}
