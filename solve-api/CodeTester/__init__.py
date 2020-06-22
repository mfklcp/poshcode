from CodeTester import CodeTester

if __name__ == "__main__":
    tester = CodeTester()
    tester.copy_files(
        code_hash="batatadoce-vale-mais-que-feijao_entradas_por_aluno@poshcode.com.br_2019-04-02 06:31:52.153",
        filename="main.c",
        entradas="batatadoce-vale-mais-que-feijao_entradas",
        saidas="batatadoce-vale-mais-que-feijao_saidas")
    print(tester.check_return())