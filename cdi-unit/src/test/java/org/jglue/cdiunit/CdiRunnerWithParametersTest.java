package org.jglue.cdiunit;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Connection;
import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.UseParametersRunnerFactory;

@RunWith(Parameterized.class)
@UseParametersRunnerFactory(CdiRunnerWithParameters.Factory.class)
@InRequestScope
@AdditionalClasses(ParamTestCdiExtension.class)
public class CdiRunnerWithParametersTest  {

    @Inject @Named("CdiRunnerWithParametersTest")
    private Object object;

    @Parameter(0)
    String param0;
    @Parameter(1)
    String param1;

    @Produces @Named("CdiRunnerWithParametersTest")
    private Object producer() {
        return new Object();
    }

    @Parameters(name = "index {index}: param0({0}) with param1({1})")
    public static Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                { "zero", 4 },
                { "one", 9 }
        });
    }

    @Test
    public void testParamsNotNull() {
        assertThat(object, notNullValue());
        System.out.println(param0);
        System.out.println(param1);
        assertThat(param0, notNullValue());
        assertThat(param1, notNullValue());
    }

}
