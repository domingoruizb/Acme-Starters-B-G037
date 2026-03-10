
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.sponsors.Sponsorship;
import acme.entities.sponsors.SponsorshipRepository;

@Validator
public class SponsorshipValidator extends AbstractValidator<ValidSponsorship, Sponsorship> {

	@Autowired
	private SponsorshipRepository repository;


	@Override
	protected void initialise(final ValidSponsorship annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Sponsorship sponsorship, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		{
			boolean uniqueTicker;
			Sponsorship existingSponsorship;

			existingSponsorship = this.repository.findSponsorshipByTicker(sponsorship.getTicker());
			uniqueTicker = existingSponsorship == null || existingSponsorship.equals(sponsorship);

			super.state(context, uniqueTicker, "ticker", "acme.validation.sponsorship.uniqueticker.message");
		}
		if (sponsorship.getDraftMode().equals(Boolean.FALSE)) {
			{
				boolean validInterval;

				if (sponsorship.getStartMoment() != null && sponsorship.getEndMoment() != null)
					validInterval = MomentHelper.isAfterOrEqual(sponsorship.getEndMoment(), sponsorship.getStartMoment());
				else
					validInterval = false;

				super.state(context, validInterval, "*", "acme.validation.sponsorship.invalidinterval.message");
			}
			{
				boolean hasDonations;
				hasDonations = this.repository.existsBySponsorshipId(sponsorship.getId());

				super.state(context, hasDonations, "*", "acme.validation.sponsorship.hasdonation.message");
			}
		}

		result = !super.hasErrors(context);

		return result;
	}

}
