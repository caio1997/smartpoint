package caio.home.smartpointdev.controllers

import caio.home.smartpointdev.dtos.CadastroPFDto
import caio.home.smartpointdev.enums.PerfilEnum
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
@RequestMapping("/api/cadastrar-pf")
class CadastroPFController(
        val funcionarioService: FuncionarioService,
        val empresaService: EmpresaService){

    @PostMapping
    fun adicionar(@Valid @RequestBody cadastroPFDto: CadastroPFDto): ResponseEntity<CadastroPFDto> {

        val funcionario: Funcionario = converterDtoParaFuncionario(cadastroPFDto)
        if(funcionario != null){
            funcionarioService.persistir(funcionario)
            return ResponseEntity.ok().body(cadastroPFDto)
        }
        return ResponseEntity.badRequest().body(null)
    }

    fun converterDtoParaFuncionario(cadastroPFDto: CadastroPFDto) = Funcionario(cadastroPFDto.id, cadastroPFDto.nome, cadastroPFDto.email, SenhaUtils().gerarBCrypt(cadastroPFDto.senha),
                                    cadastroPFDto.cpf, PerfilEnum.ROLE_USUARIO, cadastroPFDto.empresaId, cadastroPFDto.valorHora?.toDouble(), cadastroPFDto.qtdHorasTrabalhoDia?.toFloat(),
                                    cadastroPFDto.qtdHorasAlmoco?.toFloat())
}