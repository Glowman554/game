# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'launcher.ui'
#
# Created by: PyQt5 UI code generator 5.13.1
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets
import os, urllib.request, UpdateManager, easygui, GameManager, FileManager

versionLink = "https://raw.githubusercontent.com/Glowman554/game/master/version.nv2"
jarLink = "https://raw.githubusercontent.com/Glowman554/game/master/game.jar"

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(558, 335)
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
        self.line_2 = QtWidgets.QFrame(self.centralwidget)
        self.line_2.setMinimumSize(QtCore.QSize(0, 0))
        self.line_2.setFrameShape(QtWidgets.QFrame.HLine)
        self.line_2.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.line_2.setObjectName("line_2")
        self.gridLayout.addWidget(self.line_2, 3, 0, 1, 4)
        self.downloadBar = QtWidgets.QProgressBar(self.centralwidget)
        self.downloadBar.setProperty("value", 0)
        self.downloadBar.setInvertedAppearance(False)
        self.downloadBar.setObjectName("downloadBar")
        self.gridLayout.addWidget(self.downloadBar, 4, 0, 1, 4)
        self.loadWorld = QtWidgets.QCheckBox(self.centralwidget)
        self.loadWorld.setObjectName("loadWorld")
        self.gridLayout.addWidget(self.loadWorld, 2, 0, 1, 1)
        self.usernameEdit = QtWidgets.QTextEdit(self.centralwidget)
        self.usernameEdit.setMaximumSize(QtCore.QSize(16777215, 50))
        self.usernameEdit.setObjectName("usernameEdit")
        self.gridLayout.addWidget(self.usernameEdit, 1, 0, 1, 1)
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setMinimumSize(QtCore.QSize(0, 0))
        self.label.setText("")
        self.label.setObjectName("label")
        self.gridLayout.addWidget(self.label, 0, 0, 1, 3)
        MainWindow.setCentralWidget(self.centralwidget)
        self.label.setPixmap(QtGui.QPixmap(os.getcwd() + "./picture.png"))

        self.playButton.clicked.connect(self.Play)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "mck Launcher"))
        self.playButton.setText(_translate("MainWindow", "Play"))
        self.loadWorld.setText(_translate("MainWindow", "Load World"))
        self.usernameEdit.setPlaceholderText(_translate("MainWindow", "Benutzername"))

    def reporthook(self, count, blockSize, totalSize):
        readsofar = count * blockSize
        percent = readsofar * 1e2 / totalSize
        self.downloadBar.setValue(percent)
        app.processEvents()

    def Play(self):
        name = self.usernameEdit.toPlainText()
        FileManager.save("username.name", name)
        if(name == ""):
            easygui.msgbox("Bitte nutzernamen eingeben", "Error")
        else:
            if(UpdateManager.neddUpdate()):
                try:
                    urllib.request.urlretrieve(jarLink, "game.jar", self.reporthook)
                    UpdateManager.confirmUpdate()
                except:
                    easygui.msgbox("Keine Verbindung zum internet.")
        if(self.loadWorld.isChecked()):
            world = "default.msave"
        else:
            world = "null"

        MainWindow.hide();
        UpdateManager.running = False
        GameManager.launch(name, world)
        UpdateManager.running = True
        UpdateManager.init(versionLink)
        MainWindow.show();



def run():
    app.exec_()
    UpdateManager.running = False


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    ui.usernameEdit.setText(FileManager.load("username.name"))
    UpdateManager.init(versionLink)
    sys.exit(run())
