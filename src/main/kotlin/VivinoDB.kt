import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

class VivinoDB {
    var userName = "root"
    var password = "G36h8t55k115!"
    var url = "jdbc:mysql://localhost:3306/vivino"
    lateinit var conn: Connection

    fun connectToDB() {
        try {
            val conn: Connection = DriverManager.getConnection(url, userName, password)
            this.conn = conn
        } catch (e:Exception) {
            e.printStackTrace();
        }
    }
}