package services

import es.prog2425.taskmanager.servicios.GestorActividades
import es.prog2425.taskmanager.servicios.GestorEtiquetas
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain

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

    describe("Creación de Actividad") {
        it("debe aceptar descripción válida") {
            val testActividad = TestActividad("Reunión")
            testActividad.descripcion shouldBe "Reunión"
        }

        it("debe fallar con descripción vacía") {
            shouldThrow<IllegalArgumentException> {
                TestActividad("")
            }
        }
    }

    describe("Gestión de Etiquetas") {
        val actividad = TestActividad("Tarea")

        it("debe empezar sin etiquetas") {
            actividad.obtenerEtiquetas().size shouldBe 0
        }

        it("debe agregar etiquetas correctamente") {
            actividad.etiquetas.add("urgente")
            actividad.obtenerEtiquetas() shouldBe listOf("urgente")
        }
    }

    describe("Detalle de Actividad") {
        it("debe mostrar descripción correctamente") {
            val actividad = TestActividad("Evento")
            actividad.obtenerDetalle() shouldContain "Descripción: Evento"
        }

        it("debe mostrar etiquetas cuando existen") {
            val actividad = TestActividad("Recordatorio")
            actividad.etiquetas.add("importante")
            actividad.obtenerDetalle() shouldContain "importante"
        }
    }
})
