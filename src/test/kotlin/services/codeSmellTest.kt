package services

import es.prog2425.taskmanager.servicios.GestorActividades
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class codeSmellTest: DescribeSpec({


    describe("Instanciación de MiClase después de eliminar constructor vacío") {

        it("Debería instanciarse correctamente sin constructor explícito") {
            val instance = GestorActividades()
            instance shouldNotBe null
        }

    }
})