from django.http.response import HttpResponse
from .models import Resposta,Questao
from CodeTester.CodeTester import CodeTester


def solve(request,resposta_id):
    resposta = Resposta.objects.get(id=resposta_id)
    questao = resposta.id_resposta

    ct = CodeTester(
        code_hash=resposta.url_code,
        filename=resposta.original_name,
        entradas=questao.url_entradas,
        saidas=questao.url_saidas)

    dump = ct.execute()
    print(dump)
    if not dump["status"]:
        resposta.correta = 0
        resposta.tempo_de_exec_str = ""
        if dump['error'] == '':
            resposta.retorno = 'Retorno incorreto'
        else:
            resposta.retorno = dump['error']
    else:
        resposta.correta = 1
        resposta.tempo_de_exec_str = dump['time_exec']

    resposta.save()

    return HttpResponse(status=200)

