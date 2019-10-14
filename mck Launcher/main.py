# -*- coding: utf-8 -*-

from PyQt5 import QtCore, QtGui, QtWidgets
import os, easygui, urllib.request, FileCreate, wget

versionLink = "https://raw.githubusercontent.com/Glowman554/game/master/version.nv"
jarLink = "https://raw.githubusercontent.com/Glowman554/game/master/game.jar"

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("mck Launcher")
        MainWindow.resize(342, 84)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout.setObjectName("gridLayout")
        self.progressBar = QtWidgets.QProgressBar(self.centralwidget)
        self.progressBar.setProperty("value", 0)
        self.progressBar.setObjectName("progressBar")
        self.gridLayout.addWidget(self.progressBar, 1, 1, 1, 1)
        self.playButton = QtWidgets.QPushButton(self.centralwidget)
        self.playButton.setObjectName("playButton")
        self.gridLayout.addWidget(self.playButton, 1, 0, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)

        self.playButton.clicked.connect(self.Play)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)



    def Play(self, Form):
        if os.path.isfile("./game.jar"):
            pass
        else:
            easygui.msgbox("Bitte Lade game.jar herunter", "Error")

        f = open("./version.nv", "r")
        version = f.read()
        f.close()
        version = version.split("|")
        f = open("./version.cv", "r")
        versionc = f.read()
        versionc = versionc.split("|")
        f.close()

        if version[0] == versionc[0]:
            MainWindow.hide();
            os.system("java -jar game.jar");
            print("done")
            MainWindow.show()
        else:
            print("update")
            try:
                try:
                    os.remove("./game.jar")
                except:
                    pass
                wget.download(jarLink, "./game.jar")
                f = open("./version.cv", "w")
                f2 = open("./version.nv", "r")
                f.write(f2.read())
                f.flush()
                f.close()
                f2.close()
                MainWindow.hide();
                os.system("java -jar game.jar");
                print("done")
                MainWindow.show()
            except:
                easygui.msgbox("Kein internet oder so", "Error")

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.playButton.setText(_translate("MainWindow", "Play"))

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    ui.progressBar.hide()
    FileCreate.Create()

    #versionList Download
    try:
        urllib.request.urlretrieve(versionLink, "./version.nv")
        f = open("./version.nv", "r")
        version = f.read()
        f.close()
    except:
        easygui.msgbox("Kein internet oder sio", "Error")

    sys.exit(app.exec_())
