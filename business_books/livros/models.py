
from django.db import models
from datetime import datetime
from django.utils import timezone

# Create your models here.



class Usuario(models.Model):

    ESTADOS_CHOICES = (('ES', 'ES'),('MG', 'MG'),('RJ','RJ'),
                      ('SP','SP'),('AL','AL'),('BA','BA'),
                      ('CE','CE'),('MA','MA'),('PB','PB'),
                      ('PE','PE'),('PI','PI'),('RN','RN'),
                      ('SE','SE'),('DF','DF'),('GO','GO'),
                      ('MT','MT'),('MS','MS'),('AC','AC'),
                      ('AP','AP'),('AM','AM'),('PA','PA'),
                      ('RO','RO'),('RR','RR'),('TO','TO'),
                      ('PR','PR'),('SC','SC'),('RS','RS'))


    user = models.OneToOneField('auth.User')
    nome = models.CharField(max_length=255)
    endereco = models.CharField(max_length=255)
    cidade = models.CharField(max_length=255)
    estado =models.CharField(max_length=2,choices=ESTADOS_CHOICES)

    def __str__(self):
        return self.user.username


class Autor(models.Model):
    usuario = models.ForeignKey(Usuario)
    autor = models.CharField(max_length=255)

    def __str__(self):
        return self.autor


class Livro(models.Model):
    usuario = models.ForeignKey(Usuario,on_delete=models.CASCADE)
    autor = models.ForeignKey(Autor,on_delete=models.CASCADE)
    titulo = models.CharField(max_length=255)
    categoria = models.CharField(max_length=255,default='')
    ano_publicacao = models.IntegerField()

    def __str__(self):
        return self.titulo
class Estante(models.Model):
    NEGOCIO_CHOICES = (('VENDA','VENDA'),('TROCA','TROCA'))

    chave = models.CharField(max_length=100, default='ATIVO')

    codigo = models.CharField(max_length=200,unique=True)


    livro = models.ForeignKey(Livro,on_delete=models.CASCADE,related_name='estante')
    modalidade_negocio = models.CharField(max_length=2,choices=NEGOCIO_CHOICES,default='TROCA')
    preco = models.DecimalField(max_digits=5,decimal_places=2,default=0.00)


    def __str__(self):

        return self.codigo



class Operacao(models.Model):
    estante = models.ForeignKey(Estante,on_delete=models.CASCADE)
    usuario_destino = models.ForeignKey(Usuario,on_delete=models.CASCADE)
    data = models.DateField(default=timezone.now())

    def __str__(self):

        return self.estante.livro.titulo
