package com.springencryption.converters;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.springframework.stereotype.Component;

@Component
public class StringAttributeConvert implements AttributeConverter<String, String>{

    private static final String AES = "AES";
    private static final byte[] encryptKey = "VVLQVUKFKLJUKDXJ".getBytes();

    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public StringAttributeConvert() throws Exception {
        Key key = new SecretKeySpec(encryptKey, AES);
        encryptCipher = Cipher.getInstance(AES);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher = Cipher.getInstance(AES);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }
    

    /**
     * Encrypt
     */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(attribute.getBytes()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Decrypt
     */
    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
}
