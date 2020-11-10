package tests.api;

import adapters.BaseAdapter;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class APITest {
    BaseAdapter adapter = new BaseAdapter();

    public int createAndGetIDProject() {
        Response responseProject = adapter.post(new File("src/test/resources/project"), "add_project", 200);
        return responseProject.jsonPath().getInt("id");
    }

    @BeforeMethod
    public void login() {
        RestAssured.baseURI = "https://tmsshulga.testrail.io/index.php?/auth/login";
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(System.getenv("email"));
        authScheme.setPassword(System.getenv("password"));
        RestAssured.authentication = authScheme;
    }

    @Test
    public void createEditDeleteTestRunTest() {
        int projectId = createAndGetIDProject();
        Response responseRun = adapter.post(new File("src/test/resources/runs/run"), "add_run/" + projectId, 200);
        int runId = responseRun.jsonPath().getInt("id");
        responseRun = adapter.post(new File("src/test/resources/runs/edit_run"), "update_run/" + runId, 200);
        adapter.isEdited(responseRun, "name", "This is test run after updating");
        adapter.delete("delete_run/" + runId, 200);
        adapter.isDeleted("get_run/" + runId, 400, "Field :run is not a valid test run.");
        adapter.delete("delete_project/" + projectId, 200);
    }

    @Test
    public void createEditDeleteMilestoneTest() {
        int projectId = createAndGetIDProject();
        Response responseMilestone = adapter.post(new File("src/test/resources/milestones/milestone"), "add_milestone/" + projectId, 200);
        int milestoneId = responseMilestone.jsonPath().getInt("id");
        responseMilestone = adapter.post(new File("src/test/resources/milestones/edit_milestone"), "update_milestone/" + milestoneId, 200);
        adapter.isEdited(responseMilestone, "name", "Release 2.0");
        adapter.delete("delete_milestone/" + milestoneId, 200);
        adapter.isDeleted("get_milestone/" + milestoneId, 400, "Field :milestone_id is not a valid milestone.");
        adapter.delete("delete_project/" + projectId, 200);
    }

    @Test
    public void createEditDeleteTestCaseTest() {
        int projectId = createAndGetIDProject();
        Response responseSection = adapter.post(new File("src/test/resources/section"), "add_section/" + projectId, 200);
        int sectionId = responseSection.jsonPath().getInt("id");
        Response responseTestCase = adapter.post(new File("src/test/resources/testcases/testcase"), "add_case/" + sectionId, 200);
        int testcaseId = responseTestCase.jsonPath().getInt("id");
        responseTestCase = adapter.post(new File("src/test/resources/testcases/edit_testcase"), "update_case/" + testcaseId, 200);
        adapter.isEdited(responseTestCase, "title", "This is test case after updating");
        adapter.delete("delete_case/" + testcaseId, 200);
        adapter.isDeleted("get_case/" + testcaseId, 400, "Field :case_id is not a valid test case.");
        adapter.delete("delete_project/" + projectId, 200);
        adapter.isDeleted("get_project/" + projectId, 400, "Field :project_id is not a valid or accessible project.");
    }
}

