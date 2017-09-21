from django.shortcuts import render

from livros.serializers import *
import json
from django.shortcuts import get_object_or_404
from rest_framework import generics
from livros.permissions import *

from rest_framework.response import Response
from rest_framework.reverse import reverse
from django.contrib.auth.models import AnonymousUser
from django.contrib.auth.models import User
from rest_framework.authtoken.models import Token
from rest_framework.throttling import ScopedRateThrottle

from rest_framework import filters
from django_filters import NumberFilter,DateTimeFilter,AllValuesFilter
from rest_framework.filters import DjangoFilterBackend
from rest_framework.pagination import *

# Create your views here.

class LargeResultsSetPagination(PageNumberPagination):
    page_size = 100
    page_size_query_param = 'page_size'
    max_page_size = 1000

class UserList(generics.ListCreateAPIView):

    serializer_class = UserSerializer
    name='user'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnly,

   )

    def get_queryset(self):


        valor = self.request.user

        if isinstance(valor,AnonymousUser):

            queryset = User.objects.none()



        else:


            queryset = User.objects.filter(username = self.request.user.username)

        return queryset

class UsuarioList(generics.ListCreateAPIView):
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer
    name='usuario-list'

    def perform_create(self, serializer):

        dados = self.request.data

        if self.request.POST:
            user =self.request.POST.get('user.username')
            email = self.request.POST.get('user.email')
            password = self.request.POST.get('user.password')
        else:

            user = dados['user']['username']
            email = dados['user']['email']
            password =dados['user']['password']

        userserializer = UserSerializer(data={'username':user,
                                         'email':email,
                                         'password':password})


        if userserializer.is_valid():
            usuar = userserializer.save()
            serializer.save(user=usuar)



class UsuarioDetail(generics.RetrieveUpdateAPIView):
   queryset = Usuario.objects.all()
   serializer_class = UsuarioSerializer
   name = 'usuario-detail'





class LivroList(generics.ListAPIView):
    queryset = Livro.objects.all()
    serializer_class = LivroSerializer
    name ='livro-list'

    DjangoFilterBackend.template='rest_framework/filters/django_filter.html'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnly,

   )

    filter_fields = ('usuario',
                     'autor',
                     'titulo',
                     'categoria',
                     'ano_publicacao',)
    search_fields = ('^titulo',)
    ordering_fields = ('titulo',
                       'ano_publicacao',)




    def perform_create(self, serializer):

        usuario = Usuario.objects.get(user=self.request.user)
        serializer.save(usuario=usuario)


class LivroList2(generics.ListAPIView):

    serializer_class = LivroSerializer
    name ='livro-list2'

    DjangoFilterBackend.template='rest_framework/filters/django_filter.html'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnly,

   )

    filter_fields = ('usuario',
                     'autor',
                     'titulo',
                     'categoria',
                     'ano_publicacao',)
    search_fields = ('^titulo',)
    ordering_fields = ('titulo',
                       'ano_publicacao',)


    def get_queryset(self):


        valor = self.request.user

        if isinstance(valor,AnonymousUser):

            queryset = Livro.objects.none()



        else:
            usuario = Usuario.objects.get(user=self.request.user)

            queryset = Livro.objects.filter(usuario=usuario)

        return queryset




    def perform_create(self, serializer):

        usuario = Usuario.objects.get(user=self.request.user)
        serializer.save(usuario=usuario)



class LivroDetail(generics.RetrieveDestroyAPIView):
    queryset = Livro.objects.all()
    serializer_class = LivroSerializerDetail
    name='livro-detail'

class AutorList(generics.ListCreateAPIView):

    queryset = Autor.objects.all()
    pagination_class = LargeResultsSetPagination
    serializer_class = AutorSerializer
    name = 'autor-list'
    DjangoFilterBackend.template='rest_framework/filters/django_filter.html'


    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnly,

   )

    filter_fields = ('autor',)
    search_fields = ('^autor',)
    ordering_fields = ('autor',)

    def perform_create(self, serializer):

        usuario = Usuario.objects.get(user=self.request.user)
        serializer.save(usuario=usuario)


class AutorListDetail(generics.RetrieveAPIView):
    queryset = Autor.objects.all()
    serializer_class = AutorSerializer
    name = 'autor-list-detail'




class EstanteListDetail(generics.RetrieveAPIView):
    queryset = Estante.objects.all()


    serializer_class = EstanteSerializerLivro
    name='estante-detail'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnlyEstante,)





class EstanteList(generics.ListCreateAPIView):
    print('Maclaine')



    serializer_class = EstanteSerializer
    name='estante-list'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnlyEstante,

   )

    Throttle_scope = 'estantes'
    Throttle_classes = (ScopedRateThrottle,)

    def get_queryset(self):


        queryset = Estante.objects.filter(chave='ATIVO')
        return queryset




    def perform_create(self, serializer):
        dados = self.request.data
        print(dados)

        if self.request.POST:

            autor = self.request.POST.get('livro.autor')
            titulo = self.request.POST.get('livro.titulo')
            categoria = self.request.POST.get('livro.categoria')
            ano_publicacao = self.request.POST.get('livro.ano_publicacao')

        else:
            autor = dados['livro']['autor']
            titulo = dados['livro']['titulo']
            categoria = dados['livro']['categoria']
            ano_publicacao = dados['livro']['ano_publicacao']

            print (autor)



        usuario = Usuario.objects.get(user=self.request.user)
        #autor2 = Autor.objects.get(autor=autor)



        
        livroserializer = LivroSerializerEstant(data={'autor':autor,
                                                'titulo':titulo,
                                                'categoria':categoria,
                                                'ano_publicacao':ano_publicacao})

        if livroserializer.is_valid():
            livro = livroserializer.save(usuario=usuario)
            codigo = str(livro.pk)
            serializer.save(livro=livro,codigo=codigo)




class OperacaoList(generics.ListCreateAPIView):
    queryset = Operacao.objects.all()
    serializer_class = OperacaoSerializer
    name='operacao-list'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnlyOperacao,

   )


    def perform_create(self, serializer):
        usuario = Usuario.objects.get(user=self.request.user)
        operacao = serializer.save(usuario_destino=usuario)
        estante = operacao.estante
        estante.chave='INATIVO'
        estante.save()



class TransacoesList(generics.ListAPIView):

    serializer_class = TransacaoSerializer
    name='operacao-list'


    def get_queryset(self):


        valor = self.request.user

        if isinstance(valor,AnonymousUser):

            queryset = Operacao.objects.none()



        else:
            usuario = Usuario.objects.get(user=self.request.user)

            queryset = Operacao.objects.filter(usuario_destino=usuario)

        return queryset

class TransacoesDetail(generics.RetrieveUpdateAPIView):
    queryset = Operacao.objects.all()
    serializer_class = TransacaoSerializerDetail
    name='operacao-detail'

class OperacaoList(generics.CreateAPIView):
    queryset = Operacao.objects.all()
    serializer_class = OperacaoSerializer
    name='operacoes'

    permission_classes = (
       permissions.IsAuthenticatedOrReadOnly,
       IsOwnerOrReadOnlyOperacao,

   )


    def perform_create(self, serializer):
        usuario = Usuario.objects.get(user=self.request.user)
        operacao = serializer.save(usuario_destino=usuario)
        estante = operacao.estante
        estante.chave='INATIVO'
        estante.save()


class ApiRoot(generics.GenericAPIView):
  name='api-root'

  def get(self,request,*args,**kwargs):
      for user in User.objects.all():
          Token.objects.get_or_create(user=user)

      return Response({
          'usuarios':reverse(UsuarioList.name,request=request),
          'autores':reverse(AutorList.name,request=request),
          'livros':reverse(LivroList.name,request=request),
          'meuslivros':reverse(LivroList2.name,request=request),
          'estante':reverse(EstanteList.name,request=request),
          'operacoes':reverse(OperacaoList.name,request=request),
          'transacoes':reverse(TransacoesList.name,request=request),
          'documentacao':'http://127.0.0.1:8000/documentacao'


      })
