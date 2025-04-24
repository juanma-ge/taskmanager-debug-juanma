## TaskManager-Debug
Introducción

### Errores encontrados
1. Error al seleccionar por consola la opción 6 del menú principal.
![img.png](img.png)
![img.png](errorEncontrado.png)
He creado un punto de ruptura justo en el método menu qué es justo dónde salta el error. El problema era que el código lanza un error cuando introduces una opción no válida pero no lo captura, lo cuál hace que el programa se cierre de manera inminente.


