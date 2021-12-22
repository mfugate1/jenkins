@NonCPS
Map call() {
    return com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        org.jenkinsci.plugins.azurekeyvaultplugin.credentials.string.AzureSecretStringCredentials,
        Jenkins.instance
    ).collectEntries{[(it.id): it.secret]}
}