#!/bin/bash
 
# Step 1: Create a new user 'uni' and add to sudo group
echo "Creating user 'uni' and adding to the sudo group..."
sudo adduser uni --gecos "" --disabled-password
echo "uni:killingstar1994" | sudo chpasswd
sudo usermod -aG sudo uni
 
# Step 2: Configure Display Manager & Desktop Environment
echo "Configuring Display Manager & Desktop Environment..."
 
# Set up tty1
exec /sbin/getty -8 38400 tty1 &
 
# Update and upgrade the system
sudo apt update -y && sudo apt upgrade -y
 
# Install XFCE4 and necessary packages
sudo apt install -y xfce4 xfce4-goodies dbus-x11 firefox-esr
 
# Install LightDM as the display manager
sudo apt install -y lightdm
 
# Reconfigure LightDM
sudo dpkg-reconfigure lightdm
 
# Step 3: Install Java JDK 1.8
echo "Installing Java JDK 1.8..."
wget https://corretto.aws/downloads/latest/amazon-corretto-8-x64-linux-jdk.tar.gz
sudo mkdir -p /usr/lib/jvm
sudo tar zxvf amazon-corretto-8-x64-linux-jdk.tar.gz -C /usr/lib/jvm
sudo update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/jdk1.8.0*/bin/java" 1
sudo update-alternatives --set java /usr/lib/jvm/jdk1.8.0*/bin/java
java -version
 
# Step 4: Install IntelliJ IDEA Community Edition
echo "Installing IntelliJ IDEA Community Edition..."
sudo apt install snapd -y
sudo snap install snapd
sudo snap install intellij-idea-community --classic
 
# Step 5: Install GitHub CLI
echo "Installing GitHub CLI..."
(type -p wget >/dev/null || (sudo apt update && sudo apt-get install wget -y)) \
&& sudo mkdir -p -m 755 /etc/apt/keyrings \
&& out=$(mktemp) && wget -nv -O$out https://cli.github.com/packages/githubcli-archive-keyring.gpg \
&& cat $out | sudo tee /etc/apt/keyrings/githubcli-archive-keyring.gpg > /dev/null \
&& sudo chmod go+r /etc/apt/keyrings/githubcli-archive-keyring.gpg \
&& echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | sudo tee /etc/apt/sources.list.d/github-cli.list > /dev/null \
&& sudo apt update \
&& sudo apt install gh -y
 
echo "Installation completed."
 
# Start LightDM
sudo lightdm &
