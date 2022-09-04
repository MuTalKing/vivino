import java.lang.Exception

fun main(){
    val perekrestok = Perekrestok(ChromeBrowser().runBrowser())
    val vivino = Vivino(ChromeBrowser().runBrowser())
    try{
        perekrestok.openPerekrestokPage()
        val listOfWine = perekrestok.getWine()
        vivino.openVivinoPage()
        val listOfRates = vivino.getRate(listOfWine)
        vivino.createMap(listOfWine, listOfRates)
    } catch (e: Exception){
        print(e)
    }
    finally {
        perekrestok.close()
        vivino.close()
    }
}