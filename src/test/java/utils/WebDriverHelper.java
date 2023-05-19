package utils;

import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ThrowingRunnable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.ONE_SECOND;


public class WebDriverHelper {
    protected WebDriver driver;
    Actions action;
    protected JavascriptExecutor jsHandler;
    public final int FLUENT_WAIT_TIMEOUT_IN_SECONDS = 25;
    protected static final Logger LOGGER = LogManager.getLogger();

    public WebDriverHelper(WebDriver driver) {
        this.driver = driver;
        this.jsHandler = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public WebDriver getCurrentDriver() {
        return driver;
    }

    public void waitForThePageToLoad() {
        assertWithTime(
                () ->
                        assertWithMessage("The page was not loaded completely")
                                .that(jsHandler.executeScript("return document.readyState").toString())
                                .isEqualTo("complete"));
    }

    public void movePageDown() {
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    private static void assertWithTime(ThrowingRunnable throwingRunnable) {
        final int SECONDS = 20;
        ConditionFactory await = await();
        await
                .pollInSameThread()
                .ignoreExceptions()
                .catchUncaughtExceptions()
                .timeout(Duration.ofSeconds(SECONDS))
                .untilAsserted(throwingRunnable);
    }

    public void waitUntilElementIsVisible(final Object element) {
        waitUntilElementIsVisible(element, FLUENT_WAIT_TIMEOUT_IN_SECONDS);
    }

    public void waitUntilElementIsVisible(final Object element, int seconds) {
        waitForThePageToLoad();
        ExpectedCondition<WebElement> condition = element instanceof WebElement
                ? ExpectedConditions.visibilityOf((WebElement) element)
                : ExpectedConditions.visibilityOfElementLocated((By) element);
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(ONE_SECOND)
                .withMessage("Element: " + element + " is not visible on the page")
                .until(condition);
    }

    public void waitUntilElementIsNotVisible(WebElement webElement) {
        waitForThePageToLoad();
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Element: " + webElement + " is not visible on the page")
                .until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void waitUntilWebElementIsClickable(WebElement webElement) {
        waitForThePageToLoad();
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Can't click on the element: " + webElement)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilTheTextIsPresentOnTheWebElement(final Object SELECTOR, String text) {
        waitForThePageToLoad();
        ExpectedCondition<Boolean> condition = SELECTOR instanceof WebElement
                ? ExpectedConditions.textToBePresentInElement((WebElement) SELECTOR, text)
                : ExpectedConditions.textToBePresentInElementLocated((By) SELECTOR, text);
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Text: " + text + " is not present in web element with locator: " + SELECTOR)
                .until(condition);
    }

    public void waitUntilElementsAreVisible(final List<WebElement> SELECTOR) {
        waitUntilElementsAreVisible(SELECTOR, FLUENT_WAIT_TIMEOUT_IN_SECONDS);
    }

    public void waitUntilElementsAreVisible(final List<WebElement> SELECTOR, int seconds) {
        waitForThePageToLoad();
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(ONE_SECOND)
                .withMessage("Empty Element List: " + SELECTOR + " the list of elements is not visible on the page")
                .until(ExpectedConditions.visibilityOfAllElements(SELECTOR));
    }

    public void waitUntilWebElementIsVisibleAndClickable(final WebElement WEB_ELEMENT) {
        waitForThePageToLoad();
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Can't click on the element: " + WEB_ELEMENT)
                .until(ExpectedConditions.visibilityOf(WEB_ELEMENT)
                        .andThen(element -> ExpectedConditions.elementToBeClickable(element).apply(driver))
                        .andThen(element -> {
                            element.click();
                            waitForThePageToLoad();
                            return element;
                        })
                );
    }

    public void waitUntilIdentifiedElementIsPresentAndClickable(By locator) {
        waitForThePageToLoad();
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Can't click on the element: " + locator)
                .until(ExpectedConditions.presenceOfElementLocated(locator)
                        .andThen(element -> ExpectedConditions.elementToBeClickable(element).apply(driver))
                        .andThen(element -> {
                            element.click();
                            waitForThePageToLoad();
                            return element;
                        })
                );
    }

    public void fillInWebElement(final WebElement WEB_ELEMENT, String text) {
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Can't enter a value in the text field: " + WEB_ELEMENT)
                .until(ExpectedConditions.elementToBeClickable(WEB_ELEMENT)
                        .andThen(element -> {
                            element.click();
                            jsHandler.executeScript("arguments[0].value='';", element);
                            return element;
                        })
                        .andThen(element -> {
                            element.clear();
                            return element;
                        })
                        .andThen(element -> {
                            waitForThePageToLoad();
                            element.sendKeys(text);
                            return element;
                        })
                );
    }

    public void clickOnWebElementByContainingTextFromAList(final List<WebElement> WEB_ELEMENTS, String text) {
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Can't click on the Element identified by: " + WEB_ELEMENTS + " and the text: " + text)
                .until(ExpectedConditions.visibilityOfAllElements(WEB_ELEMENTS)
                        .andThen(elements -> elements
                                .stream()
                                .filter(element -> element.getText().contains(text))
                                .collect(Collectors.toList()))
                        .andThen(elements -> elements
                                .stream()
                                .findFirst()
                                .orElseThrow(NotFoundException::new))
                        .andThen(element -> ExpectedConditions.elementToBeClickable(element).apply(driver))
                        .andThen(element -> {
                            waitForThePageToLoad();
                            element.click();
                            return element;
                        })
                );
    }

    public void clickOnWebElementIsEqualToTheTextFromAList(final List<WebElement> WEB_ELEMENTS, String text) {
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Can't click on the Element identified by: " + WEB_ELEMENTS + " and the text: " + text)
                .until(ExpectedConditions.visibilityOfAllElements(WEB_ELEMENTS)
                        .andThen(elements -> elements
                                .stream()
                                .filter(element -> element.getText().equalsIgnoreCase(text))
                                .collect(Collectors.toList()))
                        .andThen(elements -> elements
                                .stream()
                                .findFirst()
                                .orElseThrow(NotFoundException::new))
                        .andThen(element -> ExpectedConditions.elementToBeClickable(element).apply(driver))
                        .andThen(element -> {
                            waitForThePageToLoad();
                            element.click();
                            return element;
                        })
                );
    }

    public WebElement getWebElementFromListByIndex(List<WebElement> webElementList, int index) {
        return new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("Not Found Web Element List " + webElementList)
                .until(ExpectedConditions.visibilityOfAllElements(webElementList)
                        .andThen((elements) -> elements.get(index)));
    }

    public void verifyThePageIsNotBlank(final List<WebElement> SELECTOR, String page) {
        new FluentWait<>(driver)
                .ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT_IN_SECONDS))
                .pollingEvery(ONE_SECOND)
                .withMessage("The: " + page + " page is not displayed")
                .until(ExpectedConditions.visibilityOfAllElements((SELECTOR)));
    }

    public void moveByOffsetAndClick(int xOffset, int yOffset) {
        new Actions(driver).moveByOffset(xOffset, yOffset).click().build().perform();
    }

    public void doubleClickOnTheElement(WebElement element) {
        waitUntilElementIsVisible(element);
        new Actions(driver).doubleClick(element).perform();
    }

    public void selectOptionByTextFromDropdown(WebElement selectComponent, String text) {
        Select dropdown = new Select(selectComponent);

        try {
            dropdown.selectByVisibleText(text);
        } catch (NoSuchElementException e) {
            loggerError("The text: " + text + " cannot be located in the dropdown");
            loggerError("Error: " + e);
            throw new AssertionError("The text: " + text + " cannot be located in the dropdown");
        }
    }

    public void assertVerifyThePageIsNotBlank(final List<WebElement> SELECTOR, String page) {
        assertWithMessage("The: %s  page is not displayed %s", page, SELECTOR)
                .that(SELECTOR)
                .isNotEmpty();
    }

    public void assertURLContainsText(String partOfTheURL) {
        assertWithTime(() ->
                assertWithMessage("The current URL doesn't contains the described text: %s, it was obtained: %s", partOfTheURL, driver.getCurrentUrl())
                        .that(driver.getCurrentUrl())
                        .contains(partOfTheURL)
        );
    }

    public static void assertThatTheTextIsEqualTo(String text, final WebElement SELECTOR) {
        assertWithTime(() ->
                assertWithMessage("Expected this text: %s,  got instead %s", text, SELECTOR.getText())
                        .that(SELECTOR.getText().trim())
                        .isEqualTo(text.trim())
        );
    }

    public static void assertThatTheTextContains(String text, final WebElement SELECTOR) {
        assertWithTime(() ->
                assertWithMessage("Expected this text: '%s', got instead: '%s'", text, SELECTOR.getText())
                        .that(SELECTOR.getText().trim())
                        .contains(text.trim())
        );
    }

    public static void assertIsTrue(String message, boolean condition) {
        assertWithMessage(message).that(condition).isTrue();
    }

    public static void assertIsFalse(String message, boolean condition) {
        assertWithMessage(message).that(condition).isFalse();
    }

    public static void assertIsNotNull(String message, Object locator) {
        assertWithMessage(message).that(locator).isNotNull();
    }

    public static void assertThatTheAttributeValueIsEqualTo(String attribute, String value, final WebElement SELECTOR) {
        String attributeValue = SELECTOR.getAttribute(attribute).trim();

        if (attribute.equalsIgnoreCase("class")) {
            List<String> webElementAttributeValues = List.of(attributeValue.split(" "));
            attributeValue = webElementAttributeValues.size() > 1
                    ? webElementAttributeValues.stream().filter(e -> e.equalsIgnoreCase(value)).findFirst().orElse(null)
                    : value;
        }

        String finalAttributeValue = attributeValue;
        assertWithTime(() ->
                assertWithMessage("Expected this attribute: %s, got instead: %s", value, finalAttributeValue)
                        .that(finalAttributeValue)
                        .isEqualTo(value)
        );
    }

    public void assertThatTheAttributeValueContains(String attribute, String value, final WebElement SELECTOR) {
        String attributeValue = SELECTOR.getAttribute(attribute).trim();
        assertWithTime(() ->
                assertWithMessage("Expected this attribute: %s, got instead: %s", value, attributeValue)
                        .that(attributeValue.trim())
                        .contains(value.trim())
        );
    }

    public static void assertThatTheCSSPropertyValueIsEqualTo(String cssProperty, String value, final WebElement SELECTOR) {
        assertWithTime(() ->
                assertWithMessage("Expected this value: %s,  got instead %s", value, SELECTOR.getCssValue(cssProperty))
                        .that(SELECTOR.getCssValue(cssProperty.trim()))
                        .isEqualTo(value.trim())
        );
    }

    public static void assertThatTheButtonIsEnabled(final WebElement SELECTOR) {
        assertWithTime(() ->
                assertWithMessage("It is expected that the button is enabled: true,  got instead: %s", SELECTOR.isEnabled())
                        .that(SELECTOR.isEnabled())
                        .isTrue()
        );
    }

    public static void assertThatTheButtonIsDisabled(final WebElement SELECTOR) {
        assertWithTime(() ->
                assertWithMessage("It is expected that the button is disabled: true,  got instead: %s", !SELECTOR.isEnabled())
                        .that(!SELECTOR.isEnabled())
                        .isTrue()
        );
    }

    public void loggerInfo(String message) {
        LOGGER.info(message);
    }

    public void loggerError(String message) {
        LOGGER.error(message);
    }

    public int getRandomIndex(int sizeList) {
        Random random = new Random();
        return random.nextInt(sizeList - 1);
    }

    public void waitToLoad(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
