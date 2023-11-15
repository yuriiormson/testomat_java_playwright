package io.testomat.web.common.locatorConditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.LocatorActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IsHiddenLocatorCondition implements LocatorCondition {

    @Override
    public void verify(LocatorActions locatorActions) {
        assertThat(locatorActions.getLocator())
                .isHidden(
                        new LocatorAssertions.IsHiddenOptions().setTimeout(Configuration.defaultTimeout)
                );
    }

    @Override
    public String toString() {
        return "hidden";
    }

}
