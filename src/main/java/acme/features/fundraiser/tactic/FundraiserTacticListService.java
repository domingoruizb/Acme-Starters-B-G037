
package acme.features.fundraiser.tactic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticListService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	FundraiserTacticRepository	repository;

	private Strategy			strat;
	private Collection<Tactic>	tactics;


	@Override
	public void load() {
		int strategyId;
		strategyId = super.getRequest().getData("strategyId", int.class);
		this.strat = this.repository.findStrategyById(strategyId);
		this.tactics = this.repository.findTacticsByStrategyId(strategyId);
	}

	@Override
	public void authorise() {
		boolean status;
		status = this.strat != null && //
			(this.strat.getFundraiser().isPrincipal() || !this.strat.getDraftMode());
		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		boolean showCreate;

		super.unbindObjects(this.tactics, "name", "expectedPercentage", "tacticKind");
		showCreate = this.strat.getDraftMode() && this.strat.getFundraiser().isPrincipal();
		super.unbindGlobal("strategyId", this.strat.getId());
		super.unbindGlobal("showCreate", showCreate);
	}

}
