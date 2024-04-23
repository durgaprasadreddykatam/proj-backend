package cs555.devdynamos.projbackend.service;
import cs555.devdynamos.projbackend.Entities.EegData;
import cs555.devdynamos.projbackend.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EegDataService {
//    @Autowired
//    KafkaTemplate <String,Object> kafkaTemplate;
//
//    public boolean updateeegData(EegData data){
//
//        try{
//            kafkaTemplate.send("EegData", String.valueOf(data.getSessionId()),data);
//            return true;
//
//        }catch(Exception e){
//            throw new EtAuthException("Failed to send data to Kafka Server");
//        }
//    }

}
