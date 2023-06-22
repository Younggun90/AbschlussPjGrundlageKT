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

    // Diese Schleife wird solange Ausgeführt wie die Kriegerinnen als auch der Endgegner noch am Leben sind
    while (kriegerinnen.isNotEmpty() && endgegner.gesundheit > 0) {
        // Aktuelle Kriegerin wird aus der Liste der Kriegerinnen abgerufen
        val aktuelleKriegerinObj = kriegerinnen[aktuelleKriegerin]
        // Die Menüoptionen für aktuelle Kriegerin werden angezeigt und ausgewählt
        val aktion = menueAnzeigen(aktuelleKriegerinObj)
        // Je nach ausgewählter Aktion der Kriegerin werden entsprechende Aktionen ausgeführt
        when (aktion) {
            1 -> {
                // Kriegerin greift an und verursacht Schaden
                val schaden = aktuelleKriegerinObj.angriffswert
                endgegner.gesundheit -= schaden
                println("${aktuelleKriegerinObj.name} greift an und verursacht $schaden Schaden.")
            }

            2 -> {
                // Die Kriegerin verwendet einen Heiltrank und heilt sich selbst.
                // Der Heilungswert wird zufällig bestimmt.
                val heilung = Random.nextInt(20, 60)
                aktuelleKriegerinObj.gesundheit += heilung
                println("${aktuelleKriegerinObj.name} verwendet einen Heiltrank und heilt sich um $heilung.")
            }

            else -> println("Ungültige Auswahl!")
        }

        // Wenn der Endgegner noch am Leben ist, greift er eine zufällige Kriegerin an
        if (endgegner.gesundheit > 0) {
            val schaden = endgegner.angriffswert
            val zufälligeKriegerinIndex = Random.nextInt(0, anzahlKriegerinnen)
            val zufälligeKriegerin = kriegerinnen[zufälligeKriegerinIndex]

            // Die zufällig ausgewählte Kriegerin erleidet Schaden
            zufälligeKriegerin.gesundheit -= schaden
            println("${endgegner.name} greift ${zufälligeKriegerin.name} an und verursacht $schaden Schaden.")

            // Falls die Kriegerin keine Lebenspunkte mehr hat, wird sie aus der Liste der Kriegerinnen entfernt
            if (zufälligeKriegerin.gesundheit <= 0) {
                kriegerinnen.removeAt(zufälligeKriegerinIndex)
                aktuelleKriegerin--
            }
        }

        // Die Index-Variable der aktuellen Kriegerin wird aktualisiert
        aktuelleKriegerin = (aktuelleKriegerin + 1) % kriegerinnen.size
    }

    // Nach dem Kampf wird überprüft, ob Kriegerinnen oder Endgegner gewonnen hat.
    if (kriegerinnen.isEmpty()) {
        println("Der Endgegner ${endgegner.name} wurde besiegt!")
        println("GEWONNEN")
    } else {
        println("Alle Kriegerinnen wurden besiegt. ${endgegner.name} gewinnt!")
        println("GAME OVER")
    } // if else{("${kampf} Wiederholen")
}

fun main() {

    // Mutable Liste der Kriegerinnen
    val kriegerinnen = mutableListOf<Kriegerin>(
        Kriegerin("Kriegerin AFI", 150, 40, "Macheten-Boomerang"),
        Kriegerin("Kriegerin ABLA", 120, 50, "Lanzen-Wirbel"),
        Kriegerin("Kriegerin EFIA", 100, 60, "Bogen-Hagel")
    )


    println("Willkommen beim Endspiel, Krigerinnen gegen Endgegner")

    // Funktion zur Eingabe der Kriegerinnen
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
                val spezialfähigkeit = readlnOrNull() ?: ""

                val kriegerin = Kriegerin(name, gesundheit, angriffswert, spezialfähigkeit)
                kriegerinnen.add(kriegerin)
            }
        } else {
            println("Ungültige Eingabe!")
            return
        }


        // Variable für Endgegner
        val endgegner = Endgegner("ENDGEGNER K.Nizer", 500, 90, 200)
        endgegner.anzeigen()
        println("Der Kampf beginnt!")

        // Starte den Kampf zwischen Kriegerinnen und Endgegner
        kampf(kriegerinnen, endgegner)

}
