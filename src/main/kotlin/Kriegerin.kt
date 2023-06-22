// Kriegerin-Unterklasse, erbt von Charakter
class Kriegerin(name: String, gesundheit: Int, angriffswert: Int, val spezialfaehigkeit: String) : Charakter(name, gesundheit, angriffswert) {
    override fun anzeigen() {
        super.anzeigen()
        println("Spezialfähigkeit: $spezialfaehigkeit")
    }
}