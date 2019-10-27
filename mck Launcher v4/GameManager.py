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

def parse(file):
    f = open(file)
    world = f.read()
    f.close()
    world = world.split(",")  # world öfnen

    length = len(world)

    print("Now parsing " + str(length - 1) + " Blocks")

    dirt = 0
    wood = 0
    stone = 0
    leaves = 0
    berry = 0
    glass = 0

    for a in range(0, length):
        cur = world[a]
        if (cur == "1"):
            dirt = dirt + 1
        if (cur == "2"):
            wood = wood + 1
        if (cur == "3"):
            stone = stone + 1
        if (cur == "4"):
            leaves = leaves + 1
        if (cur == "5"):
            berry = berry + 1
        if (cur == "6"):
            glass = glass + 1
        print("Parsing Block nr " + str(a) + " of " + str(length - 1))

    print("found " + str(dirt) + " Dirt Blocks")
    print("found " + str(wood) + " Wood Blocks")
    print("found " + str(stone) + " Stone Blocks")
    print("found " + str(leaves) + " Leaves Blocks")
    print("found " + str(berry) + " Berry Blocks")
    print("found " + str(glass) + " Glass Blocks")

