@NonCPS
Map call() {
    Map secrets = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        org.jenkinsci.plugins.azurekeyvaultplugin.credentials.string.AzureSecretStringCredentials,
        Jenkins.instance
    ).collectEntries{[(it.id.replaceAll('-', '_')): it.secret.plainText]}

    com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        org.jenkinsci.plugins.azurekeyvaultplugin.credentials.usernamepassword.AzureUsernamePasswordCredentials,
        Jenkins.instance
    ).each {
        String id = it.id.replace('-', '_')
        secrets["${id}_USERNAME"] = it.username
        secrets["${id}_PASSWORD"] = it.password
    }

    return secrets
}