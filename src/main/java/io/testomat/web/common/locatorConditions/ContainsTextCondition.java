package io.testomat.web.common.locatorConditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.LocatorActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class ContainsTextCondition implements LocatorCondition {

    private final String expectedText;

    @Override
    public void verify(LocatorActions locatorActions) {
        assertThat(locatorActions.getLocator()).containsText(
                expectedText,
                new LocatorAssertions.ContainsTextOptions().setTimeout(Configuration.defaultTimeout));
    }

}