# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'launcher.ui'
#
# Created by: PyQt5 UI code generator 5.13.1
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets
import FileCreate, os, wget, easygui, urllib.request, FileManager

versionLink = "https://raw.githubusercontent.com/Glowman554/game/master/version.nv2"
jarLink = "https://raw.githubusercontent.com/Glowman554/game/master/game.jar"

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(548, 319)
        font = QtGui.QFont()
        font.setFamily("Calibri")
        MainWindow.setFont(font)
        MainWindow.setCursor(QtGui.QCursor(QtCore.Qt.CrossCursor))
        MainWindow.setTabShape(QtWidgets.QTabWidget.Rounded)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout.setObjectName("gridLayout")
        self.line = QtWidgets.QFrame(self.centralwidget)
        self.line.setFrameShape(QtWidgets.QFrame.VLine)
        self.line.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.line.setObjectName("line")
        self.gridLayout.addWidget(self.line, 1, 2, 1, 1)
        self.playButton = QtWidgets.QPushButton(self.centralwidget)
        font = QtGui.QFont()
        font.setFamily("Calibri")
        font.setPointSize(20)
        self.playButton.setFont(font)
        self.playButton.setObjectName("playButton")
        self.gridLayout.addWidget(self.playButton, 1, 3, 1, 1)
        self.username = QtWidgets.QTextEdit(self.centralwidget)
        self.username.setMaximumSize(QtCore.QSize(16777215, 50))
        self.username.setObjectName("username")
        self.gridLayout.addWidget(self.username, 1, 0, 1, 1)
        self.line_2 = QtWidgets.QFrame(self.centralwidget)
        self.line_2.setMinimumSize(QtCore.QSize(0, 0))
        self.line_2.setFrameShape(QtWidgets.QFrame.HLine)
        self.line_2.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.line_2.setObjectName("line_2")
        self.gridLayout.addWidget(self.line_2, 3, 0, 1, 4)
        self.download = QtWidgets.QProgressBar(self.centralwidget)
        self.download.setProperty("value", 0)
        self.download.setInvertedAppearance(False)
        self.download.setObjectName("download")
        self.gridLayout.addWidget(self.download, 4, 0, 1, 4)
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setText("")
        self.label.setObjectName("label")
        self.gridLayout.addWidget(self.label, 0, 0, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.label.setPixmap(QtGui.QPixmap(os.getcwd() + "./picture.png"))

        self.playButton.clicked.connect(self.Play)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "mck Launcher"))
        self.playButton.setText(_translate("MainWindow", "Play"))
        self.username.setPlaceholderText(_translate("MainWindow", "Benutzername"))

    def reporthook(self, count, blockSize, totalSize):
        readsofar = count * blockSize
        percent = readsofar * 1e2 / totalSize
        self.download.setValue(percent)
        app.processEvents()

    def Play(self, Form):
        name = self.username.toPlainText()
        FileManager.save("username.name", name)
        if name == "":
            easygui.msgbox("Bitte nutzernamen eingeben", "Error")
        else:



            try:
                version = FileManager.load("./version.nv2")
            except:
                easygui.msgbox("Nicht erkenbarer Fehler", "Error")
                sys.exit()

            try:
                cur_version = FileManager.load("./version.cv")
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
                    #wget.download(jarLink, "./game.jar", self.reporthook)
                    urllib.request.urlretrieve(jarLink, "./game.jar", self.reporthook)
                    FileManager.save("./version.cv", FileManager.load("./version.nv2"))
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

    ui.username.setText(FileManager.load("username.name"))

    try:
        try:
            os.remove("./version.nv2")
        except:
            pass
        #wget.download(versionLink, "./version.nv2")
        urllib.request.urlretrieve(versionLink, "./version.nv2")
    except:
        easygui.msgbox("Kein Internet", "Error")

    sys.exit(app.exec_())
