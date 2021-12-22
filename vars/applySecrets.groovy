// Returns a map of files with their secrets
Map call(String fileGlob, Map secrets = [:]) {
    if (!secrets) secrets = getAzureVaultSecrets()
    Map files = [:]
    for (String file in findFiles(glob: fileGlob).collect{it.path}) {
        String contents = readFile(file)
        writeFile (
            file: file,
            text: secretReplacement(contents, secrets)
        )
        files[file] = getSecretsInFile(contents, secrets)
    }
}

@NonCPS
String secretReplacement(String contents, Map secrets) {
    secrets.each { secret, value ->
        contents = contents.replace('\${' + secret + '}', value)
    }
    return contents
}

@NonCPS
List getSecretsInFile(String contents, Map secrets) {
    return secrets.findAll{contents.contains("\${${it.key}}")}.collect{it.key}
}