package caio.home.smartpointdev.dtos

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CadastroPJDto (

        var id: Long? = null,

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio.")
        @get:Email(message="Email inválido.")
        val email: String = "",

        @get:NotEmpty(message = "Senha não pode ser vazia.")
        val senha: String = "",

        @get:NotEmpty(message = "CPF não pode ser vazio.")
        val cpf: String = "",

        @get:NotEmpty(message = "CNPJ não pode ser vazio.")
        val cnpj: String = "",

        @get:NotEmpty(message = "Razão social não pode ser vazio.")
        val razaoSocial: String = ""
)