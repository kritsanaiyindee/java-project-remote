set w = CreateObject("Wscript.Shell")
WScript.sleep 2000
w.SendKeys WScript.Arguments(0)
w.SendKeys "MESSAGE{ENTER}"