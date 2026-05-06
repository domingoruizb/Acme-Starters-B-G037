
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.MomentHelper;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.StrategyRepository;

public class StrategyValidator extends AbstractValidator<ValidStrategy, Strategy> {

	@Autowired
	private StrategyRepository repository;


	@Override
	protected void initialise(final ValidStrategy annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Strategy strategy, final ConstraintValidatorContext context) {
		assert context != null;

		if (strategy == null)
			return true;

		boolean result;

		{
			boolean uniqueStrategy;
			Strategy existingStrategy;

			existingStrategy = this.repository.findStrategyByTicker(strategy.getTicker());
			uniqueStrategy = existingStrategy == null || existingStrategy.equals(strategy);

			super.state(context, uniqueStrategy, "ticker", "acme.validation.strategy.uniqueticker.message");
		}

		if (Boolean.FALSE.equals(strategy.getDraftMode())) {
			{
				boolean validInterval;

				validInterval = strategy.getStartMoment() != null && strategy.getEndMoment() != null && MomentHelper.isAfter(strategy.getEndMoment(), strategy.getStartMoment());

				super.state(context, validInterval, "endMoment", "acme.validation.strategy.invalidinterval.message");
			}
			{
				boolean hasTactics;
				hasTactics = this.repository.countTacticsByStrategy(strategy.getId()) > 0;

				super.state(context, hasTactics, "*", "acme.validation.strategy.hastactic.message");
			}
		}
		result = !super.hasErrors(context);

		return result;
	}
}
