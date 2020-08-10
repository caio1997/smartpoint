package caio.home.smartpointdev.response

data class Response<T> (
        val erros: ArrayList<String> = arrayListOf(),
        val data: T? = null
)