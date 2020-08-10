package caio.home.smartpointdev.repositories

import caio.home.smartpointdev.models.Empresa
import org.springframework.data.repository.CrudRepository

interface EmpresaRepository : CrudRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa
}