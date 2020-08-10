package caio.home.smartpointdev

import caio.home.smartpointdev.enums.PerfilEnum
import caio.home.smartpointdev.models.Empresa
import caio.home.smartpointdev.models.Funcionario
import caio.home.smartpointdev.repositories.EmpresaRepository
import caio.home.smartpointdev.repositories.FuncionarioRepository
import caio.home.smartpointdev.repositories.LancamentoRepository
import caio.home.smartpointdev.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmartpointdevApplication(val empresaRepository: EmpresaRepository,
							   val funcionarioRepository: FuncionarioRepository,
								val lancamentoRepository: LancamentoRepository) : CommandLineRunner {

	override fun run(vararg args: String?) {
		empresaRepository.deleteAll()
		funcionarioRepository.deleteAll()
		lancamentoRepository.deleteAll()

		val empresa: Empresa = Empresa(null, "Empresa", "10443887000146")
		empresaRepository.save(empresa)

		val admin: Funcionario = Funcionario(null, "Admin", "admin@empresa.com",
				SenhaUtils().gerarBCrypt("123456"), "25708317000",
				PerfilEnum.ROLE_ADMIN, empresa.id)
		funcionarioRepository.save(admin)

		val funcionario: Funcionario = Funcionario(null, "Funcionario",
				"funcionario@empresa.com", SenhaUtils().gerarBCrypt("123456"),
				"44325441557", PerfilEnum.ROLE_USUARIO, empresa.id)
		funcionarioRepository.save(funcionario)

		System.out.println("Empresa ID: " + empresa.id)
		System.out.println("Admin ID: " + admin.id)
		System.out.println("Funcionario ID: " + funcionario.id)
	}
}

fun main(args: Array<String>) {
	runApplication<SmartpointdevApplication>(*args)
}
