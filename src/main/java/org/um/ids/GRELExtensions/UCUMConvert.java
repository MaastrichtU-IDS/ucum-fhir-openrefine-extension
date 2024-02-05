package org.um.ids.GRELExtensions;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;
import org.fhir.ucum.Decimal;
import org.fhir.ucum.UcumException;
import org.um.ids.utils.UCUMHelper;

import java.util.Properties;

public class UCUMConvert implements Function {
    @Override
    public Object call(Properties bindings, Object[] args) {
        if (args.length != 3) {
            return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " expects three arguments: a string value, a string fromUnit, and a string toUnit.");
        }


        for (Object arg : args) {
            if (arg == null) {
                return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " expects three non-null arguments: a string value, a string fromUnit, and a string toUnit.");
            }
        }

        if (!(args[0] instanceof String || args[0] instanceof Number)) {
            return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " expects the first argument to be a string or a number.");
        }
        if (!(args[1] instanceof String)) {
            return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " expects the second argument to be a string.");
        }

        if (!(args[2] instanceof String)) {
            return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " expects the third argument to be a string.");
        }

        final Decimal value;
        try {
            if (args[0] instanceof String) value = new Decimal((String) args[0]);
            else value = new Decimal(
                    args[0].toString()
            );
        } catch (UcumException e) {
            return new EvalError("Error parsing value: " + e.getMessage());
        }

        String fromUnit = (String) args[1];
        String toUnit = (String) args[2];

        try {
            return UCUMHelper.getUcumService().convert(value, fromUnit, toUnit).toString();
        } catch (Exception e) {
            return new EvalError("Error converting value from " + fromUnit + " to " + toUnit + ": " + e.getMessage());
        }

    }

    @Override
    public String getDescription() {
        return "Converts a value from one UCUM unit to another.";
    }

    @Override
    public String getParams() {
        return "string value, String fromUnit, String toUnit";
    }

    @Override
    public String getReturns() {
        return "string";
    }
}
