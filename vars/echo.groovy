/*
    Override echo to automatically try and cast things to string
*/

import static groovy.json.JsonOutput.*

void call(def x, boolean prettyPrintMap = false, String text = '') {
    try {
        if (text) text = "${text}\n"
        if (prettyPrintMap) {
            steps.echo "${text}${prettyPrint(toJson(x))}"
        } else {
            steps.echo "${text}${x.toString()}"
        }
    } catch (org.jenkinsci.plugins.workflow.steps.FlowInterruptedException ex) {
        throw ex
    } catch (Exception ex) {
        steps.echo "[echo] Exception caught:\n - x=${x.toString()}\n - prettyPrintMap=${prettyPrintMap}\n - text=${text}\n${ex.toString()}"
    }
}
