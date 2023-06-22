class Inventar {
    fun inventarVerwalten(kriegerinnen: List<Kriegerin>) {
        for (kriegerin in kriegerinnen) {
            println("${kriegerin.name}:")
            print("Anzahl der Heiltränke: ")
            val anzahlHeiltraenke = readLine()?.toIntOrNull() ?: 0
            kriegerin.anzeigen()
            println("Anzahl der Heiltränke: $anzahlHeiltraenke")
            println()
        }
    }
}