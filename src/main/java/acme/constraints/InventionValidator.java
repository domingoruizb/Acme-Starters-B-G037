
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionRepository;

@Validator
public class InventionValidator extends AbstractValidator<ValidInvention, Invention> {

	@Autowired
	private InventionRepository repository;


	@Override
	protected void initialise(final ValidInvention annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Invention invention, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		{
			boolean uniqueTicker;
			Invention existingSponsorship;

			existingSponsorship = this.repository.findInventionByTicker(invention.getTicker());
			uniqueTicker = existingSponsorship == null || existingSponsorship.equals(invention);

			super.state(context, uniqueTicker, "ticker", "acme.validation.invention.uniqueticker.message");
		}
		if (invention.getDraftMode().equals(Boolean.FALSE)) {
			{
				boolean validInterval;

				if (invention.getStartMoment() != null && invention.getEndMoment() != null)
					validInterval = MomentHelper.isAfterOrEqual(invention.getEndMoment(), invention.getStartMoment());
				else
					validInterval = false;

				super.state(context, validInterval, "*", "acme.validation.invention.invalidinterval.message");
			}
			{
				boolean hasParts;
				hasParts = this.repository.existsByInventionId(invention.getId());

				super.state(context, hasParts, "*", "acme.validation.invention.haspart.message");
			}
		}

		result = !super.hasErrors(context);

		return result;
	}

}
