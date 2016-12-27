package by.netcracker.shop.utils;

import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.pojo.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter  implements Converter<Payment,PaymentDTO> {
    @Override
    public PaymentDTO convertToFront(Payment payment) {
        PaymentDTO paymentDTO;
        if (payment==null){
             return null;
        }else {
            paymentDTO = new PaymentDTO();
            paymentDTO.setId(payment.getId());
            paymentDTO.setName(payment.getName());
            paymentDTO.setDescription(payment.getDescription());
            return paymentDTO;
        }
    }

    @Override
    public Payment convertToLocal(PaymentDTO paymentDTO, Payment payment) {
        payment.setName(paymentDTO.getName());
        payment.setDescription(paymentDTO.getDescription());
        return payment;
    }
}
