
package acme.constraints;

import java.util.Date;

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

		boolean result;

		{
			boolean uniqueStrategy;
			Strategy existingStrategy;

			existingStrategy = this.repository.findStrategyByTicker(strategy.getTicker());
			uniqueStrategy = existingStrategy == null || existingStrategy.equals(strategy);

			super.state(context, uniqueStrategy, "ticker", "acme.validation.strategy.duplicated-ticker.message");
		}

		if (strategy.getDraftMode().equals(Boolean.FALSE)) {
			boolean validTime;
			Date start;
			Date end;

			start = strategy.getStartMoment();
			end = strategy.getEndMoment();

			if (start != null && end != null)
				validTime = MomentHelper.isAfter(end, start);
			else
				validTime = false;

			super.state(context, validTime, "startMoment", "acme.validation.strategy.dates.error");
		}

		result = !super.hasErrors(context);

		return result;
	}

}
