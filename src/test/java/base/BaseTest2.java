package base;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import connection.ConnectDB;
import model.Author;
import model.Project;
import model.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import repo.TestRepo;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest2 {
    protected ISettingsFile env = new JsonSettingsFile("data.json");
    protected ObjectMapper mapper = new ObjectMapper();
    protected Author author;
    protected Project project;

    protected List<Test> testListToCompare;
    protected List<Test> testList;
    protected List<Integer> testIdToDelete = new ArrayList<>();

    @BeforeMethod
    protected void init() {
        initializeAuthorAndProject();
        testListToCompare = TestRepo.getListReDigits();
        testList = TestRepo.getListReDigits();

        for (Test t : testList) {
            testIdToDelete.add(TestRepo.add(t));
        }
    }

    @AfterMethod
    protected void destroy() {
        for (int id : testIdToDelete) {
            TestRepo.delete(id);
        }
        ConnectDB.closeConnection();
    }

    private void initializeAuthorAndProject() {
        try {
            author = mapper.readValue(env.getValue("/author").toString(), Author.class);
            project = mapper.readValue(env.getValue("/project").toString(), Project.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}