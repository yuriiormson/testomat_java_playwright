package io.testomat.web.common.pageCondition;

import lombok.AllArgsConstructor;
import io.testomat.web.common.PageActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class UrlCondition implements PageCondition {

    private final String expectedPageUrl;

    @Override
    public void verifyPage(PageActions pageActions) {
        assertThat(pageActions.getPage()).hasURL(expectedPageUrl);
    }
}