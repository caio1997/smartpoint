package caio.home.smartpointdev.services

import caio.home.smartpointdev.models.Empresa
import caio.home.smartpointdev.repositories.EmpresaRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.stereotype.Service

@SpringBootTest
@Service
class EmpresaServiceTest {

    @Autowired
    val empresaService: EmpresaService? = null

    @MockBean
    private val empresaRepository: EmpresaRepository? = null

    private val cnpj = "10120417600000"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp(){
        BDDMockito.given(empresaRepository?.findByCnpj(cnpj)).willReturn(empresa())
        BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
    }

    @Test
    fun testBuscarEmpresaCnpj(){
        val empresa: Empresa? = empresaService?.buscarPorCnpj(cnpj)
        Assertions.assertNotNull(empresa)
    }

    @Test
    fun testPersisteEmpresa(){
        val empresa: Empresa? = empresaService?.persistir(empresa())
        Assertions.assertNotNull(empresa)
    }

    private fun empresa(): Empresa = Empresa(null, "RazaoSocial", "10120417600")
}