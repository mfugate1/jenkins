FROM jenkins/jenkins:alpine

USER root

RUN apk add --no-cache rsync docker

RUN addgroup -g 998 docker2
RUN addgroup jenkins docker
RUN addgroup jenkins docker2

USER jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY jenkins.yml /usr/share/jenkins/ref/jenkins.yml
COPY job-dsl-runner.xml /usr/share/jenkins/ref/jobs/Jenkins-Job-DSL-Runner/config.xml

ENV CASC_JENKINS_CONFIG /usr/share/jenkins/ref/jenkins.yml
ENV JAVA_OPTS "-Dorg.apache.commons.jelly.tags.fmt.timeZone=America/New_York -Djenkins.install.runSetupWizard=false -Dhudson.slaves.NodeProvisioner.initialDelay=0 -Dhudson.slaves.NodeProvisioner.MARGIN=50 -Dhudson.slaves.NodeProvisioner.MARGIN0=0.85"