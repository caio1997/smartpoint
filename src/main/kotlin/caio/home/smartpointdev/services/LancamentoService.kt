package caio.home.smartpointdev.services

import caio.home.smartpointdev.models.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.CrudRepository
import java.util.*

interface LancamentoService{

    fun buscarPorFuncionarioId(funcionarioId: Long, pageRequest: PageRequest): Page<Lancamento>

    fun buscarPorId(id: Long): Optional<Lancamento>

    fun persistir(lancamento: Lancamento): Lancamento

    fun remover(id: Long)

    fun buscarTodos(): MutableIterable<Lancamento>
}