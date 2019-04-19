package controllers;



import com.mercadopago.resources.Preference;


import controller.PreferenceController;

import dto.ItemDTO;
import dto.PayerDTO;
import dto.PreferenceDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import static  org.mockito.Mockito.*;



import service.PreferenceService;

import util.ConfigAccess;

import util.ValidationException;



import java.util.Optional;



@RunWith(MockitoJUnitRunner.class)
public class PreferenceControllerMockTest {

    PreferenceService preferenceService = PreferenceService.getInstance();

    @Before
    public void setUp() throws Exception{
     ConfigAccess.accessConfig();
      }

    /**
     *
     verify that methods savePreference- params ok
     * @throws Exception
     */
    @Test
    public void testPreferenceCreatePMock() throws Exception {

        PreferenceDTO preferenceDTO = Mockito.mock(PreferenceDTO.class);
        ItemDTO itemDTO = Mockito.mock(ItemDTO.class);
        when(itemDTO.getIdProduct()).thenReturn(20);
        when(itemDTO.getQuantity()).thenReturn(1);
        when(itemDTO.getUnitPrice()).thenReturn((float) 123.4);
        when(itemDTO.getTitulo()).thenReturn("Prueba test Mock");
        when(preferenceDTO.getItem()).thenReturn(itemDTO);

        PayerDTO payerDTO = Mockito.mock(PayerDTO.class);
        when(payerDTO.getEmail()).thenReturn("test@test.com");
        when(payerDTO.getName()).thenReturn("Pablo");
        when(payerDTO.getSurname()).thenReturn("Lopez");
        when(preferenceDTO.getPayer()).thenReturn(payerDTO);

        Preference preference =  preferenceService.savePreference(preferenceDTO);

        verify(itemDTO).getQuantity();
        verify(itemDTO).getUnitPrice();
        verify(itemDTO).getTitulo();
        verify(payerDTO).getEmail();
        verify(payerDTO).getName();
        verify(payerDTO).getSurname();

        Assert.assertEquals(preference.getPayer().getEmail(),"test@test.com" );
        Assert.assertEquals(preference.getPayer().getName(),"Pablo" );
        Assert.assertEquals(Optional.ofNullable(preference.getItems().get(0).getQuantity()), Optional.ofNullable(1) );
    }

    /**
     *
     verify that the exception skips when entering because it has no data
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testMethodsPreferenceMock() throws Exception {
        PreferenceDTO preferenceDTO = Mockito.mock(PreferenceDTO.class);
        PreferenceController.createPreferenceValidate(preferenceDTO);

    }



    /**
     * verify that the exception skipd when Init_point is null.
     */
   /* @Test(expected = MPException.class)
    public void testMethodsPreferenceNullMock() throws Exception {
        PreferenceDTO preferenceDTO = INSTANCE.mapTo(HelperTest.getMockPreferenceOK(), PreferenceDTO.class);
        Preference preference = Mockito.mock(Preference.class);
        when(preferenceService.savePreference(preferenceDTO)).thenReturn(preference);
        when(preference.getInitPoint()).thenReturn(null);
        PreferenceController.createPreferenceValidate(preferenceDTO);
    }*/




}























