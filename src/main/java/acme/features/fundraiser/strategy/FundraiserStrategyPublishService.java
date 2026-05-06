
package acme.features.fundraiser.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.strategy.Strategy;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyPublishService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.strategy != null && this.strategy.getDraftMode() && this.strategy.getFundraiser().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {

		super.validateObject(this.strategy);

		{
			boolean uniqueTicker;
			Strategy existingStrategy;

			existingStrategy = this.repository.findStrategybyTicker(this.strategy.getTicker());
			uniqueTicker = existingStrategy == null || existingStrategy.equals(this.strategy);

			super.state(uniqueTicker, "ticker", "acme.validation.strategy.uniqueticker.message");
		}

		{
			boolean startMomentInFuture;

			startMomentInFuture = this.strategy.getStartMoment() != null && MomentHelper.isFuture(this.strategy.getStartMoment());
			super.state(startMomentInFuture, "startMoment", "acme.validation.strategy.startmomentinfuture.message");
		}

		{
			boolean endMomentInFuture;

			endMomentInFuture = this.strategy.getEndMoment() != null && MomentHelper.isFuture(this.strategy.getEndMoment());
			super.state(endMomentInFuture, "endMoment", "acme.validation.strategy.endmomentinfuture.message");
		}

		{
			boolean validInterval;

			validInterval = this.strategy.getStartMoment() != null && this.strategy.getEndMoment() != null && MomentHelper.isAfter(this.strategy.getEndMoment(), this.strategy.getStartMoment());

			super.state(validInterval, "endMoment", "acme.validation.strategy.invalidinterval.message");
		}

		{
			boolean hasTactics;

			hasTactics = this.repository.countTacticsByStrategyId(this.strategy.getId()) > 0;

			super.state(hasTactics, "*", "acme.validation.strategy.hastactic.message");
		}
	}
	@Override
	public void execute() {
		this.strategy.setDraftMode(false);
		this.repository.save(this.strategy);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "expectedPercentage");
	}

}
