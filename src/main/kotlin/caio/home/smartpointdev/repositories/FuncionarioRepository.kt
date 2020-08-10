package caio.home.smartpointdev.repositories

import caio.home.smartpointdev.models.Funcionario
import org.springframework.data.repository.CrudRepository
import java.util.*

interface FuncionarioRepository : CrudRepository<Funcionario, String> {

    fun findByCpf(cpf: String): Funcionario?

    fun findByEmail(email: String): Funcionario?

    override fun findById(id: String): Optional<Funcionario>
}