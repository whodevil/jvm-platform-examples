import com.google.inject.Inject
import info.offthecob.common.OpenForTesting
import mu.KotlinLogging
import kotlin.random.Random

private val logger = KotlinLogging.logger {}

@OpenForTesting
class WordFetcher @Inject constructor(private val httpClient: HttpClient) {
    fun fetch(numberOfEntries: Int): List<String> {
        val words = httpClient.get().split("\n")
        logger.info { "received ${words.size} words" }
        return MutableList(numberOfEntries) { "" }
            .map {
                words[Random.nextInt(words.size)]
            }
            .shuffled()
    }
}
