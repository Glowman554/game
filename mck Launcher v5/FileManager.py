def save(file, data):
    f = open(file, "w")
    f.write(data)
    f.flush()
    f.close()

def load(file):
    try:
        f = open(file)
        data = f.read()
        f.close()
        return data
    except:
        return