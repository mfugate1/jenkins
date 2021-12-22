@NonCPS
String call(String text, Map templateMap) {
    extractVariablesFromString(text).findAll{!templateMap.containsKey(it)}.each {
        templateMap[it] = '${' + it + '}'
    }
    return new groovy.text.GStringTemplateEngine().createTemplate(text)
                                                  .make(templateMap)
                                                  .toString()
}