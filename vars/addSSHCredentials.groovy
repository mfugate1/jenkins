import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.domains.Domain

void call(String credentialsIdForKey, String id, String username, String description) {
    withCredentials([string(credentialsId: credentialsIdForKey, variable: 'key')]) {
        def source = new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(key.replace("\\n", "\n"))
        def ck1 = new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL, id, "jenkins", source, "", description)

        SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), ck1)
    }
}