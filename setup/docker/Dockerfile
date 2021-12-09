ARG JENKINS_TAG=latest

FROM jenkins/jenkins:${JENKINS_TAG}

RUN addgroup --gid 998 docker
RUN usermod -a -G docker jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY jenkins.yml /usr/share/jenkins/ref/jenkins.yml
COPY job-dsl-runner.xml /usr/share/jenkins/ref/jobs/Job-DSL-Runner/config.xml

ENV CASC_JENKINS_CONFIG /usr/share/jenkins/ref/jenkins.yml
ENV JAVA_OPTS "-Dorg.apache.commons.jelly.tags.fmt.timeZone=America/New_York -Djenkins.install.runSetupWizard=false -Dhudson.slaves.NodeProvisioner.initialDelay=0 -Dhudson.slaves.NodeProvisioner.MARGIN=50 -Dhudson.slaves.NodeProvisioner.MARGIN0=0.85"