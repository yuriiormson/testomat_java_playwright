package io.testomat.web.pages;

import io.testomat.web.common.conditions.Condition;
import lombok.extern.slf4j.Slf4j;

import static io.testomat.web.common.PlaywrightWrapper.$;
import static io.testomat.web.common.PlaywrightWrapper.find;

@Slf4j
public class ProjectsPagePW extends BasePage {

    public ProjectsPagePW isLoaded() {
        f("h2").shouldHave(Condition.text("Projects"));

        log.info("H2 Header is Loaded");
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
