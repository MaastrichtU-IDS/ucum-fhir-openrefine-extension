package org.um.ids.GRELExtensions;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.Function;
import org.um.ids.utils.UCUMHelper;

import java.util.Properties;

public class UCUMValidate implements Function {
    @Override
    public Object call(Properties bindings, Object[] args) {
        if (args.length != 1) {
            return new EvalError("UCUMValidate expects one argument: a string unit.");
        }
        if (!(args[0] instanceof String)) {
            return new EvalError("UCUMValidate expects the first argument to be a string.");
        }

        String unit = (String) args[0];

        try {
            return UCUMHelper.getUcumService().validate(unit) == null;
        } catch (Exception e) {
            return new EvalError("Error validating unit: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Validates a UCUM unit, returns true if valid, false if not.";
    }

    @Override
    public String getReturns() {
        return "boolean";
    }

    @Override
    public String getParams() {
        return "unit: string";
    }
}
