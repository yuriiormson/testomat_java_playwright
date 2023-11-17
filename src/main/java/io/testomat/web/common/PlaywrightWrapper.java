package io.testomat.web.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PlaywrightWrapper {

    private static final ConcurrentHashMap<Long, PWContainer> pw = new ConcurrentHashMap<>();

    public static PWContainer pw() {
        return pw.computeIfAbsent(Thread.currentThread().threadId(), k -> {
            var playwright = Playwright.create();
            var browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setChannel("chrome")
                            .setHeadless(Configuration.headless)
                            .setTimeout(Configuration.browserToStartTimeout)
                            .setDevtools(Configuration.devTools)
                            .setSlowMo(Configuration.poolingInterval)
                            .setTracesDir(Paths.get(Configuration.tracesPath))
            );
            return new PWContainer(null, null, playwright, browser);
        });
    }

    public static void close() {
        long threadId = Thread.currentThread().threadId();

        if (pw.containsKey(threadId)) {
            pw.get(threadId).getPage().close();
            pw.get(threadId).getContext().close();
            pw.get(threadId).getBrowser().close();
            pw.get(threadId).getPlaywright().close();
            pw.remove(threadId);
        }

    }

    public static void initTestContext(String testName) {
        var newContextOptions = new Browser.NewContextOptions();
        newContextOptions.baseURL = Configuration.baseUrl;

        var pw = pw();
        var browserContext = pw.getBrowser().newContext(newContextOptions);
        if (Configuration.saveTraces) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setTitle(testName)
                    .setName(testName + ".zip")
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true)
            );
        }
        var targetPage = browserContext.newPage();

        pw.setContext(browserContext);
        pw.setPage(targetPage);
    }

    public static void closeContext(String testName) {
        var pw = pw();

        var targetContext = pw.getContext();
        if (Configuration.saveTraces) {
            targetContext.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(Configuration.tracesPath, testName + ".zip"))
            );
        }
        pw.getPage().close();
        targetContext.close();
    }

    public static void pausePage() {
        pw().getPage().pause();
    }

    public static void open(String url) {
        pw().getPage().navigate(url);
    }


    public static LocatorActions $(String selector) {
        pw().getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
        return find(selector);
    }

    public static LocatorActions $(String selector, String text) {
        pw().getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
        return find(selector,text);
    }

    public static LocatorActions find(String selector) {
        pw().getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
        return new LocatorActions(pw().getPage().locator(selector).first());
    }

    public static LocatorActions findAll(String selector) {
        pw().getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
        return new LocatorActions(pw().getPage().locator(selector));
    }

    public static LocatorActions find(String selector, String text) {
        pw().getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
        return new LocatorActions(pw().getPage().locator(selector).filter(
                new Locator.FilterOptions().setHasText(text)
        ));
    }


    @Data
    public static class PWContainer {

        private BrowserContext context;
        private Page page;
        private final Playwright playwright;
        private final Browser browser;

        public PWContainer(BrowserContext browserContext, Page page, Playwright playwright, Browser browser) {
            this.context = browserContext;
            this.page = page;
            this.playwright = playwright;
            this.browser = browser;
        }

    }

    public static PageActions getPageActions() {
        return new PageActions(pw().getPage());
    }

//    public static void saveStorageState(String filePath) {
//        BrowserContext context = pw().getContext();
//        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(filePath)));
//    }
//
//    public static BrowserContext createNewContextWithStorageState(String filePath) {
//        Browser browser = pw().getBrowser();
//        BrowserContext context = browser.newContext(
//                new Browser.NewContextOptions().setStorageStatePath(Paths.get(filePath)));
//        return context;
//    }

    public static void saveCookies(String filePath) {
        try {
            BrowserContext context = pw().getContext();
            List<Cookie> cookies = context.cookies();
            String cookiesJson = new Gson().toJson(cookies);
            Files.write(Paths.get(filePath), cookiesJson.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save cookies to: " + filePath, e);
        }
    }

    public static void loadCookies(String filePath) {
        try {
            String cookiesJson = new String(Files.readAllBytes(Paths.get(filePath)));
            List<Cookie> cookies = new Gson().fromJson(cookiesJson, new TypeToken<List<Cookie>>(){}.getType());
            pw().getContext().addCookies(cookies);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load cookies from: " + filePath, e);
        }
    }
}
