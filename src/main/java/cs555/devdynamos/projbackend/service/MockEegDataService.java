package cs555.devdynamos.projbackend.service;

import cs555.devdynamos.projbackend.Entities.*;
import cs555.devdynamos.projbackend.repositories.EegTestLieRepo;
import cs555.devdynamos.projbackend.repositories.EegTestTruthRepo;
import cs555.devdynamos.projbackend.repositories.EegTrainLieRepo;
import cs555.devdynamos.projbackend.repositories.EegTrainTruthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MockEegDataService {
    @Autowired
    EegTestLieRepo eegTestLieRepo;
    @Autowired
    EegTestTruthRepo eegTestTruthRepo;
    @Autowired
    EegTrainLieRepo eegTrainLieRepo;
    @Autowired
    EegTrainTruthRepo eegTrainTruthRepo;


    public List<Object> getEegTestTruth(int userNumber){
        List<Object> list =new ArrayList<>();
        long startNumber=(userNumber-1)*2304+1;
        long endNumber=(userNumber)*2304+1;
        for(long i=startNumber;i<endNumber;i++){
            EegDataTestTruth data= eegTestTruthRepo.findById(i).orElse(null);
            if (data != null) {
                list.add(data);
            }
        }
        return list;
    }

    public List<Object> getEegTrainTruth(int userNumber){
        List<Object> list =new ArrayList<>();
        long startNumber=(userNumber-1)*3840+1;
        long endNumber=(userNumber)*3840+1;
        for(long i=startNumber;i<endNumber;i++){
            EegDataTrainTruth data= eegTrainTruthRepo.findById(i).orElse(null);
            if (data != null) {
                list.add(data);
            }
        }
        return list;
    }
    public List<Object> getEegTestLie(int userNumber){
        List<Object> list =new ArrayList<>();
        long startNumber=(userNumber-1)*2304+1;
        long endNumber=(userNumber)*2304+1;
        for(long i=startNumber;i<endNumber;i++){
            EegDataTestLie data= eegTestLieRepo.findById(i).orElse(null);
            if (data != null) {
                list.add(data);
            }
        }
        return list;
    }

    public List<Object> getEegTrainLie(int userNumber){
        List<Object> list =new ArrayList<>();
        long startNumber=(userNumber-1)*3840+1;
        long endNumber=(userNumber)*3840+1;
        for(long i=startNumber;i<endNumber;i++){
            EegDataTrainLie data= eegTrainLieRepo.findById(i).orElse(null);
            if (data != null) {
                list.add(data);
            }
        }

        return list;
    }

    public Map<String, List<Object>> getData(int number) {

        Map<String, List<Object>> responseData = new HashMap<>();
        List<Object> testLie=getEegTestLie(number);
        List<Object> testTruth =getEegTestTruth(number);
        List<Object> trainLie =getEegTrainLie(number);
        List<Object> trainTruth =getEegTrainTruth(number);
        responseData.put("testLie",testLie);
        responseData.put("testTruth",testTruth);
        responseData.put("trainLie",trainLie);
        responseData.put("trainTruth",trainTruth);

        return responseData;
    }
}
