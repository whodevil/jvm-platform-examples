import info.offthecob.common.OpenForTesting

@OpenForTesting
class QuickSort {
    fun sort(words: List<String>): List<String> {
        return words.quickSort()
    }

    fun<T : Comparable<T>> List<T>.quickSort(): List<T> {
        if (this.size < 2) return this
        val pivot = this[this.size / 2]
        val less = this.filter { it < pivot }
        val equal = this.filter { it == pivot }
        val greater = this.filter { it > pivot }
        return less.quickSort() + equal + greater.quickSort()
    }
}
