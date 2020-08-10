package caio.home.smartpointdev.repositories

import caio.home.smartpointdev.models.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface LancamentoRepository : CrudRepository<Lancamento, String> {

    fun findByFuncionarioId(funcionarioId: Long, pageable: Pageable): Page<Lancamento>

    fun findById(id: Long): Optional<Lancamento>

    fun deleteById(id: Long)
}