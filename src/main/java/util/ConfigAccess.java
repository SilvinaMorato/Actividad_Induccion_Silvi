package util;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;

public class ConfigAccess   {

    private static final  String ACCESS_TOKEN = "TEST-1078979896862798-031112-3756c6b8ce46b1c70da6d4ba60b8050a-405876008";
    private static final  String CLIENT_SECRET="AoK0C0M8XwGDhQ7YZrUJp8buZ5QrxoZw";
    private static final String CLIENT_ID= "1078979896862798";

    public static void  accessConfig()throws MPException {
        MercadoPago.SDK.setClientSecret(CLIENT_SECRET);
        MercadoPago.SDK.setClientId(CLIENT_ID);

    }

}
