package by.netcracker.shop.utils;

import by.netcracker.shop.dto.PaymentDto;
import by.netcracker.shop.pojo.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter  implements Converter<Payment,PaymentDto> {
    @Override
    public PaymentDto convertToFront(Payment payment) {
        PaymentDto paymentDto= new PaymentDto();
        paymentDto.setId(payment.getId());
        paymentDto.setName(payment.getName());
        paymentDto.setDescription(payment.getDescription());

        return paymentDto;
    }

    @Override
    public Payment converToLocal(PaymentDto paymentDto, Payment payment) {
        payment.setName(paymentDto.getName());
        payment.setDescription(paymentDto.getDescription());
        return payment;
    }
}
