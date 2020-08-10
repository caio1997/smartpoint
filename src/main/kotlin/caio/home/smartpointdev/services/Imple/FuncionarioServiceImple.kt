package caio.home.smartpointdev.services.Imple

import caio.home.smartpointdev.models.Funcionario
import caio.home.smartpointdev.repositories.FuncionarioRepository
import caio.home.smartpointdev.services.FuncionarioService
import org.springframework.stereotype.Service
import java.util.*

@Service
class FuncionarioServiceImple(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    override fun persistir(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Optional<Funcionario> = funcionarioRepository.findById(id)
}