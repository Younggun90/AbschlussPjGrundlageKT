// Endgegner-Unterklasse erbt von Charakter
class Endgegner(val name: String, var gesundheit: Int, val angriffswert: Int, val spezialangriff: Int) {
    fun anzeigen() {
        println("$name - Gesundheit: $gesundheit, Angriffswert: $angriffswert, Spezialangriff: $spezialangriff")
    }
}
