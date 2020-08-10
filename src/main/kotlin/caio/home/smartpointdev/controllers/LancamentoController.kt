package caio.home.smartpointdev.controllers

import caio.home.smartpointdev.dtos.LancamentoDto
import caio.home.smartpointdev.enums.TipoEnum
import caio.home.smartpointdev.models.Lancamento
import caio.home.smartpointdev.repositories.LancamentoRepository
import caio.home.smartpointdev.services.FuncionarioService
import caio.home.smartpointdev.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(
        val lancamentoService: LancamentoService,
        val funcionarioService: FuncionarioService,
        val lancamentoRepository: LancamentoRepository){

    private  val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @PostMapping
    fun adicionar(@Valid @RequestBody lancamentoDto : LancamentoDto): ResponseEntity<LancamentoDto> {

        if(lancamentoDto.funcionarioId == null){
            return ResponseEntity.badRequest().build()
        }

        val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto)
        lancamentoService.persistir(lancamento)
        return ResponseEntity.ok().body(converterLancamentoDto(lancamento))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Optional<Lancamento>> {

        val lancamento: Optional<Lancamento> = lancamentoService.buscarPorId(id)

        if (lancamento == null){
            return ResponseEntity.badRequest().body(null)
        }
        return ResponseEntity.ok().body(lancamento)
    }

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableIterable<Lancamento>> {
        return ResponseEntity.ok().body(lancamentoService.buscarTodos())
    }

    @Transactional
    @DeleteMapping("/{id}")
    fun remover(@PathVariable id: Long) {

        val lancamento: Optional<Lancamento> = lancamentoRepository.findById(id)

        if(lancamento != null){
            lancamentoService.remover(id)
        }
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody lancamentoDto: LancamentoDto): ResponseEntity<LancamentoDto> {

        lancamentoDto.id = id
        val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto)

        if (lancamento != null) {
            lancamentoService.persistir(lancamento)
            return ResponseEntity.ok(lancamentoDto)
        }
        return ResponseEntity.badRequest().body(null)
    }

    private fun converterLancamentoDto(lancamento: Lancamento): LancamentoDto? {

        return LancamentoDto(lancamento.id, dateFormat.format(lancamento.data), lancamento.tipo.toString(), lancamento.descricao, lancamento.localizacao, lancamento.funcionarioId)
    }

    private fun converterDtoParaLancamento(lancamentoDto: LancamentoDto): Lancamento {

        return Lancamento(lancamentoDto.id, dateFormat.parse(lancamentoDto.data), TipoEnum.valueOf(lancamentoDto.tipo!!), lancamentoDto.funcionarioId!!, lancamentoDto.descricao!!, lancamentoDto.localizacao)
    }

}