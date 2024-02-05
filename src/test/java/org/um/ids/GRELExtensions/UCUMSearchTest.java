package org.um.ids.GRELExtensions;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UCUMSearchTest {
    final Function ucumSearch = new UCUMSearch();

    @BeforeClass
    public void setUp() {
        ControlFunctionRegistry.registerFunction("ucumSearch", ucumSearch);
    }

    @Test
    public void testUCUMSearchValid() {
        String concept = "unit";
        String search = "mol";
        String isRegex = "false";
        Object[] args = {concept, search, isRegex};

        Object result = ucumSearch.call(null, args);

        assert result instanceof String;

        assert result.equals("mol");

    }


    @Test
    public void testUCUMSearchInvalidConcept() {
        String concept = "invalid";
        String search = "mol";
        String isRegex = "false";
        Object[] args = {concept, search, isRegex};

        Object result = ucumSearch.call(null, args);

        assert result instanceof EvalError;
    }

    @Test
    public void testUCUMSearchInvalidSearch() {
        String concept = "unit";
        String search = "invalid";
        String isRegex = "false";
        Object[] args = {concept, search, isRegex};

        Object result = ucumSearch.call(null, args);

        assert result == null;
    }

    @Test
    public void testUCUMSearchInvalidRegex() {
        String concept = "unit";
        String search = "mol";
        String isRegex = "invalid";
        Object[] args = {concept, search, isRegex};

        Object result = ucumSearch.call(null, args);

        assert result instanceof EvalError;
    }

}
