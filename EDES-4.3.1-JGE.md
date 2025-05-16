# Errores
Voy a arreglar estos cinco errores a continuación:

- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\modelo\Tarea.kt:6:7: Class 'Tarea' with '13' functions detected. Defined threshold inside classes is set to '11' [TooManyFunctions]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\servicios\GestorActividades.kt:21:9: The function menu appears to be too complex based on Cyclomatic Complexity (complexity: 16). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\servicios\GestorActividades.kt:14:7: Class 'GestorActividades' with '21' functions detected. Defined threshold inside classes is set to '11' [TooManyFunctions]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\servicios\GestorActividades.kt:111:18: The caught exception is swallowed. The original exception could be lost. [SwallowedException]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\datos\ActividadRepository.kt:22:2: The file C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\datos\ActividadRepository.kt is not ending with a new line. [NewLineAtEndOfFile]

# Detekt
- Es una herramienta que revisa tu código para revisar errores o malas prácticas.
- Trata de mantener cierta calidad y evita futuros problemas difíciles de detectar.
- He insertado servicios 

## Descarga Detekt
He importado el detekt, aunque he tenido que cambiar la versión jdk, ya que con las versiones más actuales no funcionaba.
![img.png](img.png)
![img_1.png](img_1.png)

## Soluciones
Solución del primer error ([TooManyFunctions]): https://github.com/juanma-ge/taskmanager-debug-juanma/commit/4cbb1482628713a5e1ea053d29caf50da8887615
- El error establecía que tenía demasiadas funciones en él, 13, y no pueden ser más de 11, así que he creado otra clase "historial" y he metido as funciones que tenían que ver con el historial.
Solución del segundo error ([CyclomaticComplexMethod]): https://github.com/juanma-ge/taskmanager-debug-juanma/commit/096ff322bdd17c61c4fdcee6d78294b53ebf5a64
- Este error decía que la clase GestorActividades tenía demasiadas funciones en su menú y era demasiado "complejo", así que lo he separado en dos, para reducir la cantidad de for, while, etc.
Solución del tercer error ([TooManyFunctions]): https://github.com/juanma-ge/taskmanager-debug-juanma/commit/6894f5a25dc64d372bda192436309534bf013f41
- Como en el primer error, este dice que hay demasiadas funciones en la clase, diez más concretamente, así que las he dividido en dos clases más.
Solución del cuarto error ([SwallowedException]): https://github.com/juanma-ge/taskmanager-debug-juanma/commit/50bdc0a8dfd4a1400d90a38f0c4e7d758714c61c
- Este error establecía que el catch del try and catch mostraba poca información en caso de error, así que lo he cambiado para que muestre un error distinto algo más largo y elaborado.
Solución del quinto error ([InvalidPackageDeclaration]): https://github.com/juanma-ge/taskmanager-debug-juanma/commit/fd0150a6cb581401fe543cc43a4dbf0e3b517665
- Este error requería que al final del código, tuviera una nueva línea en blanco antes de que la clase termine.

## Preguntas 

- Responde a las preguntas:

- [1]
- 1.a ¿Qué herramienta has usado, y para qué sirve? 
- He utilizado Detekt, una herramienta de análisis para Kotlin. Sirve para detectar errores o malas prácticas.
- 1.b ¿Cuáles son sus características principales?
- Análisis y detección de código, uso de gradle y plugins, y generación de informes de los errores detectados.
- 1.c ¿Qué beneficios obtengo al utilizar dicha herramienta?
- Te ayuda a detectar errores o aspectos mejorables de tu código más rápida y fácilmente.
- [2]
- 2.a De los errores/problemas que la herramienta ha detectado y te ha ayudado a solucionar, ¿cuál es el que te ha parecido que ha mejorado más tu código?
- El tercero, ya que ha pasado de una clase tener 21 funciones a solo 11, lo cual ha sido bastante optimizada.
- 2.b ¿La solución que se le ha dado al error/problema la has entendido y te ha parecido correcta?
- Sí, me ha ayudado a solucionarlo y me ha parecido útil.
- 2.c ¿Por qué se ha producido ese error/problema?
- Como he dicho antes, dicha clase estaba sobrecargada de funciones.
- [3]
- 3.a ¿Qué posibilidades de configuración tiene la herramienta?
- Número máximo de funciones por archivo, límite de líneas, reglas de estilo o rutas de los archivos, las cuales deben de ser correctas.
- 3.b De esas posibilidades de configuración, ¿cuál has configurado para que sea distinta a la que viene por defecto?
- He cambiado "TooManyFunctions", para que permita una más, es decir 12.
- 3.c Pon un ejemplo de como ha impactado en tu código, enlazando al código anterior al cambio, y al posterior al cambio,
- Ahora si debo de volver a hacer un cambio como antes, tendré que mover menos funciones y clases para cumplir con el objetivo.
- [4]
- 4 ¿Qué conclusiones sacas después del uso de estas herramientas?
- Me parece una herramienta más que útil no solo para corregir errores o cumplir con buenas prácticas, sino que también para aprender de ellas y mejorar tu código para futuros proyectos.