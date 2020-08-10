package caio.home.smartpointdev.services

import caio.home.smartpointdev.enums.PerfilEnum
import caio.home.smartpointdev.models.Funcionario
import caio.home.smartpointdev.repositories.FuncionarioRepository
import caio.home.smartpointdev.utils.SenhaUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.stereotype.Service
import java.util.*

@SpringBootTest
@Service
class FuncionarioServiceTest {

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    private val email: String = "caio_mcoelho@hotmail.com"
    private val cpf: String = "10120417600"
    private val id: String = "1"
    private fun funcionario(): Funcionario = Funcionario(null, "Caio", email, SenhaUtils().gerarBCrypt("123456"), cpf, PerfilEnum.ROLE_USUARIO, 1, valorHora = 0.0, qtdHorasTrabalhoDia = 0.0f, qtdHorasAlmoco = 0.0f)

    @Autowired
    private val funcionarioService: FuncionarioService? = null



    @BeforeEach
    @Throws(Exception::class)
    fun setUp(){
        BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java))).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())
    }

    @Test
    fun testPeristencia(){
        val funcionario: Funcionario? = this.funcionarioService?.persistir(funcionario())
        print(funcionario)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId(){
        val funcionario: Optional<Funcionario>? = funcionarioRepository?.findById(id)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarPorEmail(){
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(email)
        Assertions.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarPorCpf(){
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(cpf)
        Assertions.assertNotNull(funcionario)
    }
}