package caio.home.smartpointdev.dtos

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class FuncionarioDto (

        var id: Long? = null,

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio.")
        @get:Email(message="Email inválido.")
        val email: String = "",

        val senha: String? = null,
        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHorasAlmoco: String? = null

)