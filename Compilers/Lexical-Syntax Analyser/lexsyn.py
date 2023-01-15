import sys

print("\n**** LEXICAL/SYNTAX ANALYSER ****")
print("Type your min document")
filename = input()
myfile = open(filename)
temp = myfile.read(1)
line = 0
if (temp == '\n'):
    line = line + 1
token = ""
tk = ""


### lex() function for the lexical analysis

def lex():
    global temp
    phrase = ""
    global line
    global token
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


        if (phrase == "program"):              # Check for keywords
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


### FUNCTIONS FOR SYNTAX ANALYSIS


def program():
    global tk
    if (tk == "programtk"):
        tk = lex()
        if (tk == "idtk"):
            tk = lex()

            if tk == "rightbrackettk":
                tk = lex()
                block()

                if (tk == "leftbrackettk"):

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
    declarations()
    subprograms()
    statements()


def declarations():
    global tk
    while (tk == "declaretk"):

        tk = lex()
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
    if (tk == "proceduretk"):
        tk = lex()
        if (tk == "idtk"):
            tk = lex()
            funcbody()
        else:
            print("ERROR : name of procedure expected" + ": line: " + str(line))
            sys.exit()
    elif (tk == "functiontk"):
        tk = lex()
        if (tk == "idtk"):
            tk = lex()

            funcbody()

        else:
            print("ERROR : name of function expected" + ": line: " + str(line))
            sys.exit()


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
    if (tk == "idtk"):
        tk = lex()
        if (tk == "assignmenttk"):
            tk = lex()
            expression()
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
    if (tk == "iftk"):
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            condition()
            if tk == "leftroundbrackettk":
                tk = lex()
                if tk == "thentk":
                    tk = lex()

                    statements()
                    elsepart()
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
    if (tk == "whiletk"):
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            condition()
            if tk == "leftroundbrackettk":
                tk = lex()
                statements()
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
    if (tk == "forcasetk"):
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
    if (tk == "calltk"):
        tk = lex()
        if (tk == "idtk"):
            tk = lex()
            actualpars()
        else:
            print("ERROR : name of procedure expected " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR : keyword 'call' expected" + ": line: " + str(line))
        sys.exit()


def returnstat():
    global tk
    if (tk == "returntk"):
        tk = lex()
        expression()
    else:
        print("ERROR : keyword 'return' or ';' expected" + ": line: " + str(line))
        sys.exit()


def printstat():
    global tk
    if (tk == "printtk"):
        tk = lex()
        if tk == "rightroundbrackettk":
            tk = lex()
            expression()
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
        if tk == "rightroundbracket":
            tk = lex()
            if (tk == "idtk"):
                tk = lex()
                if tk == "leftroundbracket":
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
    if (tk == "rightbrackettk"):
        tk = lex()
        actualparlist()
        if (tk == "leftbrackettk"):
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
        expression()
    elif (tk == "inouttk"):
        tk = lex()
        if (tk == "idtk"):
            tk = lex()
        else:
            print("ERROR: name of actual parameter expected " + ": line: " + str(line))
            sys.exit()
    else:
        print("ERROR: 'in' or 'inout' keyword expected " + ": line: " + str(line))
        sys.exit()


def condition():
    global tk
    boolterm()
    while (tk == "ortk"):
        tk = lex()
        boolterm()


def boolterm():
    global tk
    boolfactor()
    while (tk == "andtk"):
        tk = lex()
        boolfactor()


def boolfactor():
    global tk
    if (tk == "nottk"):
        tk = lex()
        if (tk == "rightsqbrackettk"):
            tk = lex()
            condition()
            if (tk == "leftsqbrackettk"):
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
            tk = lex()
        else:
            print("ERROR : ']' expected after condition" + ": line: " + str(line))
            sys.exit()
    else:
        expression()
        relationaloper()
        expression()


def expression():
    global tk
    optionalsign()
    term()
    while (tk == "plustk" or tk == "minustk"):
        addoper()
        term()


def term():
    global tk
    factor()
    while (tk == "multk" or tk == "divtk"):
        muloper()
        factor()


def factor():
    global tk
    if (tk == "constanttk"):
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
        tk = lex()
        idtail()


def idtail():
    if (tk == "rightbrackettk"):
        actualpars()


def relationaloper():
    global tk

    if (tk == "equaltk" or tk == "lessequaltk" or tk == "moreequaltk" or tk == "lesstk" or tk == "moretk" or tk == "morelesstk"):
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