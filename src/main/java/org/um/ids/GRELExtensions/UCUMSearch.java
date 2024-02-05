package org.um.ids.GRELExtensions;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.Function;
import org.fhir.ucum.Concept;
import org.fhir.ucum.ConceptKind;
import org.um.ids.utils.UCUMHelper;

import java.util.List;
import java.util.Properties;

public class UCUMSearch implements Function {


    @Override
    public Object call(Properties bindings, Object[] args) {
        if (args.length != 3) {
            return "UCUMSearch expects three arguments: a string concept, a string search, and a boolean isRegex.";
        }

        if (!(args[0] instanceof String || args[0] == null)) {
            return new EvalError("UCUMSearch expects the first argument to be a string or null.");
        }

        if (!(args[1] instanceof String)) {
            return new EvalError("UCUMSearch expects the second argument to be a string.");
        }

        if (!(args[2] instanceof String)) {
            return new EvalError("UCUMSearch expects the third argument to be a string.");
        }

        ConceptKind conceptKind = null;
        if (args[0] != null)
            switch ((String) args[0]) {
                case "prefix":
                    conceptKind = ConceptKind.PREFIX;
                    break;
                case "base":
                    conceptKind = ConceptKind.BASEUNIT;
                    break;
                case "unit":
                    conceptKind = ConceptKind.UNIT;
                    break;
                default:
                    return new EvalError("Invalid concept: " + args[0]);
            }

        boolean isRegex = false;

        switch ((String) args[2]) {
            case "true":
                isRegex = true;
                break;
            case "false":
                break;
            default:
                return new EvalError("Invalid isRegex: " + args[2]);
        }

        try {
            final List<Concept> result = UCUMHelper.getUcumService().search(conceptKind, (String) args[1], isRegex);
            if (result.isEmpty()) return null;
            return result.get(0).getCode();
        } catch (Exception e) {
            return new EvalError("Error searching for " + args[1] + ": " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Searches for a UCUM unit and returns list of matching units.";
    }

    @Override
    public String getParams() {
        return "concept: prefix|base|unit|null, search: string, isRegex: true|false(string)";
    }

    @Override
    public String getReturns() {
        return "string";
    }
}
