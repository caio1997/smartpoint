package caio.home.smartpointdev.dtos

import javax.validation.constraints.NotEmpty

data class LancamentoDto(

        var id: Long? = null,

        @get:NotEmpty(message = "Data não pode ser vazia.")
        val data: String? = null,

        @get:NotEmpty(message = "Tipo não pode ser vazio.")
        val tipo: String? = null,

        val descricao: String? = null,
        val localizacao: String? = null,
        val funcionarioId: Long? = null

)