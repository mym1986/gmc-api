package com.gmc.gmccoin.api;

import com.gmc.gmccoin.api.util.Base58;
import com.gmc.gmccoin.api.util.Sha256Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmcCoinApplicationTests {


	@Test
	void contextLoads() {

		//org.tron.trident.core.ApiWrapper wrapper = new org.tron.trident.core.ApiWrapper("grpc endpoint", "solidity grpc endpoint", "hex private key");
//
//		ApiWrapper wrapper = ApiWrapper.ofShasta("hex private key");
		float f = 999999999129L;
		String s = String.valueOf(f / 10000);
		System.out.println(s);

		String stringToConvert = "TTb9zDVUVEKzqAWtnWWdCh6tLrpXyHvyjc";

		System.out.println(tronHex(stringToConvert));

	}

	public String tronHex(String base58) {
		byte[] decoded = decode58(base58);
		String hexString = decoded == null ? "" : org.spongycastle.util.encoders.Hex.toHexString(decoded);
		return hexString;
	}


	private byte[] decode58(String input) {
		byte[] decodeCheck = Base58.decode(input);
		if (decodeCheck.length <= 4) {
			return null;
		}
		byte[] decodeData = new byte[decodeCheck.length - 4];
		System.arraycopy(decodeCheck, 0, decodeData, 0, decodeData.length);
		byte[] hash0 = Sha256Hash.hash(decodeData);
		byte[] hash1 = Sha256Hash.hash(hash0);
		if (hash1[0] == decodeCheck[decodeData.length] &&
				hash1[1] == decodeCheck[decodeData.length + 1] &&
				hash1[2] == decodeCheck[decodeData.length + 2] &&
				hash1[3] == decodeCheck[decodeData.length + 3]) {
			return decodeData;
		}
		return null;
	}

}
