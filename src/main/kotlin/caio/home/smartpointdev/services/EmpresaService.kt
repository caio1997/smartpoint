package caio.home.smartpointdev.services

import caio.home.smartpointdev.models.Empresa

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa

}