
package acme.strategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.ValidScore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tactic extends AbstractEntity {

	@Mandatory
	// @ValidHeader
	@Column
	private String		name;

	@Mandatory
	// @ValidText
	@Column
	private String		notes;

	@Mandatory
	@ValidScore
	@Column
	private Double		expectedPercentage;

	@Mandatory
	@Valid
	@Column
	private TacticKind	tacticKind;

	@ManyToOne(optional = false)
	private Strategy	strategy;

}
