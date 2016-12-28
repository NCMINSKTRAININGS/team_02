package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    MessageSource messageSource;

    //    Find All
    @RequestMapping(value = "/payment/", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDTO>> listAllPayments(){
        List<PaymentDTO> payments = null;
        try {
            payments = paymentService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(payments==null||payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
     }

    // Get by Id
    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable("id") long id){
        PaymentDTO payment = null;
        try {
             payment =paymentService.getById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
            if (payment==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    //Create
    @RequestMapping(value = "/payment/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPayment(@RequestBody PaymentDTO paymentDTO, UriComponentsBuilder ucBuilder){
        try {
            paymentService.insert(paymentDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/payment/{id}").buildAndExpand(paymentDTO.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //update
    @RequestMapping(value = "/payment/{id}",method = RequestMethod.PUT)
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable ("id") long id, @RequestBody PaymentDTO payment){
        PaymentDTO currentPayment = null;
        try {
            currentPayment = paymentService.getById(id);
            currentPayment.setId(payment.getId());
            currentPayment.setName(payment.getName());
            currentPayment.setDescription(payment.getDescription());
            paymentService.insert(currentPayment);
        }catch (ServiceException e){
// TODO: logger etc.
        }
        return new ResponseEntity<>(currentPayment, HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<PaymentDTO> deletePayment(@PathVariable("id") long id){
        PaymentDTO payment;
        try {
            payment =paymentService.getById(id);
            if (payment==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            paymentService.deleteById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
