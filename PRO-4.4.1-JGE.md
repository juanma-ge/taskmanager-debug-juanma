# Documentación de Clases con KDoc y Dokka en Kotlin
## Introducción
- Para hacer este ejercicio, he necesitado plugins, hacer documentación y utilizar kDoc, ahora paso a paso enseñaré cómo lo he hecho.
## Instalación
- He insertado varios puligins en el gradle los cuales me han permitido usar el dokka.
https://github.com/juanma-ge/taskmanager-debug-juanma/blob/474b183b25047f2c4c501e7936ce919069788888/build.gradle.kts#L1-L4
https://github.com/juanma-ge/taskmanager-debug-juanma/blob/474b183b25047f2c4c501e7936ce919069788888/build.gradle.kts#L28-L39
## Documentación
- He documentado tres funciones de una misma clase como pedía el ejercicio.
- En este caso de la clase 'TrabajarTareas'.
https://github.com/juanma-ge/taskmanager-debug-juanma/blob/474b183b25047f2c4c501e7936ce919069788888/src/main/kotlin/servicios/TrabajarTareas.kt#L17-L54
https://github.com/juanma-ge/taskmanager-debug-juanma/blob/474b183b25047f2c4c501e7936ce919069788888/src/main/kotlin/servicios/TrabajarTareas.kt#L57-L74
https://github.com/juanma-ge/taskmanager-debug-juanma/blob/474b183b25047f2c4c501e7936ce919069788888/src/main/kotlin/servicios/TrabajarTareas.kt#L76-L96
## Dokka
- Al clicar en Gradle y dokkaHtml se cargará la documentación en html.
- Si no funciona, como me pasó a mí, se podrá aplicar el código en terminal 'start build/dokka/html/index.html', por poner mi ejemplo.
- En la carpeta build podremos encontrar los archivos hmtl donde podremos ver los resultados.
![image](https://github.com/user-attachments/assets/dcf4ef11-db3b-47f3-a794-7061d010039a)
- Y dentro encontramos:
![Imagen de WhatsApp 2025-05-22 a las 18 35 18_c34fdbeb](https://github.com/user-attachments/assets/6aa10a74-8a15-45d4-bbe4-1e08eaccc43e)
![Imagen de WhatsApp 2025-05-22 a las 18 37 44_0683e79d](https://github.com/user-attachments/assets/e0d0712c-d9fb-4fe8-9c05-d01c02bbed39)
