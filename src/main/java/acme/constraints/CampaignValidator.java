/*
 * CampaignValidator.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.CampaignRepository;

@Validator
public class CampaignValidator extends AbstractValidator<ValidCampaign, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private CampaignRepository repository;

	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidCampaign annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Campaign campaign, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		{
			boolean uniqueTicker;
			Campaign existingCampaign;

			existingCampaign = this.repository.findCampaignByTicker(campaign.getTicker());
			uniqueTicker = existingCampaign == null || existingCampaign.equals(campaign);

			super.state(context, uniqueTicker, "ticker", "acme.validation.campaign.uniqueticker.message");
		}
		if (campaign.getDraftMode().equals(Boolean.FALSE)) {
			{
				boolean validInterval;

				if (campaign.getStartMoment() != null && campaign.getEndMoment() != null)
					validInterval = MomentHelper.isAfter(campaign.getEndMoment(), campaign.getStartMoment());
				else
					validInterval = false;

				super.state(context, validInterval, "*", "acme.validation.campaign.invalidinterval.message");
			}
			{
				boolean hasMilestones;
				hasMilestones = this.repository.existsByCampaignId(campaign.getId());

				super.state(context, hasMilestones, "*", "acme.validation.campaign.hasmilestone.message");
			}
		}

		result = !super.hasErrors(context);

		return result;
	}

}
