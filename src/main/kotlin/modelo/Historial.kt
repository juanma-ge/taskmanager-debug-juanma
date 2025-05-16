package es.prog2425.taskmanager.modelo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Historial {
    private val acciones = mutableListOf<Pair<String, String>>()

    fun registrar(descripcion: String) {
        val fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        acciones.add(Pair(fecha, descripcion))
    }

    fun obtener(): List<Pair<String, String>> = acciones.toList()
}
