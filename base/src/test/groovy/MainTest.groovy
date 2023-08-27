import spock.lang.Specification

class MainTest extends Specification {
    def "behavior"() {
        given:
        QuickSort quickSort = Mock(QuickSort)
        WordFetcher wordFetcher = Mock(WordFetcher)
        def numberOfWords = 10 as Integer
        def unsortedWords =  ["b", "a", "aa", "c"] as List<String>

        when:
        new Main(wordFetcher, numberOfWords, quickSort).go()

        then:
        1 * quickSort.sort(unsortedWords)
        1 * wordFetcher.fetch(_) >> unsortedWords
    }
}
