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

        System.out.println("START");
        if (this.getStatus().equals("Approved")) {
            System.out.println("seq1");
            ReservationApproved reservationApproved = new ReservationApproved();

            reservationApproved.setReservationDate(this.toString());
            reservationApproved.setReservationId(this.getReservationId());
            reservationApproved.setStatus("Approved");
            reservationApproved.setTrainerId(this.getTrainerId());

            BeanUtils.copyProperties(this, reservationApproved);
            reservationApproved.publishAfterCommit();

        } else if (this.getStatus().equals("Declined")) {
            System.out.println("seq2");
            ReservationDeclined reservationDeclined = new ReservationDeclined();

            reservationDeclined.setReservationDate(this.getReservationDate());
            reservationDeclined.setReservationId(this.getReservationId());
            reservationDeclined.setStatus("Declined");
            reservationDeclined.setTrainerId(this.getTrainerId());

            BeanUtils.copyProperties(this, reservationDeclined);
            reservationDeclined.publishAfterCommit();
        }
    }

    @PostUpdate
    public void onPostUpdate() {

        System.out.println("START2");
        if (this.getStatus().equals("Approved")) {
            System.out.println("seq1");
            ReservationApproved reservationApproved = new ReservationApproved();

            reservationApproved.setReservationDate(this.toString());
            reservationApproved.setReservationId(this.getReservationId());
            reservationApproved.setStatus("Approved");
            reservationApproved.setTrainerId(this.getTrainerId());

            BeanUtils.copyProperties(this, reservationApproved);
            reservationApproved.publishAfterCommit();

        } else if (this.getStatus().equals("Declined")) {
            System.out.println("seq2");
            ReservationDeclined reservationDeclined = new ReservationDeclined();

            reservationDeclined.setReservationDate(this.getReservationDate());
            reservationDeclined.setReservationId(this.getReservationId());
            reservationDeclined.setStatus("Declined");
            reservationDeclined.setTrainerId(this.getTrainerId());

            BeanUtils.copyProperties(this, reservationDeclined);
            reservationDeclined.publishAfterCommit();
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
