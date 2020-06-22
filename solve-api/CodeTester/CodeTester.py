import tempfile, shutil, difflib,subprocess
from datetime import datetime
from CodeTester.GenericFile import PyFile,CppFile,CFile,JavaFile

class CodeTester(object):
    root_dir = "D:/CloudDrive-MEGA/Sync/UFMA/Monografia/Poshcode/Project_TheJudge/files-poshcode/"

    def __init__(self, code_hash, filename, entradas, saidas):
        self.code_hash = code_hash
        self.filename = filename
        self.entradas = entradas
        self.saidas = saidas
        self.tempdir = "{}/".format(tempfile.mkdtemp())

    def execute(self):
        self._copy_files()
        model = self._make_model()
        if model.compilable:
            p = subprocess.Popen(model.compiler_command(), cwd=self.tempdir)
            r,e = p.communicate(timeout=60)
            print(self.tempdir)
            print(e)
        data = self._run_action(model)
        #shutil.rmtree(self.tempdir, ignore_errors=True)
        return data


    def _run_action(self, model):
        with open(self.tempdir + "out", "wb") as out, \
                open(self.tempdir + "entradas", "r") as stin:
            try:
                time_start = datetime.now()
                p = subprocess.Popen(model.run_command(), cwd=self.tempdir, stdout=out,stderr=subprocess.PIPE, stdin=stin)
                error = p.communicate(timeout=60*5)
                time_exec = datetime.now() - time_start
                return { "time_exec":time_exec,"error": error[1].decode("utf-8"),"status": self.check_return() }
            except subprocess.TimeoutExpired as t:
                return {"time_exec": None, "error": "Tempo Excedido", "status": 0}
            except Exception as e:
                return {"time_exec": None, "error": e, "status": 0}


    def _make_model(self):
        print(self._check_extension)
        if self._check_extension() == "c":
            return CFile(self.filename)
        elif self._check_extension() == "cpp":
            return CppFile(self.filename)
        elif self._check_extension() == "py":
            return PyFile(self.filename)
        elif self._check_extension() == "java":
            return JavaFile(self.filename)
        else:
            raise Exception

    def check_return(self):
        diff = None
        with open(self.tempdir + "out", 'r') as out_default:
            with open(self.tempdir + "saidas", 'r') as out_program:
                diff = difflib.unified_diff(
                    out_default.readlines(),
                    out_program.readlines()
                )
        comp = ''.join(diff)
        if len(comp) > 0:
            return False
        return True

    def _cpToTemp(self, file, newfile):
        shutil.copyfile(self.root_dir + file, self.tempdir + newfile)

    def _check_extension(self):
        return self.filename.split(".")[-1]

    def _copy_files(self):
        self._cpToTemp(self.code_hash, self.filename)
        self._cpToTemp(self.entradas, "entradas")
        self._cpToTemp(self.saidas, "saidas")
