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


    /**
     * Cambia el estado de una tarea seleccionada por el usuario.
     *
     * Muestra un menú interactivo para:
     * 1. Seleccionar una tarea existente.
     * 2. Elegir un nuevo estado (ABIERTA, EN_PROGRESO o FINALIZADA).
     * 3. Actualizar el estado mediante el servicio correspondiente.
     *
     * @throws IllegalStateException Si el cambio de estado no es válido (ej: transición no permitida).
     * @sample Estado.ABIERTA, Estado.EN_PROGRESO, Estado.FINALIZADA
     */
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


    /**
     * Cierra una tarea seleccionada por el usuario.
     *
     * - La tarea solo se cierra si no tiene subtareas abiertas.
     * - Muestra mensajes de éxito o error según corresponda.
     *
     * @throws IllegalStateException Si la tarea tiene subtareas abiertas (no se puede cerrar).
     */
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

    /**
     * Asocia una nueva subtarea a una tarea principal existente.
     *
     * Flujo:
     * 1. Selecciona una tarea principal.
     * 2. Pide la descripción de la subtarea.
     * 3. Crea la subtarea y la asocia a la tarea principal mediante el servicio.
     *
     * @sample ge.pedirDescripcion() Solicita al usuario la descripción de la subtarea.
     */
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