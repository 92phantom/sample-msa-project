package api.reserve.msa.service.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CallEnroll {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallEnroll.class);

    @Autowired
    private WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${ext.api.baseurl}")
    private String EXT_BASE_URL;
    @Value("${ext.api.timeout}")
    private long EXT_WAIT_TIMEOUT;

    public boolean doEnrollCheck(String device_id){

        URI uri = UriComponentsBuilder.fromUriString(EXT_BASE_URL + "/exist_check")
                .queryParam("device_id", device_id)
                .build().toUri();

        Mono<String> result = doGetWithDefaultConnectAndReadTimeOut(uri, EXT_WAIT_TIMEOUT);

        AtomicBoolean flag = new AtomicBoolean(false);
        AtomicReference<String> tmp = new AtomicReference<>("INITIAL");

        result
                .doOnError(it -> tmp.set("false"))
                .doOnSuccess(it -> tmp.set(it))
                .subscribe();

        long start = System.currentTimeMillis();
        long end = start + EXT_WAIT_TIMEOUT;
        while(true) {

            if(!tmp.get().equals("INITIAL")){
                if(tmp.get().equals("false")){
                    return false;
                }
                return true;
            }
            // Server Wait Timeout
            if(System.currentTimeMillis() > end) {
                return false;
            }

        }

    }

    public Mono<String> doGetWithDefaultConnectAndReadTimeOut(URI uri, long timeout) {

        webClient = webClientBuilder.build();

        System.out.println("#"+uri);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    LOGGER.error("Error while calling External API {} with status code {}",
                            uri.toString(), clientResponse.statusCode());
                    throw new RuntimeException("Error while calling External API");
                }).bodyToMono(String.class)
                // setting the signal timeout
                .timeout(Duration.ofMillis(timeout))
                .doOnError(error -> LOGGER.error("External API Timeout signal detected", error));
    }



}
