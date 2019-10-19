import os, threading

def compile(file):
    run = threading.Thread(target=thread, args=(file,))
    run.start()

def thread(file):
    os.system("pyinstaller " + file)