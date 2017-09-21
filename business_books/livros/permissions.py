from rest_framework import permissions





class IsOwnerOrReadOnly(permissions.BasePermission):



    def has_object_permission(self, request, view, obj):

        if request.method in permissions.SAFE_METHODS:
            return True

        else:

            return obj.usuario.user == request.user


class IsOwnerOrReadOnlyEstante(permissions.BasePermission):



    def has_object_permission(self, request, view, obj):

        if request.method in permissions.SAFE_METHODS:
            return True

        else:

            return obj.livro.usuario.user == request.user


class IsOwnerOrReadOnlyOperacao(permissions.BasePermission):



    def has_object_permission(self, request, view, obj):

        if request.method in permissions.SAFE_METHODS:
            return True

        else:

            return obj.estante.livro.usuario.user == request.user



