package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "likes")
@NamedQueries({
    @NamedQuery(
            name = "getAllLikes",//いいねした従業員を取得
            query = "SELECT l FROM Likes AS l WHERE l.report = :report ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getAllLikesCount",//いいねした従業員の総数を取得
            query = "SELECT COUNT(l) FROM Likes AS l WHERE l.report = :report"
            ),
    @NamedQuery(
            name = "checkLikedAlready",//ログイン中の従業員が訪れているページをすでにいいねしているかを取得
            query = "SELECT COUNT(l) FROM Likes AS l WHERE l.employee = :employee AND l.report = :report"
            )
})
@Entity
public class Likes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "created_at" , nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
