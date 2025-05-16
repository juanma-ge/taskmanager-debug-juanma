# Errores
Voy a arreglar estos cinco errores a continuación:

- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\modelo\Tarea.kt:6:7: Class 'Tarea' with '13' functions detected. Defined threshold inside classes is set to '11' [TooManyFunctions]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\servicios\GestorActividades.kt:21:9: The function menu appears to be too complex based on Cyclomatic Complexity (complexity: 16). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\servicios\GestorActividades.kt:14:7: Class 'GestorActividades' with '21' functions detected. Defined threshold inside classes is set to '11' [TooManyFunctions]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\servicios\GestorActividades.kt:111:18: The caught exception is swallowed. The original exception could be lost. [SwallowedException]
- C:\Users\juanm\IdeaProjects\taskmanager-debug-juanma\src\main\kotlin\datos\ActividadRepository.kt:1:1: The package declaration does not match the actual file location. [InvalidPackageDeclaration]

## Descarga Detekt
He importado el detekt, aunque he tenido que cambiar la versión jdk, ya que con las versiones más actuales no funcionaba.
![img.png](img.png)
![img_1.png](img_1.png)

## Soluciones
Solución del primer error ([TooManyFunctions]): https://github.com/juanma-ge/taskmanager-debug-juanma/commit/4cbb1482628713a5e1ea053d29caf50da8887615

