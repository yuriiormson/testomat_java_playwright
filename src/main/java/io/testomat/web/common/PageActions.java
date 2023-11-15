package io.testomat.web.common;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Data;
import io.testomat.web.common.pageCondition.PageCondition;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PageActions {

    private final Page page;

    public PageActions shouldHave(PageCondition pageCondition) {
        pageCondition.verifyPage(this);
        return this;
    }

    public PageActions should(PageCondition pageCondition) {
        pageCondition.verifyPage(this);
        return this;
    }

    public PageActions getTitle() {
        page.title();
        return this;
    }

    public PageActions getUrl() {
        page.url();
        return this;
    }

    public PageActions clickByRoleButton(String setName) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(setName).setExact(true)).click();
        return this;
    }

    public void setInputFilesByLabel(String labelText, String fileName) {
        page.getByLabel(labelText).setInputFiles(Paths.get("src/test/resources/" + fileName));
    }

    public void setInputFilesByLabelWithFullFilePath(String labelText, String filePath) {
        page.getByLabel(labelText).setInputFiles(Paths.get(filePath));
    }

    public List<String> getTextsOfLocator(String cssSelector) {
        List<ElementHandle> elements = page.querySelectorAll(cssSelector);
        return elements.stream()
                .map(element -> element.textContent().trim())
                .collect(Collectors.toList());
    }
 }
