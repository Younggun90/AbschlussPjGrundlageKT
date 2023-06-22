// Basisklasse für Charaktere
open class Charakter(val name: String, var gesundheit: Int, val angriffswert: Int) {
    open fun anzeigen() {
        println("$name - Gesundheit: $gesundheit, Angriffswert: $angriffswert")
    }
}