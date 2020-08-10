package caio.home.smartpointdev.models

import caio.home.smartpointdev.enums.PerfilEnum
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Funcionario(
        @Id
        @GeneratedValue
        val id: Long? = 0L,
        val nome: String,
        val email: String,
        val senha: String,
        val cpf: String,
        val perfil: PerfilEnum,
        val empresaId: Long?,
        val valorHora: Double? = 0.0,
        val qtdHorasTrabalhoDia: Float? = 0.0f,
        val qtdHorasAlmoco: Float? = 0.0f
)