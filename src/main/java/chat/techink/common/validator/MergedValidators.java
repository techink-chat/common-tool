package chat.techink.common.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xujianxing
 * @date 2023年11月25日 00:06
 */

public class MergedValidators<C extends ValidateContext> {

    private List<Validator<C>> validators;

    private C context;

    MergedValidators(List<Validator<C>> validators, C context) {
        this.validators = validators;
        this.context = context;
    }

    public C validate() {
        for (Validator validator : validators) {
            validator.validate(context);
        }
        return context;
    }


    public static class Builder<R, C extends ValidateContext> {

        private C context;

        private List<Validator<C>> validators = new ArrayList<>();

        public Builder() {
        }

        public Builder addContext(C context) {
            this.context = context;
            return this;
        }

        public Builder addValidator(Validator<C> validator) {
            validators.add(validator);
            return this;
        }

        public MergedValidators<C> build() {
            return new MergedValidators(validators, context);
        }
    }

}
