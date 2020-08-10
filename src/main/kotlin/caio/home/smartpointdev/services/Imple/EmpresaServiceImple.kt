package caio.home.smartpointdev.services.Imple

import caio.home.smartpointdev.models.Empresa
import caio.home.smartpointdev.repositories.EmpresaRepository
import caio.home.smartpointdev.services.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImple(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)
}