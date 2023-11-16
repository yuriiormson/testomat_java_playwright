package io.testomat.web.playwright;

import com.microsoft.playwright.Locator;
import io.testomat.configutils.ConfProperties;
import io.testomat.configutils.LoggingConfProperties;
import io.testomat.confutils.ConfigReader;
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
    protected ConfProperties properties = new LoggingConfProperties(new ConfProperties());
    private static final String STORAGE_STATE_FILE = "playwright/.auth/state.json";

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();
    private final ProjectsPagePW projectsPagePW = new ProjectsPagePW();

    static {
        Configuration.baseUrl = ConfigReader.getBaseUrl();
        Configuration.headless = false;
        Configuration.saveTraces = false;
        Configuration.poolingInterval = 0;
    }

    @Test
    public void HomePageIsLoadedSuccessfullyAfterLogIn() {
        open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser(properties.getProperty("Username"),properties.getProperty("Password"));

        preloaderIsHidden();

        // After logging in, save the cookies to a file
        PlaywrightWrapper.saveCookies(STORAGE_STATE_FILE);

        projectsPagePW
                .getHomePageHeader()
                .shouldHave(LocatorCondition.text("Projects"));

    }

    @Test(dependsOnMethods = {"HomePageIsLoadedSuccessfullyAfterLogIn"})
    public void UserAbleToSearchTheProject() {
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");

        preloaderIsHidden();

        projectsPagePW
                .clickOnListView()
                .fillSearchProject("Computers")
                .clickOnItemInTheList("\n" +
                        "                            Computers & Tools\n" +
                        "                          ");

        projectsPagePW
                .getProjectHeader()
                .shouldHave(LocatorCondition.text("Computers & Tools"));
    }

    @Test
    public void shouldBePossibleToCreateTestSuiteForTheProject() {
        PlaywrightWrapper.loadCookies(STORAGE_STATE_FILE);

        open("");

        preloaderIsHidden();

        projectsPagePW
                .clickOnListView()
                .fillSearchProject("Computers")
                .clickOnItemInTheList("\n" +
                        "                            Computers & Tools\n" +
                        "                          ");

        String targetTestSuite = faker.commerce().productName();

        projectsPagePW
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
