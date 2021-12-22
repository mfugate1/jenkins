@NonCPS
String call(String text, Map templateMap) {
    return new groovy.text.GStringTemplateEngine().createTemplate(text)
                                                  .make(templateMap)
                                                  .toString()
}