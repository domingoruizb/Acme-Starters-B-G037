
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.strategy.Tactic;
import acme.entities.strategy.TacticKind;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticUpdateService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private Tactic						tactic;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);

		this.tactic = this.repository.findTacticById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.tactic.getStrategy() != null && //
			this.tactic.getStrategy().getFundraiser().isPrincipal() && //
			this.tactic.getStrategy().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.tactic, "name", "notes", "expectedPercentage", "tacticKind");
	}

	@Override
	public void validate() {
		super.validateObject(this.tactic);
		;
	}

	@Override
	public void execute() {
		this.repository.save(this.tactic);
	}

	@Override
	public void unbind() {
		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(TacticKind.class, this.tactic.getTacticKind());

		tuple = super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "tacticKind");
		tuple.put("strategyId", super.getRequest().getData("strategyId", int.class));
		tuple.put("draftMode", this.tactic.getStrategy().getDraftMode());
		tuple.put("tacticKinds", choices);
	}

}
