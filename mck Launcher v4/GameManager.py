import os, easygui

def launch(username, world):
    if(world == "null"):
        os.system("java -jar game.jar " + username)
    else:
        if(os.path.isfile(world)):
            os.system("java -jar game.jar " + username + " " + world)
        else:
            easygui.msgbox("Welt nicht vorhanden generiere Welt")
            os.system("java -jar game.jar " + username)