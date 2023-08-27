import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.name.Named
import dev.misfitlabs.kotlinguice4.KotlinModule

const val WORD_URL = "word-url"
const val MIT_ECPRICE_WORD_LIST = "https://www.mit.edu/~ecprice/wordlist.10000"
const val NUMBER_OF_WORDS = "number-of-words"

class BaseModule : KotlinModule() {
    @Provides
    @Singleton
    @Named(WORD_URL)
    fun wordUrl(): String {
        return MIT_ECPRICE_WORD_LIST
    }

    @Provides
    @Singleton
    @Named(NUMBER_OF_WORDS)
    fun numberOfWords(): Int {
        return 10
    }
}
