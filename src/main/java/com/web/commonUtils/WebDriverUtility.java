package com.web.commonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


public class WebDriverUtility 
{
	public WebDriver driver;
	
	public WebDriverUtility (WebDriver driver)

	{
		this.driver = driver;
	}
	
	
	
	
	
	/**
	 * wait for the Page to Load Before identifying any synchronized in DOM[HTML-Document]
	 * @param driver
	 */
	
	
//	public static void waitForPageToLoad(WebDriver driver)
//	{
//		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//	}
	
	/**
	 * Waits for the web page to fully load by checking the document's readystate.
	 *
	 * This method uses JavaScript execution through the WebDriver to check if the
	 * document's readystate is 'complete', indicating that the page has fully loaded.
	 * It waits up to a specified timeout for this condition to be true.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param timeoutInSeconds The maximum time in seconds to wait for the page to load.
	 */
	
	public static void waitForPageToLoad(WebDriver driver) 
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) 
	        {
	            return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	    };

	    wait.until(pageLoadCondition);
	}
	
	
	/**
	 * Sets the maximum time to wait for an asynchronous script to finish execution before throwing an error.
	 *
	 * This method is used to specify the amount of time the WebDriver should wait for an asynchronous
	 * JavaScript execution to complete before considering it a timeout. It is particularly useful when
	 * dealing with AJAX requests or any long-running JavaScript operations that are expected to take time
	 * before the page becomes fully interactive or data gets fully loaded.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 */
	
	
	public void waitForPageToLoadForJSElement(WebDriver driver)
	{
		driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
		
	}
	

	    /**
	     * Waits for a loader/spinner to disappear from the page.
	     * 
	     * @param driver The WebDriver instance.
	     * @param loaderBy The By locator for the loader/spinner element.
	     * @param timeoutInSeconds The maximum time to wait for the loader to disappear.
	     */
	
	
	    public static void waitForLoaderToDisappear(WebDriver driver, By loaderBy) 
	    {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderBy));
	    }
	
	
	
	    /**
	     * Waits for a specific web element to become clickable within a defined timeout.
	     *
	     * This method uses an explicit wait to pause the execution until the specified element is detected
	     * to be clickable. Clickable, in this context, means that the element is visible and enabled, thereby
	     * allowing it to receive a click action. This is particularly useful in scenarios where elements might
	     * be overlaid by a loader, undergoing animation, or not yet enabled due to pending operations such as
	     * AJAX calls.
	     *
	     * @param driver The WebDriver instance controlling the browser.
	     * @param element The WebElement to wait for its clickability.
	     */
	
	    
	public void waitForElementToBeClicAble(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	/**
	 * Waits until a specific web element is visible on the page within a specified timeout.
	 *
	 * This method implements an explicit wait to pause the test execution until the specified web element
	 * becomes visible on the page. Visibility, in this context, means that the element is present on the DOM
	 * of a page and is visible. This is useful for handling scenarios where elements might be delayed in
	 * their appearance due to animations, AJAX loads, or being conditionally rendered based on user actions
	 * or other events.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param timeout The maximum time to wait for the element to become visible. Specified as a Duration object.
	 * @param element The WebElement to wait for visibility.
	 */
	
	
	public static void waitUntilElementVisible(WebDriver driver, Duration timeout,WebElement element)
	{
		     WebDriverWait wait = new WebDriverWait(driver, timeout);
		     wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * Waits for a web element to become clickable with a custom polling interval.
	 *
	 * This method uses FluentWait to create a flexible wait with a custom polling frequency. FluentWait
	 * allows for the configuration of the maximum amount of time to wait for a condition, as well as the
	 * polling frequency to check for the condition. This is particularly useful for handling elements that
	 * may take a variable amount of time to become interactable, allowing for more efficient checking
	 * compared to fixed polling intervals.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param element The WebElement to wait for until it becomes clickable.
	 * @param pollingTime The interval in seconds at which to poll for the condition to be true.
	 * @throws InterruptedException if the thread is interrupted while waiting.
	 */
	
	public void waitForElementWithCustomTimeOut(WebDriver driver, WebElement element, int pollingTime) throws InterruptedException 
	{
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30)) // Set the total wait time
	            .pollingEvery(Duration.ofSeconds(pollingTime)) // Set the polling frequency
	            .ignoring(NoSuchElementException.class); // Ignore this exception when polling

	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	/**
	 * Waits until a specific web element is visible on the page within a specified timeout and with a custom polling interval.
	 *
	 * This method uses FluentWait to create a flexible and configurable wait condition for an element's visibility.
	 * It allows specifying the maximum time to wait for the element to become visible and the frequency with which to check
	 * for the element's visibility. This is particularly useful for handling web pages with dynamic content that might take
	 * varying times to load, as it can reduce the wait time by adjusting the polling frequency based on expected conditions.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param timeout The maximum time to wait for the element to become visible.
	 * @param element The WebElement to wait for visibility.
	 * @param pollingTime The interval, in seconds, at which to poll for the element's visibility.
	 */
	
	
	public static void waitUntilElementVisibleWithCustomPoll1(WebDriver driver, Duration timeout, WebElement element, long pollingTime) 
	{
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(timeout)
	            .pollingEvery(Duration.ofSeconds(pollingTime))
	            .ignoring(Throwable.class); // Use a more specific exception if possible, e.g., NoSuchElementException

	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * Switches to the currently active JavaScript alert window and accepts it.
	 *
	 * This method is used in scenarios where a JavaScript alert (a simple modal dialog that displays some information
	 * or requires user interaction) is presented to the user. It switches the context from the main web page to the alert,
	 * and then performs the action to accept (or 'OK') the alert. This is commonly needed in web automation tests where
	 * interacting with JavaScript alerts is required to proceed with the test flow.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 */
	
	
	public void switchToAlertWindowAndAccept(WebDriver driver) 
	{
	    driver.switchTo().alert().accept();
	}
	
	
	/**
	 * Switches to the currently active JavaScript alert window and cancels it.
	 *
	 * This method is utilized in situations where a web application triggers a JavaScript alert that offers
	 * a choice to the user, typically in the form of confirm dialogs. It switches the WebDriver's context from
	 * the main webpage to the alert dialog and then performs the action to dismiss or cancel the alert. This is
	 * particularly useful in automated testing scenarios where the test needs to simulate the action of a user
	 * choosing to not proceed with a warning or confirmation dialog by clicking the 'Cancel' button or closing
	 * the alert without accepting it.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 */
	
	
	public void switchToAlertWindowAndCancel(WebDriver driver) 
	{
	    driver.switchTo().alert().dismiss();
	}
	
	
	
	/**
	 * Switches the WebDriver's context to a frame on the web page identified by its index.
	 *
	 * This method is crucial for interacting with web elements that are nested within a frame or an iframe
	 * element in a web page. Since WebDriver operates in the context of the main document by default, it is
	 * necessary to switch the context to the desired frame before performing any operations on elements
	 * within that frame. The frame is identified by its index, with 0 being the first frame on the page.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param index The zero-based index of the frame to switch to. The first frame on the page is at index 0.
	 */
	
	public void switchToFrame(WebDriver driver, int index) 
	{
	    driver.switchTo().frame(index);
	}
	
	
	/**
	 * Switches the WebDriver's context to a frame on the web page identified by its id or name attribute.
	 *
	 * In web pages that utilize frames (or iframes) to embed other documents or interactive content,
	 * it is necessary to switch the WebDriver's context to the specific frame before interacting with
	 * elements within that frame. This method facilitates such a switch by using the frame's id or name
	 * attribute as the identifier, allowing for direct interaction with the web elements inside the frame.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param id_name_attribute The id or name attribute of the frame to which to switch. This is a string
	 *        identifier that matches the 'id' or 'name' attribute of the frame/iframe element in the HTML document.
	 */
	
	public void switchToFrame(WebDriver driver, String id_name_attribute) 
	{
	    driver.switchTo().frame(id_name_attribute);
	}
	
	
	/**
	 * Selects an option from a dropdown menu based on its index.
	 *
	 * This method is intended for use with <select> elements in web pages, allowing automated test scripts
	 * to select an option as a user would. The options within the <select> element are zero-indexed, meaning
	 * the first option is at index 0, the second option is at index 1, and so on. This method utilizes the
	 * Selenium WebDriver's Select class, which provides methods to interact with <select> elements conveniently.
	 *
	 * @param element The WebElement representing the <select> dropdown to interact with.
	 * @param index The zero-based index of the option to select. This corresponds to the order of <option>
	 *        elements within the <select> tag.
	 */
	
	
	public void select(WebElement element, int index) 
	{
	    Select sel = new Select(element);
	    sel.selectByIndex(index);
	}
	
	
	
	
	/**
	 * Selects an option from a dropdown menu based on the option's visible text.
	 *
	 * This method facilitates automated interactions with <select> elements on web pages, allowing
	 * test scripts to select a specific option as a user might do manually. It leverages the Selenium
	 * WebDriver's Select class, which provides a convenient API for interacting with <select> elements.
	 * The method identifies the desired option within the dropdown by its visible text, making it easy
	 * to select options in a way that aligns with how users see and interact with the menu.
	 *
	 * @param element The WebElement representing the <select> dropdown to be interacted with.
	 * @param text The visible text of the option to select. This should match exactly with the text
	 *        as it is displayed in the dropdown menu, including spaces and case.
	 */
	
	
	public void select(WebElement element, String text) 
	{
	    Select sel = new Select(element);
	    sel.selectByVisibleText(text);
	}
	
	
	
	/**
	 * Simulates a mouse hover action over a specified web element.
	 *
	 * This method is essential for automated testing scenarios that require verification of behaviors
	 * triggered by mouse hover actions. For instance, hovering over a menu item to reveal a dropdown menu,
	 * displaying tooltips, or activating hidden elements. It uses the Selenium WebDriver's Actions class
	 * to create a sequence of actions for moving to the specified element, effectively simulating a mouse
	 * hover.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param element The WebElement over which to perform the mouse hover action.
	 */
	
	public void mouseOverOnElement(WebDriver driver, WebElement element) 
	{
	    Actions act = new Actions(driver);
	    act.moveToElement(element).perform();
	}
	
	
	/**
	 * Simulates a right-click (context click) action on a specified web element.
	 *
	 * This method is vital for automated testing scenarios that involve interactions requiring
	 * a right-click on a web element. It can be used to test the functionality of context menus,
	 * verify that right-click actions trigger the correct events, or ensure that web applications
	 * properly handle context click interactions. The method utilizes the Selenium WebDriver's
	 * Actions class to create a sequence of actions that mimic the user performing a right-click
	 * on the target element, thereby allowing for the validation of the application's response
	 * to such interactions.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param element The WebElement on which to perform the right-click action.
	 */
	
	public void rightClickOnElement(WebDriver driver, WebElement element) 
	{
	    Actions act = new Actions(driver);
	    act.contextClick(element).perform();
	}
	
	
	/**
	 * Executes a specified JavaScript code asynchronously on the current web page.
	 *
	 * This method leverages the capabilities of the JavascriptExecutor interface in Selenium WebDriver
	 * to run custom JavaScript code. Asynchronous execution is particularly useful for scripts that
	 * include setTimeout, XMLHttpRequests, or any operations that rely on callback functions. This
	 * allows for the execution of JavaScript that performs actions or computations that do not
	 * synchronously return a value or whose operations might take time to complete. It's important
	 * to ensure that the JavaScript code handles the signaling of completion, typically through
	 * callback functions, to prevent the WebDriver from hanging or timing out.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param javaScript The JavaScript code to be executed asynchronously.
	 */
	
	
	public void executeJavaScript(WebDriver driver, String javaScript )
	{
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeAsyncScript(javaScript, null);
		
	}
	
	
	/**
	 * Attempts to click on a web element, with retries upon failure.
	 *
	 * This method aims to address scenarios where a web element is not immediately clickable due to
	 * asynchronous page behaviors, such as AJAX content loading, CSS animations, or JavaScript-driven
	 * visibility changes. It tries to click the element, and if unsuccessful, it catches any thrown
	 * exceptions (captured broadly as Throwable) and retries after a specified delay. This process
	 * repeats until the click is successful or the retry limit is reached.
	 *
	 * Note: The original loop condition has an issue where it would never enter the loop since
	 * 'count' starts at 20 and the loop condition checks if 'count' is less than 30, which is always true.
	 * It's likely intended to decrement 'count' or use a different loop condition.
	 *
	 * @param element The WebElement to be clicked.
	 * @throws InterruptedException if the thread sleeping is interrupted.
	 */
	
	
	public void waitAndClick(WebElement element) throws InterruptedException 
	{
	    int count = 0; // Fixed to ensure the loop works as intended.
	    while (count < 30) {
	        try {
	            element.click();
	            break; // Exit the loop if click is successful.
	        } catch (Throwable e) { // Catch any exceptions during click attempt.
	            Thread.sleep(5000); // Wait for 5 seconds before retrying.
	            count++; // Increment count to eventually exit the loop.
	        }
	    }
	}

	
	/**
	 * Waits for an element to become clickable, attempting to click it at regular intervals until successful or a timeout is reached.
	 *
	 * This method implements a custom polling mechanism to handle scenarios where web elements are not immediately
	 * interactable. It tries to click the specified element at intervals defined by 'pollingTime'. If the click is
	 * unsuccessful due to the element not being found (NoSuchElementException), it pauses for 'pollingTime' milliseconds
	 * before trying again. This process repeats until the click is successful or the 'timeout' limit (in terms of the number
	 * of attempts, not seconds) is reached.
	 *
	 * Note: The method parameters suggest 'timeout' is a count of attempts rather than a time duration in seconds.
	 * It's essential to ensure the 'timeout' and 'pollingTime' parameters are correctly interpreted and used.
	 *
	 * @param element The WebElement to be clicked.
	 * @param timeout The maximum number of attempts to try clicking the element.
	 * @param pollingTime The interval in milliseconds between each click attempt.
	 * @throws InterruptedException if the thread sleeping between polling intervals is interrupted.
	 */
	
	
	public static void customeWaitTillElementClickable(WebElement element, int timeout, int pollingTime) throws InterruptedException 
	{
	    int count = 0;
	    while (count < timeout) { // Corrected to ensure it tries 'timeout' number of times.
	        try {
	            element.click();
	            break; // Exit the loop if click is successful.
	        } catch (NoSuchElementException e) {
	            Thread.sleep(pollingTime); // Wait for 'pollingTime' milliseconds before retrying.
	            count++; // Increment the attempt count.
	        }
	    }
	}
	
	
	/**
	 * Waits until a specified web element is visible on the page, with a custom polling interval.
	 *
	 * This method uses FluentWait to allow for a flexible wait configuration, specifying both a timeout
	 * for how long to wait for the element to become visible and a custom interval to check for the
	 * element's visibility. This is particularly useful for web pages that dynamically load content,
	 * where elements might not be immediately visible. By adjusting the polling interval, tests can be
	 * made more efficient and less prone to prematurely failing due to timing issues.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param timeout The maximum time to wait for the element to become visible.
	 * @param element The WebElement to wait for visibility.
	 * @param pollingTime The interval, in seconds, at which to poll for the element's visibility.
	 */
	
	
	public static void waitUntilElementVisibleWithCustomPoll(WebDriver driver, Duration timeout, WebElement element, long pollingTime) 
	{
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(timeout)
	            .pollingEvery(Duration.ofSeconds(pollingTime))
	            .ignoring(Throwable.class); // It's often better to specify more precise exceptions.

	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * Captures and saves a screenshot of the current browser view.
	 *
	 * This method utilizes the Selenium WebDriver's TakesScreenshot interface to capture a screenshot of the
	 * web page currently loaded in the browser. It saves the screenshot to a specified directory with a custom
	 * file name. This is particularly useful for capturing visual evidence during automated test execution, which
	 * can aid in debugging, documenting test results, or verifying UI elements and layouts.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param screenshotName The name to be given to the screenshot file, without the file extension.
	 * @param folderName The name of the folder (directory) where the screenshot will be saved. The folder will be
	 *        located within the project's root directory.
	 * @throws IOException if an error occurs during file creation or writing to the file.
	 */
	
	
	public void takeScreenshot(WebDriver driver, String screenshotName, String folderName) throws IOException
	{
	    TakesScreenshot ts = (TakesScreenshot)driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    // Ensure the directory path ends with a "/" to avoid concatenation errors
	    String directoryPath = "./screenshots" + (folderName.endsWith("/") ? folderName : folderName + "/");
	    File file = new File(directoryPath + screenshotName + ".png");
	    // Using java.nio.file.Files for copying, ensuring the parent directory exists
	    file.getParentFile().mkdirs(); // Create the directory structure if it doesn't exist
	    //Files.copy(file.toPath(), file.toPath());
	    Files.copy(src, file);
	}
	
	
	/**
	 * Launches a web application URL, waits for the page to fully load, and then maximizes the browser window.
	 *
	 * This method is essential for initializing a web testing environment in a consistent state. By navigating
	 * to the specified URL and maximizing the window, it ensures that the application is ready for automated
	 * interactions under optimal viewing conditions. This setup is particularly useful for tests that may
	 * depend on specific screen resolutions or element visibility that could be affected by the browser window size.
	 *
	 * Note: The method signature includes a 'timeout' parameter intended for controlling the page load waiting
	 * time. However, the current implementation does not explicitly use this parameter. Implementors might consider
	 * using this 'timeout' in a custom 'waitForPageToLoad' method to set a specific waiting threshold.
	 *
	 * @param driver The WebDriver instance controlling the browser.
	 * @param url The URL of the web application to be launched.
	 * @param timeout The maximum time to wait for the page to load fully before proceeding. This parameter is
	 *        currently not used in the method implementation and could be integrated into a custom page load
	 *        wait mechanism.
	 */
	
	
	public static void launchApplicationWithMaximize(WebDriver driver, String url, long timeout) 
	{
	    driver.get(url);
	    // Potential place to implement a wait using 'timeout', e.g., waitForPageToLoad(driver, timeout);
	    waitForPageToLoad(driver); // Placeholder for a custom page load wait mechanism.
	    driver.manage().window().maximize();
	}



public  void sendKeys(By locator, String inputText) {

	if (driver != null) {
		WebElement element = driver.findElement(locator);
		element.clear();  // Optional: Clear the existing text in the field
		element.sendKeys(inputText);
	}

}





}





	

