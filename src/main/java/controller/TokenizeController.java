package controller;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Payment;
import dto.PaymentDTO;
import spark.*;
import util.ConfigAccess;
import util.Json;


public class TokenizeController {

public static Payment payment= new Payment();
public static PaymentDTO paymentDTO;

    public static Object paymentPreference(Request req, Response res) throws Exception {


       MercadoPago.SDK.setAccessToken (ConfigAccess.getAccessToken());

        PaymentDTO paymentDTO = Json.INSTANCE.mapTo(req.body(), PaymentDTO.class);
            System.out.println(paymentDTO);


//                    .setToken(req.queryParams ("token"))
//                    .setDescription("Algo muy codiciado")
//                    .setInstallments(Integer.parseInt (req.queryParams ("installments")))
//                    .setPaymentMethodId(req.queryParams("paymentMethodId"))
//                    .setPayer(new com.mercadopago.resources.datastructures.payment.Payer()
//                            .setEmail(req.queryParams("email")));
//
//            payment.save();
//
//
//        System.out.println("Estado: " + payment.getStatus());

        return payment;
    }



}