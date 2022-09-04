import db.jooq.vivino.Tables.WINES
import db.jooq.vivino.tables.Wines
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory
import java.sql.Types.NULL
import java.util.concurrent.TimeUnit

class Vivino(private val driver: WebDriver) {
    val action = Actions(driver)

    @FindBy(xpath = "//input[@placeholder = 'Search any wine']")
    lateinit var searchField: WebElement

    @FindBy(xpath = "//div[@class = 'search-results-list']/div[1]//div[@class= 'text-inline-block light average__number']")
    lateinit var averageRate: WebElement

    init {
        PageFactory.initElements(AjaxElementLocatorFactory(driver, 10), this)
    }

    fun openVivinoPage(){
        driver.get("https://www.vivino.com")
    }

    fun getRate(listOfWine: MutableList<String>): MutableList<String>{
        val vivinoDB = VivinoDB()
        vivinoDB.connectToDB()
        val create: DSLContext = DSL.using(vivinoDB.conn, SQLDialect.MYSQL)
        var i = 0
        val listOfRates: MutableList<String> = ArrayList()
        for(wine in 201 until listOfWine.size) {
            searchField.clear()
            searchField.sendKeys(listOfWine[wine])
            action.sendKeys(Keys.ENTER).perform()
            var isPresent: Boolean = driver.findElements(By.xpath("//div[@class = 'search-results-list']/div[1]//div[@class= 'text-inline-block light average__number']")).size > 0
            if (isPresent){
                listOfRates.add(averageRate.text)
                create.insertInto(WINES, WINES.ID, WINES.NAME, WINES.RATE).values(wine.toString(), listOfWine[wine], averageRate.text).execute()
            }
            else {
                listOfRates.add("-")
                create.insertInto(WINES, WINES.ID, WINES.NAME, WINES.RATE).values(wine.toString(), listOfWine[wine], "-").execute()
            }
            i++
            if(i%15==0){
                TimeUnit.SECONDS.sleep(15)
            }
            println(listOfRates)
        }
        println(listOfRates)
        return listOfRates
    }

    fun createMap(listOfWine: MutableList<String>, listOfRates: MutableList<String>){
        val wineMap: MutableMap<String, String> = mutableMapOf()
        for(i in listOfWine.indices){
            wineMap.put(listOfWine[i], listOfRates[i])
            println(wineMap)
        }
        println(wineMap)
    }

    fun insertWineInDB(){
        val vivinoDB = VivinoDB()
        val create: DSLContext = DSL.using(vivinoDB.conn, SQLDialect.MYSQL)
        create.insertInto(WINES, WINES.ID, WINES.NAME, WINES.RATE).values("NULL", "", "").execute()
    }

    fun close(){
        driver.quit()
    }

}