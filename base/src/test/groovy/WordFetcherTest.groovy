import spock.lang.Specification

class WordFetcherTest extends Specification {

    def "received random list"() {
        given:
        def httpClient = Mock(HttpClient)
        httpClient.get() >> rawWords
        def fetcher = new WordFetcher(httpClient)
        def size = 5

        when:
        def fetched_1 = fetcher.fetch(size)
        def fetched_2 = fetcher.fetch(size)

        then:
        fetched_1.size() == size
        fetched_2.size() == size
        fetched_1 != fetched_2
    }

    def rawWords = """
    adverse
    advert
    advertise
    advertisement
    advertisements
    advertiser
    advertisers
    advertising
    advice
    advise
    advised
    advisor
    advisors
    advisory
    advocacy
    advocate
    adware
    ae
    aerial
    aerospace
    af
    affair
    affairs
    affect
    affected
    affecting
    affects
    affiliate
    affiliated
    affiliates
    affiliation
    afford
    affordable
    afghanistan
    afraid
    africa
    african
    after
    afternoon
    afterwards
    ag
    again
    against
    age
    aged
    agencies
    agency
    agenda
    """.stripIndent()
}
