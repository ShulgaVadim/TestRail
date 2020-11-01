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
        int project_id = createAndGetIDProject();
        Response responseRun = adapter.post(new File("src/test/resources/runs/run"), "add_run/" + project_id, 200);
        int run_id = responseRun.jsonPath().getInt("id");
        responseRun = adapter.post(new File("src/test/resources/runs/edit_run"), "update_run/" + run_id, 200);
        adapter.isEdited(responseRun, "name", "This is test run after updating");
        adapter.delete("delete_run/" + run_id, 200);
        adapter.isDeleted("get_run/" + run_id, 400, "Field :run is not a valid test run.");
        adapter.delete("delete_project/" + project_id, 200);
    }

    @Test
    public void createEditDeleteMilestoneTest() {
        int project_id = createAndGetIDProject();
        Response responseMilestone = adapter.post(new File("src/test/resources/milestones/milestone"), "add_milestone/" + project_id, 200);
        int milestone_id = responseMilestone.jsonPath().getInt("id");
        responseMilestone = adapter.post(new File("src/test/resources/milestones/edit_milestone"), "update_milestone/" + milestone_id, 200);
        adapter.isEdited(responseMilestone, "name", "Release 2.0");
        adapter.delete("delete_milestone/" + milestone_id, 200);
        adapter.isDeleted("get_milestone/" + milestone_id, 400, "Field :milestone_id is not a valid milestone.");
        adapter.delete("delete_project/" + project_id, 200);
    }

    @Test
    public void createEditDeleteTestCaseTest() {
        int project_id = createAndGetIDProject();
        Response responseSection = adapter.post(new File("src/test/resources/section"), "add_section/" + project_id, 200);
        int section_id = responseSection.jsonPath().getInt("id");
        Response responseTestCase = adapter.post(new File("src/test/resources/testcases/testcase"), "add_case/" + section_id, 200);
        int testcase_id = responseTestCase.jsonPath().getInt("id");
        responseTestCase = adapter.post(new File("src/test/resources/testcases/edit_testcase"), "update_case/" + testcase_id, 200);
        adapter.isEdited(responseTestCase, "title", "This is test case after updating");
        adapter.delete("delete_case/" + testcase_id, 200);
        adapter.isDeleted("get_case/" + testcase_id, 400, "Field :case_id is not a valid test case.");
        adapter.delete("delete_project/" + project_id, 200);
        adapter.isDeleted("get_project/" + project_id, 400, "Field :project_id is not a valid or accessible project.");
    }
}

