package chat.techink.common.error;

import lombok.Data;

import java.util.Map;

import static chat.techink.common.error.code.DefaultErrorCode.GITHUB_API_ACCESS_ERROR;

/**
 * @author xujianxing@techink.chat
 * @date 2023年12月01日 17:13
 */
@Data
public class GithubAccessException extends RuntimeException {
    private int status;
    private Object detail;


    public GithubAccessException(int status, Throwable throwable) {
        super(GITHUB_API_ACCESS_ERROR.title(), throwable);
        this.status = status;
        this.detail = throwable.getMessage();
    }

    public GithubAccessException(int status, Map<String, Object> response) {
        super(GITHUB_API_ACCESS_ERROR.title());
        this.status = status;
        this.detail = response;
    }

    public GithubAccessException(int status, String message) {
        super(GITHUB_API_ACCESS_ERROR.title());
        this.status = status;
        this.detail = message;
    }
}
