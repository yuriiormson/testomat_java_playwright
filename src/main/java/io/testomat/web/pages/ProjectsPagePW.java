package io.testomat.web.pages;

import io.testomat.web.common.LocatorActions;
import io.testomat.web.common.locatorConditions.LocatorCondition;
import lombok.extern.slf4j.Slf4j;

import static io.testomat.web.common.PlaywrightWrapper.*;

@Slf4j
public class ProjectsPagePW extends BasePage {

    public ProjectsPagePW isLoaded() {
        f("h2").shouldHave(LocatorCondition.text("Projects"));

        log.info("H2 Header is Loaded");
        return this;
    }

    public ProjectsPagePW fillSearchProject(String searchValue) {
        f("input[id='search']").pressSequentially(searchValue);

        log.info("fill Search Project");
        return this;
    }

    public ProjectsPagePW clickOnListView(){
        f("#table-icon").click();

        log.info("click On List View");

        return this;
    }

    public ProjectsPagePW clickToAdd(){
        find("#ember31").click();

        return this;
    }

    public TestSuitesPagePW clickToAddSuite() {
        getPageActions().clickByRoleButton("Suite Collection of test cases");

        return new TestSuitesPagePW();
    }

    public ProjectsPagePW clickOnItemInTheList(String locatorText){
        f(".dark\\:bg-dark-800 div a div",locatorText).click();

        return new ProjectsPagePW();
    }

    public LocatorActions getHomePageHeader() {

        return f("h2");
    }

    public LocatorActions getProjectHeader() {
        return find(".sticky-header h2").getText();
    }

    public ProjectsPagePW closeReadmeModal() {
        $(".back").click();

        log.info("close Readme Modal");

        return this;
    }

    public ProjectsPagePW clickOnNewProjectButton() {
        f("[href='/projects/new']").click();

        log.info("click On [New Project] Button");

        return this;
    }

    public ProjectsPagePW ololoTest() {
        find("#id", "ololo").click();
        return this;
    }

    public ProjectsPagePW fillProjectTitle(String projectTitle) {
        $("#project-form #project_title").setValue(projectTitle);

        log.info("fill Project Title");

        return this;
    }

    public ProjectsPagePW submitProjectCreation() {
        $("[name='commit']").click();

        log.info("submit Project Creation");

        return this;
    }

    public ProjectsPagePW checkProjectExist(String projectTitle) {
        return this;
    }

}
