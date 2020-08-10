package caio.home.smartpointdev.services

import caio.home.smartpointdev.enums.TipoEnum
import caio.home.smartpointdev.models.Lancamento
import caio.home.smartpointdev.repositories.LancamentoRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
@Service
class LancamentoServiceTest {

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    @Autowired
    private val lancamentoService: LancamentoService? = null

    private val id: Long = 1L
    private val idLong: Long = 1L

    private fun lancamento(): Lancamento = Lancamento(null, Date(), TipoEnum.INICIO_TRABALHO, id)

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given<Page<Lancamento>>(lancamentoRepository?.findByFuncionarioId(id, PageRequest.of(0, 10))).willReturn(PageImpl(ArrayList<Lancamento>()))
        BDDMockito.given(lancamentoRepository?.save(Mockito.any(Lancamento::class.java))).willReturn(lancamento())
    }

    @Test
    fun testBuscarLancamentoPorFuncionario() {
        val lancamento: Page<Lancamento>? = lancamentoService?.buscarPorFuncionarioId(id, PageRequest.of(0, 10))
        Assertions.assertNotNull(lancamento)

    /*
        @Test
        fun testBuscarLancamentoPorId() {
            val lancamento: Optional<Lancamento>? = lancamentoService?.findById(idLong)
            Assertions.assertNotNull(lancamento)
        }

     */

        @Test
        fun testPersistir() {
            val lancamento: Lancamento? = lancamentoService?.persistir(lancamento())
            Assertions.assertNotNull(lancamento)
        }
    }
}