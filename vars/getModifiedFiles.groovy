@NonCPS
void call() {
    List files = []
    def build = currentBuild
    while (build != null && build.result != 'SUCCESS') {
        build.changeSets.each { changeSet ->
            changeSet.items.each {
                files += it.affectedPaths
            }
        }
        build = build.previousBuild
    }
    return files
}