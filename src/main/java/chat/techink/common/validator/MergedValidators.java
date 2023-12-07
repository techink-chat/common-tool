package chat.techink.common.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xujianxing
 * @date 2023年11月25日 00:06
 */

public class MergedValidators<R, C extends ValidateContext<R>> {

    private List<Validator<R, C>> validators;

    private C context;

    MergedValidators(List<Validator<R, C>> validators, C context) {
        this.validators = validators;
        this.context = context;
    }

    public C validate() {
        for (Validator validator : validators) {
            validator.validate(context);
        }
        return context;
    }


    public static class Builder<R, C extends ValidateContext<R>> {

        private C context;

        private List<Validator<R, C>> validators = new ArrayList<>();

        public Builder() {
        }

        public Builder addContext(C context) {
            this.context = context;
            return this;
        }

        public Builder addValidator(Validator<R, C> validator) {
            validators.add(validator);
            return this;
        }

        public MergedValidators<R, C> build() {
            return new MergedValidators(validators, context);
        }
    }

}