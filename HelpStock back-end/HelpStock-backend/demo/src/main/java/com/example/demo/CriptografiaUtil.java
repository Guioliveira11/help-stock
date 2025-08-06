package com.example.demo;

import org.mindrot.jbcrypt.BCrypt;

public class CriptografiaUtil {

    public static String criptografarSenha(String senhaPura) {
        return BCrypt.hashpw(senhaPura, BCrypt.gensalt(12)); // 12 = fator de custo
    }

    public static boolean verificarSenha(String senhaPura, String senhaHash) {
        return BCrypt.checkpw(senhaPura, senhaHash);
    }
}

