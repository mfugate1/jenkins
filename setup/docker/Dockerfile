FROM jenkins/jenkins:latest

USER root

RUN apt update && apt upgrade -y && apt install wget

ENV UBUNTU_CODENAME=jammy
RUN wget -O- "https://keyserver.ubuntu.com/pks/lookup?fingerprint=on&op=get&search=0x6125E2A8C77F2818FB7BD15B93C4A3FD7BB9C367" | gpg --dearmour -o /usr/share/keyrings/ansible-archive-keyring.gpg
RUN echo "deb [signed-by=/usr/share/keyrings/ansible-archive-keyring.gpg] http://ppa.launchpad.net/ansible/ansible/ubuntu $UBUNTU_CODENAME main" | tee /etc/apt/sources.list.d/ansible.list
RUN apt update && apt install -y ansible

USER jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY jenkins.yml /usr/share/jenkins/ref/jenkins.yml
COPY job-dsl-runner.xml /usr/share/jenkins/ref/jobs/Jenkins-Job-DSL-Runner/config.xml

ENV CASC_JENKINS_CONFIG /usr/share/jenkins/ref/jenkins.yml
ENV JAVA_OPTS "-Dorg.apache.commons.jelly.tags.fmt.timeZone=America/New_York -Djenkins.install.runSetupWizard=false -Dhudson.slaves.NodeProvisioner.initialDelay=0 -Dhudson.slaves.NodeProvisioner.MARGIN=50 -Dhudson.slaves.NodeProvisioner.MARGIN0=0.85"
