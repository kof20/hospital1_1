package hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "surname", nullable = true, length = 45)
    private String surname;
    @Basic
    @Column(name = "dateofbirth", nullable = true, length = 45)
    private String dateOfBirth;
    @Basic
    @Column(name = "telephone", nullable = true, length = 50)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "id_doctor", referencedColumnName = "id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "id_card", referencedColumnName = "id")
    private Card card;

    public Patient(Patient patient) {
    }

    public Patient() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctorByIdDoctor) {
        this.doctor = doctorByIdDoctor;
    }


    public Card getCard() {
        return card;
    }

    public void setCard(Card cardByIdCard) {
        this.card = cardByIdCard;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", telephone='" + telephone +
                '}';
    }
}
