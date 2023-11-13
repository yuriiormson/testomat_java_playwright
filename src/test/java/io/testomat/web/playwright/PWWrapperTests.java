package io.testomat.web.playwright;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import io.testomat.web.common.PlaywrightWrapper;
import net.datafaker.Faker;
import io.testomat.web.common.PWContextExtension;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.conditions.Condition;
import io.testomat.web.pages.LoginPagePW;
import io.testomat.web.pages.ProjectsPagePW;
import io.testomat.web.pages.TestSuitesPagePW;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import java.nio.file.Paths;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import static io.testomat.web.common.PlaywrightWrapper.$;
import static io.testomat.web.common.PlaywrightWrapper.open;


public class PWWrapperTests extends PWContextExtension{
    private static final String STORAGE_STATE_FILE = "playwright/.auth/state.json";

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.headless = true;
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

//        PlaywrightWrapper.saveStorageState(STORAGE_STATE_FILE);

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
//        // Load the saved storage state before running the test
//        BrowserContext context = PlaywrightWrapper.createNewContextWithStorageState(STORAGE_STATE_FILE);
//
//        // Set the new context with the loaded storage state in the PWContainer
//        PlaywrightWrapper.pw().setContext(context);

        // Load the saved cookies before running the test
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");
//        loginPage
//                .isLoaded()
//                .loginUser("newromka@gmail.com", "3y77b7HzrL2ebwQ!");
        //or loginUser(CredsWithRoles.MANAGER);

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
        open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("newromka@gmail.com", "3y77b7HzrL2ebwQ!"); //or loginUser(CredsWithRoles.MANAGER);

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

//                .asserts()
//                .listShouldHaveSize(1)
//                .firstTestSuiteInListShouldHaveText(targetTestSuite);
    }

    private void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear);
    }

}
