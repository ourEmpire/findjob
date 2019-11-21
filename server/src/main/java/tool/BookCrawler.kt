package tool;
import com.alibaba.fastjson.JSON
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.io.*

suspend fun main() = coroutineScope {

    val books = ArrayList<Book>()
    val userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36"
    for(i in 0..49){
        val url = "https://book.douban.com/tag/程序?start=${20*i}&type=T"
        var doc = Jsoup.connect(url).header("User-Agent",userAgent).get()
        val lis = doc.select("li.subject-item")

        for(li in lis){
            launch {
                val name = li.select(".info>h2>a").attr("title")
                val author = li.select(".info>.pub").text()
                val pic = li.select(".pic>.nbg>img").attr("src")
                val desc = li.select(".info>p").text()
                books.add(Book(name,author,desc,pic))
                println("name: $name author: $author pic: $pic desc: $desc")
            }
        }
    }
    write(File("E:\\repo\\data\\json\\book\\program.json"), JSON.toJSONString(books),"UTF-8")
}

@Throws(IOException::class)
fun write(file: File, content: String, encoding: String) {
    file.delete()
    file.createNewFile()
    val writer = BufferedWriter(OutputStreamWriter(
            FileOutputStream(file), encoding))
    writer.write(content)
    writer.close()
}

data class Book(
        val name: String,
        val author: String,
        val desc: String,
        val pic: String
)
