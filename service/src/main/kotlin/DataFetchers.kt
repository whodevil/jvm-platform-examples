import graphql.schema.DataFetcher
import info.offthecob.examples.library.types.Word

class DataFetchers {
    fun getWords(): DataFetcher<List<Word>> {
        return DataFetcher<List<Word>> { input ->
            input.variables["numberOfWords"]
            listOf(Word("dude", 1, "FFFFFF"))
        }
    }
}
