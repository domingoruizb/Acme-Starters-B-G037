
package acme.entities.sponsorships;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Moment;
import acme.client.components.datatypes.Money;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.client.components.validation.ValidUrl;
import acme.entities.donations.Donation;
import acme.realms.Sponsor;
import lombok.Getter;
import lombok.Setter;

// TODO: Implement @Valid
@Entity
@Getter
@Setter
public class Sponsorship extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	// @ValidTicker
	@Column(unique = true)
	private String				ticker;

	@Mandatory
	// @ValidHeader
	@Column
	private String				name;

	@Mandatory
	// @ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	// @Temporal(TemporalType.TIMESTAMP)
	private Moment				startMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	// @Temporal(TemporalType.TIMESTAMP)
	private Moment				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	@Mandatory
	@Valid
	@Column
	private Boolean				draftMode;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;

	@Mandatory
	@Valid
	@OneToMany
	private List<Donation>		donations;


	@Transient
	public Double getMonthsActive() {
		return null;
	}

	@Transient
	public Money getTotalMoney() {
		return null;
	}

}
