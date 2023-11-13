private static final String COOKIES_FILE = "playwright/.auth/cookies.json";

@Test
public void test1() {
// ... (existing test code)

    // After logging in, save the cookies to a file
    PlaywrightWrapper.saveCookies(COOKIES_FILE);

    // ... (remaining test code)
}

@Test
public void test2() {
// Load the saved cookies before running the test
PlaywrightWrapper.loadCookies(COOKIES_FILE);

    open("");

    // Comment out loginUser as you are reusing the saved cookies
    // loginPage.isLoaded().loginUser("test@gmail.com", "password");

    // ... (remaining test code)
}

This approach should work for reusing the signed-in state.
In the first test, we save the cookies after the user logs in, and in the second test,
we load these cookies before performing any actions.
This should allow the second test to start with the user already logged in.