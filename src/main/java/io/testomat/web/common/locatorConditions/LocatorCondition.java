package io.testomat.web.common.locatorConditions;

import io.testomat.web.common.LocatorActions;

public interface LocatorCondition {
    LocatorCondition visible = new VisibleLocatorCondition();
    LocatorCondition hidden = new IsHiddenLocatorCondition();
    LocatorCondition checked = new IsCheckedCondition();
    LocatorCondition readOnly = new IsReadOnlyCondition();
    LocatorCondition empty = new IsEmptyCondition();

    static LocatorCondition text(String expectedText) {
        return new TextLocatorCondition(expectedText);
    }

    static LocatorCondition containsText(String expectedText) {
        return new ContainsTextCondition(expectedText);
    }

    static LocatorCondition notContainsText(String expectedText) {
        return new NotContainsTextCondition(expectedText);
    }

    static LocatorCondition value(String expectedValue) {
        return new ValueCondition(expectedValue);
    }

    static LocatorCondition attribute(String expectedTypeAttribute,String expectedAttributeText) {
        return new AttributeCondition(expectedTypeAttribute,expectedAttributeText);
    }

    static LocatorCondition hasCount(int expectedCount) {
        return new HasCountCondition(expectedCount);
    }


    void verify(LocatorActions locatorActions);

}
