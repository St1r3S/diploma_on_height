package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.request.DeliveryMethod;
import ua.com.diploma.onheight.model.request.PaymentMethod;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.model.request.RequestStatus;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByCompanyIdOrderByIdDesc(Long companyId);

    List<Request> findAllByCompanyIdAndLastName(Long companyId, String lastName);

    List<Request> findAllByCompanyIdAndEmail(Long companyId, String email);

    List<Request> findAllByCompanyIdAndDeliveryMethod(Long companyId, DeliveryMethod deliveryMethod);

    List<Request> findAllByCompanyIdAndPaymentMethod(Long companyId, PaymentMethod paymentMethod);

    List<Request> findAllByCompanyIdAndCompletionStage_RequestStatus(Long companyId, RequestStatus requestStatus);
}
