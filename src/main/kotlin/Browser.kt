
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.concurrent.TimeUnit

interface Browser<D: RemoteWebDriver> {
    val driverName: String

    fun runBrowser(timeout: Long = 0) : D

    fun D.setTimeout(timeout: Long = 0) = apply {manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS)}

    fun D.maximize() = apply { manage().window().maximize() }

}