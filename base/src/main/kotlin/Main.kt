import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.name.Named
import dev.misfitlabs.kotlinguice4.getInstance
import mu.KotlinLogging
import kotlin.time.measureTime

private val logger = KotlinLogging.logger {}

fun main() {
    val injector = Guice.createInjector(BaseModule())
    val main = injector.getInstance<Main>()
    main.apply {
        go()
    }
}

class Main @Inject constructor(
    private val wordFetcher: WordFetcher,
    @Named(NUMBER_OF_WORDS) private val numberOfWords: Int,
    private val quickSort: QuickSort,
) {
    fun go() {
        val words = wordFetcher.fetch(numberOfWords)
        logger.info { "unsorted $words" }
        var sortedWords: List<String>
        val timeSpent = measureTime {
            sortedWords = quickSort.sort(words)
        }
        logger.info { "took $timeSpent to complete" }
        logger.info { "sorted $sortedWords" }
    }
}
