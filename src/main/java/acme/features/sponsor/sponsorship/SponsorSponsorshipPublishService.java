
package acme.features.sponsor.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.sponsors.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipPublishService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Sponsorship						sponsorship;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.sponsorship = this.repository.findSponsorshipById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.sponsorship != null && this.sponsorship.getDraftMode() && this.sponsorship.getSponsor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.sponsorship);
		{
			boolean uniqueTicker;
			Sponsorship existingSponsorship;

			existingSponsorship = this.repository.findSponsorshipByTicker(this.sponsorship.getTicker());
			uniqueTicker = existingSponsorship == null || existingSponsorship.equals(this.sponsorship);

			super.state(uniqueTicker, "ticker", "acme.validation.sponsorship.uniqueticker.message");
		}
		{
			boolean startMomentInFuture;

			startMomentInFuture = MomentHelper.isFuture(this.sponsorship.getStartMoment());
			super.state(startMomentInFuture, "startMoment", "acme.validation.sponsorship.startmomentinfuture.message");
		}
		{
			boolean endMomentInFuture;

			endMomentInFuture = MomentHelper.isFuture(this.sponsorship.getEndMoment());
			super.state(endMomentInFuture, "endMoment", "acme.validation.sponsorship.endmomentinfuture.message");
		}
		{
			boolean validInterval;

			if (this.sponsorship.getStartMoment() != null && this.sponsorship.getEndMoment() != null)
				validInterval = MomentHelper.isAfterOrEqual(this.sponsorship.getEndMoment(), this.sponsorship.getStartMoment());
			else
				validInterval = false;

			super.state(validInterval, "*", "acme.validation.sponsorship.invalidinterval.message");
		}
		{
			boolean hasDonations;
			hasDonations = this.repository.existsBySponsorshipId(this.sponsorship.getId());

			super.state(hasDonations, "*", "acme.validation.sponsorship.hasdonation.message");
		}
	}

	@Override
	public void execute() {
		this.sponsorship.setDraftMode(false);
		this.repository.save(this.sponsorship);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.sponsorship, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "totalMoney");
	}
}
