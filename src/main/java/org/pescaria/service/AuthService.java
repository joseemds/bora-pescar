package org.pescaria.service;

import org.pescaria.entity.TipoUsuario;
import org.pescaria.entity.Usuario;

public class AuthService {

    private static Usuario autenticado = null;

    public static void login(Usuario u) {
        autenticado = u;
    }

    public static void logout() {
        autenticado = null;
    }

    public static Usuario getAutenticado() {
        return autenticado;
    }

    public static boolean isAutenticado() {
        return getAutenticado() != null;
    }

    public static boolean isAdmin() {
        return isAutenticado() && getAutenticado().getTipo() == TipoUsuario.ADMIN;
    }

}
