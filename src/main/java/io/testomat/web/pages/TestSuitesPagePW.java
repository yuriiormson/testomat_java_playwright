package io.testomat.web.pages;

import io.testomat.web.common.LocatorActions;
import io.testomat.web.common.conditions.Condition;
import lombok.extern.slf4j.Slf4j;

import static io.testomat.web.common.PlaywrightWrapper.$;

@Slf4j
public class TestSuitesPagePW extends BasePage {

    String firstTestSuiteSelector = "[placeholder='%s']";
    private LocatorActions firstTestSuite = $("[placeholder='First Suite']");
    protected String suitesListItem = ".list-group-wrapper .dragSortItem";


    public TestSuitesPagePW isLoaded() {
        firstTestSuite.shouldBe(Condition.visible);

        log.info("first Test Suite isLoaded");

        return this;
    }

    public TestSuitesPagePW closeReadmeModal() {
        $(".back").click();

        log.info("close Readme Modal");

        return this;
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
