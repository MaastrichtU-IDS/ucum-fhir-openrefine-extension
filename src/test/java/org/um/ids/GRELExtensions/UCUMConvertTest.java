package org.um.ids.GRELExtensions;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;

public class UCUMConvertTest {
    Function ucumConvert = new UCUMConvert();

    @BeforeClass
    public void setUp() {
        ControlFunctionRegistry.registerFunction("ucumConvert", ucumConvert);
    }

    @Test
    public void testUCUMConvertValid() {
        String value = "1";
        String fromUnit = "mmol/L";
        String toUnit = "mol/L";
        Object[] args = {value, fromUnit, toUnit};

        Object result = ucumConvert.call(new Properties(), args);

        assert result.equals("0.001");
    }

    @Test
    public void testUCUMConvertValid_number_to_str() {
        int value = 2;
        String fromUnit = "mol/L";
        String toUnit = "mmol/L";
        Object[] args = {value, fromUnit, toUnit};

        Object result = ucumConvert.call(new Properties(), args);

        assert result.equals("2000");
    }

    @Test
    public void testUCUMConvertInvalid() {
        String value = "1";
        String fromUnit = "mmol/L";
        String toUnit = "mol";
        Object[] args = {value, fromUnit, toUnit};

        Object result = ucumConvert.call(new Properties(), args);

        assert result instanceof EvalError;
    }

    @Test
    public void testUCUMConvertInvalidValue() {
        String value = "a";
        String fromUnit = "mmol/L";
        String toUnit = "mol/L";
        Object[] args = {value, fromUnit, toUnit};

        Object result = ucumConvert.call(new Properties(), args);

        assert result instanceof EvalError;
    }
}
