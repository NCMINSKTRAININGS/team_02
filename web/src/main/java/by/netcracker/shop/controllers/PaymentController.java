package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.PaymentDto;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    MessageSource messageSource;

    //    Find All
    @RequestMapping(value = "/payment/", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDto>> listAllPayments(){
        List<PaymentDto> payments = null;
        try {
            payments = paymentService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(payments.isEmpty()){
            return new ResponseEntity<List<PaymentDto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PaymentDto>>(payments, HttpStatus.OK);
     }

    // Get by Id
    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDto> getPayment(@PathVariable("id") long id){
        try {
            PaymentDto payment =paymentService.getById(id);
            if (payment==null){
                return new ResponseEntity<PaymentDto>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<PaymentDto>(payment,HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<PaymentDto>(HttpStatus.METHOD_FAILURE);
    }
}
