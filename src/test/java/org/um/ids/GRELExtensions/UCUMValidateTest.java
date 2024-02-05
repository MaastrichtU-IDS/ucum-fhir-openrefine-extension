package org.um.ids.GRELExtensions;

import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UCUMValidateTest {
    final Function ucumValidate = new UCUMValidate();

    @BeforeClass
    public void setUp() {
        ControlFunctionRegistry.registerFunction("ucumValidate", ucumValidate);
    }

    @Test
    public void testUCUMValidateValid() {
        String unit = "mol/L";
        Object[] args = {unit};

        Object result = ucumValidate.call(null, args);

        assert result.equals(true);
    }

    @Test
    public void testUCUMValidateInvalid() {
        String unit = "invalid";
        Object[] args = {unit};

        Object result = ucumValidate.call(null, args);

        assert result.equals(false);
    }
}
