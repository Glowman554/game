#import  sys
from shutil import copyfile
#from cx_Freeze import setup, Executable

#build_exe_options = {"packages": ["os", "wget", "urllib.request", "FileCreate", "easygui", "FileManager"]}

#base = None
#if sys.platform == "win32":
#    base = "Win32GUI"

#setup(  name = "mck Launcher",
#        version = "1.5",
#        description = "mck launcher v5",
#        options = {"build_exe": build_exe_options},
#        executables = [Executable("main.py", base=base)])



from cx_Freeze import setup, Executable

target = Executable(
    script="main.py",
    base="Win32GUI",
    icon="icon.ico"
    )

options = {"packages": ["os", "wget", "urllib.request", "FileCreate", "easygui", "FileManager"]}

setup(
    name="mck Launcher",
    version="1.5",
    description="",
    author="glowman434",
    options={"build_exe": options},
    executables=[target]
    )






copyfile("./picture.png", "./build/exe.win-amd64-3.7/picture.png")
copyfile("./java-setup.exe", "./build/exe.win-amd64-3.7/java-setup.exe")