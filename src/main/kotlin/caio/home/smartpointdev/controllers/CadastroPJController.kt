package caio.home.smartpointdev.controllers

import caio.home.smartpointdev.dtos.CadastroPJDto
import caio.home.smartpointdev.dtos.FuncionarioDto
import caio.home.smartpointdev.enums.PerfilEnum
import caio.home.smartpointdev.models.Empresa
import caio.home.smartpointdev.models.Funcionario
import caio.home.smartpointdev.services.EmpresaService
import caio.home.smartpointdev.services.FuncionarioService
import caio.home.smartpointdev.utils.SenhaUtils
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/cadastrar-pj")
class CadastroPJController(
        val empresaService: EmpresaService,
        val funcionarioService: FuncionarioService) {

    @PostMapping
    fun cadastrar(@Valid @RequestBody cadastroPJDto: CadastroPJDto): ResponseEntity<CadastroPJDto> {

        val empresa: Empresa = converterDtoParaEmpresa(cadastroPJDto)
        if (empresa != null) {
            empresaService.persistir(empresa)
            val funcionario: Funcionario = converterDtoParaFuncionario(cadastroPJDto, empresa)
            funcionarioService.persistir(funcionario)
            return ResponseEntity.ok().body(cadastroPJDto)
        }
        return ResponseEntity.badRequest().body(null)
    }

    private fun converterDtoParaEmpresa(cadastroPJDto: CadastroPJDto): Empresa{
        return Empresa(cadastroPJDto.id, cadastroPJDto.razaoSocial, cadastroPJDto.cnpj)
    }

    private fun converterDtoParaFuncionario(cadastroPJDto: CadastroPJDto, empresa: Empresa) = Funcionario(cadastroPJDto.id, cadastroPJDto.nome, cadastroPJDto.email, SenhaUtils().gerarBCrypt(cadastroPJDto.senha),
                                            cadastroPJDto.cpf, PerfilEnum.ROLE_ADMIN, empresa.id)
}