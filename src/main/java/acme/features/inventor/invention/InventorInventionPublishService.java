
package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Service
public class InventorInventionPublishService extends AbstractService<Inventor, Invention> {

	@Autowired
	private InventorInventionRepository	repository;

	private Invention					invention;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.invention != null && this.invention.getDraftMode() && this.invention.getInventor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		super.validateObject(this.invention);
		{
			boolean uniqueTicker;
			Invention existingInvention;

			existingInvention = this.repository.findInventionByTicker(this.invention.getTicker());
			uniqueTicker = existingInvention == null || existingInvention.equals(this.invention);

			super.state(uniqueTicker, "ticker", "acme.validation.invention.uniqueticker.message");
		}
		{
			boolean startMomentInFuture;

			startMomentInFuture = MomentHelper.isFuture(this.invention.getStartMoment());
			super.state(startMomentInFuture, "startMoment", "acme.validation.invention.startmomentinfuture.message");
		}
		{
			boolean endMomentInFuture;

			endMomentInFuture = MomentHelper.isFuture(this.invention.getEndMoment());
			super.state(endMomentInFuture, "endMoment", "acme.validation.invention.endmomentinfuture.message");
		}
		{
			boolean validInterval;

			if (this.invention.getStartMoment() != null && this.invention.getEndMoment() != null)
				validInterval = MomentHelper.isAfterOrEqual(this.invention.getEndMoment(), this.invention.getStartMoment());
			else
				validInterval = false;

			super.state(validInterval, "*", "acme.validation.invention.invalidinterval.message");
		}
		{
			boolean hasParts;
			hasParts = this.repository.existsByInventionId(this.invention.getId());

			super.state(hasParts, "*", "acme.validation.invention.haspart.message");
		}
	}

	@Override
	public void execute() {
		this.invention.setDraftMode(false);
		this.repository.save(this.invention);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "monthsActive", "cost");
	}
}
