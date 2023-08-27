import com.google.inject.Inject
import com.google.inject.name.Named
import info.offthecob.common.OpenForTesting
import kong.unirest.Unirest

@OpenForTesting
class HttpClient @Inject constructor(@Named(WORD_URL) private val url: String) {
    fun get(): String {
        return Unirest.get(url).asString().body
    }
}
