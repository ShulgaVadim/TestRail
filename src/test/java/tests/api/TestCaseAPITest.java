package tests.api;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

@Log4j2
public class TestCaseAPITest {

    @Test
    public void createEditProject() throws Exception {
        APIClient client = new APIClient("https://aqa6shulga.testrail.io/");
        client.setUser("stiffler88@bk.ru"); // getEnv
        client.setPassword("St5777758"); //getEnv

        HashMap projectData = new HashMap();
        projectData.put("announcement", "The first project");
        projectData.put("name", "TestRail");
        projectData.put("show_announcement", true);
        projectData.put("suite_mode", 1);
        JSONObject project = (JSONObject) client.sendPost("add_project", projectData);
        Assert.assertEquals(project.get("name"), "TestRail");

        HashMap editProjectData = new HashMap();
        String editedAnnouncement = "The project was edited";
        editProjectData.put("announcement", editedAnnouncement);
        JSONObject editedProject = (JSONObject) client.sendPost("update_project/" + project.get("id"), editProjectData);
        Assert.assertEquals(editedProject.get("announcement"), editedAnnouncement);
    }


    @Test
    public void createEditTestCase() throws Exception {
        APIClient client = new APIClient("https://aqa6shulga.testrail.io/");
        client.setUser("stiffler88@bk.ru");
        client.setPassword("St5777758");

        HashMap caseData = new HashMap();
        caseData.put("title", "The first Test Case");
        caseData.put("type_id", 1);
        caseData.put("priority_id", 3);
        caseData.put("estimate", "30s");
        JSONObject testCase = (JSONObject) client.sendPost("add_case/44", caseData);
        System.out.println(testCase.get("id"));
        Assert.assertEquals(testCase.get("title"), "The first Test Case");

        HashMap editedCaseData = new HashMap();
        editedCaseData.put("title", "Edited Test Case");
        JSONObject editedTestCase = (JSONObject) client.sendPost("update_case/" + testCase.get("id"), editedCaseData);
        Assert.assertEquals(editedTestCase.get("title"), "Edited Test Case");
    }
}

