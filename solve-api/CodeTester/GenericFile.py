class GenericFile:

    @property
    def filename(self) -> str:
        raise NotImplementedError

    @property
    def compilable(self) -> bool:
        raise NotImplementedError

    @property
    def extension(self) -> str:
        raise NotImplementedError

    @property
    def compiler(self) -> str:
        if self.compilable:
            raise NotImplementedError

    @property
    def compiler_args(self) -> list:
        return []

    @property
    def interpreter(self) -> str:
        if not self.compilable:
            raise NotImplementedError

    @property
    def outputname(self) -> str:
        return self.filename

    def remove_extension(self) -> str:
        return self.filename[:-(len(self.extension) + 1)]

    def compiler_command(self):
        if self.compilable is False:
            return Exception
        return [self.compiler, self.filename] + self.compiler_args;

    def run_command(self):
        if self.interpreter:
            return [self.interpreter, self.outputname]
        return [self.outputname]


class CFile(GenericFile):
    filename = None
    extension = "c"
    compilable = True
    compiler = "gcc"
    outputname = "./a.out"

    def __init__(self, filename):
        self.filename = filename


class CppFile(GenericFile):
    filename = None
    extension = "cpp"
    compilable = True
    compiler = "g++"
    outputname = "./a.out"

    def __init__(self, filename):
        self.filename = filename


class PyFile(GenericFile):
    filename = None;
    extension = "py"
    compilable = False
    interpreter = "python"

    def __init__(self, filename):
        self.filename = filename


class JavaFile(GenericFile):
    filename = None
    extension = "java"
    compilable = True
    compiler = "javac"
    interpreter = "java"
    outputname = None

    def __init__(self, filename):
        self.filename = filename
        self.outputname = filename.split(".")[0]
