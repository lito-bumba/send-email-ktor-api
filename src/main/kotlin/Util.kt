fun getHtmlFromResource(fileName: String): String {
    try {
        return {}.javaClass.getResource("/$fileName")
            ?.readText() ?: throw Exception()
    } catch (_: Exception) {
        throw Exception("Resource file not found")
    }
}