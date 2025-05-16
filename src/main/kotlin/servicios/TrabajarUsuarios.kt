package es.prog2425.taskmanager.servicios

import es.prog2425.taskmanager.presentacion.Interfaz

class TrabajarUsuarios(val salida: Interfaz, val servicios: IUsuarioService) {

    private val servicioUsuario: IUsuarioService = UsuarioService()
    private val ge: GestorActividades = GestorActividades()


    fun crearUsuario() {
        salida.mostrar("\nIntroduce el nombre del nuevo usuario: ")
        val nombre = salida.leerString()
        servicioUsuario.crearUsuario(nombre)
        salida.mostrar("\nUsuario '$nombre' creado con éxito.")
    }

    fun asignarTareaAUsuario() {
        salida.mostrar("\nSelecciona la tarea a asignar: ")
        val tarea = ge.obtenerTarea()

        salida.mostrar("\nIntroduce el nombre del usuario al que asignar la tarea: ")
        val nombreUsuario = salida.leerString()

        val usuario = servicioUsuario.obtenerUsuarioPorNombre(nombreUsuario)
        if (usuario != null) {
            servicioUsuario.asignarTareaAUsuario(usuario, tarea)
            salida.mostrar("\nTarea asignada correctamente a $nombreUsuario.")
        } else {
            salida.mostrar("\nNo se encontró un usuario con ese nombre.")
        }
    }

    fun consultarTareasUsuario() {
        salida.mostrar("\nIntroduce el nombre del usuario para consultar sus tareas: ")
        val nombreUsuario = salida.leerString()

        val usuario = servicioUsuario.obtenerUsuarioPorNombre(nombreUsuario)
        if (usuario != null) {
            val tareas = servicioUsuario.obtenerTareasPorUsuario(usuario)
            if (tareas.isEmpty()) {
                salida.mostrar("\nEl usuario no tiene tareas asignadas.")
            } else {
                salida.mostrar("\nTareas asignadas a $nombreUsuario:")
                tareas.forEach { tarea ->
                    salida.mostrar(tarea.obtenerDetalle())
                }
            }
        } else {
            salida.mostrar("\nNo se encontró un usuario con ese nombre.")
        }
    }
}