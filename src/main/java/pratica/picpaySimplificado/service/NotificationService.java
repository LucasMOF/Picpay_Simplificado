package pratica.picpaySimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pratica.picpaySimplificado.domain.User;
import pratica.picpaySimplificado.dtos.AuthorizationDTO;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        AuthorizationDTO authorization = new AuthorizationDTO(email, message);

        ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", authorization, String.class);

        if (!(response.getStatusCode() == HttpStatus.OK)){
            System.out.println("Erro ao enviar notificação");
            throw new Exception("Serviço de notifação não está funcionando no momento");
        }
    }

}
