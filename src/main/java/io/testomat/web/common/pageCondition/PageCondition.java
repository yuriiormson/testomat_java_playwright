package io.testomat.web.common.pageCondition;

import io.testomat.web.common.PageActions;

import java.util.regex.Pattern;

public interface PageCondition {

    static TitleCondition title(String expectedTitle) {
        return new TitleCondition(expectedTitle);
    }

    static ContainsTextInElementsListCondition listOfElementsContainsText(
            String locator, String[] expectedText) {
        return new ContainsTextInElementsListCondition(locator,expectedText);
    }

    static NotContainsTextInElementsListCondition listOfElementsNotContainsText(
            String locator, String[] expectedText) {
        return new NotContainsTextInElementsListCondition(locator,expectedText);
    }

    static UrlCondition url(String expectedUrl) {
        return new UrlCondition(expectedUrl);
    }

    static UrlContainsCondition urlContains(Pattern expectedUrl) {
        return new UrlContainsCondition(expectedUrl);
    }

    void verifyPage(PageActions pageActions);

}
