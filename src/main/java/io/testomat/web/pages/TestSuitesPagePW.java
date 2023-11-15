package io.testomat.web.pages;

import io.testomat.web.common.LocatorActions;
import io.testomat.web.common.locatorConditions.LocatorCondition;
import lombok.extern.slf4j.Slf4j;

import static io.testomat.web.common.PlaywrightWrapper.$;
import static io.testomat.web.common.PlaywrightWrapper.find;

@Slf4j
public class TestSuitesPagePW extends BasePage {

    String firstTestSuiteSelector = "[placeholder='%s']";
    private LocatorActions firstTestSuite = $("[placeholder='First Suite']");
    protected String suitesListItem = ".list-group-wrapper .dragSortItem";


    public TestSuitesPagePW isLoaded() {
        firstTestSuite.shouldBe(LocatorCondition.visible);

        log.info("first Test Suite isLoaded");

        return this;
    }

    public TestSuitesPagePW closeReadmeModal() {
        $(".back").click();

        log.info("close Readme Modal");

        return this;
    }

    public TestSuitesPagePW fillTitleOfANewSuite(String title){
        find("#new-test-title").fill(title);

        log.info("fill Title Of A New Suite");

        return this;
    }

    public TestSuitesPagePW clickSaveTestSuite(){
        find(".detail-view-header-wrapper button").click();

        log.info("click Save Test Suite");

        return this;
    }

    public LocatorActions getTextOfTestSuiteHeader(){

       return find(".mb-4.space-y-2 h3").getText();
    }

    public TestSuitesPagePW fillFirstTestSuite(String targetTestSuite) {
        firstTestSuite.setValue(targetTestSuite).press("Enter");

        log.info("fill First Test Suite");

        return this;
    }


    //just for example
    public TestSuitesPagePW fillFirstTestSuiteName(String targetTestSuite) {
        $(String.format(firstTestSuiteSelector, "First Suite")).setValue(targetTestSuite);
        return null;
    }

    //just for example
    public TestSuitesPagePW fillSecondTestSuiteName(String targetTestSuite) {
        $("[placeholder='First Suite']").setValue(targetTestSuite);
        return null;
    }
}
