package services

import es.prog2425.taskmanager.modelo.Actividad

class TestActividad(desc: String) : Actividad(desc) {
    override fun obtenerDetalle(): String {
        return super.obtenerDetalle()
    }
}