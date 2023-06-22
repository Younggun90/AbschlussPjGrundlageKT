// Endgegner-Unterklasse erbt von Charakter
class Endgegner(name: String, gesundheit: Int, angriffswert: Int, val spezialangriff: Int): Charakter(name, gesundheit, angriffswert) {
    override fun anzeigen() {
        println("$name - Gesundheit: $gesundheit, Angriffswert: $angriffswert, Spezialangriff: $spezialangriff")
    }
}
