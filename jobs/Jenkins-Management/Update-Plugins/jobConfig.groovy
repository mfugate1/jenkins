job ('Jenkins-Update-Plugins') {
    label('docker')
    triggers {
        cron('H 1 * * *')
    }
    steps {
        systemGroovyCommand(readFileFromWorkspace('jobs/Jenkins-Management/Update-Plugins/update-plugins.groovy'))
    }
}