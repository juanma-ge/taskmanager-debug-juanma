# TaskManager-Debug

## Introducción

## Errores encontrados 

### 1. Error al seleccionar por consola la opción 6 del menú principal(Jesús)

![img.png](assets/img.png)
![img.png](assets/errorEncontrado.png)

He creado un punto de ruptura justo en el método menu qué es justo dónde salta el error. El problema era que el código lanza un error cuando no hay tareas existentes para cerrar
pero no lo captura, lo cuál hace que el programa se cierre de manera inminente. La solución ha sido introducir un try-catch en el método menu.

Para encontrar el error, también podemos utilizar un println y ver por consola dónde está el error:

![img_1.png](assets/img_1.png)
![img_2.png](assets/img_2.png)

### 2. Error al seleccionar la opción 5 en el menú principal.

![img_4.png](assets/img_4.png)
![img_5.png](assets/img_5.png)

He puesto un punto de ruptura en el menú, aunque al final no fue del todo necesario, para ver si saltaba el error. El problema era que al no haber ninguna tarea daba error, haciendo que el programa se cierre.
Para solucionarlo he implementado un try and catch en el menu de la aplicación, para si vuelve a dar el error, que el programa no se cierre.

![img_6.png](assets/img_6.png)

### 3. Error al separar las etiquetas (Jose Luis)

#### Imagen previa al error
![](assets/etiqueta_error.png)

Nos dimos cuenta de que, al pedir las etiquetas al usuario, todo lo que escribía (por ejemplo "urgente; oficina; reunión") 
llegaba al sistema como una sola frase en vez de como tres palabras separadas. Eso hacía que, al buscar tareas por una 
etiqueta concreta, nunca apareciera nada. Metimos un "punto de parada" para inspeccionar lo que se leía y comprobamos 
que seguía siendo un único texto. La solución fue muy simple: en lugar de pasar la cadena tal cual, la separamos donde 
hubiera ";", quitamos espacios sobrantes, de modo que el servicio recibiera siempre una 
lista limpia de etiquetas. Después de ajustar un par de pruebas para cubrir casos raros, todo empezó a funcionar bien.

#### Imagen posterior al error
![](assets/etiqueta_solucion.png)