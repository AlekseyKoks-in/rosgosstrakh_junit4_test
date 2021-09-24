package com.alex.study.tests;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class RgsScenarioTest {

    WebDriver webDriver;
    WebDriverWait webDriverWait;
    Actions actions;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, 30, 1000);
        actions = new Actions(webDriver);
        webDriver.get("https://www.rgs.ru/");
    }

    @Test
    public void test() {

        String cookieOkXPath = "//div[@class='btn btn-default text-uppercase']";
        waitUtilElementToBeVisible(By.xpath(cookieOkXPath));
        waitUtilElementToBeClickable(webDriver.findElement(By.xpath(cookieOkXPath)));
        WebElement cookieOk = webDriver.findElement(By.xpath(cookieOkXPath));
        cookieOk.click();

        String frameCovidXPath = "//iframe[@class='flocktory-widget']";
        String buttonFrameCovidCloseXPath = "//button[@class= 'CloseButton']";
        closeFrame(By.xpath(frameCovidXPath), By.xpath(buttonFrameCovidCloseXPath));

        String menuXPath = "//a[@class='hidden-xs' and contains(text(),'Меню')]";
        waitUtilElementToBeVisible(By.xpath(menuXPath));
        WebElement dropdownMenu = webDriver.findElement(By.xpath(menuXPath));
        waitUtilElementToBeClickable(dropdownMenu);
        dropdownMenu.click();

        String companiesXPath = "//a[contains(text(),'Компаниям')]";
        waitUtilElementToBeVisible(By.xpath(companiesXPath));
        WebElement buttonCompanies = webDriver.findElement(By.xpath(companiesXPath));
        waitUtilElementToBeClickable(buttonCompanies);
        buttonCompanies.click();

        switchToNewWindow(By.xpath("//a[contains(text(),'Страхование здоровья')]"));

        String voluntaryMedicalInsuranceXPath = "//a[contains(@class, 'adv-analytics-navigation') and contains(@href, '/health/dms')]";
        waitUtilElementToBeVisible(By.xpath(voluntaryMedicalInsuranceXPath));
        WebElement voluntaryMedicalInsurance = webDriver.findElement(By.xpath(voluntaryMedicalInsuranceXPath));
        waitUtilElementToBeClickable(voluntaryMedicalInsurance);
        voluntaryMedicalInsurance.click();

        String titleVoluntaryMedicalInsuranceXPath = "//h1[@class='content-document-header']";
        waitUtilElementToBeVisible(By.xpath(titleVoluntaryMedicalInsuranceXPath));
        WebElement titleVoluntaryMedicalInsurance = webDriver.findElement(By.xpath(titleVoluntaryMedicalInsuranceXPath));
        String titleVoluntaryMedicalInsuranceMassage = "Заголовок \"Добровольное медецинское страхование\", отсутствует";
        Assert.assertEquals(titleVoluntaryMedicalInsuranceMassage, "Добровольное медицинское страхование",
                titleVoluntaryMedicalInsurance.getText());

        String sendRequestXPath = "//a[contains(@class, 'btn btn-default text-uppercase')]";
        waitUtilElementToBeVisible(By.xpath(sendRequestXPath));
        WebElement buttonSendRequest = webDriver.findElement(By.xpath(sendRequestXPath));
        waitUtilElementToBeClickable(buttonSendRequest);
        buttonSendRequest.click();

        String dialogWindowXPath = "//div[@class='modal-dialog']";
        String titleDialogWindowXPath = "//b[@data-bind = 'text: options.title']";
        waitUtilElementToBeVisible(webDriver.findElement(By.xpath(dialogWindowXPath)));
        WebElement dialogWindow = webDriver.findElement(By.xpath(dialogWindowXPath));
        waitUtilElementToBeVisible(By.xpath(titleDialogWindowXPath));
        WebElement titleDialogWindow = webDriver.findElement(By.xpath(titleDialogWindowXPath));
        Assert.assertTrue("Диалоговое окно заявки на ДМС не открылось", dialogWindow.isDisplayed()
                && titleDialogWindow.getText().contains("Заявка на добровольное медицинское страхование"));

        String frameStockXPath = "//iframe[@class='flocktory-widget' and contains(@style, '500px')]";
        String buttonFrameStockCloseXPath = "//div[@class = 'widget__close js-collapse-login']";
        closeFrame(By.xpath(frameStockXPath), By.xpath(buttonFrameStockCloseXPath));

        String fieldLastNameXPath = "//input[@name= 'LastName']";
        WebElement fieldLastName = webDriver.findElement(By.xpath(fieldLastNameXPath));
        fillInputField(fieldLastName, "Иванов");

        String fieldNameXpath = "//input[@name= 'FirstName']";
        WebElement fieldName = webDriver.findElement(By.xpath(fieldNameXpath));
        fillInputField(fieldName, "Иван");

        String fieldMiddleNameXpath = "//input[@name= 'MiddleName']";
        WebElement fieldMiddleName = webDriver.findElement(By.xpath(fieldMiddleNameXpath));
        fillInputField(fieldMiddleName, "Иванович");

        Select region = new Select(webDriver.findElement(By.xpath("//select[@name = 'Region']")));
        region.selectByVisibleText("Москва");

        String fieldPhoneXPath = "//input[contains(@data-bind, 'Phone')]";
        WebElement fieldPhone = webDriver.findElement(By.xpath(fieldPhoneXPath));
        actions.sendKeys(fieldPhone, "9998885533").sendKeys(Keys.ENTER).build().perform();

        String fieldEmailXPath = "//input[@name= 'Email']";
        WebElement fieldEmail = webDriver.findElement(By.xpath(fieldEmailXPath));
        fieldEmail.click();
        fillInputField(fieldEmail, "qwertyqwerty");

        String fieldDateXPath = "//input[@name= 'ContactDate']";
        WebElement fieldDate = webDriver.findElement(By.xpath(fieldDateXPath));
        fieldDate.click();
        String fieldSelectDateXPath = "//td[@class='datepicker-day' and contains(text(),'30')]";
        WebElement fieldSelectDate = webDriver.findElement(By.xpath(fieldSelectDateXPath));
        fieldSelectDate.click();

        String fieldCommentXPath = "//textarea[@class= 'popupTextarea form-control']";
        WebElement fieldComment = webDriver.findElement(By.xpath(fieldCommentXPath));
        fillInputField(fieldComment, "aaa");

        String checkBoxIAcceptXPath = "//input[@type='checkbox']";
        WebElement checkBoxIAccept = webDriver.findElement(By.xpath(checkBoxIAcceptXPath));
        checkBoxIAccept.click();

        checkErrorMessageAtField(fieldLastName, "*");
        checkErrorMessageAtField(fieldName, "*");
        checkErrorMessageAtField(fieldDate, "*");
        checkErrorMessageAtField(fieldPhone, "*");

        String buttonSendXPath = "//button[@id='button-m']";
        WebElement buttonSend = webDriver.findElement(By.xpath(buttonSendXPath));
        buttonSend.click();

        String checkEmailXPath = "//span[@class='validation-error-text' and contains(text(), 'Введите адрес электронной почты')]";
        String emailErrorMessage = "Пропускает возможность заполнить поле не валидным значением";
        Assert.assertEquals(emailErrorMessage, "Введите адрес электронной почты", webDriver.findElement(By.xpath(checkEmailXPath)).getText());

    }

    @After
    public void after() {
        webDriver.quit();
    }

    public void switchToNewWindow(By byPath) {
        final Set<String> oldWindowsSet = webDriver.getWindowHandles();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(byPath)).click();

        String newWindow = (new WebDriverWait(webDriver, 10))
                .until(new ExpectedCondition<>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl WebDriver driver) {
                        Assert.assertNotNull("Driver equals null" ,driver);
                        Set<String> newWindowsSet = driver.getWindowHandles();
                        newWindowsSet.removeAll(oldWindowsSet);
                        return newWindowsSet.size() > 0 ? newWindowsSet.iterator().next() : null;
                    }
                });
        webDriver.switchTo().window(newWindow);
    }

    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void waitUtilElementToBeClickable(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitUtilElementToBeVisible(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitUtilElementToBeVisible(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    private void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = webDriverWait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assert.assertTrue("Поле было заполнено некорректно", checkFlag);
    }

    private void checkErrorMessageAtField(WebElement element, String errorMessage) {
        element = element.findElement(By.xpath("./..//span"));
        Assert.assertEquals("Проверка ошибки у поля не была пройдена", errorMessage, element.getText());
    }

    public void closeFrame(By byFrame,By byButtonCloseFrame) {
        if (checkPresentElement(byFrame) != null) {
            WebElement frame = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(byFrame));
            webDriver.switchTo().frame(frame);
            WebElement buttonFrameStockClose = webDriver.findElement(byButtonCloseFrame);
            actions.moveToElement(buttonFrameStockClose).click().build().perform();
            webDriver.switchTo().defaultContent();
        }
    }

    public WebElement checkPresentElement(By frame) {
        webDriverWait.withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(100));
        WebElement element = null;

        try {
            element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(frame));
        } catch (TimeoutException ignore) {
        }

        return element;
    }

}
