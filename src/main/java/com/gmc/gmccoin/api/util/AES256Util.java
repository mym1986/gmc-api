package com.gmc.gmccoin.api.util;


import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class AES256Util {
	private String iv;
	private byte[] ivBytes;
	private byte[] keyBytes;
	private Key keySpec;
	private String key;

	final private AES256Properties aes256Properties;

	private void init() throws Exception {
		this.key = aes256Properties.getAesPass();
		this.iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);

	}
	/**
	 * AES256 으로 암호화 한다.
	 *
	 * @param str 암호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */


	public String encrypt(String str) {
		try {
			init();
			KeyParameter keyParam = new KeyParameter(keyBytes);
			CipherParameters params = new ParametersWithIV(keyParam, ivBytes);
			BlockCipherPadding padding = new PKCS7Padding();
			PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
					new CBCBlockCipher(new AESEngine()), padding);
			cipher.reset();
			cipher.init(true, params);
			//byte[] data = ("0x"+ DigestUtils.md5Hex(str).toLowerCase()).getBytes(StandardCharsets.UTF_16LE);
			byte[] data = str.getBytes(StandardCharsets.UTF_16LE);

			byte[] buffer = new byte[cipher.getOutputSize(data.length)];
			int len = cipher.processBytes(data, 0, data.length, buffer, 0);

			len += cipher.doFinal(buffer, len);
			Arrays.copyOfRange(buffer, 0, len);

			return Base64.encodeBase64String(buffer);
		} catch (Exception e) {
			throw new RuntimeException("encrypt error in SimpleAesManaged", e);
		}

	}

	/**
	 * AES256으로 암호화된 txt 를 복호화한다.
	 *
	 * @param str 복호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String decrypt(String str) {
		try {
			init();
			KeyParameter keyParam = new KeyParameter(keyBytes);
			CipherParameters params = new ParametersWithIV(keyParam, ivBytes);
			BlockCipherPadding padding = new PKCS7Padding();
			PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
					new CBCBlockCipher(new AESEngine()), padding);
			cipher.reset();
			cipher.init(false, params);
			byte[] data = Base64.decodeBase64(str);

			byte[] buffer = new byte[cipher.getOutputSize(data.length)];
			int len = cipher.processBytes(data, 0, data.length, buffer, 0);

			len += cipher.doFinal(buffer, len);

			byte[] out = Arrays.copyOfRange(buffer, 0, len);

			return new String(out, StandardCharsets.UTF_16LE);
		} catch (Exception e) {
			throw new RuntimeException("decrypt error in SimpleAesManaged", e);
		}
	}

	public String encryptByKey(String key, String str)  {
		try {
			String iv = key.substring(0, 16);
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;
			if (len > keyBytes.length) {
				len = keyBytes.length;
			}
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
			String enStr = new String(java.util.Base64.getEncoder().encode(encrypted));

			return enStr;
		}
		catch(Exception ex){
			throw new RuntimeException("encrypt error in SimpleAesManaged", ex);
		}
	}

	public String decryptByKey(String key, String str)  {
		try {
			String iv = key.substring(0, 16);
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;
			if (len > keyBytes.length) {
				len = keyBytes.length;
			}
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] encrypted = c.doFinal(java.util.Base64.getDecoder().decode(str));
			String enStr = new String(encrypted);

			return enStr;
		}
		catch(Exception ex){
			throw new RuntimeException("encrypt error in SimpleAesManaged", ex);
		}
	}
}