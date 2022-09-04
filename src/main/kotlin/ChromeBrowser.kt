import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver

class ChromeBrowser : Browser<ChromeDriver> {
    override val driverName: String = "webdriver.chrome.driver"

    override fun runBrowser(timeout: Long): ChromeDriver {
        WebDriverManager.chromedriver().setup()
        return ChromeDriver().setTimeout(timeout).maximize()
    }
}