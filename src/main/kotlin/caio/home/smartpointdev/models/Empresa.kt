package caio.home.smartpointdev.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Empresa(
        @Id
        @GeneratedValue
        val id: Long? = 0L,
        val razaoSocial: String? = "",
        val cnpj: String? = ""
)