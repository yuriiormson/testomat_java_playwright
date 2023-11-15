package io.testomat.web.common.locatorConditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.web.common.Configuration;
import io.testomat.web.common.LocatorActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IsReadOnlyCondition implements LocatorCondition {

    @Override
    public void verify(LocatorActions locatorActions) {
        assertThat(locatorActions.getLocator()).not().isEditable(
                new LocatorAssertions.IsEditableOptions().setTimeout(Configuration.defaultTimeout)
        );
    }

}