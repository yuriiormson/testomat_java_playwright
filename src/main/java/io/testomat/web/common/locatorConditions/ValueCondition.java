package io.testomat.web.common.locatorConditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import lombok.AllArgsConstructor;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.LocatorActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class ValueCondition implements LocatorCondition {

    private final String expectedValue;

    @Override
    public void verify(LocatorActions locatorActions) {
        assertThat(locatorActions.getLocator()).hasValue(
                expectedValue,
                new LocatorAssertions.HasValueOptions().setTimeout(Configuration.defaultTimeout)
        );
    }

}
