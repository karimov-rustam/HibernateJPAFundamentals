package edu.hibernate.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bond")
public class Bond extends Investment {


    @Id
    @GeneratedValue
    @Column(name = "BOND_ID")
    private Long bondId;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;

    @Column(name = "MATURITY_DATE")
    private Date maturityDate;

    public Long getBondId() {
        return bondId;
    }

    public void setBondId(Long bondId) {
        this.bondId = bondId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }
}
