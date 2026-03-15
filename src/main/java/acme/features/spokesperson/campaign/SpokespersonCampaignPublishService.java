/*
 * SpokespersonCampaignPublishService.java
 *
 * Copyright (C) 2012-2026 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.spokesperson.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.campaigns.Campaign;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignPublishService extends AbstractService<Spokesperson, Campaign> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Campaign						campaign;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.campaign != null && this.campaign.getDraftMode() && this.campaign.getSpokesperson().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.campaign);

		{
			boolean uniqueTicker;
			Campaign existingCampaign;

			existingCampaign = this.repository.findCampaignByTicker(this.campaign.getTicker());
			uniqueTicker = existingCampaign == null || existingCampaign.equals(this.campaign);

			super.state(uniqueTicker, "ticker", "acme.validation.campaign.uniqueticker.message");
		}
		{
			boolean startMomentInFuture;

			startMomentInFuture = MomentHelper.isFuture(this.campaign.getStartMoment());
			super.state(startMomentInFuture, "startMoment", "acme.validation.campaign.startmomentinfuture.message");
		}
		{
			boolean endMomentInFuture;

			endMomentInFuture = MomentHelper.isFuture(this.campaign.getEndMoment());
			super.state(endMomentInFuture, "endMoment", "acme.validation.campaign.endmomentinfuture.message");
		}
		{
			boolean validInterval;

			if (this.campaign.getStartMoment() != null && this.campaign.getEndMoment() != null)
				validInterval = MomentHelper.isAfterOrEqual(this.campaign.getEndMoment(), this.campaign.getStartMoment());
			else
				validInterval = false;

			super.state(validInterval, "*", "acme.validation.campaign.invalidinterval.message");
		}
		{
			boolean hasMilestones;
			hasMilestones = this.repository.existsByCampaignId(this.campaign.getId());

			super.state(hasMilestones, "*", "acme.validation.campaign.hasmilestone.message");
		}
	}

	@Override
	public void execute() {
		this.campaign.setDraftMode(false);
		this.repository.save(this.campaign);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.campaign, //
			"ticker", "name", "description", "startMoment", //
			"endMoment", "moreInfo", "draftMode", "monthsActive", "effort");
	}

}
