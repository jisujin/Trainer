package TrainerReservation;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 public class TrainerController {

   @Autowired
   TrainerRepository trainerRepository;

    @RequestMapping(method= RequestMethod.POST, path="/postTrainers")
    public void postReservation(@RequestBody Trainer trainer) {

        Trainer oneTrainer = new Trainer();

        System.out.println("@@@@ Test 1" + trainer.getReservationId());
        oneTrainer.setTrainerId(trainer.getTrainerId());
        oneTrainer.setStatus(trainer.getStatus());
        oneTrainer.setReservationDate(trainer.getReservationDate());
        oneTrainer.setReservationId(trainer.getReservationId());

        trainerRepository.save(oneTrainer);

    }

   @RequestMapping(method=RequestMethod.PATCH, path="/trainers/check")
   public void checkReservation(@RequestParam(value="reservationId", required=false, defaultValue="0") Long reservationId) {

    Trainer pickTrainer = trainerRepository.findByReservationId(reservationId);
    int random1 = (int)(Math.random() * 2);

       if(pickTrainer.getReservationId() == null){
           Long val = 1L;
          pickTrainer.setReservationId(val);
          pickTrainer.setReservationDate("20202020");
          pickTrainer.setTrainerId(1001L);
       }
    System.out.println("### test " + reservationId);

     if (random1 == 0) {
       pickTrainer.setStatus("Approved");
     } else {
       pickTrainer.setStatus("Declined");
     }
      trainerRepository.save(pickTrainer);

    }


 }
