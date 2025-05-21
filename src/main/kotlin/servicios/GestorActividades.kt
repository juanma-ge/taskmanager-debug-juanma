package es.prog2425.taskmanager.servicios


import es.prog2425.taskmanager.modelo.*
import es.prog2425.taskmanager.presentacion.Consola
import es.prog2425.taskmanager.presentacion.Interfaz
import es.prog2425.taskmanager.utils.Utilidades


class GestorActividades {

    private val historial: Historial = Historial()
    private val servicios: IUsuarioService = UsuarioService()
    private val salida: Interfaz = Consola()
    private val servicio = ActividadService()
    private val ta = TrabajarTareas(salida, servicio)
    private val us = TrabajarUsuarios(salida, servicios)
    private val servicioUsuario: IUsuarioService = UsuarioService()

    // Muestra el menu principal
    fun menu() {
        var salir = false
        do {
            try {
                salida.mostrarMenu()
                val opcion = salida.leerNum()
                procesarOpcion(opcion)?.let { salir = it }
            } catch (e: IllegalStateException) {
                salida.mostrar("Error: ${e.message}")
                e.printStackTrace()
            }
        } while (!salir)
    }

    private fun procesarOpcion(opcion: Int): Boolean? {
        return when (opcion) {
            -1 -> {
                salida.mostrar("\nOpción no válida.")
                null
            }
            1 -> {
                servicio.crearEvento(descripcion = pedirDescripcion(), fecha = pedirFecha(), ubicacion = pedirUbicacion())
                null
            }
            2 -> {
                ta.crearTarea()
                null
            }
            3 -> {
                listarActividades()
                null
            }
            4 -> {
                ta.asociarSubtarea()
                null
            }
            5 -> {
                ta.cambiarEstadoTarea()
                null
            }
            6 -> {
                ta.cerrarTarea()
                null
            }
            7 -> {
                us.crearUsuario()
                null
            }
            8 -> {
                us.asignarTareaAUsuario()
                null
            }
            9 -> {
                us.consultarTareasUsuario()
                null
            }
            10 -> {
                filtrarActividades()
                null
            }
            11 -> {
                consultarHistorialTarea()
                null
            }
            12 -> true
            else -> {
                salida.mostrar("\nOpción no válida.")
                null
            }
        }
    }


    private fun consultarHistorialTarea() {
        salida.mostrar("\nSelecciona la tarea para ver su historial:")
        val tarea = obtenerTarea()
        val historial = historial.obtener()
        if (historial.isEmpty()) {
            salida.mostrar("\nLa tarea no tiene historial.")
        } else {
            salida.mostrar("\nHistorial de la tarea:")
            historial.forEach { (fecha, accion) ->
                salida.mostrar("[$fecha] $accion")
            }
        }
    }

    // Listar actividades
    private fun listarActividades() {
        val actividades = servicio.listarActividades()
        if (actividades.isEmpty()) {
            salida.mostrar("\nNo hay actividades registradas.")
        } else {
            salida.mostrar("\nListado de actividades:")
            actividades.forEach { actividad ->
                salida.mostrar(actividad.obtenerDetalle())
            }
        }
    }

    private fun pedirEtiquetas(): List<String> {
        salida.mostrarInput("Introduce las etiquetas (separadas por ';'):")
        return salida.leerString().split(';').map { it.trim() }.filter { it.isNotEmpty() }
    }

    // Obtener tarea por ID o descripción
    fun obtenerTarea(): Tarea {
        // Listamos todas las tareas para que el usuario vea y elija
        val tareas = servicio.listarActividades().filterIsInstance<Tarea>()

        if (tareas.isEmpty()) {
            salida.mostrar("\nNo hay tareas disponibles.")
            println("Error no controlado si no hay try-catch")
            throw IllegalStateException("No hay tareas disponibles para seleccionar.")

        }

        salida.mostrar("\nSelecciona una tarea de la lista:")
        tareas.forEachIndexed { index, tarea ->
            salida.mostrar("${index + 1}. ${tarea.obtenerDetalle()}")
        }

        // Le pedimos al usuario que elija una tarea por su número (índice + 1)
        var tareaSeleccionada: Tarea? = null
        while (tareaSeleccionada == null) {
            salida.mostrar("\nIntroduce el número de la tarea:")
            val numeroTarea = salida.leerNum()

            if (numeroTarea in 1..tareas.size) {
                tareaSeleccionada = tareas[numeroTarea - 1]
            } else {
                salida.mostrar("\nOpción inválida. Por favor, elige un número válido.")
            }
        }

        return tareaSeleccionada
    }

    fun pedirDescripcion(): String {
        while (true) {
            salida.mostrar("\nIntroduce la descripcion")
            salida.mostrarInput("> ")
            val descripcion = salida.leerString()

            if (descripcion != "") return descripcion else salida.mostrar("\nLa descripcion debe contener algo.")
        }
    }

    private fun pedirFecha(): String {
        while (true) {
            salida.mostrar("\nIntroduce la fecha con el siguiente formato (dd-MM-yyyy)")
            salida.mostrarInput("> ")
            val fecha = salida.leerString()

            if (Utilidades().esFechaValida(fecha)) {
                return fecha
            } else salida.mostrar("\nFecha invalida.")
        }
    }

    private fun pedirUbicacion(): String {
        while (true) {
            salida.mostrar("\nIntroduce la ubicacion")
            salida.mostrarInput("> ")
            val ubicacion = salida.leerString()

            if (ubicacion != "") return ubicacion else salida.mostrar("\nLa ubicacion debe contener algo.")
        }
    }


    private fun filtrarActividades() {
        salida.mostrar("\nFiltrar actividades por:")
        salida.mostrar("1. Tipo (Tarea o Evento)")
        salida.mostrar("2. Estado (ABIERTA, EN_PROGRESO, FINALIZADA)")
        salida.mostrar("3. Etiquetas")
        salida.mostrar("4. Usuario")
        val opcionFiltro = salida.leerNum()

        when (opcionFiltro) {
            1 -> filtrarPorTipo()
            2 -> filtrarPorEstado()
            3 -> filtrarPorEtiquetas()
            4 -> filtrarPorUsuario()
            else -> salida.mostrar("\nOpción no válida.")
        }
    }

    private fun filtrarPorUsuario() {
        salida.mostrar("\nIntroduce el nombre del usuario a filtrar:")
        val nombreUsuario = salida.leerString()
        val usuario = servicioUsuario.obtenerUsuarioPorNombre(nombreUsuario) ?: run {
            salida.mostrar("\nNo se encontró un usuario con ese nombre.")
            return
        }
        val filtradas = servicio.listarActividades()
            .filterIsInstance<Tarea>()
            .filter { it.obtenerUsuarioAsignado() == usuario }
        mostrarActividades(filtradas)
    }

    private fun filtrarPorTipo() {
        salida.mostrar("\nSelecciona el tipo de actividad a filtrar:")
        salida.mostrar("1. Tarea")
        salida.mostrar("2. Evento")
        val tipoSeleccionado = salida.leerNum()

        val actividadesFiltradas = when (tipoSeleccionado) {
            1 -> servicio.listarActividades().filterIsInstance<Tarea>()
            2 -> servicio.listarActividades().filterIsInstance<Evento>()
            else -> {
                salida.mostrar("\nOpción no válida.")
                return
            }
        }

        mostrarActividades(actividadesFiltradas)
    }

    private fun filtrarPorEstado() {
        salida.mostrar("\nSelecciona el estado de la actividad a filtrar:")
        salida.mostrar("1. ABIERTA")
        salida.mostrar("2. EN PROGRESO")
        salida.mostrar("3. FINALIZADA")
        val estadoSeleccionado = salida.leerNum()

        val estado = when (estadoSeleccionado) {
            1 -> Estado.ABIERTA
            2 -> Estado.EN_PROGRESO
            3 -> Estado.FINALIZADA
            else -> {
                salida.mostrar("\nOpción no válida.")
                return
            }
        }

        val actividadesFiltradas = servicio.listarActividades().filter { it is Tarea && it.estado == estado }
        mostrarActividades(actividadesFiltradas)
    }

    private fun filtrarPorEtiquetas() {
        salida.mostrar("\nIntroduce la etiqueta a filtrar: ")
        val etiqueta = salida.leerString()

        val actividadesFiltradas = servicio.listarActividades().filter {
            (it is Tarea || it is Evento) && it.obtenerEtiquetas().contains(etiqueta)
        }

        mostrarActividades(actividadesFiltradas)
    }

    private fun mostrarActividades(actividades: List<Actividad>) {
        if (actividades.isEmpty()) {
            salida.mostrar("\nNo se encontraron actividades con los filtros seleccionados.")
        } else {
            salida.mostrar("\nActividades filtradas:")
            actividades.forEach { actividad ->
                salida.mostrar(actividad.obtenerDetalle())
            }
        }
    }

}