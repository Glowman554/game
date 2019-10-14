import os

def Create():
    if os.path.isfile("./version.cv"):
        print("No ned to create file version.cv")
    else:
        print("Ned to create file version.cv")
        f = open("./version.cv", "w")
        f.write("0.0 | 0")
        f.flush()
        f.close()