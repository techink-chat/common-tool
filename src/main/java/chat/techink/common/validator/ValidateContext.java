package chat.techink.common.validator;

/**
 * @author xujianxing@sensetime.com
 * @date 2023年11月25日 00:24
 */

public class ValidateContext<R> {
    protected final R request;

    public ValidateContext(R request) {
        this.request = request;
    }

    public R getRequest() {
        return request;
    }


}
