package io.testomat.web.pages;

import io.testomat.web.common.LocatorActions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompaniesPage extends BasePage{

    public LocatorActions getCompaniesHeader() {

        return f("h2");
    }
}
