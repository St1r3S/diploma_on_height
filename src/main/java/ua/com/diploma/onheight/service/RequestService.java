package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.request.DeliveryMethod;
import ua.com.diploma.onheight.model.request.PaymentMethod;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.model.request.RequestStatus;

import java.util.List;

public interface RequestService extends CrudService<Request, Long> {

    List<Request> findAllByCompanyId(Long companyId);

    List<Request> findAllByCompanyIdAndLastName(Long companyId, String lastName);

    List<Request> findAllByCompanyIdAndEmail(Long companyId, String email);

    List<Request> findAllByCompanyIdAndDeliveryMethod(Long companyId, DeliveryMethod deliveryMethod);

    List<Request> findAllByCompanyIdAndPaymentMethod(Long companyId, PaymentMethod paymentMethod);

    List<Request> findAllByCompanyIdAndCompletionStage_RequestStatus(Long companyId, RequestStatus requestStatus);
}
