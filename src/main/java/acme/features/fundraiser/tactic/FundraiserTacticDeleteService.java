
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.strategy.Tactic;
import acme.entities.strategy.TacticKind;
import acme.realms.Fundraiser;

public class FundraiserTacticDeleteService extends AbstractService<Fundraiser, Tactic> {

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

	}

	@Override
	public void execute() {
		this.repository.delete(this.tactic);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices choices;

		choices = SelectChoices.from(TacticKind.class, this.tactic.getTacticKind());

		tuple = super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "tacticKind");

		tuple.put("tacticKind", choices);
		tuple.put("strategyId", this.tactic.getStrategy().getId());
		tuple.put("published", !this.tactic.getStrategy().getDraftMode());

		super.getResponse().addData(tuple);
	}

}
