import sys

print("\n**** LEXICAL/SYNTAX ANALYSER ****")
print("Type your min document (eg: px1.min)")

filename = input()
myfile = open(filename)
temp = myfile.read(1)
line = 0
if (temp == '\n'):
    line = line + 1
token = ""
tk = ""

global currentLine
global numberOfQuads
currentLine = 0
numberOfQuads = 0

global listOfLabels
listOfLabels = {}
global temporaryValue
temporaryValue = 0
global name
name = ''
global t
global ForPname
global Pname
global number
global T
global E
global F
global callName
global Bquad
global currentName
currentName = ""
global relop
global RT
global RF
global QT
global QF
global BT
global BF
global varList
varList = list()


### lex() function for the lexical analysis

def lex():
    global temp
    phrase = ""
    global line
    global token
    global name
    global number
    while (temp == "\n" or temp == ' ' or temp == '\t'):  ## Pass the tabs, spaces and new line characters
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    if (temp == "/"):  ## Check for comments
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1

        if (temp == "*"):
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1
            while (temp != "*"):
                temp = myfile.read(1)
                if (temp == "\n"):
                    line = line + 1
                if (temp == "/"):
                    temp = myfile.read(1)
                    if (temp == "\n"):
                        line = line + 1
                    if (temp == "*") or temp == "/":
                        print("ERROR: Comment inside a comment")
                        sys.exit()
                if not temp:
                    print("ERROR: EOF reached :  '*/' expected")
                    sys.exit()

            if (temp == "*"):
                temp = myfile.read(1)
                if (temp == "\n"):
                    line = line + 1
                if (temp == "/"):
                    temp = myfile.read(1)
                    if (temp == "\n"):
                        line = line + 1
                else:
                    print("ERROR:'/' expected")
                    sys.exit()
        elif (temp == "/"):
            myfile.readline()
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1

        else:
            token = "divtk"
            return token

    while (temp == '\n' or temp == ' ' or temp == '\t'):

        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    if (temp.isalpha()):  ## State 1: Check for alphabetical symbol
        phrase = phrase + temp
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
        count = 2
        while (temp.isalpha() or temp.isdigit()):
            if (count <= 30):
                phrase = phrase + temp
                temp = myfile.read(1)
                if (temp == "\n"):
                    line = line + 1
                count += 1
            else:
                while (temp.isalpha() or temp.isdigit()):
                    temp = myfile.read(1)
                    if (temp == "\n"):
                        line = line + 1
                print("WARNING: length of variable greater than 30 characters")

        name = phrase

        if (phrase == "program"):  # Check for keywords
            token = "programtk"

        elif (phrase == "declare"):
            token = "declaretk"

        elif (phrase == "if"):
            token = "iftk"

        elif (phrase == "else"):
            token = "elsetk"

        elif (phrase == "then"):
            token = "thentk"

        elif (phrase == "while"):
            token = "whiletk"

        elif (phrase == "doublewhile"):
            token = "doublewhiletk"

        elif (phrase == "loop"):
            token = "looptk"

        elif (phrase == "exit"):
            token = "exittk"

        elif (phrase == "forcase"):
            token = "forcasetk"

        elif (phrase == "incase"):
            token = "incasetk"

        elif (phrase == "when"):
            token = "whentk"

        elif (phrase == "default"):
            token = "defaulttk"

        elif (phrase == "not"):
            token = "nottk"

        elif (phrase == "and"):
            token = "andtk"

        elif (phrase == "or"):
            token = "ortk"

        elif (phrase == "function"):
            token = "functiontk"

        elif (phrase == "procedure"):
            token = "proceduretk"

        elif (phrase == "call"):
            token = "calltk"

        elif (phrase == "return"):
            token = "returntk"

        elif (phrase == "in"):
            token = "intk"

        elif (phrase == "inout"):
            token = "inouttk"

        elif (phrase == "input"):
            token = "inputtk"

        elif (phrase == "print"):
            token = "printtk"

        else:
            token = "idtk"  ## If not keyword, then id of variable

    elif (temp.isdigit()):  ## Check for digit
        phrase = phrase + temp
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
        while (temp.isdigit()):
            phrase = phrase + temp
            if (-32767 <= int(phrase) <= 32767):
                temp = myfile.read(1)
                if (temp == "\n"):
                    line = line + 1
            elif (int(phrase) > 32767):
                print("ERROR: number out of bounds(number bigger than 32767)" + phrase)
                sys.exit()
            elif (int(phrase) < -32767):
                print("ERROR: number out of bounds(number smaller than -32767)")
                sys.exit()
        if (temp.isalpha()):
            print("ERROR:letter after number")
            sys.exit()
        number = phrase
        token = "constanttk"
    elif (temp == "+"):  ## Check for the remaining symbols of the language
        token = "plustk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "-"):
        token = "minustk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "*"):
        token = "multk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "="):
        token = "equaltk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "<"):
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
        if (temp == "="):
            token = "lessequaltk"
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1
        elif (temp == ">"):
            token = "morelesstk"
            temp = myfile.read(1)
        else:
            token = "lesstk"
            if (temp == "\n"):
                line = line + 1
    elif (temp == ">"):
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
        if (temp == "="):
            token = "moreequaltk"
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1
        else:
            token = "moretk"
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1
    elif (temp == ":"):
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
        if (temp == "="):
            token = "assignmenttk"
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1
        else:
            token = "colontk"
            temp = myfile.read(1)
            if (temp == "\n"):
                line = line + 1
    elif (temp == ","):
        token = "commatk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == ";"):
        token = "semicolontk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "("):
        token = "rightroundbrackettk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == ")"):
        token = "leftroundbrackettk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "{"):
        token = "rightbrackettk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "}"):
        token = "leftbrackettk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "["):
        token = "rightsqbrackettk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif (temp == "]"):
        token = "leftsqbrackettk"
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    elif not temp:
        print("WARNING : EOF ")  ## End of file reached
        token = "eoftk"
    else:  ## Not known character
        print("ERROR : invalid character: " + temp + " : line : " + str(line))
        sys.exit()
        temp = myfile.read(1)
        if (temp == "\n"):
            line = line + 1
    return token


#### Endiamesos kodikas !!!!!!!!!!!


def nextquad():
    global numberOfQuads
    s = numberOfQuads + 1
    return s


def genQuad(op, x, y, z):
    global currentLine
    global numberOfQuads
    global t
    numberOfQuads = numberOfQuads + 1
    dict = {"op": "", "x": "", "y": "", "z": ""}
    # _,_,_,_

    t = nextquad()

    dict["op"] = op
    dict["x"] = x
    dict["y"] = y
    dict["z"] = z

    listOfLabels[numberOfQuads] = dict


def newtemp():
    global temporaryValue
    temporaryValue = temporaryValue + 1
    varList.append("T_" + str(temporaryValue))
    return "T_" + str(temporaryValue)


def emptylist():
    emptyList = list()
    return emptyList


def makelist(x):
    newList = [x]
    return newList


def merge(list_1, list_2):
    mergeList = list_1 + list_2
    return mergeList


def backpatch(list1, z):
    for element in list1:
        listOfLabels[element]["z"] = z


### FUNCTIONS FOR SYNTAX ANALYSIS


def program():
    global tk
    global name
    global Pname
    global currentName

    if (tk == "programtk"):
        tk = lex()
        if (tk == "idtk"):
            Pname = name               # exw to onoma tou programmatos
            currentName = Pname
            tk = lex()

            if tk == "rightbrackettk":
                tk = lex()
                block()
                genQuad("halt", "_", "_", "_")

                if (tk == "leftbrackettk"):
                    genQuad("end_block", Pname, "_", "_")


                    file_out = open("out.int", "+w")

                    for e in listOfLabels:
                        file_out.write(str(e) + ":" + " ")
                        file_out.write(str(listOfLabels[e]["op"]) + ",")
                        file_out.write(str(listOfLabels[e]["x"]) + ",")
                        file_out.write(str(listOfLabels[e]["y"]) + ",")
                        file_out.write(str(listOfLabels[e]["z"]))
                        file_out.write("\n")

                    file_out = open("out.c", "+w")
                    file_out.write("int main()" + "\n" + "{" + "\n")
                    file_out.write("int ")

                    for i in range(len(varList)):
                        if i == len(varList) - 1:
                            file_out.write(varList[i] + ";" + "\n\n")
                        else:
                            file_out.write(varList[i] + ",")

                    for e in listOfLabels:
                        if (str(listOfLabels[e]["op"]) == ":="):
                            file_out.write("L_" + str(e) + ": " + str(listOfLabels[e]["z"]) + "=" + str(
                                listOfLabels[e]["x"]) + ";" + " // " + str(listOfLabels[e]["op"]) + "," + str(
                                listOfLabels[e]["x"]) + "," + str(listOfLabels[e]["y"]) + "," + str(
                                listOfLabels[e]["z"]) + "\n")
                        elif (str(listOfLabels[e]["op"]) == "*" or str(listOfLabels[e]["op"]) == "/" or str(
                                listOfLabels[e]["op"]) == "+" or str(listOfLabels[e]["op"]) == "-"):
                            file_out.write("L_" + str(e) + ": " + str(listOfLabels[e]["z"]) + "=" + str(
                                listOfLabels[e]["x"]) + str(listOfLabels[e]["op"]) + str(
                                listOfLabels[e]["y"]) + ";" + " // " + str(listOfLabels[e]["op"]) + "," + str(
                                listOfLabels[e]["x"]) + "," + str(listOfLabels[e]["y"]) + "," + str(
                                listOfLabels[e]["z"]) + "\n")
                        elif (str(listOfLabels[e]["op"]) == ">" or str(listOfLabels[e]["op"]) == "<" or str(
                                listOfLabels[e]["op"]) == ">=" or str(listOfLabels[e]["op"]) == "<=" or str(
                                listOfLabels[e]["op"]) == "<>" or str(listOfLabels[e]["op"]) == "="):
                            file_out.write("L_" + str(e) + ": " + "if (" + str(listOfLabels[e]["x"]) + str(
                                listOfLabels[e]["op"]) + str(listOfLabels[e]["y"]) + ") goto " + "L_" + str(
                                listOfLabels[e]["z"]) + " // " + str(listOfLabels[e]["op"]) + "," + str(
                                listOfLabels[e]["x"]) + "," + str(listOfLabels[e]["y"]) + "," + str(
                                listOfLabels[e]["z"]) + "\n")
                        elif (str(listOfLabels[e]["op"]) == "jump"):
                            file_out.write("L_" + str(e) + ": goto L_" + str(listOfLabels[e]["z"]) + " // " + str(
                                listOfLabels[e]["op"]) + "," + str(listOfLabels[e]["x"]) + "," + str(
                                listOfLabels[e]["y"]) + "," + str(listOfLabels[e]["z"]) + "\n")

                        elif (str(listOfLabels[e]["op"]) == "halt"):
                            file_out.write(
                                "L_" + str(e) + ": " + "{}" + " // " + str(listOfLabels[e]["op"]) + "," + str(
                                    listOfLabels[e]["x"]) + "," + str(listOfLabels[e]["y"]) + "," + str(
                                    listOfLabels[e]["z"]) + "\n")
                            file_out.write("}" + "\n")

                    print("*** LEXICAL/SYNTAX ANALYSIS COMPLETED SUCCCESSFULLY ***")
                else:
                    print("ERROR : left bracket expected after program ends  " + ": line: " + str(line))
            else:
                print("ERROR : right bracket expected after program name  " + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : program name expected" + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : keyword 'program' expected" + ": line: " + str(line))
        sys.exit()


def block():
    global currentName
    global Pname

    declarations()
    subprograms()

    if (currentName == Pname):
        genQuad("begin_block", Pname, "_", "_")
    statements()


def declarations():
    global tk
    global name

    while (tk == "declaretk"):

        tk = lex()
        varList.append(name)
        varlist()
        if (tk == "semicolontk"):
            tk = lex()
        else:
            print("ERROR : semicolon expected " + ": line: " + str(line))
            sys.exit()


def varlist():
    global tk
    if (tk == "idtk"):
        tk = lex()

        while (tk == "commatk"):
            tk = lex()
            varList.append(name)
            if (tk == "idtk"):
                tk = lex()
            else:
                print("ERROR : name of variable expected after ',' " + ": line: " + str(line))
                sys.exit()


def subprograms():
    global tk
    while (tk == "proceduretk" or tk == "functiontk"):
        subprogram()


def subprogram():
    global tk
    global ForPname
    global currentName

    if (tk == "proceduretk"):
        tk = lex()
        if (tk == "idtk"):
            ForPname = name
            currentName = name
            genQuad("begin_block", ForPname, "_", "_")
            tk = lex()
            funcbody()
            genQuad("end_block", ForPname, "_", "_")
        else:
            print("ERROR : name of procedure expected" + ": line: " + str(line))
            sys.exit()
    elif (tk == "functiontk"):
        tk = lex()
        if (tk == "idtk"):
            ForPname = name
            currentName = name
            genQuad("begin_block", ForPname, "_", "_")
            tk = lex()

            funcbody()
            genQuad("end_block", ForPname, "_", "_")

        else:
            print("ERROR : name of function expected" + ": line: " + str(line))
            sys.exit()
    currentName = Pname


def funcbody():
    global tk
    formalpars()
    if tk == "rightbrackettk":
        tk = lex()
        block()
        if tk == "leftbrackettk":
            tk = lex()
        else:
            print("ERROR : '}' expected " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : '{' expected " + ": line: " + str(line))
        sys.exit()


def formalpars():
    global tk
    if (tk == "rightroundbrackettk"):
        tk = lex()
        formalparlist()
        if (tk == "leftroundbrackettk"):
            tk = lex()
        else:
            print("ERROR : ')' expected after formal parameters of procedure or function " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : '(' expected before formal parameters of procedure or function " + ": line: " + str(line))
        sys.exit()


def formalparlist():
    formalparitem()
    global tk
    while (tk == "commatk"):
        tk = lex()
        formalparitem()


def formalparitem():
    global tk
    if (tk == "intk" or tk == "inouttk"):
        tk = lex()
        if (tk == "idtk"):
            tk = lex()

        else:
            print("ERROR: name of formal parameter expected " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR: 'in' or 'inout' keyword expected before the name of formal parameter " + ": line: " + str(line))
        sys.exit()


def statements():
    global tk

    if tk == "rightbrackettk":
        tk = lex()
        statement()

        while (tk == "semicolontk"):
            tk = lex()
            statement()
        if tk == "leftbrackettk":
            tk = lex()

        else:
            print("ERROR: '}' expected" + ": line: " + str(line))
            sys.exit()
    else:
        statement()


def statement():
    if (tk == "idtk"):
        assignmentstat()
    elif (tk == "iftk"):
        ifstat()
    elif (tk == "whiletk"):
        whilestat()
    elif tk == "doublewhiletk":
        doublewhilestat()
    elif (tk == "looptk"):
        loopstat()
    elif (tk == "exittk"):
        exitstat()
    elif (tk == "forcasetk"):
        forcasestat()
    elif (tk == "incasetk"):
        incasestat()
    elif (tk == "calltk"):
        callstat()
    elif (tk == "returntk"):
        returnstat()
    elif (tk == "inputtk"):
        inputstat()
    elif (tk == "printtk"):
        printstat()


def assignmentstat():
    global tk
    global E
    if (tk == "idtk"):
        varName = name
        tk = lex()
        if (tk == "assignmenttk"):
            tk = lex()
            expression()
            genQuad(":=", E, "_", varName)
            if (tk == "semicolontk"):
                tk = lex()
                statement()
        else:
            print("ERROR : ':=' expected" + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : name of variable expected before assignment token " + ": line: " + str(line))
        sys.exit()


def ifstat():
    global tk
    global BT

    if (tk == "iftk"):
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            condition()  # B
            if tk == "leftroundbrackettk":
                tk = lex()
                if tk == "thentk":
                    backpatch(BT, nextquad())
                    tk = lex()
                    statements()
                    ifList = makelist(nextquad())
                    genQuad("jump", "_", "_", "_")
                    backpatch(BF, nextquad())
                    elsepart()
                    backpatch(ifList, nextquad())
                else:
                    print("ERROR : keyword 'then' expected " + ": line: " + str(line))
                    sys.exit()
            else:
                print("ERROR : ')' expected after condition " + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : '(' expected after if " + ": line: " + str(line))
            sys.exit()


def elsepart():
    global tk
    if (tk == "elsetk"):
        tk = lex()
        statements()


def loopstat():
    global tk
    if (tk == "looptk"):
        tk = lex()
        statements()


def exitstat():
    global tk
    if (tk == "exittk"):
        tk = lex()
    else:
        print("ERROR : keyword 'exit' or ';' expected " + ": line: " + str(line))
        sys.exit()


def whilestat():
    global tk
    global Bquad
    global BT
    global BF

    if (tk == "whiletk"):
        Bquad = nextquad()
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            condition()
            backpatch(BT, nextquad())
            if tk == "leftroundbrackettk":
                tk = lex()
                statements()
                genQuad("jump", "_", "_", Bquad)
                backpatch(BF, nextquad())
            else:
                print("ERROR : ')' expected after while condition " + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : '(' expected afte 'while' " + ": line: " + str(line))
            sys.exit()


def doublewhilestat():
    global tk
    tk = lex()
    if tk == "rightroundbrackettk":
        tk = lex()
        condition()
        if tk == "leftroundbrackettk":
            tk = lex()
            statements()
            if tk == "elsetk":
                tk = lex()
                statements()
            else:
                print("ERROR : keyword 'else' expected " + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : ')' expected after while condition " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : '(' expected afte 'while' " + ": line: " + str(line))
        sys.exit()


def incasestat():
    global tk
    if (tk == "incasetk"):
        tk = lex()
        if (tk == "whentk"):
            while (tk == "whentk"):
                tk = lex()
                if tk == "rightroundbrackettk":
                    tk = lex()
                    condition()
                    if tk == "leftroundbrackettk":
                        tk = lex()
                        if (tk == "colontk"):
                            tk = lex()
                            statements()
                        else:
                            print("ERROR : ':' expected after condition " + ": line: " + str(line))
                            sys.exit()
                    else:
                        print("ERROR : ')' expected" + ": line: " + str(line))
                        sys.exit()
                else:
                    print("ERROR : '(' expected" + ": line: " + str(line))
                    sys.exit()
    else:
        print("ERROR : keyword 'incase' expected" + ": line: " + str(line))
        sys.exit()


def forcasestat():
    global tk
    global BT

    if (tk == "forcasetk"):
        first_quad = nextquad()
        tk = lex()
        if (tk == "whentk"):
            while (tk == "whentk"):
                tk = lex()
                if tk == "rightroundbrackettk":
                    tk = lex()
                    condition()
                    if tk == "leftroundbrackettk":
                        tk = lex()
                        if (tk == "colontk"):
                            backpatch(BT, nextquad())
                            tk = lex()
                            statements()
                            genQuad("jump", "_", "_", first_quad)
                            backpatch(BF, nextquad())
                        else:
                            print("ERROR : ':' expected after condition " + ": line: " + str(line))
                            sys.exit()
                    else:
                        print("ERROR : ')' expected" + ": line: " + str(line))
                        sys.exit()
                else:
                    print("ERROR : '(' expected" + ": line: " + str(line))
                    sys.exit()
        if tk == "defaulttk":
            tk = lex()
            if tk == "colontk":
                tk = lex()
                statements()
            else:
                print("ERROR : ':' expected" + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : 'default' expected" + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : keyword 'forcase' expected" + ": line: " + str(line))
        sys.exit()


def callstat():
    global tk
    global callName

    if (tk == "calltk"):
        tk = lex()
        if (tk == "idtk"):
            callName = name
            tk = lex()
            actualpars()
            genQuad("call", callName, "_", "_")
        else:
            print("ERROR : name of procedure expected " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : keyword 'call' expected" + ": line: " + str(line))
        sys.exit()


def returnstat():
    global tk
    global E
    if (tk == "returntk"):
        tk = lex()
        expression()
        genQuad("retv", E, "_", "_")
    else:
        print("ERROR : keyword 'return' or ';' expected" + ": line: " + str(line))
        sys.exit()


def printstat():
    global tk
    global E
    if (tk == "printtk"):
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            expression()
            genQuad("out", E, "_", "_")
            if tk == "leftroundbrackettk":
                tk = lex()
            else:
                print("ERROR :  ')' expected" + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR :  '(' expected" + ": line: " + str(line))
            sys.exit()


def inputstat():
    global tk
    if (tk == "inputtk"):
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            if (tk == "idtk"):
                genQuad("inp", name, "_", "_")
                tk = lex()
                if tk == "leftroundbrackettk":
                    tk = lex()
                else:
                    print("ERROR : '(' expected" + ": line: " + str(line))
                    sys.exit()
            else:
                print("ERROR : id expected" + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : '(' expected" + ": line: " + str(line))
            sys.exit()


def actualpars():
    global tk
    if (tk == "rightroundbrackettk"):
        tk = lex()
        actualparlist()
        if (tk == "leftroundbrackettk"):
            tk = lex()
        else:
            print("ERROR: ')' expected after actual parameters" + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR: '(' expected before actual parameters " + ": line: " + str(line))
        sys.exit()


def actualparlist():
    global tk
    actualparitem()
    while (tk == "commatk"):
        tk = lex()
        actualparitem()


def actualparitem():
    global tk
    if (tk == "intk"):

        tk = lex()
        expression()  # to E einai auto pou tha ferei h expression
        genQuad("par", E, "CV", "_")
    elif (tk == "inouttk"):
        tk = lex()
        if (tk == "idtk"):
            genQuad("par", name, "REF", "_")
            tk = lex()
        else:
            print("ERROR: name of actual parameter expected " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR: 'in' or 'inout' keyword expected " + ": line: " + str(line))
        sys.exit()


def condition():
    global tk
    global BT
    global BF
    global QT
    global QF

    boolterm()  # Q1

    BT = QT
    BF = QF
    while (tk == "ortk"):
        backpatch(BF, nextquad())
        tk = lex()
        boolterm()  # Q2
        QT2 = QT
        QF2 = QF
        BT = merge(BT, QT2)
        BF = QF2


def boolterm():
    global tk
    global RT
    global RF
    global QT
    global QF

    boolfactor()  # R1
    QT = RT
    QF = RF

    while (tk == "andtk"):
        backpatch(QT, nextquad())
        tk = lex()

        boolfactor()  # R2
        RT2 = RT
        RF2 = RF
        QF = merge(QF, RF2)
        QT = RT2


def boolfactor():
    global tk
    global RT
    global RF
    global E
    global relop

    if (tk == "nottk"):
        # RT = BF
        # RF = BT
        tk = lex()
        if (tk == "rightsqbrackettk"):
            tk = lex()
            condition()
            if (tk == "leftsqbrackettk"):
                RT = BT
                RF = BF
                tk = lex()
            else:
                print("ERROR : ']' expected after condition " + ": line: " + str(line))
                sys.exit()
        else:
            print("ERROR : '[' expected after 'not' keyword " + ": line: " + str(line))
            sys.exit()
    elif (tk == "rightsqbrackettk"):
        tk = lex()
        condition()
        if (tk == "leftsqbrackettk"):
            RT = BT
            RF = BF
            tk = lex()
        else:
            print("ERROR : ']' expected after condition" + ": line: " + str(line))
            sys.exit()
    else:
        expression()  # E1
        E1 = E
        relationaloper()  # relop
        expression()  # E2
        E2 = E
        RT = makelist(nextquad())
        genQuad(relop, E1, E2, "_")
        RF = makelist(nextquad())
        genQuad("jump", "_", "_", "_")


def expression():
    global tk
    global E
    optionalsign()
    term()  # T1
    T1 = T
    while (tk == "plustk"):
        addoper()
        term()  # T2
        T2 = T
        w = newtemp()
        genQuad("+", T1, T2, w)
        T1 = w

    while (tk == "minustk"):
        addoper()
        term()  # T2
        T2 = T
        w = newtemp()
        genQuad("-", T1, T2, w)
        T1 = w
    E = T1


def term():
    global tk
    factor()  # F1
    F1 = F
    global T
    while (tk == "multk"):
        muloper()
        factor()  # F2
        F2 = F
        w = newtemp()
        genQuad("*", F1, F2, w)
        F1 = w
    while (tk == "divtk"):
        muloper()
        factor()  # F2
        F2 = F
        w = newtemp()
        genQuad("/", F1, F2, w)
        F1 = w
    T = F1


def factor():
    global tk
    global F
    global number
    if (tk == "constanttk"):
        F = number
        tk = lex()
    elif (tk == "rightroundbrackettk"):
        tk = lex()
        expression()

        if (tk == "leftroundbrackettk"):
            tk = lex()
        else:
            print("ERROR : ')' expected after expression" + ": line: " + str(line))
            sys.exit()
    elif (tk == "idtk"):
        F = name
        tk = lex()
        idtail()


def idtail():
    if (tk == "rightbrackettk"):
        actualpars()


def relationaloper():
    global tk
    global relop

    if (
            tk == "equaltk" or tk == "lessequaltk" or tk == "moreequaltk" or tk == "lesstk" or tk == "moretk" or tk == "morelesstk"):
        if (tk == "equaltk"):
            relop = "="
        elif (tk == "lessequaltk"):
            relop = "<="
        elif (tk == "moreequaltk"):
            relop = ">="
        elif (tk == "lesstk"):
            relop = "<"
        elif (tk == "moretk"):
            relop = ">"
        elif (tk == "morelesstk"):
            relop = "<>"

        tk = lex()

    else:
        print("ERROR: relational operator expected " + ": line: " + str(line))
        sys.exit()


def addoper():
    global tk
    if (tk == "plustk" or tk == "minustk"):
        tk = lex()
    else:
        print("ERROR: '+' or '-' expected " + ": line: " + str(line))
        sys.exit()


def muloper():
    global tk
    if (tk == "multk" or tk == "divtk"):
        tk = lex()
    else:
        print("ERROR: '*' or '/' expected" + ": line: " + str(line))
        sys.exit()


def optionalsign():
    global tk
    if (tk == "plustk" or tk == "minustk"):
        addoper()


tk = lex()
program()
