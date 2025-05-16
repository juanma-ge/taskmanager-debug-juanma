package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.modelo.Estado
import es.prog2425.taskmanager.presentacion.Interfaz

class TrabajarTareas(val salida: Interfaz, val servicio: ActividadService) {

    private val ge: GestorActividades = GestorActividades()

    fun crearTarea() {
        val descripcion = ge.pedirDescripcion()
        servicio.crearTarea(descripcion)
        salida.mostrar("\nTarea creada con éxito y etiquetas asignadas.")
    }


    // Cambiar el estado de una tarea
    fun cambiarEstadoTarea() {
        salida.mostrar("\nSelecciona la tarea cuyo estado deseas cambiar:")
        val tarea = ge.obtenerTarea()

        salida.mostrar("\nElige el nuevo estado para la tarea:")
        salida.mostrar("1. ABIERTA")
        salida.mostrar("2. EN PROGRESO")
        salida.mostrar("3. FINALIZADA")

        val estadoSeleccionado = salida.leerNum()
        val nuevoEstado = when (estadoSeleccionado) {
            1 -> Estado.ABIERTA
            2 -> Estado.EN_PROGRESO
            3 -> Estado.FINALIZADA
            else -> {
                salida.mostrar("\nOpción no válida. El estado no ha sido cambiado.")
                return
            }
        }

        try {
            servicio.cambiarEstadoTarea(tarea, nuevoEstado)
            salida.mostrar("\nEstado de la tarea cambiado exitosamente a ${nuevoEstado.name}.")
        } catch (e: IllegalStateException) {
            salida.mostrar("\nError: ${e.message}")
        }
    }


    // Cerrar tarea (verificando que todas las subtareas estén cerradas)
    fun cerrarTarea() {
        salida.mostrar("\nSelecciona la tarea a cerrar:")
        val tarea = ge.obtenerTarea()
        try {
            tarea.cerrar()
            salida.mostrar("\nTarea cerrada exitosamente.")
        } catch (e: IllegalStateException) {
            salida.mostrar("\nNo se puede cerrar la tarea porque tiene subtareas abiertas.")
        }
    }

    // Asociar una subtarea a una tarea principal
    fun asociarSubtarea() {
        salida.mostrar("\nSelecciona la tarea principal:")
        val tareaPrincipal = ge.obtenerTarea()
        salida.mostrar("\nDescribe la subtarea a asociar:")
        val descripcionSubtarea = ge.pedirDescripcion()
        val subtarea = servicio.crearTarea(descripcionSubtarea)
        servicio.asociarSubtarea(tareaPrincipal, subtarea)
        salida.mostrar("\nSubtarea asociada a la tarea principal.")
    }

}