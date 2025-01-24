cat <<EOF > ~/Desktop/IntellijLauncher.desktop
[Desktop Entry]
Version=1.0
Type=Application
Name=IntellijLauncher
Comment=
Exec=./idea.sh
Icon=
Path=/snap/intellij-idea-community/current/bin
Terminal=false
StartupNotify=false
EOF

chmod +x ~/Desktop/IntellijLauncher.desktop
