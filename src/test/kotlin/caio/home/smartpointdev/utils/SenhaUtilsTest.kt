package caio.home.smartpointdev.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {

    private val senha = "123456"
    private val senhaByCrypter = BCryptPasswordEncoder()

    @Test
    fun testSenhaCrypter(){
        val hash = SenhaUtils().gerarBCrypt(senha)
        Assertions.assertTrue(senhaByCrypter.matches(senha, hash))
    }
}