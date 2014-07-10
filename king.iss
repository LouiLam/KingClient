; 脚本由 Inno Setup 脚本向导 生成！
; 有关创建 Inno Setup 脚本文件的详细资料请查阅帮助文档！

#define MyAppName "我是王者"
#define MyAppVersion "1.0"
#define MyAppPublisher "ltl"
#define MyAppExeName "king.exe"

[Setup]
; 注: AppId的值为单独标识该应用程序。
; 不要为其他安装程序使用相同的AppId值。
; (生成新的GUID，点击 工具|在IDE中生成GUID。)
AppId={{A8AB0BD8-55CE-4B01-BD49-14751E092568}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
LicenseFile=C:\Documents and Settings\Administrator\桌面\JAVA_SWT.txt
InfoBeforeFile=C:\Documents and Settings\Administrator\桌面\JAVA_SWT.txt
InfoAfterFile=C:\Documents and Settings\Administrator\桌面\JAVA_SWT.txt
OutputDir=D:\KING_OUTPUT
OutputBaseFilename=setup
SetupIconFile=C:\Documents and Settings\Administrator\桌面\favicon.ico
Compression=lzma
SolidCompression=yes

[Languages]
Name: "chinesesimp"; MessagesFile: "compiler:Default.isl"
Name: "english"; MessagesFile: "compiler:Languages\English.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: checkablealone

[Icons]
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\king.exe"; Tasks: desktopicon

[Files]
Source: "D:\King\king.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\King\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; 注意: 不要在任何共享系统文件上使用“Flags: ignoreversion”

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

