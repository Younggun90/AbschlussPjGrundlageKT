import kotlin.random.Random

// Funktion zur Anzeige des Menüs für die Aktionen der Kriegerinnen
fun menueAnzeigen(kriegerin: Kriegerin): Int {
    println("Aktuelle Kriegerin: ${kriegerin.name}")
    println("1. Angreifen")
    println("2. Heiltrank verwenden")
    print("Aktion auswählen: ")
    return readlnOrNull()?.toIntOrNull() ?: 0
}

// Funktion zur Eingabe des Endgegners
fun endgegnerEingeben(): Endgegner {
    println("Endgegner:")
    print("Name: ")
    val name = readlnOrNull() ?: ""
    print("Gesundheit: ")
    val gesundheit = readlnOrNull()?.toIntOrNull() ?: 0
    print("Angriffswert: ")
    val angriffswert = readlnOrNull()?.toIntOrNull() ?: 0
    val spezialangriff = readln().toIntOrNull() ?: 0

    return Endgegner(name, gesundheit, angriffswert, spezialangriff)
}

// Funktion zur Durchführung des rundenbasierten Kampfes
fun kampf(kriegerinnen: MutableList<Kriegerin>, endgegner: Endgegner) {
    val anzahlKriegerinnen = kriegerinnen.size
    var aktuelleKriegerin = 0

    while (kriegerinnen.isNotEmpty() && endgegner.gesundheit > 0) {
        val aktuelleKriegerinObj = kriegerinnen[aktuelleKriegerin]
        val aktion = menueAnzeigen(aktuelleKriegerinObj)
        when (aktion) {
            1 -> {
                val schaden = aktuelleKriegerinObj.angriffswert
                endgegner.gesundheit -= schaden
                println("${aktuelleKriegerinObj.name} greift an und verursacht $schaden Schaden.")
            }

            2 -> {
                val heilung = Random.nextInt(20, 40)
                aktuelleKriegerinObj.gesundheit += heilung
                println("${aktuelleKriegerinObj.name} verwendet einen Heiltrank und heilt sich um $heilung.")
            }

            else -> println("Ungültige Auswahl!")
        }

        if (endgegner.gesundheit > 0) {
            val schaden = endgegner.angriffswert
            val zufälligeKriegerinIndex = Random.nextInt(0, anzahlKriegerinnen)
            val zufälligeKriegerin = kriegerinnen[zufälligeKriegerinIndex]
            zufälligeKriegerin.gesundheit -= schaden
            println("${endgegner.name} greift ${zufälligeKriegerin.name} an und verursacht $schaden Schaden.")
            if (zufälligeKriegerin.gesundheit <= 0) {
                kriegerinnen.removeAt(zufälligeKriegerinIndex)
                aktuelleKriegerin--
            }
        }

        aktuelleKriegerin = (aktuelleKriegerin + 1) % kriegerinnen.size
    }

    if (kriegerinnen.isEmpty()) {
        println("Der Endgegner ${endgegner.name} wurde besiegt!")
    } else {
        println("Alle Kriegerinnen wurden besiegt. ${endgegner.name} gewinnt!")
    }
}

fun main() {


    val kriegerinnen = mutableListOf<Kriegerin>(
        Kriegerin("Kriegerin AFI", 100, 40, "Macheten-Boomerang"),
        Kriegerin("Kriegerin ABLA", 120, 50, "Lanzen-Wirbel"),
        Kriegerin("Kriegerin EFIA", 80, 60, "Bogen-Hagel")
    )


    println("Willkommen beim Endspiel, Krigerinnen gegen Endgegner")

    // Rufe das Menü auf und erhalte die ausgewählte Aktion

    println("Geben Sie die Anzahl der Kriegerinnen ein:")
    val anzahl = readLine()?.toIntOrNull()


    // Funktion zur Eingabe der Kriegerinnen
    fun kriegerinnenEingeben() {
        println("Geben Sie die Anzahl der Kriegerinnen ein:")
        val anzahl = readlnOrNull()?.toIntOrNull()

        if (anzahl != null && anzahl > 0) {
            for (i in 1..anzahl) {
                println("Kriegerin $i:")
                print("Name: ")
                val name = readlnOrNull() ?: ""
                print("Gesundheit: ")
                val gesundheit = readlnOrNull()?.toIntOrNull() ?: 0
                print("Angriffswert: ")
                val angriffswert = readlnOrNull()?.toIntOrNull() ?: 0
                print("Spezialfähigkeit: ")
                val spezialfaehigkeit = readlnOrNull() ?: ""

                val kriegerin = Kriegerin(name, gesundheit, angriffswert, spezialfaehigkeit)
                kriegerinnen.add(kriegerin)
            }
        } else {
            println("Ungültige Eingabe!")
            return
        }

        for (kriegerin in kriegerinnen) {
            kriegerin.anzeigen()
            println()
        }
        // Variable für Endgegner
        val endgegner = Endgegner("K.Nizer", 500, 90, 200)
        endgegner.anzeigen()
        println("Der Kampf beginnt!")

        // Starte den Kampf zwischen Kriegerinnen und Endgegner
        kampf(kriegerinnen, endgegner)

    }

}
