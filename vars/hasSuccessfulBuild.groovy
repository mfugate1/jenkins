@NonCPS
boolean call() {
    boolean hasSuccess = false
    def build = currentBuild
    while (build != null) {
        if (build.result == 'SUCCESS') {
            hasSuccess = true
            break
        }
        build = build.previousBuild
    }
    return hasSuccess
}