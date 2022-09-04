import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.concurrent.TimeUnit

class Perekrestok(private val driver: WebDriver) {
    val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    @FindBy(xpath = "//span[@class='button-children' and text()='Мне исполнилось 18 лет']")
    lateinit var confirmAgeButton: WebElement

    @FindBy(xpath = "//span[@class='button-children' and text()='Принять']")
    lateinit var acceptCookieButton: WebElement

    @FindBy(xpath = "//div[@class='catalog-feature-enum__title' and text()='Сухое']")
    lateinit var filterByDryWineButton: WebElement

    @FindBy(xpath = "//div[@class='catalog-feature-enum__title' and text()='Полусухое']")
    lateinit var filterBySemiDryWineButton: WebElement

    @FindBy(xpath = "//div[@class='catalog-feature-enum__title' and text()='Красное']")
    lateinit var filterByRedWineButton: WebElement

    fun getWine(): MutableList<String> {
        val listOfWines: MutableList<String> = ArrayList()
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//span[@class = 'product-card__link-text']")))).forEach{
            webElement ->
            listOfWines.add(webElement.text)

        }
        print(listOfWines)
        return listOfWines
    }

    init {
        PageFactory.initElements(AjaxElementLocatorFactory(driver, 10), this)
    }

    fun openPerekrestokPage(){
        driver.get("https://www.perekrestok.ru/cat/c/2/vino")
        TimeUnit.SECONDS.sleep(1)
        confirmAgeButton.click()
        TimeUnit.SECONDS.sleep(1)
        acceptCookieButton.click()
        filterByDryWineButton.click()
        filterBySemiDryWineButton.click()
        filterByRedWineButton.click()
        TimeUnit.SECONDS.sleep(1)
    }

    fun close(){
        driver.quit()
    }
    
}