package es.prog2425.taskmanager.servicios

class GestorEtiquetas {
    fun agregarEtiquetaUnica(conjunto: MutableSet<String>, etiqueta: String) {
        conjunto.add(etiqueta) // Set automáticamente evita duplicados
    }

    fun procesarEntradaEtiquetas(entrada: String): List<String> {
        return entrada.split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
    }
}