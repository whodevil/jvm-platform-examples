import spock.lang.Specification

class QuickSortTest extends Specification {

    def "can sort"() {
        given:
        def wordList = words.split("\n") as List<String>

        when:
        def sortedList = new QuickSort().sort(wordList)

        then:
        sortedList != wordList
        sortedList == wordList.sort()
    }

    def words = """
    X-ray
    first
    bang
    watch
    last
    lump
    fall
    illustrate
    genuine
    separate
    fog
    helpless
    hill
    remind
    sniff
    disappointment
    entitlement
    swim
    protest
    tumour
    axis
    willpower
    idea
    license
    element
    paper
    resort
    sweat
    curl
    reach
    skate
    frank
    protect
    dump
    turkey
    important
    border
    autonomy
    joke
    marriage
    infrastructure
    raw
    progress
    coincide
    index
    defend
    knit
    panic
    quarter
    trunk
    """.trim().stripIndent()

}
