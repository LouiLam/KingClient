; �ű��� Inno Setup �ű��� ���ɣ�
; �йش��� Inno Setup �ű��ļ�����ϸ��������İ����ĵ���

#define MyAppName "��������"
#define MyAppVersion "1.0"
#define MyAppPublisher "ltl"
#define MyAppExeName "king.exe"

[Setup]
; ע: AppId��ֵΪ������ʶ��Ӧ�ó���
; ��ҪΪ������װ����ʹ����ͬ��AppIdֵ��
; (�����µ�GUID����� ����|��IDE������GUID��)
AppId={{A8AB0BD8-55CE-4B01-BD49-14751E092568}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
LicenseFile=C:\Documents and Settings\Administrator\����\JAVA_SWT.txt
InfoBeforeFile=C:\Documents and Settings\Administrator\����\JAVA_SWT.txt
InfoAfterFile=C:\Documents and Settings\Administrator\����\JAVA_SWT.txt
OutputDir=D:\KING_OUTPUT
OutputBaseFilename=setup
SetupIconFile=C:\Documents and Settings\Administrator\����\favicon.ico
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
; ע��: ��Ҫ���κι���ϵͳ�ļ���ʹ�á�Flags: ignoreversion��

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

