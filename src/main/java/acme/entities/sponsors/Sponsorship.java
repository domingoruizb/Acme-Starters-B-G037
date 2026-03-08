
package acme.entities.sponsors;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidUrl;
import acme.client.helpers.MomentHelper;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import acme.constraints.ValidTicker;
import acme.realms.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsorship extends AbstractEntity {

	private static final long		serialVersionUID	= 1L;

	@Mandatory
	@ValidTicker
	@Column(unique = true)
	private String					ticker;

	@Mandatory
	@ValidHeader
	@Column
	private String					name;

	@Mandatory
	@ValidText
	@Column
	private String					description;

	@Mandatory
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date					startMoment;

	@Mandatory
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date					endMoment;

	@Optional
	@ValidUrl
	@Column
	private String					moreInfo;

	@Mandatory
	// HINT: @Valid by default.
	@Column
	private Boolean					draftMode;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Sponsor					sponsor;

	@Mandatory
	@Valid
	@Transient
	@Autowired
	private SponsorshipRepository	repository;


	@Mandatory
	// @Valid // HINT: Eclipse's validator forbids this annotation here.
	@Transient
	public Double getMonthsActive() {
		if (this.startMoment == null || this.endMoment == null)
			return null;

		return MomentHelper.computeDifference(this.startMoment, this.endMoment, ChronoUnit.MONTHS);
	}

	@Mandatory
	@Transient
	public Money getTotalMoney() {
		Double total = this.repository.computeTotalMoney(this.getId());
		Money money = new Money();
		money.setAmount(total == null ? 0 : total);
		money.setCurrency("EUR");
		return money;
	}

}
