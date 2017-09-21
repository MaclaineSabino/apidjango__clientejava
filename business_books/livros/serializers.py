from rest_framework import serializers
from livros.models import *
from django.contrib.auth.models import User


class AutorSerializer(serializers.HyperlinkedModelSerializer):

    class Meta:
        model = Autor
        fields = ('pk','autor',)

class UserSerializer(serializers.HyperlinkedModelSerializer):

    class Meta:
        model = User
        fields = ('pk',
                  'email',
                  'username',
                  'password')

        extra_kwargs = {
            "password": {"write_only": True},

        }

    def create(self, validated_data):
        user = User.objects.create_user(**validated_data)
        return user






class UsuarioSerializer(serializers.HyperlinkedModelSerializer):
    user = UserSerializer()
    class Meta:
        model = Usuario
        fields = ('pk',
                  'user',
                  'nome',
                  'endereco',
                  'cidade',
                  'estado')



    '''def create(self, validated_data):
        usar = validated_data.get('user')
        usuario = Usuario.objects.create(**validated_data)
        Usuario.objects.create(usuario=usuario,**usar)
        return usuario'''

class LivroSerializerDetail(serializers.HyperlinkedModelSerializer):
    autor = AutorSerializer()
    usuario =UsuarioSerializer()

    class Meta:
        model = Livro
        fields = (
                  'pk',
                  'usuario',
                  'autor',
                  'titulo',
                  'categoria',
                  'ano_publicacao'
                  )


class EstanteSerializerLivro(serializers.ModelSerializer):

    livro=LivroSerializerDetail()



    class Meta:
        model = Estante
        fields = ('pk',
                  'livro',
                  'modalidade_negocio',
                  'preco',
                  'codigo')
        extra_kwargs = {

            "chave": {"read_only": True},
            "codigo": {"read_only": True},
        }

class LivroSerializer(serializers.HyperlinkedModelSerializer):
    estante = EstanteSerializerLivro
    #autor = serializers.SlugRelatedField(queryset=Autor.objects.all(),slug_field='autor')

    class Meta:
        model = Livro
        fields = ('url',
                  'pk',

                  'titulo',
                  'categoria',
                  'ano_publicacao'


                  )


        def create(self, validated_data):
            livro = Livro.objects.create(**validated_data)
            return livro


class LivroSerializerEstant(serializers.HyperlinkedModelSerializer):




    autor = serializers.SlugRelatedField(queryset=Autor.objects.all(),slug_field='autor')

    class Meta:
        model = Livro
        fields = ('url',
                  'pk',

                  'autor',

                  'titulo',
                  'categoria',
                  'ano_publicacao'


                  )

        extra_kwargs = {
            "usuario": {"read_only": True}

        }
        def create(self, validated_data):
            livro = Livro.objects.create(**validated_data)
            return livro



class UsuarioDestinoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Usuario
        fields = ('url',
                  'pk',
                  'user',
                  'nome',
                  'endereco',
                  'cidade',
                  'estado')

        extra_kwargs = {
            "nome": {"read_only": True},
            "endereco": {"read_only": True},
            "cidade": {"read_only": True},
            "estado": {"read_only": True},
        }





class EstanteSerializer(serializers.HyperlinkedModelSerializer):

    livro = LivroSerializerEstant()






    class Meta:
        model = Estante
        fields = ('url',
                  'pk',
                  'livro',
                  'chave',
                  'modalidade_negocio',
                  'preco',
                  'codigo')
        extra_kwargs = {

            "chave": {"read_only": True},
            "codigo": {"read_only": True},
        }


class EstanteSerializerTransacao(serializers.ModelSerializer):

    livro = LivroSerializerEstant()





    class Meta:
        model = Estante
        fields = ('url',
                  'pk',
                  'livro'

                  )


class EstanteOperacaoSerializer(serializers.HyperlinkedModelSerializer):

    livro = LivroSerializer()





    class Meta:
        model = Estante
        fields = ('livro',
                  'modalidade_negocio',
                  'preco')

        extra_kwargs = {

            "modalidade_negocio": {"read_only": True},
            "preco": {"read_only": True},
        }


class OperacaoSerializer(serializers.ModelSerializer):

    estante = serializers.SlugRelatedField(queryset=Estante.objects.filter(chave='ATIVO'),slug_field='pk')




    class Meta:
        model = Operacao
        fields = (
                  'pk',
                  'estante',


                  )


        def create(self, validated_data):

            operacao = Operacao.objects.create(**validated_data)

            return operacao




class TransacaoSerializerDetail(serializers.ModelSerializer):
    usuario_destino=UsuarioDestinoSerializer()
    estante = EstanteSerializerTransacao()





    class Meta:
        model = Operacao

        fields = (
                  'pk',
                  'estante',
                  'usuario_destino',

                  )


        extra_kwargs = {
            "usuario_destino": {"read_only": True},
        }



class TransacaoSerializer(serializers.HyperlinkedModelSerializer):
    usuario_destino=UsuarioDestinoSerializer()
    estante = EstanteSerializerTransacao()





    class Meta:
        model = Operacao

        fields = ('url',
                  'pk',
                  'estante',
                  'usuario_destino',

                  )


        extra_kwargs = {
            "usuario_destino": {"read_only": True},
        }










