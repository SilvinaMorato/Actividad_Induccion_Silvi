
import com.mercadopago.*;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.preference.*;
import spark.*;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.HashMap;
import java.util.Map;


public class Main {

    private static final  String ACCESS_TOKEN = "TEST-1078979896862798-031112-3756c6b8ce46b1c70da6d4ba60b8050a-405876008";
    private static final  String CLIENT_SECRET="AoK0C0M8XwGDhQ7YZrUJp8buZ5QrxoZw";
    private static final String CLIENT_ID= "1078979896862798";
    public static void main(String[] args) {
        try {
            MercadoPago.SDK.setClientSecret(CLIENT_SECRET);
            MercadoPago.SDK.setClientId(CLIENT_ID);

        } catch (MPException e) {
            e.printStackTrace();
        }

        Spark.get("/form", Main::render, new ThymeleafTemplateEngine());

        Spark.post("/pagarPreferencia", Main::pagarPreferencia, JsonUtils.json ());

        Spark.post("/procesar-pago", Main::procesarPago, JsonUtils.json ());

        Spark.post("/procesar-pago-2", Main::procesarPago2, JsonUtils.json ());

    }


    public static ModelAndView render(Request req, Response res) throws  MPException{

        //Create preference

        Preference preference = new Preference();
        //Create  an item object

        Item item= new Item();
        item.setId("23")
                .setTitle("Prueba Pago")
                .setQuantity(4)
                .setDescription("ARS")
                .setUnitPrice((float) 84.74);

        //Create a Payer

        Payer payer = new Payer();
        payer.setEmail("test_user_66550278@testuser.com")
                .setName("Jose")
                .setSurname("Lopez")
                .setDateCreated("2018-06-02T12:58:41.425-04:00")
                .setPhone(new Phone()
                        .setNumber("3434462020")
                        .setAreaCode(" "))
                .setAddress(new Address()
                        .setStreetName("Calle prueba ")
                        .setStreetNumber(234))
                .setIdentification( new Identification()
                        .setNumber("33271334")
                        .setType("DNI"));



        //Setting preference properties

        preference.setPayer(payer);
        preference.appendItem(item);



        try {
            preference.save();
        } catch (MPException e) {
            e.printStackTrace();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("name", preference.getPayer().getName());
        params.put("surname",preference.getPayer().getSurname());
        params.put("DNI", preference.getPayer().getIdentification().getNumber());
        params.put("precio", preference.getItems().get(0).getUnitPrice());
        params.put("titulo", preference.getItems().get(0).getTitle());
        params.put("correo",preference.getPayer().getEmail());
        params.put("initPoint", preference.getInitPoint());
        params.put ("idPreference", preference.getId () );

        return new ModelAndView(params, "form");
    }


    private static Object pagarPreferencia(Request req, Response res) throws MPException  {

       Payment payment = new Payment();

       try {
           MercadoPago.SDK.setAccessToken ( ACCESS_TOKEN );

           payment.setTransactionAmount(Float.parseFloat(req.queryParams("amount")))
                   .setToken(req.queryParams ("token"))
                  .setDescription("Algo muy codiciado")
                   .setInstallments(Integer.parseInt (req.queryParams ("installments")))
                   .setPaymentMethodId(req.queryParams("paymentMethodId"))
                  .setPayer(new com.mercadopago.resources.datastructures.payment.Payer()
                         .setEmail(req.queryParams("email")));

           payment.save();
       } catch(Exception e) {
           e.printStackTrace ();
       }

       System.out.println("Estado: " + payment.getStatus());

       return payment;
    }


    private static Object procesarPago(Request req, Response res) throws  MPException{
        Payment payment = new Payment();

        String token = req.queryParams ("token");
        String payment_method_id = req.queryParams ("payment_method_id");
        Integer installments = Integer.parseInt (req.queryParams ("installments"));
        Integer issuer_id = (Integer.parseInt (req.queryParams ("issuer_id")));
        System.out.println ("token" +token );
        System.out.println ("payment_method_id" +payment_method_id );
        System.out.println ("installments" +installments );
        System.out.println ("issuer_id" +issuer_id );

        MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
//...
        payment.setTransactionAmount( Float.valueOf ( 345 ) )
                .setToken(req.queryParams ("token"))
                .setDescription("prueba procesar pago tarjeta")
                .setInstallments(Integer.parseInt (req.queryParams ("installments")))
                .setPaymentMethodId(req.queryParams ("payment_method_id"))
                .setIssuerId( String.valueOf ( Integer.parseInt (req.queryParams ("issuer_id")) ) )
                .setPayer(new com.mercadopago.resources.datastructures.payment.Payer()
                        .setEmail("test_user_66550278@testuser.com"));
// Guarda y postea el pago
        payment.save();
//...
// Imprime el estado del pago
        System.out.println("Estado del pago: "+payment.getStatus());
    return payment;
    }


    private static Object procesarPago2 (Request req, Response res){

        System.out.println ( "Back_url:" + req.queryParams ( "back_url" ));
        System.out.println ( "Merchant_order_id:" + req.queryParams ( "merchant_order_id" ));
        System.out.println ( "Payment id:" + req.queryParams ( "payment_id" ));
        System.out.println ( "Payment_status:" + req.queryParams ( "payment_status" ));
        System.out.println ( "preference_id:" + req.queryParams ( "preference_id" ));

        return req.queryParams ("payment_status");
    }

}
