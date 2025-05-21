package services

import es.prog2425.taskmanager.servicios.GestorActividades
import es.prog2425.taskmanager.servicios.GestorEtiquetas
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class codeSmellTest: DescribeSpec({


    describe("Instanciación de MiClase después de eliminar constructor vacío") {

        it("Debería instanciarse correctamente sin constructor explícito") {
            val instance = GestorActividades()
            instance shouldNotBe null
        }

    }

    val gestor = GestorEtiquetas()

    describe("agregarEtiquetaUnica") {
        it("agrega cuando no existe") {
            val etiquetas = mutableSetOf<String>()
            gestor.agregarEtiquetaUnica(etiquetas, "nueva")
            etiquetas shouldBe setOf("nueva")
        }

        it("no agrega duplicados") {
            val etiquetas = mutableSetOf("existente")
            gestor.agregarEtiquetaUnica(etiquetas, "existente")
            etiquetas shouldBe setOf("existente")
        }
    }

    describe("procesarEntradaEtiquetas") {
        it("divide por comas y limpia espacios") {
            gestor.procesarEntradaEtiquetas(" a , b , c ") shouldBe listOf("a", "b", "c")
        }

        it("elimina elementos vacíos") {
            gestor.procesarEntradaEtiquetas("a,,b") shouldBe listOf("a", "b")
        }
    }
})
