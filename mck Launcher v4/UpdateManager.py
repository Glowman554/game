import wget, threading, os, easygui
from time import sleep

running = True

def init(versionLink):
    if(os.path.isfile("version.cv")):
        pass
    else:
        f = open("./version.cv", "w")
        f.write("0.0")
        f.flush()
        f.close()
    run = threading.Thread(target=thread, args=(versionLink, ))
    run.start()

def neddUpdate():
    f = open("version.nv")
    version = f.read()
    f.close()
    f = open("version.cv")
    versionc = f.read()
    f.close()
    if version == versionc:
        return False
    else:
        return True
def confirmUpdate():
    f = open("version.nv")
    f2 = open("version.cv", "w")
    f2.write(f.read())
    f2.flush()
    f.close()
    f2.close()

def thread(link):
    while running:
        try:
            try:
                os.remove("version.nv")
            except:
                pass

            wget.download(link, "version.nv")
        except:
            easygui.msgbox("Keine Verbindung zum internet.")
        sleep(5)