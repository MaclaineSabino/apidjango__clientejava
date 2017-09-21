
from django.conf.urls import url
from livros import views

from rest_framework_swagger.views import get_swagger_view

schema_view = get_swagger_view(title='documentacao')


urlpatterns = [
    url(r'user/$',
        views.UserList.as_view(),
        name=views.UserList.name),

    url(r'^usuario/$',
        views.UsuarioList.as_view(),
        name=views.UsuarioList.name),

    url(r'^usuario/(?P<pk>[0-9]+)/$',
        views.UsuarioDetail.as_view(),
        name=views.UsuarioDetail.name),

    url(r'^autor/$',
        views.AutorList.as_view(),
        name = views.AutorList.name),

    url(r'^autor/(?P<pk>[0-9]+)/$',
        views.AutorListDetail.as_view(),
        name=views.AutorListDetail.name),


    url(r'^livro/$',
        views.LivroList.as_view(),
        name=views.LivroList.name),
    url(r'^livro/meuslivros/$',
        views.LivroList2.as_view(),
        name=views.LivroList2.name),

    url(r'^livro/(?P<pk>[0-9]+)/$',
        views.LivroDetail.as_view(),
        name=views.LivroDetail.name),
    url(r'^estante/$',
        views.EstanteList.as_view(),
        name=views.EstanteList.name),
    url(r'^estante/(?P<pk>[0-9]+)/$',
        views.EstanteListDetail.as_view(),
        name=views.EstanteListDetail.name),

    url(r'^operacao/$',
        views.OperacaoList.as_view(),
        name=views.OperacaoList.name),
    url(r'^transacao/$',
        views.TransacoesList.as_view(),
        name=views.TransacoesList.name),
    url(r'transacao/(?P<pk>[0-9]+)/$',
        views.TransacoesDetail.as_view(),
        name=views.TransacoesDetail.name),
    url(r'documentacao/$',schema_view),
    url(r'^$',
        views.ApiRoot.as_view(),
        name=views.ApiRoot.name),

]
