cat <<EOF > ~/Desktop/MyLauncher.desktop
[Desktop Entry]
Version=1.0
Type=Application
Name=My Launcher
Comment=Launch my application
Exec=/path/to/your/application
Icon=/path/to/icon.png
Terminal=false
Categories=Utility;
EOF

chmod +x ~/Desktop/MyLauncher.desktop
