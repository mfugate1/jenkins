#!/usr/bin/env bash

if ! which docker &>/dev/null; then
    echo "Installing docker"
    sudo apt-get update
    sudo apt-get install -y \
        ca-certificates \
        curl \
        gnupg \
        lsb-release

    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

    echo \
    "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

    sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io

    sudo usermod -aG docker $(whoami)
fi

if ! which docker-compose &>/dev/null; then

fi

if ! id -u jenkins &>/dev/null; then
    echo "Creating jenkins user"

fi