package io.testomat.web.pages;

import io.testomat.web.common.LocatorActions;

import static io.testomat.web.common.PlaywrightWrapper.$;


public class BasePage {

private final String baseContent = System.getProperty("isMobile") == null ? "#content-desktop " : "#content-mobile ";

    protected LocatorActions f(String selector) {
        return $(baseContent + selector);
    }

    protected LocatorActions f(String selector,String selectorText) {
        return $(baseContent + selector,selectorText);
    }

}
