package chat.techink.common.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xujianxing@sensetime.com
 * @date 2023年11月25日 00:24
 */
@AllArgsConstructor
@NoArgsConstructor
public class ValidateContext<R> {
    @Getter
    protected R request;
}
