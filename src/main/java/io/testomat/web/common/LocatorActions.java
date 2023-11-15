package io.testomat.web.common;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;
import io.testomat.web.common.locatorConditions.LocatorCondition;
import lombok.Data;

@Data
public class LocatorActions {

    private final Locator locator;

    public LocatorActions $(String selector) {
        locator.locator(selector);
        return this;
    }

    public LocatorActions fill(String text) {
        locator.fill(text);
        return this;
    }

    public LocatorActions setValue(String text) {
        return fill(text);
    }

    public LocatorActions press(String key) {
        locator.press(key);
        return this;
    }

    public LocatorActions click() {
        locator.click();
        return this;
    }



    public void waitFor(Locator.WaitForOptions options) {
        locator.waitFor(options);
    }

    public LocatorActions getText() {
        locator.textContent(new Locator.TextContentOptions().setTimeout(Configuration.locatorTimeout));
        return this;
    }

    public void pressSequentially(String text) {
        locator.pressSequentially(text);
    }

    public void fillAfterClear(String text) {
        clear();
        locator.fill(text, new Locator.FillOptions().setTimeout(Configuration.locatorTimeout));
    }

    public String getTextString() {
        return locator.textContent(new Locator.TextContentOptions().setTimeout(Configuration.locatorTimeout));
    }

    public LocatorActions clear() {
        locator.clear();
        return this;
    }

    public LocatorActions check() {
        locator.check(new Locator.CheckOptions().setTimeout(Configuration.locatorTimeout));
        return this;
    }

    public void uncheck() {
        locator.uncheck(new Locator.UncheckOptions().setTimeout(Configuration.locatorTimeout));
    }

    public LocatorActions selectByValue(String value) {
        locator.selectOption(value);
        return this;
    }

    public void selectByLabel(String label) {
        locator.selectOption(new SelectOption().setLabel(label));
    }

    public void selectByIndex(int index) {
        locator.selectOption(new SelectOption().setIndex(index));
    }

    public LocatorActions getAttribute(String name) {
        locator.getAttribute(name, new Locator.GetAttributeOptions().setTimeout(Configuration.defaultTimeout));
        return this;
    }

    public LocatorActions getInputValue() {
        locator.inputValue();
        return this;
    }

    public boolean isEditable() {
        return locator.isEditable();
    }

    public boolean isVisible() {
        return locator.isVisible();
    }

    public LocatorActions shouldHave(LocatorCondition locatorCondition) {
        locatorCondition.verify(this);
        return this;
    }

    public LocatorActions shouldHas(LocatorCondition locatorCondition) {
        locatorCondition.verify(this);
        return this;
    }

    public LocatorActions should(LocatorCondition locatorCondition) {
        locatorCondition.verify(this);
        return this;
    }

    public LocatorActions shouldBe(LocatorCondition locatorCondition) {
        locatorCondition.verify(this);
        return this;
    }
}
