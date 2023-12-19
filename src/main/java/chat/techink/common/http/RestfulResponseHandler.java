package chat.techink.common.http;

import okhttp3.Response;
import org.springframework.http.HttpStatus;

/**
 * @author xujianxing
 * @date 2023年11月26日 00:12
 */
public interface RestfulResponseHandler<T> {

    /**
     * @param response
     * @return
     */
    T handle(int status, String responseBody);

    void error(Response response);

    void error(Exception e);

    default boolean success(int status) {
        return status == HttpStatus.OK.value();
    }

}
