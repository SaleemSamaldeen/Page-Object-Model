package Utils;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class TestDataReader extends ConfigReader {

    @DataProvider(name = "GenericDataProvider")
    public static Object[][] getExcelData(Method m, ITestContext testContext) throws Exception {
        String testSheet = ((Test) m.getAnnotation(Test.class)).testName();
        return DataInputProvider.getSheet(testSheet);
    }
}
