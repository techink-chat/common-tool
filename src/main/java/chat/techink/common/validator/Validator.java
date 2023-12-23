package chat.techink.common.validator;

/**
 * @author xujianxing
 * @date 2023年11月25日 00:00
 */
public interface Validator<C extends ValidateContext> {


    void validate(C context);


}
