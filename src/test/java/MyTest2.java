import base.BaseTest2;
import model.Test;
import org.testng.Assert;
import repo.TestRepo;
import java.util.Date;

public class MyTest2 extends BaseTest2 {

    @org.testng.annotations.Test
    public void myTestCase() {

        for (Test test : testList) {
            test.setName("New Test");
            test.setTime(new Date().toString());
            test.setStatus(1);
            test.setAuthorId(author.getId());
            test.setProjectId(project.getId());
            TestRepo.update(test);
        }

        for (Test t : testListToCompare) {
            Assert.assertTrue(!t.equals(TestRepo.get(t.getId())), "Two tests are equal");
        }
    }
}