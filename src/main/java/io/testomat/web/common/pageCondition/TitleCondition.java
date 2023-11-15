package io.testomat.web.common.pageCondition;


import lombok.AllArgsConstructor;
import io.testomat.web.common.PageActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class TitleCondition implements PageCondition {

    private final String expectedPageTitle;
    @Override
    public void verifyPage(PageActions pageActions) {
        assertThat(pageActions.getPage()).hasTitle(expectedPageTitle);
    }
}
