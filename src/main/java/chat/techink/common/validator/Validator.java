package chat.techink.common.validator;

/**
 * @author xujianxing
 * @date 2023年11月25日 00:00
 */
public interface Validator<R, C extends ValidateContext<R>> {


    void validate(C context);


}
