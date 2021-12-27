void call() {
    GlobalConfiguration.all().get(org.jenkinsci.plugins.azurekeyvaultplugin.AzureKeyVaultGlobalConfiguration.class).doReloadCache()
}