package uz.brogrammers.eshop.shipping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.shipping.entity.Shipping;
import uz.brogrammers.eshop.shipping.repository.ShippingRepository;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public Shipping save(Shipping shipping){
        return shippingRepository.save(shipping);
    }

}
