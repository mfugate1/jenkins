FROM alpine:latest

RUN apk add ansible py3-pip gcc python3-dev libc-dev linux-headers openjdk16-jdk openssh openssh-client
RUN ansible-galaxy collection install azure.azcollection
RUN pip install -r ~/.ansible/collections/ansible_collections/azure/azcollection/requirements-azure.txt