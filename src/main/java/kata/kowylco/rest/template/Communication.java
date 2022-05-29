package kata.kowylco.rest.template;

import kata.kowylco.rest.template.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    private final String URL = "http://94.198.50.185:7081/api/users";

    HttpHeaders headers = new HttpHeaders();

    private final RestTemplate restTemplate;

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null
                , new ParameterizedTypeReference<>() {
                });

        String cookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        headers.add("Cookie", cookie);
        return responseEntity.getBody();
    }

    public String saveUser(User user) {
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity
                , new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public String editUser(User user) {
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity
                , new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public String deleteUser(User user) {
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + user.getId(), HttpMethod.DELETE, requestEntity
                , new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }
}
