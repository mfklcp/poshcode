# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class HibernateSequence(models.Model):
    next_val = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'hibernate_sequence'


class Questao(models.Model):
    id = models.BigIntegerField(primary_key=True)
    atualizado_em = models.DateTimeField(blank=True, null=True)
    criado_em = models.DateTimeField()
    corpo = models.CharField(max_length=255, blank=True, null=True)
    titulo = models.CharField(max_length=255, blank=True, null=True)
    url_entradas = models.CharField(max_length=255, blank=True, null=True)
    url_file = models.CharField(max_length=255, blank=True, null=True)
    url_saidas = models.CharField(max_length=255, blank=True, null=True)
    id_autor = models.ForeignKey('Usuarios', models.DO_NOTHING, db_column='id_autor', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'questao'


class Resposta(models.Model):
    id = models.BigIntegerField(primary_key=True)
    criado_em = models.DateTimeField()
    correta = models.BooleanField(blank=True, null=True)  # This field type is a guess.
    retorno = models.CharField(max_length=255, blank=True, null=True)
    tempo_de_execucao = models.TimeField(blank=True, null=True)
    url_code = models.CharField(max_length=255)
    id_autor = models.ForeignKey('Usuarios', models.DO_NOTHING, db_column='id_autor', blank=True, null=True)
    id_resposta = models.ForeignKey(Questao, models.DO_NOTHING, db_column='id_resposta', blank=True, null=True)
    tempo_de_exec_str = models.CharField(max_length=255, blank=True, null=True)
    original_name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'resposta'


class Usuarios(models.Model):
    id = models.BigAutoField(primary_key=True)
    atualizado_em = models.DateTimeField(blank=True, null=True)
    criado_em = models.DateTimeField()
    email = models.CharField(unique=True, max_length=255)
    email_verified = models.TextField()  # This field type is a guess.
    image_url = models.CharField(max_length=255, blank=True, null=True)
    nickname = models.CharField(max_length=255)
    nome = models.CharField(max_length=255)
    provider = models.CharField(max_length=255)
    provider_id = models.CharField(max_length=255, blank=True, null=True)
    role = models.CharField(max_length=255)
    senha = models.CharField(max_length=255, blank=True, null=True)
    sobrenome = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'usuarios'
