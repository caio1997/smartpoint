package caio.home.smartpointdev.services.Imple

import caio.home.smartpointdev.models.Lancamento
import caio.home.smartpointdev.repositories.LancamentoRepository
import caio.home.smartpointdev.services.LancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class LancamentoServiceImple(val lancamentoRepository: LancamentoRepository) : LancamentoService {

    override fun buscarPorFuncionarioId(funcionarioId: Long, pageRequest: PageRequest): Page<Lancamento> = lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: Long): Optional<Lancamento> = lancamentoRepository.findById(id)

    override fun persistir(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)

    override fun remover(id: Long) = lancamentoRepository.deleteById(id)

    override fun buscarTodos(): MutableIterable<Lancamento> = lancamentoRepository.findAll()
}