package io.testomat.web.playwright;

import io.testomat.web.common.PlaywrightWrapper;
import net.datafaker.Faker;
import io.testomat.web.common.PWContextExtension;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.conditions.Condition;
import io.testomat.web.pages.LoginPagePW;
import io.testomat.web.pages.ProjectsPagePW;
import io.testomat.web.pages.TestSuitesPagePW;
import org.testng.annotations.Test;

import static io.testomat.web.common.PlaywrightWrapper.*;


public class PWWrapperTests extends PWContextExtension{
    private static final String STORAGE_STATE_FILE = "playwright/.auth/state.json";

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.headless = false;
        Configuration.saveTraces = false;
        Configuration.poolingInterval = 0;
    }

    @Test
    public void shouldBePossibleToCreateTestSuiteForNewProject() {
        open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("newromka@gmail.com", "3y77b7HzrL2ebwQ!"); //or loginUser(CredsWithRoles.MANAGER);

        preloaderIsHidden();

        // After logging in, save the cookies to a file
        PlaywrightWrapper.saveCookies(STORAGE_STATE_FILE);

        final var targetProjectTitle = faker.commerce().department();
        new ProjectsPagePW()
                .isLoaded()
                .clickOnNewProjectButton()
                .fillProjectTitle(targetProjectTitle)
                .submitProjectCreation();

        preloaderIsHidden();

        String targetTestSuite = faker.commerce().productName();

        new TestSuitesPagePW()
                .isLoaded()
                .closeReadmeModal()
                .fillFirstTestSuite(targetTestSuite);

    }

    @Test
    public void shouldBePossibleToCreateTestSuiteForNewProject2() {
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");

        preloaderIsHidden();

        final var targetProjectTitle = faker.commerce().department();
        new ProjectsPagePW()
                .isLoaded()
                .clickOnNewProjectButton()
                .fillProjectTitle(targetProjectTitle)
                .submitProjectCreation();

        preloaderIsHidden();

        String targetTestSuite = faker.commerce().productName();

        new TestSuitesPagePW()
                .isLoaded()
                .closeReadmeModal()
                .fillFirstTestSuite(targetTestSuite);

    }

    @Test
    public void shouldBePossibleToCreateTestSuiteForNewProject3() {
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");

        preloaderIsHidden();

        final var targetProjectTitle = faker.commerce().department();
        new ProjectsPagePW()
                .isLoaded()
                .clickOnNewProjectButton()
                .fillProjectTitle(targetProjectTitle)
                .submitProjectCreation();

        preloaderIsHidden();

        String targetTestSuite = faker.commerce().productName();

        new TestSuitesPagePW()
                .isLoaded()
                .closeReadmeModal()
                .fillFirstTestSuite(targetTestSuite);
    }

    private void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear);
    }

}
