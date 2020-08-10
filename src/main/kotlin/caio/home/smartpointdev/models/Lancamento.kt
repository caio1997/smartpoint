package caio.home.smartpointdev.models


import caio.home.smartpointdev.enums.TipoEnum
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Lancamento(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val data: Date,
        val tipo: TipoEnum,
        val funcionarioId: Long? = null,
        val descricao: String? = "",
        val localizacao: String? = ""
)