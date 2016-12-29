package by.netcracker.shop.utils;

import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.pojo.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
    public PaymentDTO toPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO;
        if (payment == null) {
            return null;
        }
        paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setName(payment.getName());
        paymentDTO.setDescription(payment.getDescription());
        return paymentDTO;
    }

    public Payment toPaymentPOJO(PaymentDTO paymentDTO, Payment payment) {
        if (paymentDTO == null || payment == null)
            return null;
        payment.setName(paymentDTO.getName());
        payment.setDescription(paymentDTO.getDescription());
        return payment;
    }
}
