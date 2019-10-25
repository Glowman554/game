# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'launcher.ui'
#
# Created by: PyQt5 UI code generator 5.13.1
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets
import FileCreate, os, wget, easygui

versionLink = "https://raw.githubusercontent.com/Glowman554/game/master/version.nv2"
jarLink = "https://raw.githubusercontent.com/Glowman554/game/master/gamev2.jar"


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(475, 298)
        font = QtGui.QFont()
        font.setFamily("Calibri")
        MainWindow.setFont(font)
        MainWindow.setCursor(QtGui.QCursor(QtCore.Qt.CrossCursor))
        MainWindow.setTabShape(QtWidgets.QTabWidget.Rounded)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout.setObjectName("gridLayout")
        self.playButton = QtWidgets.QPushButton(self.centralwidget)
        font = QtGui.QFont()
        font.setFamily("Calibri")
        font.setPointSize(20)
        self.playButton.setFont(font)
        self.playButton.setObjectName("playButton")
        self.gridLayout.addWidget(self.playButton, 1, 2, 1, 1)
        self.line = QtWidgets.QFrame(self.centralwidget)
        self.line.setFrameShape(QtWidgets.QFrame.VLine)
        self.line.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.line.setObjectName("line")
        self.gridLayout.addWidget(self.line, 1, 1, 1, 1)
        self.username = QtWidgets.QTextEdit(self.centralwidget)
        self.username.setMaximumSize(QtCore.QSize(16777215, 50))
        self.username.setObjectName("username")
        self.gridLayout.addWidget(self.username, 1, 0, 1, 1)
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setText("")
        self.label.setObjectName("label")
        self.gridLayout.addWidget(self.label, 0, 0, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.label.setPixmap(QtGui.QPixmap(os.getcwd() + "/picture.png"))

        self.playButton.clicked.connect(self.Play)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "mck Launcher"))
        self.playButton.setText(_translate("MainWindow", "Play"))
        self.username.setPlaceholderText(_translate("MainWindow", "Benutzername"))

    def reporthook(self, count, blockSize, totalSize):
        print(count, blockSize, totalSize)

    def Play(self, Form):
        name = self.username.toPlainText()
        if name == "":
            easygui.msgbox("Bitte nutzernamen eingeben", "Error")
        else:
            try:
                f = open("./version.nv2", "r")
                version = f.read()
                f.close()
            except:
                easygui.msgbox("Nicht erkenbarer Fehler", "Error")
                sys.exit()

            try:
                f = open("./version.cv", "r")
                cur_version = f.read()
                f.close()
            except:
                easygui.msgbox("Nicht erkenbarer Fehler", "Error")
                sys.exit()

            if version == cur_version:
                MainWindow.hide();
                os.system("java -jar game.jar " + name);
                print("done")
                MainWindow.show()
            else:
                print("update")
                try:
                    try:
                        os.remove("./game.jar")
                    except:
                        pass
                    wget.download(jarLink, "./game.jar", self.reporthook)
                    f = open("./version.cv", "w")
                    f2 = open("./version.nv2", "r")
                    f.write(f2.read())
                    f.flush()
                    f.close()
                    f2.close()
                    MainWindow.hide();
                    os.system("java -jar game.jar " + name);
                    print("done")
                    MainWindow.show()
                except:
                    easygui.msgbox("Kein internet oder so", "Error")

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    FileCreate.Create()
    try:
        try:
            os.remove("version.nv2")
        except:
            pass
        wget.download(versionLink, "./version.nv2")
    except:
        easygui.msgbox("Kein Internet", "Error")

    sys.exit(app.exec_())
