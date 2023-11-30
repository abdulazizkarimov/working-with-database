import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RandUtil;

public class MyTest extends BaseTest {

    @Test
    public void myTestCase() {
        Assert.assertTrue(RandUtil.getRandDouble() > 0.5, "some message");
    }
}
