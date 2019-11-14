package com.bnpparibas.crafters.tutorial.junitparams;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.NamedParameters;
import junitparams.Parameters;
import junitparams.converters.ConversionFailedException;
import junitparams.converters.Converter;
import junitparams.converters.Param;
import junitparams.custom.combined.CombinedParameters;
import junitparams.mappers.CsvWithHeaderMapper;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {

    @Test
    @Parameters({"AAA,1", "BBB,2"})
    public void paramsInAnnotation(String p1, Integer p2) {
        System.out.println("p1 = [" + p1 + "], p2 = [" + p2 + "]");
    }

    @Test
    @Parameters("please\\, escape commas if you use it here and don't want your parameters to be splitted")
    public void commasInParametersUsage(String phrase) {
        System.out.println("phrase = [" + phrase + "]");
    }

    @Test
    @Parameters({"AAA|1", "BBB|2"})
    public void paramsInAnnotationPipeSeparated(String p1, Integer p2) {
        System.out.println("p1 = [" + p1 + "], p2 = [" + p2 + "]");
    }

    @Test
    @Parameters
    public void paramsInDefaultMethod(String p1, Integer p2) {
        System.out.println("p1 = [" + p1 + "], p2 = [" + p2 + "]");
    }

    private Object parametersForParamsInDefaultMethod() {
        return new Object[]{new Object[]{"AAA", 1}, new Object[]{"BBB", 2}};
    }

    @Test
    @Parameters
    public void paramsInIterableOfIterables(String p1, String p2) {
        System.out.println("p1 = [" + p1 + "], p2 = [" + p2 + "]");
    }

    private List<List<String>> parametersForParamsInIterableOfIterables() {
        return Arrays.asList(
                Arrays.asList("s01e01", "s01e02"),
                Arrays.asList("s02e01", "s02e02")
        );
    }

    @Test
    @Parameters(method = "named2,named3")
    public void paramsInMultipleMethods(String p1, Integer p2) {
        System.out.println("p1 = [" + p1 + "], p2 = [" + p2 + "]");
    }

    private Object named2() {
        return new Object[]{"AAA", 1};
    }

    private Object named3() {
        return new Object[]{"BBB", 2};
    }

    @Test
    @Parameters(method = "named4")
    public void paramsWithVarargs(String... args) {
        System.out.println("args = [" + args + "]");
        assertThat(args).isEqualTo(new String[]{"AAA", "BBB"});
    }

    private Object named4() {
        return new Object[]{new String[]{"AAA", "BBB"}};
    }

    @Test
    @Parameters(named = "return 1")
    public void paramsInNamedParameters(int number) {
        System.out.println("number = [" + number + "]");
    }

    @NamedParameters("return 1")
    private Integer[] customNamedParameters() {
        return new Integer[]{1};
    }

    @Test
    @Parameters(source = OneIntegerProvider.class)
    public void paramsFromExternalClass(int integer) {
        System.out.println("integer = [" + integer + "]");
    }
    public static class OneIntegerProvider {

        public static Object[] provideTwoNumbers() {
            return new Object[]{new Object[]{1}, new Object[]{2}};
        }

        public static Object[] provideOneNumber() {
            return new Object[]{new Object[]{3}};
        }

    }

    @Test
    @Parameters
    public void wrapParamsWithConstructor(PersonTest.Person person) {
        System.out.println("person = [" + person + "]");
    }

    private Object parametersForWrapParamsWithConstructor() {
        return new Object[]{new Object[]{"first", 1}, new Object[]{"second", 2}};
    }

    @Test
    @FileParameters("src/test/resources/junitparams.csv")
    public void loadParamsFromCsv(int age, String name) {
        System.out.println("age = [" + age + "], name = [" + name + "]");
    }

    @Test
    @FileParameters(value = "src/test/resources/junitparams.csv", mapper = PersonMapper.class)
    public void loadParamsFromAnyFile(PersonTest.Person person) {
        System.out.println("person = [" + person + "]");
    }

    @Test
    @FileParameters("classpath:junitparams.csv")
    public void loadParamsFromClasspath(int age, String name) {
        System.out.println("age = [" + age + "], name = [" + name + "]");
    }

    @Test
    @FileParameters(value = "classpath:junitparams_with_header.csv", mapper = CsvWithHeaderMapper.class)
    public void loadParamsFromCsvWithHeader(int id, String name) {
        System.out.println("id = [" + id + "], name = [" + name + "]");
    }

    @Test
    @Parameters({"01.12.2012"})
    public void convertParams(@Param(converter = SimpleDateConverter.class) Date date) {
        System.out.println("date = [" + date + "]");
    }

    public static class SimpleDateConverter implements Converter<Param, Date> {
        @Override
        public void initialize(Param annotation) {
        }

        @Override
        public Date convert(Object param) throws ConversionFailedException {
            try {
                return new SimpleDateFormat("dd.MM.yyyy").parse(param.toString());
            } catch (ParseException e) {
                throw new ConversionFailedException("failed");
            }
        }
    }

    @Test
    @Parameters({"1,1", "2,2", "3,6"})
    @TestCaseName("factorial({0}) = {1}")
    public void customNamesForTestCase(int argument, int result) {
        System.out.println("argument = [" + argument + "], result = [" + result + "]");
    }

    @Test
    @Parameters({"value1, value2", "value3, value4"})
    @TestCaseName("[{index}] {method}: {params}")
    public void predefinedMacroForTestCaseNames(String param1, String param2) {
        System.out.println("param1 = [" + param1 + "], param2 = [" + param2 + "]");
    }

    public Object mixedParameters() {
        boolean booleanValue = true;
        int[] primitiveArray = {1, 2, 3};
        String stringValue = "Test";
        String[] stringArray = {"one", "two", null};
        return $(
                $(booleanValue, primitiveArray, stringValue, stringArray)
        );
    }

    private Object[] $(Object ... objects) {
        return objects;
    }

    @Test
    @Parameters(method = "mixedParameters")
    @TestCaseName("{0}, {1}, {2}, {3}")
    public void usageOfMultipleTypesOfParameters(
            boolean booleanValue, int[] primitiveArray, String stringValue, String[] stringArray) {
        System.out.println("booleanValue = [" + booleanValue + "], primitiveArray = [" + primitiveArray + "], stringValue = [" + stringValue + "], stringArray = [" + stringArray + "]");
    }

    @Test
    @CombinedParameters({"AAA,BBB", "1|2"})
    public void combineParamsInAnnotation(String p1, Integer p2) {
        System.out.println("p1 = [" + p1 + "], p2 = [" + p2 + "]");
    }

}
