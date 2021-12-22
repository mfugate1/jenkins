@NonCPS
List call(String str) {
    def pattern = ~/\$\{([^\}]*)\}/
    return pattern.matcher(str).collect { it[1] }
}