package base;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import connection.ConnectDB;
import model.Author;
import model.Project;
import model.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import repo.TestRepo;
import java.util.Date;

public abstract class BaseTest {
    protected ISettingsFile env = new JsonSettingsFile("data.json");
    protected ObjectMapper mapper = new ObjectMapper();
    protected Author author;
    protected Project project;

    @AfterMethod
    protected void destroy(ITestResult testResult) {
        initializeAuthorAndProject();
        TestRepo.add(new Test(
                testResult.getName(),
                new Date(testResult.getEndMillis()).toString(),
                testResult.getStatus(),
                author.getId(), project.getId()));
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