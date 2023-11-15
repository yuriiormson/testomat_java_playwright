package io.testomat.web.playwright;

import com.microsoft.playwright.Locator;
import io.testomat.web.common.PlaywrightWrapper;
import io.testomat.web.common.pageCondition.PageCondition;
import io.testomat.web.pages.CompaniesPage;
import net.datafaker.Faker;
import io.testomat.web.common.PWContextExtension;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.locatorConditions.LocatorCondition;
import io.testomat.web.pages.LoginPagePW;
import io.testomat.web.pages.ProjectsPagePW;
import org.testng.annotations.Test;

import static com.microsoft.playwright.options.WaitForSelectorState.HIDDEN;
import static io.testomat.web.common.Configuration.loaderTimeout;
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
    public void HomePageIsLoadedSuccessfullyAfterLogIn() {
        open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("yurii.ormson@gmail.com", "zEqgib-histuc-qibxo1"); //or loginUser(CredsWithRoles.MANAGER);

        preloaderIsHidden();

        // After logging in, save the cookies to a file
        PlaywrightWrapper.saveCookies(STORAGE_STATE_FILE);

        new ProjectsPagePW()
                .getHomePageHeader()
                .shouldHave(LocatorCondition.text("Projects"));

    }

    @Test(dependsOnMethods = {"HomePageIsLoadedSuccessfullyAfterLogIn"})
    public void UserAbleToSearchTheProject() {
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");

        preloaderIsHidden();

        new ProjectsPagePW()
                .clickOnListView()
                .fillSearchProject("Computers")
                .clickOnItemInTheList("\n" +
                        "                            Computers & Tools\n" +
                        "                          ");

        new ProjectsPagePW()
                .getProjectHeader()
                .shouldHave(LocatorCondition.text("Computers & Tools"));
    }

    @Test
    public void shouldBePossibleToCreateTestSuiteForTheProject() {
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");

        preloaderIsHidden();

        new ProjectsPagePW()
                .clickOnListView()
                .fillSearchProject("Computers")
                .clickOnItemInTheList("\n" +
                        "                            Computers & Tools\n" +
                        "                          ");

        String targetTestSuite = faker.commerce().productName();

        new ProjectsPagePW()
                .clickToAdd()
                .clickToAddSuite()
                .fillTitleOfANewSuite(targetTestSuite)
                .clickSaveTestSuite()
                .getTextOfTestSuiteHeader()
                .shouldHave(LocatorCondition.text(targetTestSuite));
    }
    @Test
    public void shouldBePossibleToOpenTheCompanyPage(){
        var expectedCompanyPageURL = "https://uat.testomat.io/companies";
        var expectedCompaniesHeader = "Companies";
        var expectedCompaniesTitle = "Companies - Testomat.io";

        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("/companies");

        preloaderIsHidden();

        getPageActions().getUrl().shouldHave(PageCondition.url(expectedCompanyPageURL));
        new CompaniesPage().getCompaniesHeader().shouldHave(LocatorCondition.text(expectedCompaniesHeader));
        getPageActions().getTitle().shouldHave(PageCondition.title(expectedCompaniesTitle));
    }

    private void preloaderIsHidden() {
        $("#app-loader").waitFor(new Locator.WaitForOptions().setState(HIDDEN).setTimeout(loaderTimeout));
    }
}
