package TrainerReservation;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="Trainer_table")
public class Trainer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long trainerId;
    private String status;
    private String reservationDate;
    private Long reservationId;

    @PrePersist
    public void onPrePersist() {

        int random1 = (int)(Math.random() * 2);

        System.out.println("Start.");



        if(this.getStatus().equals("Requested")) {
            if (random1 == 0) {

                try {
                    Thread.currentThread().sleep((long) (1500 + Math.random() * 220));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ReservationApproved reservationApproved = new ReservationApproved();
                System.out.println("Test1: " + this.getStatus());
                System.out.println("TEST1: " + this.getReservationDate());
                System.out.println("TEST1: " + this.getTrainerId());

                reservationApproved.setReservationDate(this.getReservationDate());
                reservationApproved.setReservationId(this.getReservationId());
                reservationApproved.setStatus("Approved");
                reservationApproved.setTrainerId(this.getTrainerId());


                BeanUtils.copyProperties(this, reservationApproved);
                reservationApproved.publishAfterCommit();
            } else {

                try {
                    Thread.currentThread().sleep((long) (1500 + Math.random() * 220));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Test2: " + this.getStatus());
                System.out.println("TEST2: " + this.getReservationDate());
                System.out.println("TEST2: " + this.getTrainerId());

                ReservationDeclined reservationDeclined = new ReservationDeclined();

                reservationDeclined.setReservationDate(this.getReservationDate());
                reservationDeclined.setReservationId(this.getReservationId());
                reservationDeclined.setStatus("Declined");
                reservationDeclined.setTrainerId(this.getTrainerId());

                BeanUtils.copyProperties(this, reservationDeclined);
                reservationDeclined.publishAfterCommit();
            }
        }
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }




}
