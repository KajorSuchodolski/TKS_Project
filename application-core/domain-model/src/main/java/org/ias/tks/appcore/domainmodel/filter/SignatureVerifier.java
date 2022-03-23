package org.ias.tks.appcore.domainmodel.filter;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.ias.tks.appcore.domainmodel.model.Singable;

import java.text.ParseException;

public class SignatureVerifier {

    private final static String SECRET = "51655468576D5A7134743777217A25432A46294A404E635266556A586E327235";

    public static String calculateEntitySignature(Singable signableEntity) {
        try {
            JWSSigner signer = new MACSigner(SECRET);
            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(signableEntity.getSingablePayload()));
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            return "ETag failure";
        }
    }

    public static boolean validateEntitySignature(String tagValue) {
        try {
            JWSObject jwsObject = JWSObject.parse(tagValue);
            JWSVerifier jwsVerifier = new MACVerifier(SECRET);

            return jwsObject.verify(jwsVerifier);
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyEntityIntegrity(String tagValue, Singable signableEntity) {
        try {
            final String ifMatchHeader = JWSObject.parse(tagValue).getPayload().toString();
            final String payload = signableEntity.getSingablePayload();
            return validateEntitySignature(tagValue) && ifMatchHeader.equals(payload);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}