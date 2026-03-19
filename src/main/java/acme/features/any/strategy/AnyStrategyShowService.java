
package acme.features.any.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;

@Service
public class AnyStrategyShowService extends AbstractService<Any, Strategy> {

	@Autowired
	private AnyStrategyRepository	repository;

	private Strategy				strategy;


	@Override
	public void load() {

		int id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {

		boolean authorised = this.strategy != null && !this.strategy.getDraftMode();
		super.getResponse().setAuthorised(authorised);
	}

	@Override
	public void unbind() {

		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "expectedPercentage");

		super.getResponse().addGlobal("fundraiserId", this.strategy.getFundraiser().getId());
	}

}
